package com.ace.console.controller.sys;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.controller.BaseCRUDController;
import com.ace.console.service.sys.PermissionService;
import com.ace.core.persistence.sys.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * User: denghp
 * Date: 11/1/13
 * Time: 4:32 PM
 */
@Controller
@RequestMapping("/admin/sys/permission/permission")
public class PermissionController extends BaseCRUDController<Permission,Integer> {

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Resource
    @BaseComponent
    private PermissionService permissionService;


}
