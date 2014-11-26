package com.ace.console.model;

import com.ace.commons.json.JsonUtils;

import com.ace.console.exception.Error;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: denghp
 * @Date: 14-11-23
 * @Time: 下午2:56
 * @Description:
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ACEResponse implements Serializable {

    private static final long serialVersionUID = 3056471023678709706l;
    private ResponseHeader responseHeader;
    private Integer records;
    private Integer total;
    private Integer page;
    private List<?> rows;
    private Error error;

    public ACEResponse() {}

    public ACEResponse(ResponseHeader responseHeader){
        this.responseHeader = responseHeader;
    }

    public ACEResponse(Integer total, Integer page, Integer records) {
        this.total = total;
        this.page = page;
        this.records = records;
    }

    public ACEResponse(Integer total, Integer page, Integer records, List<?> rows) {
        this(total,page,records);
        this.rows = rows;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public static ACEResponse createErrorResp(int code, String msg) {
        ACEResponse response = new ACEResponse();
        response.setError(new Error(code,msg));
        return response;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
