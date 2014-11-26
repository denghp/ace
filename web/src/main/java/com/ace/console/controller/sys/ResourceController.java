package com.ace.console.controller.sys;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.controller.BaseCRUDController;
import com.ace.console.exception.AceException;
import com.ace.console.model.ACEResponse;
import com.ace.console.model.ResponseHeader;
import com.ace.console.service.sys.ResourceService;
import com.ace.console.service.sys.RoleService;
import com.ace.core.persistence.sys.entity.Resources;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/31/13
 * Time: 10:26 PM
 */
@Controller
@RequestMapping(value = "/admin/sys/resources")
public class ResourceController extends BaseCRUDController<Resources, Integer> {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    @BaseComponent
    private ResourceService resourceService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/ajax/load")
    @ResponseBody
    public Object load(
            HttpServletRequest request,
            @RequestParam(value = "roleId", required = false) Long roleId,
            @RequestParam(value = "async", defaultValue = "true") boolean async) {

        return resourceService.getZTreeList(async, roleId);
    }


    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String updateForm(@PathVariable String id, Model model,
                             RedirectAttributes redirectAttributes) throws AceException {

//        if (permissionList != null) {
//            permissionList.assertHasUpdatePermission();
//        }


        if (StringUtils.isBlank(id)) {
           throw AceException.create(AceException.Code.BAD_REQUEST,"请选择有效的资源.");
        }
        Resources resource = resourceService.selectById(Long.valueOf(id));
        if (resource == null) {
            throw AceException.create(AceException.Code.NOT_FOUND,"没有找到有效的资源.");
        }
        model.addAttribute("resource", resource);
        return viewName("editForm");
    }

    @RequestMapping(value = "/children", method = RequestMethod.GET)
    @ResponseBody
    public ACEResponse children(
            @RequestParam(value = "nodeid", defaultValue = "1",required = false) Integer nodeid,
            HttpServletRequest request,
                         HttpServletResponse response) throws AceException {
        List<Resources> resourceList = resourceService.getChildsByPid(nodeid);
        ACEResponse responseJson = new ACEResponse();
        responseJson.setRows(resourceList);
        return responseJson;

    }

    /**
    @RequestMapping(value = "/add")
    @ResponseBody
    public Response saveResource(Resource m, HttpServletRequest request,BindingResult bindingResult, Model model) throws AceException {
        long starTime = System.currentTimeMillis();
        abstractService.save(m);
        return new Response(new ResponseHeader(200, System.currentTimeMillis() - starTime));
    }
    **/

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ACEResponse delete(
            @RequestParam("oper") String oper,
            @RequestParam("id") String ids,
            Model model) throws AceException {
        long starTime = System.currentTimeMillis();
//        if (permissionList != null) {
//            this.permissionList.assertHasDeletePermission();
//        }

        if (StringUtils.isNotBlank(oper) && oper.equalsIgnoreCase("del") && StringUtils.isNotBlank(ids)) {
            String[] idItems = ids.split(",");
            //查看该资源是否还有子节点,如果有则删除失败,没有则正常删除
            if (idItems != null && idItems.length > 0) {
                Resources resource = resourceService.selectById(Long.valueOf(idItems[0]));
                if (resource != null && !resource.isHasChildren()) {
                    //TODO
//                    resourceService.deleteList(idItems);
                    return new ACEResponse(new ResponseHeader(200, System.currentTimeMillis() - starTime));
                }
                throw AceException.create(AceException.Code.BAD_REQUEST, "删除资源失败了, 该资源包含子菜单!");
            }
        }
        throw AceException.create(AceException.Code.BAD_REQUEST, "无效的请求!");
    }

}
