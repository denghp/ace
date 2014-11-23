
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
            <a href="#">UI Elements</a>
        </li>
        <li class="active">Treeview</li>
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
    <div class="page-header">
        <h1>
            Treeview
            <small>
                <i class="icon-double-angle-right"></i>
                with selectable items(single &amp; multiple) and custom icons
            </small>
        </h1>
    </div><!-- /.domain-header -->

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->

            <div class="row">
                <div class="col-sm-6">
                    <div class="widget-box">
                        <div class="widget-header header-color-blue2">
                            <h4 class="lighter smaller">资源列表</h4>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main padding-8">
                                <ul id="treeview" class="ztree"
                                    style="height: 340px;width: 490px;margin-top: 0px;">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="updateForm"   class="col-sm-6">

                </div>
            </div>

            <script type="text/javascript">
                var $path_base = "${rc.getContextPath()}";//this will be used in gritter alerts containing images
            </script>

            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.domain-content -->




<!-- inline scripts related to this domain -->

<script type="text/javascript">
    eval('debugger;');
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };
    var zTreeObj;
    var zNodes = [];
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: $path_base + '/admin/sys/resource/ajax/load.json?async=false',
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
    });

    function onClick(event, treeId, treeNode, clickFlag) {
        //alert("[onClick ]&nbsp;&nbsp;treeId :" + treeId + " treeNode : " + treeNode);
        var url = $path_base + "/admin/sys/resource/" + parseInt(treeNode.id) + "/update"
        $("#updateForm").load(url);
    }
</script>

