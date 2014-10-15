package com.ace.console.common.utils;

import java.util.List;

/**
 * @Project_Name: ace-parent
 * @File: Page
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 10:05 PM
 * @Description: 分页对象, 一个分页对象包含查询的结果集<list>和分页信息pageBean
 */
public class Page<T> implements java.io.Serializable {

    private static final long serialVersionUID = -5491862383942862703L;
    /**
     * 查询结果
     */
    private List<T> result;
    /**
     * 分页信息Bean
     */
    private PageBean pageBean;

    /**
     * (空)
     */
    public Page() {
    }

    /**
     * 根据查询结果、分页信息构造
     *
     * @param lstResult 查询结果
     * @param pageBean  分页信息Bean
     */
    public Page(List lstResult, PageBean pageBean) {
        this.result = lstResult;
        this.pageBean = pageBean;
    }

    /**
     * 取得查询结果
     *
     * @return 查询结果
     */
    public List getResult() {
        return result;
    }

    /**
     * 设置查询结果
     *
     * @param lstResult 查询结果
     */
    public void setResult(List lstResult) {
        this.result = lstResult;
    }

    /**
     * 取得分页信息Bean
     *
     * @return 分页信息Bean
     */
    public PageBean getPageBean() {
        return pageBean;
    }

    /**
     * 设置分页信息Bean
     *
     * @param pageBean 分页信息Bean
     */
    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Page");
        sb.append("{result=").append(result);
        sb.append(", pageBean=").append(pageBean);
        sb.append('}');
        return sb.toString();
    }
}
