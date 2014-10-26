/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys;

import com.ace.console.exception.AceException;
import com.ace.console.service.GenericService;
import com.ace.core.entity.ZTree;
import com.ace.core.persistence.sys.entity.Menu;
import com.ace.core.persistence.sys.entity.Resource;
import com.ace.core.persistence.sys.entity.User;

import java.util.List;

/**
 * @Author: denghp
 * @Date: 10/25/14 11:26 PM
 * @Description:
 */
public interface ResourceService extends GenericService<Resource, Long> {

    /**
     * 得到真实的资源标识  即 父亲:儿子
     *
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(Resource resource);
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

    public List<Resource> getChildsByPid(int pid);

    /**
     * 转换成Menu对象集合
     * @param resources
     * @return
     */
    public List<Menu> convertToMenus(List<Resource> resources);

    public List<Resource>  getAllWithSort();

    public List<Resource>  getAllWithSort(String sort);

    public List<ZTree<Integer>> getZTreeList(boolean async, Long roleId);


}
