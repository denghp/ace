<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../../commons/taglibs.jsp" %>

<div class="widget-box">
<div class="widget-header header-color-green2">
    <h4 class="lighter smaller">资源信息修改</h4>
</div>

<div class="widget-body">
    <div class="widget-main padding-8">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                    资源名称 </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" placeholder="资源名称" value="${resource.name}"
                           class="col-xs-10 col-sm-5">
                </div>
            </div>

            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                    资源标识 </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-2" placeholder="资源标识" value="${resource.identity}"
                           class="col-xs-10 col-sm-5">
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" ${resource.url} placeholder="菜单跳转地址"
                       for="form-field-2">
                    URL路径 </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-3" value=""
                           class="col-xs-10 col-sm-5">
                </div>
            </div>

            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">
                    父节点ID
                </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-4" value="0"
                           class="col-xs-10 col-sm-5">
                </div>
            </div>
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">
                    所有父节点ID
                </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-5" value="0/"
                           class="col-xs-10 col-sm-5">
                </div>
            </div>

            <div class="space-4"></div>

            <div class="form-group">

                <label class="col-sm-3 control-label no-padding-right" for="form-field-4">状
                    态</label>

                <div class="col-sm-9">
                    <div class="control-group">
                  <c:choose>
                    <c:when test="${resource.show == true}" >
                        <div class="radio">
                            <label>
                                <input name="form-field-radio" checked="checked" type="radio"
                                       class="ace">
                                <span class="lbl"> 可用</span>
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input name="form-field-radio" type="radio" class="ace">
                                <span class="lbl"> 不可用</span>
                            </label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="radio">
                            <label>
                                <input name="form-field-radio" type="radio" class="ace">
                                <span class="lbl"> 可用</span>
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input checked="checked" name="form-field-radio" type="radio"
                                       class="ace">
                                <span class="lbl"> 不可用</span>
                            </label>
                        </div>
                    </c:otherwise>
                 </c:choose>
                    </div>
                </div>
            </div>
            <div>
                <button  class="btn">取消</button>
                <button class="btn btn-success">修改</button>
            </div>
        </form>
    </div>
</div>
</div>
