package com.ace.core.paginator.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 10:05 PM
 * @Description:
 *      分页对象, 一个分页对象包含查询的结果集<list>和分页信息pageBean
 *
 *      <p>要得到总页数请使用 toPaginator().getTotalPages();</p>
 *
 */
public class PageList<E> extends ArrayList<E> {

    private static final long serialVersionUID = 1412751236332294208L;

    private Paginator paginator;

    public PageList() {}

    public PageList(Collection<? extends E> c) {
        super(c);
    }


    public PageList(Collection<? extends E> c,Paginator p) {
        super(c);
        this.paginator = p;
    }

    public PageList(Paginator p) {
        this.paginator = p;
    }


    /**
     * 得到分页器，通过Paginator可以得到总页数等值
     * @return
     */
    public Paginator getPaginator() {
        return paginator;
    }

}
