<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 class="modal-title">角色授权</h4>
</div>
<div class="modal-body" id="modal-body">
    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active">
                        <a data-toggle="tab" href="#home">
                            <i class="green icon-home bigger-110"></i>
                            角色基本信息
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#profile">
                            <i class="green icon-cogs bigger-110"></i>
                            授权信息
                        </a>
                    </li>

                </ul>

                <div class="tab-content">
                    <div id="role-info" class="tab-pane in active">
                        <form class="form-horizontal" id="validation-form" role="form">
                            <div class="form-group" style="display: none">
                                <label class="col-sm-3 control-label no-padding-right" for="role-name">
                                    角色ID </label>

                                <div class="col-sm-9">
                                    <input type="text" name="id" id="id" placeholder="角色ID" value="${role.id}"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="role-name">
                                    角色名称 </label>

                                <div class="col-sm-9">
                                    <input type="text" name="name" id="name" placeholder="角色名称" value="${role.name}"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                                    角色标识 </label>

                                <div class="col-sm-9">
                                    <input type="text" name="role" id="role" value="${role.role}" placeholder="角色标识"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">
                                    详细描述
                                </label>

                                <div class="col-sm-9">
                                    <textarea class="form-control" name="description" id="description"
                                              placeholder="Default Text">${role.description}</textarea>
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right" for="status">状
                                    态</label>

                                <div class="col-sm-9">
                                    <div class="control-group">
                                    <#if role.show ? string('true','false') == 'true'>
                                        <div class="radio">
                                            <label>
                                                <input name="show" checked="checked" type="radio"
                                                       class="ace">
                                                <span class="lbl"> 可用</span>
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input name="show" type="radio" class="ace">
                                                <span class="lbl"> 不可用</span>
                                            </label>
                                        </div>
                                    <#else>
                                        <div class="radio">
                                            <label>
                                                <input name="show" type="radio" class="ace">
                                                <span class="lbl"> 可用</span>
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input checked="checked" name="show" type="radio"
                                                       class="ace">
                                                <span class="lbl"> 不可用</span>
                                            </label>
                                        </div>
                                    </#if>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>

                    <div id="profile" class="tab-pane">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="widget-box">
                                    <div class="widget-header header-color-blue2">
                                        <h4 class="lighter smaller">资源信息</h4>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main padding-8">
                                            <ul id="treeview" class="ztree"
                                                style="height: 240px;width: 450px;margin-top: 0px;">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /span -->
                            <!--
                                <div class="col-sm-6">
                                    <div class="widget-box">
                                        <div class="widget-header header-color-green2">
                                            <h4 class="lighter smaller">资源权限</h4>
                                        </div>
                                        <div class="widget-body">
                                            <div class="widget-main padding-8" >
                                                <select class="form-control" id="form-field-select"
                                                        multiple="multiple" style="height: 240px">
                                                <#if permissionList?exists>
                                                     <#list permissionList as permission>
                                                         <option value="${permission.permission}">${permission.name} ( ${permission.permission} )</option>
                                                     </#list>
                                                </#if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                -->
                            <!-- /span -->
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn">取消</button>
    <button type="button" id="update" class="btn btn-primary">更新</button>
</div>


<script type="text/javascript">
    var $path_base = "${rc.getContextPath()}";//this will be used in gritter alerts containing images
</script>
<!--
<script src="${rc.getContextPath()}/assets/js/additional-methods.min.js"></script>
-->
<script src="${rc.getContextPath()}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript">
    eval('debugger;');
    var $modal = $('#ajax-modal');
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    var zTreeObj;
    var zNodes = [];
    var permissNodes = [];
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: $path_base + '/admin/sys/resource/ajax/load?roleId=' +${role.id},
            dataType: "json",
            global: false,
            async: false,
            success: function (strReult) {
                zNodes = eval(strReult);
            },
            error: function () {
                alert("Ajax请求数据失败!");
            }
        });
        zTreeObj = $.fn.zTree.init($("#treeview"), setting, zNodes);

        $('#validation-form').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                roleName: {
                    required: true,
                    minlength: 6
                },
                roleRole: {
                    required:true,
                    minlength: 6
                }
            },
            messages: {
                roleName: {
                    required: "请输入有效角色名称.",
                    minlength: "角色名称不能少于6个字符."
                },
                roleRole: {
                    required: "请输入有效的角色标识.",
                    minlength: "角色名称不能少于6个字符."
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },

            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
                $(e).remove();
            }
        });

        $('#update').on('click', function (e) {
            if ($('#validation-form').valid()) {
                alert('hello - valid form');
                var nodes = zTreeObj.getCheckedNodes();
                var resourceIds = new Array();
                for (var i = 0; i < nodes.length; i++) {
                    if (!nodes[i].isParent) {
                        resourceIds.push(nodes[i].id);
                    }
                    //alert("id : "+nodes[i].id+" name : " + nodes[i].name + " checked : " + nodes[i].checked);
                }
                //var postData = $('#validation-form').serializeArray();
                var postData = $('#validation-form').serialize();
                postData = postData + "&resourceIds="+resourceIds;
                setTimeout(function () {
                    $.ajax({
                        type: 'POST',
                        url: $path_base + "/admin/sys/permission/role/updateAll",
                        dataType: "json",
                        data: postData,
                        traditional: true,
                        success: function (data, status) {
                            //alert("success : " + data);
                            var resp = JSON.stringify(data);
                            $modal.find('.modal-body')
                                    .prepend('<div class="alert alert-info fade in">' +
                                            resp + '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                            '</div>');
                        },
                        error: function () {
                            alert('操作错误,请与系统管理员联系!');
                        }
                    });
                }, 1000);
            }
        } );
    });
</script>


