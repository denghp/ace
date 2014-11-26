package com.ace.console.controller.sys;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.controller.BaseCRUDController;
import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/18/13
 * Time: 10:11 PM
 */
@Controller
@RequestMapping("/admin/sys/user")
public class UserController extends BaseCRUDController<User,Integer> {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    @BaseComponent
    private UserService userService;


    public UserController() {
        setResourceIdentity("sys:user");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String viewPerfectInfo(User user, Model model) {

        return "/perfectInfo";
    }

}
