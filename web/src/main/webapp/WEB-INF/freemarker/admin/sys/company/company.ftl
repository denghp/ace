<div class="page-content">
<div class="page-header">
    <h1>
        公司档案信息
    </h1>
</div>
<!-- /.domain-header -->

<div class="row">
<div class="col-xs-12">
<div id="alert-info" class="alert alert-info">
    <i class="icon-hand-right"></i>

    请注意: 这里显示您对公司档案的任何操作信息!
    <button class="close" data-dismiss="alert">
        <i class="icon-remove"></i>
    </button>
</div>
<!-- PAGE CONTENT BEGINS -->
<div class="tabbable">
<ul class="nav nav-tabs" id="myTab">
    <li class="active">
        <a data-toggle="tab" href="#home">
            <i class="green icon-home bigger-110"></i>
            公司基本信息
        </a>
    </li>

    <li>
        <a data-toggle="tab" href="#profile">
            <i class="green icon-cogs bigger-110"></i>
            上传公司LOGO
        </a>
    </li>

</ul>

<div class="tab-content">
<div id="home" class="tab-pane in active">
    <form class="form-horizontal" id="organ-form" role="form">
        <div class="row">
            <div class="col-xs-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">当前企业logo预览</h3>
                    </div>
                    <div class="panel-body center">
                        <img src="${rc.getContextPath()}/assets/images/pic_bg.png">
                    </div>
                </div>
            </div>
            <div class="col-xs-4">

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="rogan-id">
                        公司帐号： </label>

                    <div class="col-sm-9">
                        <input type="text" name="id" id="id" hidden="true" value="<#if organ.id??>${organ.id}</#if>"
                               placeholder="企业帐号">
                        <input type="text" name="id-copy" id="id-copy" disabled="true" placeholder="企业帐号"
                               value="<#if organ.id??>${organ.id}</#if>"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="organ-name">
                        公司名称： </label>

                    <div class="col-sm-9">
                        <input type="text" name="name" id="name" placeholder="公司名称" class="form-control"
                               value="<#if organ.name??>${organ.name}</#if>">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="organ-tradeCodeId">
                        所属行业： </label>

                    <div class="col-sm-9">
                        <input type="text" name="tradeCodeId" id="tradeCodeId"
                               value="<#if organ.tradeCodeId??>${organ.tradeCodeId}</#if>" placeholder="所属行业"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="userCount">用户数：</label>

                    <div class="col-sm-9">
                        <input type="text" name="userCount" disabled="true" id="userCount"
                               value="<#if organ.userCount??>${organ.userCount}</#if>" placeholder="最大用户数"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                        开户时间： </label>

                    <div class="col-sm-9">
                        <input type="text" name="createTime" disabled="true" id="createTime"
                               value="<#if organ.createTime??>${organ.createTime}</#if>" placeholder="开户时间"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                        到期时间： </label>

                    <div class="col-sm-9">
                        <input type="text" name="expireTime" disabled="true" id="expireTime"
                               value="<#if organ.expireTime??>${organ.expireTime}</#if>" placeholder="开户时间"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="status">状态：</label>

                    <div class="col-sm-9">
                        <select class="form-control"
                                style="color:rgb(132, 132, 132);background-color: rgb(238, 238, 238);" disabled
                                id="status-select">
                            <option value="0">正常</option>
                            <option value="1">禁用</option>
                        </select>
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="status">企业网站：</label>

                    <div class="col-sm-9">
                        <input type="text" name="site" id="site" value="<#if organ.site??>${organ.site}</#if>"
                               placeholder="企业网站"
                               class="form-control">
                    </div>
                </div>
            </div>
            <div class="col-xs-5">
                <div class="space-4"></div>

                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="address">地址：</label>

                    <div class="col-sm-9">
                        <input type="text" name="address" id="address"
                               value="<#if organ.address??>${organ.address}</#if>" placeholder="地址"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="telephone">电话：</label>

                    <div class="col-sm-9">
                        <input type="text" name="telephone" id="telephone"
                               value="<#if organ.telephone??>${organ.telephone}</#if>" placeholder="电话"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="email">电子邮件：</label>

                    <div class="col-sm-9">
                        <input type="text" name="email" id="email" value="<#if organ.email??>${organ.email}</#if>"
                               placeholder="电子邮件"
                               class="form-control">
                    </div>
                </div>
                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                        管理员帐号： </label>

                    <div class="col-sm-9">
                        <input type="text" name="role" id="role" value="" placeholder="管理员帐号"
                               class="form-control">
                    </div>
                </div>

                <div class="space-4"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">
                        详细描述：
                    </label>

                    <div class="col-sm-9">
                        <textarea class="form-control" name="description" id="description"
                                  placeholder="Default Text"><#if organ.description??>${organ.description}</#if></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-3 col-md-9">
                <button id="organ-save" class="btn btn-info " type="button">
                    <i class="icon-ok bigger-110"></i>
                    Save
                </button>

                &nbsp; &nbsp;
                <button class="btn" type="reset">
                    <i class="icon-undo bigger-110"></i>
                    Reset
                </button>
            </div>
        </div>
    </form>
</div>

<div id="profile" class="tab-pane">

    <div class="row">
        <form id="fileupload">
            <div class="inline-labels">
                <input type="hidden" size="4" id="x1" name="x1"/>
                <input type="hidden" size="4" id="y1" name="y1"/>
                <input type="hidden" size="4" id="x2" name="x2"/>
                <input type="hidden" size="4" id="y2" name="y2"/>
                <input type="hidden" size="4" id="w" name="w"/>
                <input type="hidden" size="4" id="h" name="h"/>
            </div>
            <div class="col-xs-12 ">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                        <span class="btn btn-success fileinput-button">
                            <i class="glyphicon glyphicon-plus"></i>
                            <span>选择图片</span>
                            <input type="file" id='file-input' name="file">
                        </span>
                            仅支持JPG、JPEG、PNG格式（2M以下）
                        </h3>
                    </div>
                    <div class="panel-body" id="panel-body">
                        <img src="${rc.getContextPath()}/assets/images/sago.jpg" id="target" alt="[Jcrop Example]"/>

                        <div id="preview-pane">
                            <div class="preview-container">
                                <img src="${rc.getContextPath()}/assets/images/sago.jpg" class="jcrop-preview"
                                     alt="Preview"/>
                            </div>
                        </div>
                                        <span>
                                                头像预览区
                                            生成头像大小200px*200px，
                                            上传图片后，左侧选取图片合适大小，
                                            点击下面的提交按钮。
                                            </span>
                    </div>
                </div>
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" id="uploadLogo" type="button">
                            <i class="icon-ok bigger-110"></i>
                            Save
                        </button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</div>

</div>
</div>

<!-- PAGE CONTENT ENDS -->
</div>
<!-- /.col -->
</div>
<!-- /.row -->
</div>
<script type="text/javascript">
    var $path_base = "${rc.getContextPath()}";//this will be used in gritter alerts containing images
</script>

<script src="${rc.getContextPath()}/assets/js/jquery.validate.min.js"></script>
<script src="${rc.getContextPath()}/assets/js/jquery.Jcrop.js"></script>
<script src="${rc.getContextPath()}/assets/js/load-image.js"></script>
<script src="${rc.getContextPath()}/assets/js/jquery.fileupload.js"></script>
<script src="${rc.getContextPath()}/assets/js/jquery.ui.widget.js"></script>
<link rel="stylesheet" href="${rc.getContextPath()}/assets/css/jquery.fileupload.css"/>
<link rel="stylesheet" href="${rc.getContextPath()}/assets/css/jquery.Jcrop.css" type="text/css"/>
<style type="text/css">

        /* Apply these styles only when #preview-pane has
           been placed within the Jcrop widget */
    .jcrop-holder #preview-pane {
        display: block;
        position: absolute;
        z-index: 2000;
        top: 10px;
        right: -290px;
        padding: 6px;
        border: 1px rgba(0, 0, 0, .4) solid;
        background-color: white;

        -webkit-border-radius: 6px;
        -moz-border-radius: 6px;
        border-radius: 6px;

        -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
    }

        /* The Javascript code will set the aspect ratio of the crop
           area based on the size of the thumbnail preview,
           specified here */
    #preview-pane .preview-container {
        width: 250px;
        height: 170px;
        overflow: hidden;
    }


</style>
<script type="text/javascript">
    eval('debugger');
    jQuery(function ($) {

        // Create variables (in this scope) to hold the API and image size
        var jcrop_api,
                boundx,
                boundy,

        // Grab some information about the preview pane
                $preview = $('#preview-pane'),
                $pimg = $('#preview-pane .preview-container img');


        $('#target').Jcrop({
            onChange: updatePreview,
            onSelect: updatePreview,
            //aspectRatio: 16 / 9
            //aspectRatio: xsize / ysize
            aspectRatio:1
        }, function () {
            // Use the API to get the real image size
            var bounds = this.getBounds();
            boundx = bounds[0];
            boundy = bounds[1];
            // Store the API in the jcrop_api variable
            jcrop_api = this;

            // Move the preview into the jcrop container for css positioning
            $preview.appendTo(jcrop_api.ui.holder);
        });

        function updatePreview(c) {
            $pcnt = $('#preview-pane .preview-container')
            xsize = $pcnt.width()
            ysize = $pcnt.height()
            if (parseInt(c.w) > 0) {
                //给隐藏参数赋值
                $('#x1').val(c.x);
                $('#y1').val(c.y);
                $('#x2').val(c.x2);
                $('#y2').val(c.y2);
                $('#w').val(c.w);
                $('#h').val(c.h);


                var rx = xsize / c.w;
                var ry = ysize / c.h;

                $pimg.css({
                    width: Math.round(rx * boundx) + 'px',
                    height: Math.round(ry * boundy) + 'px',
                    marginLeft: '-' + Math.round(rx * c.x) + 'px',
                    marginTop: '-' + Math.round(ry * c.y) + 'px'
                });
            }
        };

        $('#organ-form').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                name: {
                    required: true,
                    minlength: 6
                }
            },
            messages: {
                name: {
                    required: "请输入有效公司名称.",
                    minlength: "公司名称不能少于6个字符."
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit
                $('.alert-danger', $('.organ-form')).show();
            },

            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },

            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
                $(e).remove();
            }
        });

        $('#organ-save').on('click', function (e) {
            if ($('#organ-form').valid()) {
                var postData = $('#organ-form').serialize();
                setTimeout(function () {
                    $.ajax({
                        type: 'POST',
                        url: $path_base + "/admin/sys/company/update",
                        dataType: "json",
                        data: postData,
                        traditional: true,
                        success: function (response) {
                            if (response.responseHeader != undefined &&
                                    response.responseHeader.status != undefined &&
                                    response.responseHeader.status == "200") {
                                ace.show_msg("更新成功!");
                            } else {
                                ace.show_msg("更新失败,请与系统管理员联系!");
                            }
                        },
                        error: function () {
                            ace.show_msg('操作错误,请与系统管理员联系!');
                        }
                    });
                }, 1000);
            }
        });

       $('#file-input').change(function (e) {
            var imageName = e.target.files[0].name;
            if(!/\.(gif|jpg|jpeg|png|JPG|PNG)$/.test(imageName))
            {
                ace.show_msg("不支持的图片格式.图片类型必须是.jpeg,jpg,png,gif格式.");
                return false;
            }

            loadImage(
                    e.target.files[0],

                    function (img) {
                        if (img.type === "error") {
                            console.log("Error loading image ");
                        } else {
                            $('#panel-body').find('.jcrop-holder img').attr('src', $(img).attr('src'))
                        }
                    },
                    {
                        maxWidth: 600,
                        noRevoke: true // 默认情况下当前的URL被使用一次就失效
                    } // Options
            );

        });

        /**
        $('#file-input').fileupload({
            dataType: 'json',
            url:$path_base + "/admin/sys/company/company/upload",
            add: function (e, data) {
                $('#uploadLogo').click(function(e) {
                    data.submit();
                });
            },
            done: function (e, data) {
                if (data.responseHeader != undefined &&
                        data.responseHeader.status != undefined &&
                        data.responseHeader.status == "200") {
                    ace.show_msg("上传成功!");
                } else {
                    ace.show_msg("上传失败,请与系统管理员联系!");
                }
            }
        });
        **/

        $('#uploadLogo').on('click', function(e) {
            // 初始化数据
            var x1 = $('#x1').val() == "" ? 0 : $('#x1').val();
            var y1 = $('#y1').val() == "" ? 0 : $('#y1').val();
            var x2 = $('#x2').val();
            var y2 = $('#y2').val();
            var w = $('#w').val() == "" ? 150 : $('#w').val();
            var h= $('#h').val() == "" ? 150 : $('#h').val();

            var srcFile = $(".jcrop-preview").attr("src");

            if (srcFile == "" || !srcFile) {
                ace.show_msg("没有选择任何图片.");
                return;
            }

            var showDiv = $(".jcrop-holder > .jcrop-tracker");
            // 从压缩存放图片的div中获取压缩后显示的宽度和高度，用来交给后台同比例进行裁剪
            // width: 404px; height: 304px; position: absolute; top: -2px; left: -2px; z-index: 290; cursor: crosshair;
            var style = showDiv.attr("style");
            // 原图片页面显示的宽度
            var showWidth = _getShowWidth(style);
            // 原图片页面显示的高度
            var showHeight = _getShowHeight(style);
            console.log("showDiv : " +showDiv + " showWidth: " + showWidth + " showHeight : " + showHeight);
            //var postData = $('#organ-form').serialize();
            setTimeout(function () {
                $.ajax({
                    type: 'POST',
                    url: $path_base + "/admin/sys/company/company/uploadLogo",
                    dataType: "json",
                    fileElementId : 'file-input',
                    contentType: false,
                    processData: false,        //不可缺参数
                    data: {
                        srcImageFile : srcFile,
                        x : x1,
                        y : y1,
                        destWidth : w,
                        destHeight : h,
                        srcShowWidth : showWidth,
                        srcShowHeight : showHeight
                    },
                    data:data,
                    traditional: true,
                    success: function (response) {
                        if (response.responseHeader != undefined &&
                                response.responseHeader.status != undefined &&
                                response.responseHeader.status == "200") {
                            ace.show_msg("保存成功!");
                        } else {
                            ace.show_msg("保存失败,请与系统管理员联系!");
                        }
                    },
                    error: function () {
                        ace.show_msg('操作错误,请与系统管理员联系!');
                    }
                });
            }, 1000);
        });

    });

    function _getShowWidth(str) {
        return _getValue(str, "width");
    }

    function _getShowHeight(str) {
        return _getValue(str, "height");
    }

    function _getValue (str, key) {
        var str = str.replace(/\:|\;|\s/g, '').toLowerCase();
        var pos = str.indexOf(key);
        if (pos >= 0) {
            // 截取
            var tmp = str.substring(pos, str.length);
            var px = tmp.indexOf("px");
            if (px > 0){
                var width = tmp.substring(key.length, px);
                return width;
            }
            return 0;
        }
        return 0;
    }
</script>

