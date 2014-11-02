package com.ace.console.extra.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Project_Name: ace
 * @File: ShiroSessionRepository
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-11-1
 * @Time: 下午1:46
 * @Description:
 */
public interface ShiroSessionRepository {

    /**
     * 存儲session
     * @param session
     */
    public void saveSession(Session session);

    /**
     * 刪除seesion
     * @param sessionId
     */
    public void deleteSession(Serializable sessionId);

    /**
     * 根據sessionId獲取session
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);

    /**
     * 獲取所有的session
     * @return
     */
    Collection<Session> getAllSessions();
}
