package com.ace.console.controller.sys;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.controller.BaseCRUDController;
import com.ace.console.exception.AceException;
import com.ace.console.model.ACEResponse;
import com.ace.console.model.ResponseHeader;
import com.ace.console.service.sys.PermissionService;
import com.ace.console.service.sys.RoleService;
import com.ace.core.persistence.sys.entity.Permission;
import com.ace.core.persistence.sys.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: denghp
 * Date: 10/20/13
 * Time: 3:24 PM
 */
@Controller
@RequestMapping("/admin/sys/permission/role")
public class RoleController extends BaseCRUDController<Role, Integer> {
    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    @BaseComponent
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/{edit:edit;?.*}")
    public String editIndex(Role role, Model model) {
        List<Permission> permissionList = permissionService.getPageList(0, 50);

        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionList);

        return "/admin/sys/permission/role/edit";
    }

    @RequestMapping(value = "/updateAll", method = RequestMethod.POST)
    @ResponseBody
    public ACEResponse updateWithResourcePermission(Role role,
                                                 BindingResult result,
                                                 Long[] resourceIds,
                                                 Model model) throws AceException {
        long starTime = System.currentTimeMillis();
        if (role.getId() != null && resourceIds != null) {
            //TODO 需要清除缓存中存储的数据
            roleService.updateWithResourcePermission(resourceIds, role);
            return new ACEResponse(new ResponseHeader(AceException.Code.OK.intValue(),System.currentTimeMillis() - starTime));
        }
        return ACEResponse.createErrorResp(AceException.Code.BAD_REQUEST.intValue(), "Bad Request, the params is invalid.");
    }


}
