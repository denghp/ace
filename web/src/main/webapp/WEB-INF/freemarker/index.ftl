<#assign shiro=JspTaglibs["http://shiro.apache.org/tags"]>
<!DOCTYPE html>
<html lang="en">
<#include "./commons/header.ftl" >

<body >
<#include "./commons/navbar.ftl" >

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

    <#include "./commons/sidebar.ftl" >

        <div class="main-content" id="main-content" style="height: 100%">
            <#include "./main.ftl">
        </div><!-- /.main-content -->


    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<#include "./commons/common-script.ftl" >


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
