package com.ace.console.controller;

import com.ace.console.bind.annotation.CurrentUser;
import com.ace.console.common.support.InjectBaseDependencyHelper;
import com.ace.console.exception.AceException;
import com.ace.console.model.ACEResponse;
import com.ace.console.model.ResponseHeader;
import com.ace.console.service.GenericService;
import com.ace.core.paginator.domain.PageList;
import com.ace.core.persistence.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Project_Name: ace
 * File: BaseCRUDController
 * User: denghp
 * Date: 11/1/13
 * Time: 6:22 PM
 */
public class BaseCRUDController<M, ID extends Serializable> extends BaseController<M, ID> implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(BaseCRUDController.class);

    protected GenericService<M, ID> genericService;

//    protected PermissionList permissionList = null;

    /**
     * 设置基础service
     *
     * @param genericService
     */
    public void setGenericService(GenericService<M, ID> genericService) {
        this.genericService = genericService;
    }

    @Override
    public void afterPropertiesSet() {
        InjectBaseDependencyHelper.findAndInjectGenericServiceDependency(this);
        Assert.notNull(genericService, "GenericService required, Class is:" + getClass());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
//        if (permissionList != null) {
//            this.permissionList.assertHasViewPermission();
//        }
        return view("list",model);
    }

    public String view(String url, Model model) {

        return viewName(url);
    }

    /**
     * 权限前缀：如sys:user
     * 则生成的新增权限为 sys:user:create
     */
    public void setResourceIdentity(String resourceIdentity) {
//        if (!StringUtils.isEmpty(resourceIdentity)) {
//            permissionList = PermissionList.newPermissionList(resourceIdentity);
//        }
    }

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
            //genericService.deleteList(Arrays.asList(idItems));
            return new ACEResponse(new ResponseHeader(200, System.currentTimeMillis() - starTime));
        }
        throw AceException.create(AceException.Code.BAD_REQUEST, "无效的请求!");
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ACEResponse update(
            Model model, M m, BindingResult result,
            RedirectAttributes redirectAttributes) throws AceException {
        long starTime = System.currentTimeMillis();
//        if (permissionList != null) {
//            this.permissionList.assertHasUpdatePermission();
//        }
        genericService.update(m);
        return new ACEResponse(new ResponseHeader(200, System.currentTimeMillis() - starTime));
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public ACEResponse save(@CurrentUser User user,M m, BindingResult bindingResult, Model model) throws AceException {
        long starTime = System.currentTimeMillis();
        genericService.save(m);

        return new ACEResponse(new ResponseHeader(200, System.currentTimeMillis() - starTime));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ACEResponse list(@CurrentUser User user,HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        if (StringUtils.isBlank(page)) {
            page = "1";
        }
        if (StringUtils.isBlank(rows)) {
            rows = "10";
        }

        PageList<User> pageList = (PageList<User>) genericService.page(null, Integer.parseInt(page), Integer.parseInt(rows));

        ACEResponse responseJson = new ACEResponse();
        responseJson.setRows(pageList);
        responseJson.setPage(pageList.getPaginator().getPage());
        responseJson.setTotal(pageList.getPaginator().getTotalPages());
        responseJson.setRecords(pageList.getPaginator().getTotalCount());

        return responseJson;
    }

}
