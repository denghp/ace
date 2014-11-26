package com.ace.console.controller;

import com.ace.console.exception.AceException;
import com.ace.console.model.ACEResponse;
import com.ace.console.model.ResponseHeader;
import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * User: denghp
 * Date: 10/16/13
 * Time: 6:09 PM
 */
@Controller
public class RegisterUserController {
    private Logger logger = LoggerFactory.getLogger(RegisterUserController.class);
    @Resource
    private UserService userService;

    @Value(value = "${shiro.login.url}")
    private String loginUrl;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = {"/{register:register;?.*}"}, method = RequestMethod.POST)
    @ResponseBody
    public ACEResponse registerForm(@Valid User user,BindingResult result) throws AceException {
        long starTime = System.currentTimeMillis();
        try {
            //step1 注册用户
            userService.save(user);
            //step2 注册企业
            //TODO 用户注册默认企业信息
            //Organization organization = new Organization(user.getUsername(),0,2);
            //organizationService.save(organization);
            //step3 添加用户与企业关系
            //userOrganizationService.save(new UserOrganization(user.getId(),organization.getId()));
            //step4 添加注册用户的角色
            //userService.saveUserOrOrganization(user);
        } catch (Exception ex) {
            logger.error("register user error : ", ex);
        }

        return new ACEResponse(new ResponseHeader(AceException.Code.OK.intValue(),System.currentTimeMillis() - starTime));
    }

    /**
     * 验证注册用户信息
     *
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public String validateForm(HttpServletRequest request,HttpServletResponse response) {

        if (StringUtils.isNotBlank(request.getParameter("username"))) {
            User user = userService.getByUsername(request.getParameter("username"));
            if (user != null) {
                return "false";
            }
            return "true";
        }

        if (StringUtils.isNotBlank(request.getParameter("email"))) {
            User user = userService.getByEmail(request.getParameter("email"));
            if (user != null) {
                return "false";
            }
            return "true";
        }
        return "false";
    }

}
