<%@ include file="../commons/taglibs.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<!DOCTYPE html>
<html lang="en">
<%@ include file="../commons/head.jsp" %>

<body >
<%@ include file="../commons/navbar.jsp" %>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <%@ include file="../commons/sidebar.jsp" %>

        <div class="main-content" id="main-content" style="height: 100%">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>

                    <li>
                        <a href="#">Other Pages</a>
                    </li>
                    <li class="active">Error 404</li>
                </ul><!-- .breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- #nav-search -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <div class="error-container">
                            <div class="well">
                                <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-sitemap"></i>
												404
											</span>
                                    Page Not Found
                                </h1>

                                <hr />
                                <h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>

                                <div>
                                    <form class="form-search">
												<span class="input-icon align-middle">
													<i class="icon-search"></i>

													<input type="text" class="search-query" placeholder="Give it a search..." />
												</span>
                                        <button class="btn btn-sm" onclick="return false;">Go!</button>
                                    </form>

                                    <div class="space"></div>
                                    <h4 class="smaller">Try one of the following:</h4>

                                    <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                        <li>
                                            <i class="icon-hand-right blue"></i>
                                            Re-check the url for typos
                                        </li>

                                        <li>
                                            <i class="icon-hand-right blue"></i>
                                            Read the faq
                                        </li>

                                        <li>
                                            <i class="icon-hand-right blue"></i>
                                            Tell us about it
                                        </li>
                                    </ul>
                                </div>

                                <hr />
                                <div class="space"></div>

                                <div class="center">
                                    <a href="#" class="btn btn-grey">
                                        <i class="icon-arrow-left"></i>
                                        Go Back
                                    </a>

                                    <a href="#" class="btn btn-primary">
                                        <i class="icon-dashboard"></i>
                                        Dashboard
                                    </a>
                                </div>
                            </div>
                        </div><!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.domain-content -->
        </div><!-- /.main-content -->


    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<%@ include file="../commons/common-script.jsp" %>


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