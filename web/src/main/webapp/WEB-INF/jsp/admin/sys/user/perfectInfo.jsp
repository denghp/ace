<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../../commons/taglibs.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ include file="../../../commons/head.jsp" %>

<body>
<%@ include file="../../../commons/navbar.jsp" %>

<div class="main-container" id="main-container">
<script type="text/javascript">
    try {
        ace.settings.check('main-container', 'fixed')
    } catch (e) {
    }
</script>

<div class="page-content">
<div class="page-header">
    <h1>
        Form Wizard
        <small>
            <i class="icon-double-angle-right"></i>
            and Validation
        </small>
    </h1>
</div>
<!-- /.domain-header -->

<div class="row">
<div class="col-xs-12">
<!-- PAGE CONTENT BEGINS -->

<h4 class="lighter">
    <i class="icon-hand-right icon-animated-hand-pointer blue"></i>
    <a href="#modal-wizard" data-toggle="modal" class="pink"> Wizard Inside a Modal Box </a>
</h4>

<div class="hr hr-18 hr-double dotted"></div>

<div class="row-fluid">
<div class="span12">
<div class="widget-box">
<div class="widget-header widget-header-blue widget-header-flat">
    <h4 class="lighter">New Item Wizard</h4>

    <div class="widget-toolbar">
        <label>
            <small class="green">
                <b>Validation</b>
            </small>

            <input id="skip-validation" type="checkbox" class="ace ace-switch ace-switch-4">
            <span class="lbl"></span>
        </label>
    </div>
</div>

<div class="widget-body">
<div class="widget-main">
<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
    <ul class="wizard-steps">
        <li data-target="#step1" class="active">
            <span class="step">1</span>
            <span class="title">Validation states</span>
        </li>

        <li data-target="#step2" class="">
            <span class="step">2</span>
            <span class="title">Alerts</span>
        </li>

        <li data-target="#step3" class="">
            <span class="step">3</span>
            <span class="title">Payment Info</span>
        </li>

        <li data-target="#step4" class="">
            <span class="step">4</span>
            <span class="title">Other Info</span>
        </li>
    </ul>
</div>

<hr>
<div class="step-content row-fluid position-relative" id="step-container">
<div class="step-pane active" id="step1">
<h3 class="lighter block green">Enter the following information</h3>

<form class="form-horizontal" id="sample-form">
    <div class="form-group has-warning">
        <label for="inputWarning" class="col-xs-12 col-sm-3 control-label no-padding-right">Input with warning</label>

        <div class="col-xs-12 col-sm-5">
																		<span class="block input-icon input-icon-right">
																			<input type="text" id="inputWarning"
                                                                                   class="width-100">
																			<i class="icon-leaf"></i>
																		</span>
        </div>
        <div class="help-block col-xs-12 col-sm-reset inline">
            Warning tip help!
        </div>
    </div>

    <div class="form-group has-error">
        <label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">Input with
            error</label>

        <div class="col-xs-12 col-sm-5">
																		<span class="block input-icon input-icon-right">
																			<input type="text" id="inputError"
                                                                                   class="width-100">
																			<i class="icon-remove-sign"></i>
																		</span>
        </div>
        <div class="help-block col-xs-12 col-sm-reset inline"> Error tip help!</div>
    </div>

    <div class="form-group has-success">
        <label for="inputSuccess" class="col-xs-12 col-sm-3 control-label no-padding-right">Input with success</label>

        <div class="col-xs-12 col-sm-5">
																		<span class="block input-icon input-icon-right">
																			<input type="text" id="inputSuccess"
                                                                                   class="width-100">
																			<i class="icon-ok-sign"></i>
																		</span>
        </div>
        <div class="help-block col-xs-12 col-sm-reset inline">
            Success tip help!
        </div>
    </div>

    <div class="form-group has-info">
        <label for="inputInfo" class="col-xs-12 col-sm-3 control-label no-padding-right">Input with info</label>

        <div class="col-xs-12 col-sm-5">
																		<span class="block input-icon input-icon-right">
																			<input type="text" id="inputInfo"
                                                                                   class="width-100">
																			<i class="icon-info-sign"></i>
																		</span>
        </div>
        <div class="help-block col-xs-12 col-sm-reset inline"> Info tip help!</div>
    </div>

    <div class="form-group">
        <label for="inputError2" class="col-xs-12 col-sm-3 control-label no-padding-right">Input with error</label>

        <div class="col-xs-12 col-sm-5">
																		<span class="input-icon block">
																			<input type="text" id="inputError2"
                                                                                   class="width-100">
																			<i class="icon-remove-sign red"></i>
																		</span>
        </div>
        <div class="help-block col-xs-12 col-sm-reset inline"> Error tip help!</div>
    </div>
</form>

<form class="form-horizontal hide" id="validation-form" method="get" novalidate="novalidate">
<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="email">Email Address:</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <input type="email" name="email" id="email" class="col-xs-12 col-sm-6">
        </div>
    </div>
</div>

<div class="space-2"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="password">Password:</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <input type="password" name="password" id="password" class="col-xs-12 col-sm-4">
        </div>
    </div>
</div>

<div class="space-2"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="password2">Confirm Password:</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <input type="password" name="password2" id="password2" class="col-xs-12 col-sm-4">
        </div>
    </div>
</div>

<div class="hr hr-dotted"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">Company Name:</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <input type="text" id="name" name="name" class="col-xs-12 col-sm-5">
        </div>
    </div>
</div>

<div class="space-2"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="phone">Phone Number:</label>

    <div class="col-xs-12 col-sm-9">
        <div class="input-group">
																			<span class="input-group-addon">
																				<i class="icon-phone"></i>
																			</span>

            <input type="tel" id="phone" name="phone">
        </div>
    </div>
</div>

<div class="space-2"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">Company URL:</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <input type="url" id="url" name="url" class="col-xs-12 col-sm-8">
        </div>
    </div>
</div>

<div class="hr hr-dotted"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right">Subscribe to</label>

    <div class="col-xs-12 col-sm-9">
        <div>
            <label>
                <input name="subscription" value="1" type="checkbox" class="ace">
                <span class="lbl"> Latest news and announcements</span>
            </label>
        </div>

        <div>
            <label>
                <input name="subscription" value="2" type="checkbox" class="ace">
                <span class="lbl"> Product offers and discounts</span>
            </label>
        </div>
    </div>
</div>

<div class="space-2"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right">Gender</label>

    <div class="col-xs-12 col-sm-9">
        <div>
            <label class="blue">
                <input name="gender" value="1" type="radio" class="ace">
                <span class="lbl"> Male</span>
            </label>
        </div>

        <div>
            <label class="blue">
                <input name="gender" value="2" type="radio" class="ace">
                <span class="lbl"> Female</span>
            </label>
        </div>
    </div>
</div>

<div class="hr hr-dotted"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="s2id_autogen1">State</label>

    <div class="col-xs-12 col-sm-9">
        <div class="select2-container select2" id="s2id_state" style="width: 200px;"><a href="javascript:void(0)"
                                                                                        onclick="return false;"
                                                                                        class="select2-choice"
                                                                                        tabindex="-1"> <span
                class="select2-chosen">&nbsp;</span><abbr class="select2-search-choice-close"></abbr> <span
                class="select2-arrow"><b></b></span></a><input class="select2-focusser select2-offscreen" type="text"
                                                               id="s2id_autogen1">

            <div class="select2-drop select2-display-none select2-with-searchbox">
                <div class="select2-search"><input type="text" autocomplete="off" autocorrect="off" autocapitalize="off"
                                                   spellcheck="false" class="select2-input"></div>
                <ul class="select2-results"></ul>
            </div>
        </div>
        <select id="state" name="state" class="select2 select2-offscreen" data-placeholder="Click to Choose..."
                style="width: 200px;" tabindex="-1">
            <option value="">&nbsp;</option>
            <option value="AL">Alabama</option>
            <option value="AK">Alaska</option>
            <option value="AZ">Arizona</option>
            <option value="AR">Arkansas</option>
            <option value="CA">California</option>
            <option value="CO">Colorado</option>
            <option value="CT">Connecticut</option>
            <option value="DE">Delaware</option>
            <option value="FL">Florida</option>
            <option value="GA">Georgia</option>
            <option value="HI">Hawaii</option>
            <option value="ID">Idaho</option>
            <option value="IL">Illinois</option>
            <option value="IN">Indiana</option>
            <option value="IA">Iowa</option>
            <option value="KS">Kansas</option>
            <option value="KY">Kentucky</option>
            <option value="LA">Louisiana</option>
            <option value="ME">Maine</option>
            <option value="MD">Maryland</option>
            <option value="MA">Massachusetts</option>
            <option value="MI">Michigan</option>
            <option value="MN">Minnesota</option>
            <option value="MS">Mississippi</option>
            <option value="MO">Missouri</option>
            <option value="MT">Montana</option>
            <option value="NE">Nebraska</option>
            <option value="NV">Nevada</option>
            <option value="NH">New Hampshire</option>
            <option value="NJ">New Jersey</option>
            <option value="NM">New Mexico</option>
            <option value="NY">New York</option>
            <option value="NC">North Carolina</option>
            <option value="ND">North Dakota</option>
            <option value="OH">Ohio</option>
            <option value="OK">Oklahoma</option>
            <option value="OR">Oregon</option>
            <option value="PA">Pennsylvania</option>
            <option value="RI">Rhode Island</option>
            <option value="SC">South Carolina</option>
            <option value="SD">South Dakota</option>
            <option value="TN">Tennessee</option>
            <option value="TX">Texas</option>
            <option value="UT">Utah</option>
            <option value="VT">Vermont</option>
            <option value="VA">Virginia</option>
            <option value="WA">Washington</option>
            <option value="WV">West Virginia</option>
            <option value="WI">Wisconsin</option>
            <option value="WY">Wyoming</option>
        </select>
    </div>
</div>

<div class="space-2"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="platform">Platform</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <select class="input-medium" id="platform" name="platform">
                <option value="">------------------</option>
                <option value="linux">Linux</option>
                <option value="windows">Windows</option>
                <option value="mac">Mac OS</option>
                <option value="ios">iOS</option>
                <option value="android">Android</option>
            </select>
        </div>
    </div>
</div>

<div class="space-2"></div>

<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="comment">Comment</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <textarea class="input-xlarge" name="comment" id="comment"></textarea>
        </div>
    </div>
</div>

<div class="space-8"></div>

<div class="form-group">
    <div class="col-xs-12 col-sm-4 col-sm-offset-3">
        <label>
            <input name="agree" id="agree" type="checkbox" class="ace">
            <span class="lbl"> I accept the policy</span>
        </label>
    </div>
</div>
</form>
</div>

<div class="step-pane" id="step2">
    <div class="row-fluid">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">
                <i class="icon-remove"></i>
            </button>

            <strong>
                <i class="icon-ok"></i>
                Well done!
            </strong>

            You successfully read this important alert message.
            <br>
        </div>

        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert">
                <i class="icon-remove"></i>
            </button>

            <strong>
                <i class="icon-remove"></i>
                Oh snap!
            </strong>

            Change a few things up and try submitting again.
            <br>
        </div>

        <div class="alert alert-warning">
            <button type="button" class="close" data-dismiss="alert">
                <i class="icon-remove"></i>
            </button>
            <strong>Warning!</strong>

            Best check yo self, you're not looking too good.
            <br>
        </div>

        <div class="alert alert-info">
            <button type="button" class="close" data-dismiss="alert">
                <i class="icon-remove"></i>
            </button>
            <strong>Heads up!</strong>

            This alert needs your attention, but it's not super important.
            <br>
        </div>
    </div>
</div>

<div class="step-pane" id="step3">
    <div class="center">
        <h3 class="blue lighter">This is step 3</h3>
    </div>
</div>

<div class="step-pane" id="step4">
    <div class="center">
        <h3 class="green">Congrats!</h3>
        Your product is ready to ship! Click finish to continue!
    </div>
</div>
</div>

<hr>
<div class="row-fluid wizard-actions">
    <button class="btn btn-prev" disabled="disabled">
        <i class="icon-arrow-left"></i>
        Prev
    </button>

    <button class="btn btn-success btn-next" data-last="Finish ">
        Next

        <i class="icon-arrow-right icon-on-right"></i></button>
</div>
</div>
<!-- /widget-main -->
</div>
<!-- /widget-body -->
</div>
</div>
</div>

<div id="modal-wizard" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" data-target="#modal-step-contents">
                <ul class="wizard-steps">
                    <li data-target="#modal-step1" class="active">
                        <span class="step">1</span>
                        <span class="title">Validation states</span>
                    </li>

                    <li data-target="#modal-step2">
                        <span class="step">2</span>
                        <span class="title">Alerts</span>
                    </li>

                    <li data-target="#modal-step3">
                        <span class="step">3</span>
                        <span class="title">Payment Info</span>
                    </li>

                    <li data-target="#modal-step4">
                        <span class="step">4</span>
                        <span class="title">Other Info</span>
                    </li>
                </ul>
            </div>

            <div class="modal-body step-content" id="modal-step-contents">
                <div class="step-pane active" id="modal-step1">
                    <div class="center">
                        <h4 class="blue">Step 1</h4>
                    </div>
                </div>

                <div class="step-pane" id="modal-step2">
                    <div class="center">
                        <h4 class="blue">Step 2</h4>
                    </div>
                </div>

                <div class="step-pane" id="modal-step3">
                    <div class="center">
                        <h4 class="blue">Step 3</h4>
                    </div>
                </div>

                <div class="step-pane" id="modal-step4">
                    <div class="center">
                        <h4 class="blue">Step 4</h4>
                    </div>
                </div>
            </div>

            <div class="modal-footer wizard-actions">
                <button class="btn btn-sm btn-prev" disabled="disabled">
                    <i class="icon-arrow-left"></i>
                    Prev
                </button>

                <button class="btn btn-success btn-sm btn-next" data-last="Finish ">
                    Next
                    <i class="icon-arrow-right icon-on-right"></i>
                </button>

                <button class="btn btn-danger btn-sm pull-left" data-dismiss="modal">
                    <i class="icon-remove"></i>
                    Cancel
                </button>
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

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
</div>
<!-- /.main-container -->

<#include "../../../commons/common-script.ftl" >


    <script type="text/javascript">
        /**
         $(document).ready(function(id, url) {
        $("#element").click(function() {
            console.log("load...");
            $("#main-content").load("./main.ftl");
        });
    });
         **/
        function loadPage(url) {
            $("#main-content").load(url);
        }
    </script>
</body>
</html>


