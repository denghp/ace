package com.ace.console.service.sys;

import com.ace.console.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project_Name: ace-web
 * @File: UserService
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:09 PM
 * @Description:
 */
@Service
public interface UserService {

    public Long save(UserEntity entity);
}
