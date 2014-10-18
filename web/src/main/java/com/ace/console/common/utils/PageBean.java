package com.ace.console.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project_Name: ace-parent
 * @File: PageBean
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 10:06 PM
 * @Description:
 */
public class PageBean implements java.io.Serializable {

    private static final long serialVersionUID = 6843814549013761926L;
    /**
     * 缺省的分页条数
     */
    private static int DEF_PAGE_VIEW_SIZE = 30;

    /**
     * 当前页
     */
    private int page;
    /**
     * 当前页显示记录条数
     */
    private int pageSize;
    /**
     * 取得查询总记录数
     */
    private int count;
    /**
     * 共几页
     */
    private int sumPage;
    /**
     * 分页显示，如：1...6789101112...15
     */
    private List pageDetail = new ArrayList();

    /**
     * (空)
     */
    public PageBean() {
    }

    /**
     * 根据当前显示页与每页显示记录数设置查询信息初始对象
     *
     * @param page     当前显示页号
     * @param pageSize 当前页显示记录条数
     */
    public PageBean(int page, int pageSize) {
        this.page = (page <= 0) ? 1 : page;
        this.pageSize = (pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize;
    }


    /**
     * 取得当前显示页号
     *
     * @return 当前显示页号
     */
    public int getPage() {
        return (page <= 0) ? 1 : page;
    }

    /**
     * 设置当前页
     *
     * @param page 当前页
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 取得当前显示页号最多显示条数
     *
     * @return 当前显示页号最多显示条数
     */
    public int getPageSize() {
        return (pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize;
    }

    /**
     * 设置当前页显示记录条数
     *
     * @param pageSize 当前页显示记录条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        // this.setNewPaging(this.count, this.pageSize);
    }

    /**
     * 取得查询取得记录总数
     *
     * @return 取得查询取得记录总数
     */
    public long getCount() {
        return count;
    }

    /**
     * 设置查询取得记录总数
     *
     * @param count 查询取得记录总数
     */
    public void setCount(int count) {
        this.count = (count < 0) ? 0 : count;
        if (this.page > this.getPages()) {
            this.page = this.getPages();
        }
        this.sumPage = (count % this.pageSize == 0) ? (count / this.pageSize)
                : (count / this.pageSize) + 1;
        // this.setNewPaging(this.count, this.pageSize);
    }

    /**
     * 取得当前查询总页数
     *
     * @return 当前查询总页数
     */
    public int getPages() {
        return (count + getPageSize() - 1) / getPageSize();
    }

    /**
     * 取得起始显示记录号
     *
     * @return 起始显示记录号
     */
    public int getStartNo() {
        return ((getPage() - 1) * getPageSize() + 1);
    }

    /**
     * 取得结束显示记录号
     *
     * @return 结束显示记录号
     */
    public int getEndNo() {
        return Math.min(getPage() * getPageSize(), count);
    }

    /**
     * 取得前一显示页码
     *
     * @return 前一显示页码
     */
    public int getPrePageNo() {
        return Math.max(getPage() - 1, 1);
    }

    /**
     * 取得后一显示页码
     *
     * @return 后一显示页码
     */
    public int getNextPageNo() {
        return Math.min(getPage() + 1, getPages());
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public List getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(List pageDetail) {
        this.pageDetail = pageDetail;
    }

}
