<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="commons/head.jsp" %>

<body class="login-layout">
<div class="main-container">
<div class="main-content">
<div class="row">
<div class="col-sm-10 col-sm-offset-1">
<div class="login-container">
<div class="center">
    <h1>
        <i class="icon-leaf green"></i>
        <span class="red">Ace</span>
        <span class="white">ERP</span>
    </h1>
    <h4 class="blue">&copy; Company Name</h4>
</div>

<div class="space-6"></div>

<div class="position-relative">
<div id="login-box" class="login-box visible widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header blue lighter bigger">
                <ace:showMessage></ace:showMessage>

                <i class="icon-coffee green"></i>
                Please Enter Your Information
            </h4>

            <div class="space-6"></div>
            <form id="loginForm" method="post" action="${contextPath}/login">
                <fieldset>
                    <div class="form-group">
                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" value="admin"
                                                                   class="form-control"
                                                                   placeholder="Username"/>
															<i class="icon-user"></i>
														</span>
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" value="123456"
                                                                   class="form-control"
                                                                   placeholder="Password"/>
															<i class="icon-lock"></i>
														</span>
                        </label>
                    </div>
                    <c:if test="${!empty jCaptchaDisabled && !jCaptchaDisabled}">
                        <label class="block clearfix">
                                <span class="block input-icon input-icon-right">
                                <input type="text" id="jcaptchaCode" name="jcaptchaCode"
                                       class="input-medium validate[required,ajax[ajaxJcaptchaCall]]"
                                       placeholder="请输入验证码">
                                <img class="jcaptcha-btn jcaptcha-img" style="margin-left: 10px;"
                                     src="${contextPath}/jcaptcha.jpg" title="点击更换验证码">
                                </span>
                        </label>
                    </c:if>
                    <div class="space"></div>

                    <div class="clearfix">
                        <label class="inline">
                            <input type="checkbox" class="ace"/>
                            <span class="lbl"> 记住我</span>
                        </label>

                        <button type="submit" id="login" class="width-35 pull-right btn btn-sm btn-primary">
                            <i class="icon-key"></i>
                            Login
                        </button>
                    </div>

                    <div class="space-4"></div>
                </fieldset>
            </form>
            <!--
           <div class="social-or-login center">
               <span class="bigger-110">Or Login Using</span>
           </div>

           <div class="social-login center">
               <a class="btn btn-primary">
                   <i class="icon-facebook"></i>
               </a>

               <a class="btn btn-info">
                   <i class="icon-twitter"></i>
               </a>

               <a class="btn btn-danger">
                   <i class="icon-google-plus"></i>
               </a>
           </div>
           -->
        </div>
        <!-- /widget-main -->

        <div class="toolbar clearfix">
            <div>
                <a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
                    <i class="icon-arrow-left"></i>
                    忘记密码?
                </a>
            </div>

            <div>
                <a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
                    我要注册
                    <i class="icon-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- /widget-body -->
</div>
<!-- /login-box -->

<div id="forgot-box" class="forgot-box widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header red lighter bigger">
                <i class="icon-key"></i>
                找回密码
            </h4>

            <div class="space-6"></div>
            <p>
                请输入接收密码的电子邮箱
            </p>

            <form>
                <fieldset>
                    <div class="form-group">
                        <div class="block input-icon input-icon-right ">
                            <input type="email" class="form-control"
                                   placeholder="Email"/>
                            <i class="icon-envelope"></i>
                        </div>
                    </div>

                    <div class="clearfix">
                        <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                            <i class="icon-lightbulb"></i>
                            Send Me!
                        </button>
                    </div>
                </fieldset>
            </form>
        </div>
        <!-- /widget-main -->

        <div class="toolbar center">
            <a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
                Back to login
                <i class="icon-arrow-right"></i>
            </a>
        </div>
    </div>
    <!-- /widget-body -->
</div>
<!-- /forgot-box -->

<div id="signup-box" class="signup-box widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header green lighter bigger">
                <i class="icon-group blue"></i>
                新用户注册
            </h4>

            <div class="space-6"></div>
            <p> 开始输入你的详细信息: </p>

            <form id="register-form" name="registerForm" method="post" action="${contextPath}/register">
                <fieldset>
                    <div class="form-group">
                        <div class="block input-icon input-icon-right ">
                            <input type="email" name="email" id="email"
                                   class="form-control"
                                   placeholder="电子邮箱"/>
                            <i class="icon-envelope"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="block input-icon input-icon-right ">
                            <input type="text" name="username" id="username" class="form-control"
                                   placeholder="用户名" class="col-xs-10 col-sm-5"/>
                            <i class="icon-user"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="block input-icon input-icon-right ">
                            <input type="password" name="password" id="password"
                                   class="form-control"
                                   placeholder="设置密码"/>
                            <i class="icon-lock"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="block input-icon input-icon-right ">
                            <input type="password" name="repeat_password"
                                   class="form-control"
                                   placeholder="确认密码"/>
                            <i class="icon-retweet"></i>
                        </div>
                    </div>
                    <label class="block">
                        <input type="checkbox" class="ace"/>
														<span class="lbl">
															同意
															<a href="#">用户协议</a>
														</span>
                    </label>

                    <div class="space-24"></div>

                    <div class="clearfix">
                        <button type="reset" class="width-30 pull-left btn btn-sm">
                            <i class="icon-refresh"></i>
                            重置
                        </button>

                        <button type="button" id="registerBtn" class="width-65 pull-right btn btn-sm btn-success">
                            注册
                            <i class="icon-arrow-right icon-on-right"></i>
                        </button>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="toolbar center">
            <a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
                <i class="icon-arrow-left"></i>
                返回登录
            </a>
        </div>
    </div>
    <!-- /widget-body -->
</div>
<!-- /signup-box -->
</div>
<!-- /position-relative -->
</div>
</div>
<!-- /.col -->
</div>
<!-- /.row -->
</div>
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<%@include file="commons/common-script.jsp" %>

<script src="${contextPath}/assets/js/jquery.validate.min.js"></script>
<script src="${contextPath}/assets/js/jquery.validate.locale-cn.js"></script>
<!-- inline scripts related to this domain -->

<script type="text/javascript">
    function show_box(id) {
        jQuery('.widget-box.visible').removeClass('visible');
        jQuery('#' + id).addClass('visible');
    }
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".jcaptcha-btn").click(function () {
            var img = $(".jcaptcha-img");
            var imageSrc = img.attr("src");
            if (imageSrc.indexOf("?") > 0) {
                imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));
            }
            imageSrc = imageSrc + "?" + new Date().getTime();
            img.attr("src", imageSrc);
        });
        $('#loginForm').validate({
            rules: {
                username: {
                    minlength: 2,
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    minlength: 2,
                    required: true
                }
            },

            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (element) {
                //element
                //        .text('OK!').addClass('valid')
                //        .closest('.form-group').removeClass('error').addClass('success');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-info');
                $(element).remove();
            }
        });
        $('#register-form').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                username: {
                    minlength: 2,
                    required: true,
                    remote: {
                        url: "${contextPath}/validate",
                        type: "get",
                        dataType: "json",
                        data: {                     //要传递的数据
                            username: function () {
                                return $("#username").val();
                            }
                        }
                    }

                },
                email: {
                    required: true,
                    email: true,
                    remote: {
                        url: "${contextPath}/validate",
                        type: "get",
                        dataType: "json",
                        data: {                     //要传递的数据
                            email: function () {
                                return $("#email").val();
                            }
                        }
                    }
                },
                password: {
                    minlength: 5,
                    required: true
                },
                repeat_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                }

            },
            messages: {
                username: {
                    required: "请输入用户名.",
                    minlength: "用户名不能少于6个字符.",
                    remote: "用户名经被使用，请重新输入."
                },
                email: {
                    required: "请输入Email地址.",
                    email: "请输入正确的email地址.",
                    remote: "邮箱已经被使用，请重新输入."
                },
                password: {
                    required: "请输入密码.",
                    minlength: "密码不能小于5个字符."
                },
                repeat_password: {
                    required: "请输入确认密码.",
                    minlength: "确认密码不能小于5个字符.",
                    equalTo: "两次输入密码不一致."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                $('.alert-danger', $('.registerForm')).show();
            },

            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },

            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
                $(e).remove();
            }
        });

        $('#registerBtn').on('click', function (e) {
            if ($('#register-form').valid()) {
                var postData = $('#register-form').serialize();
                setTimeout(function () {
                    $.ajax({
                        type: 'POST',
                        url: '${contextPath}/register',
                        dataType: "json",
                        data: postData,
                        traditional: true,
                        success: function (data, status) {
                            //alert("success : " + data);
                            var resp = JSON.stringify(data);
                            $('#signup-box').find('.widget-main').prepend('<div class="alert alert-info fade in">' +
                                    resp + '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '</div>');
                        },
                        error: function () {
                            alert('操作错误,请与系统管理员联系!');
                        }
                    });
                }, 1000);
            }
        });

    }); // end document.ready

</script>

</body>
</html>
