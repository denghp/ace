/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys;

import com.ace.console.service.GenericService;
import com.ace.core.entity.ZTree;
import com.ace.core.persistence.sys.entity.Menu;
import com.ace.core.persistence.sys.entity.Resources;
import com.ace.core.persistence.sys.entity.User;

import java.util.List;

/**
 * @Author: denghp
 * @Date: 10/25/14 11:26 PM
 * @Description:
 */
public interface ResourceService extends GenericService<Resources, Long> {

    /**
     * 得到真实的资源标识  即 父亲:儿子
     *
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(Resources resource);
    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     * @return
     */
    public List<Menu> findMenus();

    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     * @param user
     * @return
     */
    public List<Menu> findMenus(User user);

    /**
     * 根据parent_id获取子节点集合
     * @param pid 父级ID
     * @return 子级的资源集合
     */
    public List<Resources> getChildsByPid(int pid);

    /**
     * 转换成Menu对象集合
     * @param resources
     * @return
     */
    public List<Menu> convertToMenus(List<Resources> resources);

    public List<Resources>  getAllWithSort(String sort);

    /**
     *  获取ztree 集合
     * @param async
     * @param roleId 角色ID
     * @return
     */
    public List<ZTree<Integer>> getZTreeList(boolean async, Long roleId);


}
