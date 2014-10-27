<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


    <!DOCTYPE html>
    <html lang="en">
    <%@ include file="commons/head.jsp" %>

        <body >
        <%@ include file="commons/navbar.jsp" %>

            <div class="main-container" id="main-container">
                <script type="text/javascript">
                    try{ace.settings.check('main-container' , 'fixed')}catch(e){}
                </script>

                <div class="main-container-inner">
                    <a class="menu-toggler" id="menu-toggler" href="#">
                        <span class="menu-text"></span>
                    </a>

                    <%@ include file="commons/sidebar.jsp" %>

                        <div class="main-content" id="main-content" style="height: 100%">
                            <%@ include file="./main.jsp" %>
                        </div><!-- /.main-content -->


                </div><!-- /.main-container-inner -->

                <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                    <i class="icon-double-angle-up icon-only bigger-110"></i>
                </a>
            </div><!-- /.main-container -->

            <%@ include file="commons/common-script.jsp" %>


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

