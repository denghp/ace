/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.ResourceService;
import com.ace.console.service.sys.RoleService;
import com.ace.console.service.sys.UserAuthService;
import com.ace.console.utils.Constants;
import com.ace.core.entity.ZTree;
import com.ace.core.persistence.sys.entity.Menu;
import com.ace.core.persistence.sys.entity.Resources;
import com.ace.core.persistence.sys.entity.RoleResourcePermission;
import com.ace.core.persistence.sys.entity.User;
import com.ace.core.persistence.sys.mapper.ResourcesMapper;
import com.google.code.ssm.api.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: denghp
 * @Date: 10/25/14 11:33 PM
 * @Description:
 */
@Service
public class ResourceServiceImpl extends GenericServiceImpl<Resources, Long> implements ResourceService {

    private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @javax.annotation.Resource
    private UserAuthService userAuthService;

    @javax.annotation.Resource
    private RoleService roleService;

    @BaseComponent
    @javax.annotation.Resource
    private ResourcesMapper resourcesMapper;

    public static final String DEFAULT_SORT = "parent_id desc,weight desc";

    /**
     * 得到真实的资源标识  即 父亲:儿子
     *
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(Resources resource) {

        if (resource == null) {
            return null;
        }

        StringBuilder s = new StringBuilder(resource.getIdentity());

        boolean hasResourceIdentity = !StringUtils.isEmpty(resource.getIdentity());

        Resources parent = selectById(resource.getParentId());
        while (parent != null) {
            if (!StringUtils.isEmpty(parent.getIdentity())) {
                s.insert(0, parent.getIdentity() + ":");
                hasResourceIdentity = true;
            }
            parent = selectById(parent.getParentId());
        }

        //如果用户没有声明 资源标识  且父也没有，那么就为空
        if (!hasResourceIdentity) {
            return "";
        }


        //如果最后一个字符是: 因为不需要，所以删除之
        int length = s.length();
        if (length > 0 && s.lastIndexOf(":") == length - 1) {
            s.deleteCharAt(length - 1);
        }

        //如果有儿子 最后拼一个*
        /**
         boolean hasChildren = false;
         for (Resources r : resourcesMapper.getList()) {
         if (resource.getId().equals(r.getParentId())) {
         hasChildren = true;
         break;
         }
         }
         **/

        if (resource.isHasChildren()) {
            s.append(":*");
        }

        return s.toString();
    }

    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     *
     * @return
     */
    @ReadThroughAssignCache(assignedKey = ":findMenus", namespace = Constants.DEFAULT_PROJECT_NAME +":menu", expiration = 60 * 60)
    public List<Menu> findMenus() {
        String sort = "parent_id desc,weight desc";

        List<Resources> resources = getAllWithSort(sort);

        return convertToMenus(resources);
    }

    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     *
     * @param user
     * @return
     */
    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":menus:findMenusByUser", expiration = 600)
    public List<Menu> findMenus(@ParameterValueKeyProvider User user) {
        String sort = "parent_id desc,weight desc";
        List<Resources> resources = getAllWithSort(sort);

        Set<String> userPermissions = null;
        userPermissions = userAuthService.findStringPermissions(user);

        Iterator<Resources> iter = resources.iterator();
        while (iter.hasNext()) {
            if (!hasPermission(iter.next(), userPermissions)) {
                iter.remove();
            }
        }
        return convertToMenus(resources);
    }

    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":menus:getChildsByPid", expiration = 600)
    public List<Resources> getChildsByPid(@ParameterValueKeyProvider int pid) {
        logger.debug(">>>> getChildsByPid : {}", pid);
        return resourcesMapper.getChildsByPid(pid);
    }


    private boolean hasPermission(Resources resource, Set<String> userPermissions) {
        String actualResourceIdentity = findActualResourceIdentity(resource);
        if (StringUtils.isEmpty(actualResourceIdentity)) {
            return true;
        }

        for (String permission : userPermissions) {
            boolean bool = hasPermission(permission, actualResourceIdentity);
            if (bool) {
                return true;
            }
        }

        return false;
    }

    private boolean hasPermission(String permission, String actualResourceIdentity) {

        //得到权限字符串中的 资源部分，如a:b:create --->资源是a:b
        String permissionResourceIdentity = permission.substring(0, permission.lastIndexOf(":"));

        //如果权限字符串中的资源 是 以资源为前缀 则有权限 如a:b 具有a:b的权限
        if (permissionResourceIdentity.startsWith(actualResourceIdentity)) {
            return true;
        }


        //模式匹配
        WildcardPermission p1 = new WildcardPermission(permissionResourceIdentity);
        WildcardPermission p2 = new WildcardPermission(actualResourceIdentity);

        return p1.implies(p2) || p2.implies(p1);
    }

    /**
     * 转换成Menu对象集合
     *
     * @param resources
     * @return
     */
    @ReadThroughMultiCache(namespace = Constants.DEFAULT_PROJECT_NAME +":menu:convertToMenus", expiration = 600)
    public List<Menu> convertToMenus(@ParameterValueKeyProvider List<Resources> resources) {

        if (resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

        //获取根节点,并从集合中移除根节点
        Menu root = convertToMenu(resources.remove(resources.size() - 1));

        recursiveMenu(root, resources);
        List<Menu> menus = root.getChildren();
        removeNoLeafMenu(menus);

        return menus;
    }

    private static void removeNoLeafMenu(List<Menu> menus) {
        if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && StringUtils.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
    }

    private static void recursiveMenu(Menu menu, List<Resources> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
            Resources resource = resources.get(i);
            if (resource.getParentId() != null && resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                resources.remove(i);
            }
        }

        for (Menu subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resources);
        }
    }

    private static Menu convertToMenu(Resources resource) {
        return new Menu(resource.getId(), resource.getName(), resource.getIcon(), resource.getUrl(),
                resource.getWeight(), resource.getIdentity(), resource.getEnabled());
    }

    @ReadThroughAssignCache(assignedKey = ":menu:getAllWithSort", namespace = Constants.DEFAULT_PROJECT_NAME , expiration = 600)
    public List<Resources> getAllWithSort(String sort) {
        if (StringUtils.isBlank(sort)) {
            logger.warn("sort is empty, use default sort!!");
            sort = DEFAULT_SORT;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sort", sort);
        return resourcesMapper.getAllWithSort(params);
    }

    @ReadThroughMultiCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":menu:getZTreeList", expiration = 600,
            option = @ReadThroughMultiCacheOption(generateKeysFromResult = true))
    public List<ZTree<Integer>> getZTreeList(boolean async, Long roleId) {
        List<Resources> resourceList = getAllWithSort(null);
        Map<Long, RoleResourcePermission> rrpMaps = null;
        if (roleId != null) {
            rrpMaps = roleService.getRoleResourceMaps(roleId);
            return convertToZtreeList(resourceList, async, rrpMaps);
        }
        return convertToZtreeList(resourceList, async, null);
    }

    private List<ZTree<Integer>> convertToZtreeList(List<Resources> models, boolean async, Map<Long, RoleResourcePermission> rrpMaps) {
        List<ZTree<Integer>> zTrees = Lists.newArrayList();

        if (models == null || models.isEmpty()) {
            return zTrees;
        }

        for (Resources resource : models) {
            ZTree zTree = convertToZtree(resource, async);
            if (rrpMaps != null && rrpMaps.containsKey(resource.getId())) {
                zTree.setChecked(true);
            }
            zTrees.add(zTree);
        }
        return zTrees;
    }

    @ReadThroughAssignCache(assignedKey = ":menu:convertToZtree", namespace = Constants.DEFAULT_PROJECT_NAME , expiration = 600)
    private ZTree convertToZtree(Resources m, boolean open) {
        ZTree<Long> zTree = new ZTree<Long>();
        zTree.setId(m.getId());
        zTree.setpId(m.getParentId());
        zTree.setName(m.getName());
        if (!open && m.getId() == 1) {
            zTree.setOpen(true);
        } else {
            zTree.setOpen(open);
        }
        //zTree.setIconSkin(m.getIcon());
        //zTree.setRoot(m.isRoot());
        //zTree.setIsParent(m.isHasChildren());

        //zTree.setNocheck(false);

        return zTree;
    }

    /**
     * 保存单个实体
     *
     * @param t 实体
     * @return 返回保存的实体
     */
    public Resources save(Resources t) throws AceException {
        if (t == null) {
            return null;
        }
        //获取父级
        Resources resource = selectById(t.getParentId());
        if (resource == null) {
            throw AceException.create(AceException.Code.NOT_FOUND, "父级资源没有找到!");
        }
        if (resource.getParentIds().endsWith("/")) {
            t.setParentIds(resource.getParentIds() + resource.getId());
        } else {
            t.setParentIds(resource.getParentIds() + "/" + resource.getId());
        }
        resourcesMapper.insert(t);
        return t;
    }

    @Override
    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":menus:selectById", expiration = 600)
    public Resources selectById(@ParameterValueKeyProvider Long aLong) {
        logger.info("===========Resources selectById : {}", aLong);
        return resourcesMapper.selectById(aLong);
    }
}
