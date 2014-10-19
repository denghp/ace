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
            <a href="#">系统设置</a>
        </li>
        <li class="active">资源列表</li>
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

            <div id="alert-info" class="alert alert-info">
                <i class="icon-hand-right"></i>

                请注意: 这里显示您对资源管理的任何操作信息!
                <button class="close" data-dismiss="alert">
                    <i class="icon-remove"></i>
                </button>
            </div>

            <table id="grid-table"></table>

            <div id="grid-pager"></div>

            <script type="text/javascript">
                var $path_base = "${rc.getContextPath()}";//this will be used in gritter alerts containing images
            </script>

            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
<div id="paddtree"></div>
<!-- inline scripts related to this page -->
<script type="text/javascript">
var grid_data =
        [
            {id:1,name:'Cash',num:'100', debit:"400.00", credit:"250.00", balance:"150.00", lft:"1", rgt:"8",level:"0",isLeaf:"false",expanded:"false"},
            {id:2,name:'Cash 1',num:'1', debit:"300.00", credit:"200.00", balance:"100.00", lft:2, rgt:5,level:1,isLeaf:false,expanded:false},
            {id:3,name:'Sub Cash 1',num:'1', debit:"300.00", credit:"200.00", balance:"100.00", lft:3, rgt:4,level:2,isLeaf:true,expanded:false},
            {id:4,name:'Cash 2',num:'2', debit:"100.00", credit:"50.00", balance:"50.00", lft:6, rgt:7,level:1,isLeaf:true,expanded:false}
        ];

eval("debugger");
jQuery(function($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    jQuery(grid_selector).jqGrid({
        url:$path_base + "/admin/sys/resource/children",
        //data: grid_data,
        datatype:"json",
        mtype: 'GET',
        height: 450,
        colNames:['','ID','资源名称','父节点ID','排序编码','资源图标','资源标识','URL路径', '是否可用'],
        colModel:[
            {name:'resource',index:'', width:100, fixed:true, sortable:false, resize:false,
                formatter:'actions',
                formatoptions:{
                    keys:true,
                    delOptions:{url:$path_base+"/admin/sys/resource/delete",recreateForm: true, beforeShowForm:beforeDeleteCallback,
                        /** TODO:服务器端做验证
                        beforeSubmit: function(posdata, formid){
                            if($("#grid-table").jqGrid('getRowData',posdata).leaf == "true") {
                                return[true,"delete posdata " + formid];
                            }
                            ace.show_msg("提交失败了, 该资源包含子菜单!")
                            return [false,'提交失败了, 该资源包含子菜单!']
                        },
                         **/
                        afterSubmit : function(response, postdata)  {
                            var resp = response.responseJSON;
                            if (resp.responseHeader != undefined &&
                                    resp.responseHeader.status != undefined &&
                                    resp.responseHeader.status == "200" ) {
                                ace.show_msg("删除成功!");
                                return [true];
                            }
                            if (resp.error != undefined ) {
                                return [false,JSON.stringify(resp.error)];
                            }
                            return [false,"删除失败,服务器内部的错误! "];
                        }
                    },
                    //editformbutton:true,
                    //editOptions:{url:$path_base+"/admin/sys/user/update",recreateForm: true, beforeShowForm:beforeEditCallback},
                    onSuccess: function(response) {
                        var resp = response.responseJSON;
                        if (resp.responseHeader != undefined &&
                                resp.responseHeader.status != undefined &&
                                resp.responseHeader.status == "200" ) {
                            ace.show_msg("更新成功!");
                            return [true];
                        }
                        return [false];

                    },
                    onError: function(response) {
                        if (response.responseJSON != undefined) {
                            var resp = response.responseJSON;
                            //获取error
                            var errorMsg  = JSON.stringify(resp);
                            ace.show_msg("更新失败! "+ errorMsg);
                        }
                        ace.show_msg("更新失败! ");
                        return [true];
                    }
                }
            },
            {name:'id',index:'id', width:30,hidden:false,key:true, editable:false},
            {name:'name',index:'name', width:100, editable:true, align:"left"},
            {name:'parentId',index:'parentId', width:35, align:"center",editable:true},
            {name:'weight',index:'weight', width:35,align:"center",editable:true},
            {name:'icon',index:'icon', width:50, align:"center",editable:true},
            {name:'identity',index:'identity', width:80,align:"left",editable:true},
            {name:'url',index:'url', width:80,align:"left",editable:true, formatter:formatURL},
            {name:'show',index:'show', width:50, editable: true, edittype:"checkbox",  editoptions:{value:"true:false"},unformat: aceSwitch}
        ],

        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        pager : pager_selector,
        //toppager: true,
        multiselect: false,
        //multikey: "ctrlKey",
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                styleCheckbox(table);

                updateActionIcons(table);
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },
        treeGrid:true,
        treeGridModel:"adjacency",
        ExpandColumn: 'name',
        //ExpandColClick: true,
        treeIcons:{
            plus:'arrow icon-double-angle-down',
            minus:'icon-double-angle-right'
            //leaf:'ui-icon-radio-off' //使用leafDefaultIcon = icon-angle-right
        },
        treeReader: {
            level_field: "level",
            parent_id_field: "parent", // then why does your table use "parent_id"?
            leaf_field: "leaf",
            expanded_field: "expanded"
        },
        editurl: $path_base+"/admin/sys/resource/update",//nothing is saved
        caption: "系统资源管理",
        autowidth: true

    });

    function formatURL( cellvalue, options, cell ) {
        if (cellvalue.length <= 0) {
            return "javascript:void(0)"
        }
        return cellvalue;
    }
    //enable search/filter toolbar
    //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})

    //switch element when editing inline
    function aceSwitch( cellvalue, options, cell ) {
        setTimeout(function(){
            $(cell) .find('input[type=checkbox]')
                    .wrap('<label class="inline" />')
                    .addClass('ace ace-switch ace-switch-5')
                    .after('<span class="lbl"></span>');
        }, 0);
    }
    //enable datepicker
    function pickDate( cellvalue, options, cell ) {
        setTimeout(function(){
            $(cell) .find('input[type=text]')
                    .datepicker({format:'yyyy-mm-dd' , autoclose:true});
        }, 0);
    }


    //navButtons
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
            { 	//navbar options
                edit: true,
                editicon : 'icon-pencil blue',
                add: true,
                addicon : 'icon-plus-sign purple',
                del: true,
                delicon : 'icon-trash red',
                search: true,
                searchicon : 'icon-search orange',
                refresh: true,
                refreshicon : 'icon-refresh green',
                view: true,
                viewicon : 'icon-zoom-in grey'
            },
            {
                //edit record form
                url:$path_base+"/admin/sys/resource/update",
                closeAfterEdit:true,
                recreateForm: true,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_edit_form(form);
                },
                afterSubmit : function(response, postdata)  {
                    var resp = response.responseJSON;
                    if (resp.responseHeader != undefined &&
                            resp.responseHeader.status != undefined &&
                            resp.responseHeader.status == "200" ) {
                        ace.show_msg("更新成功!");
                        return [true,'更新成功!'];
                    }
                    if (resp.error != undefined ) {
                        return [false,JSON.stringify(resp.error)];
                    }
                    return [false,"更新失败,服务器内部的错误! "];
                }
            },
            {
                //new record form
                url:$path_base+"/admin/sys/resource/add",
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                serializeEditData: function(data){
                    //新增数据的时候把默认的id='_empty'设置为id='0'
                    return $.param($.extend({},data,{id:0}));
                },
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_edit_form(form);

                },
                beforeSubmit: function(posdata,formid) {
                    console.log(posdata);
                    return [true,formid];
                },
                afterSubmit : function(response, postdata)  {
                    var resp = response.responseJSON;
                    if (resp.responseHeader != undefined &&
                            resp.responseHeader.status != undefined &&
                            resp.responseHeader.status == "200" ) {
                        ace.show_msg("添加成功!");
                        return [true,'添加成功!'];
                    }
                    if (resp.error != undefined ) {
                        return [false,JSON.stringify(resp.error)];
                    }
                    return [false,"更新失败,服务器内部的错误! "];
                }
            },
            {
                //delete record form
                url:$path_base+"/admin/sys/resource/delete",
                recreateForm: true,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    if(form.data('styled')) return false;

                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_delete_form(form);

                    form.data('styled', true);
                },
                afterSubmit : function(response, postdata)  {
                    var resp = response.responseJSON;
                    if (resp.responseHeader != undefined &&
                            resp.responseHeader.status != undefined &&
                            resp.responseHeader.status == "200" ) {
                        ace.show_msg("删除成功!");
                        return [true,'删除成功!'];
                    }
                    if (resp.error != undefined ) {
                        return [false,JSON.stringify(resp.error)];
                    }
                    return [false,"删除失败,服务器内部的错误! "];
                }
            },
            {
                //search form
                url:$path_base+"/admin/sys/resource/search",
                recreateForm: true,
                afterShowSearch: function(e){
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                    style_search_form(form);
                },
                afterRedraw: function(){
                    style_search_filters($(this));
                }
                ,
                multipleSearch: true
                /**
                 multipleGroup:true,
                 showQuery: true
                 */
            },
            {
                //view record form
                recreateForm: true,
                beforeShowForm: function(e){
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                }
            }
    )


    function style_edit_form(form) {
        //enable datepicker on "sdate" field and switches for "stock" field
        form.find('input[name=show]')
                .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

        //update buttons classes
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
        buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
        buttons.eq(1).prepend('<i class="icon-remove"></i>')

        buttons = form.next().find('.navButton a');
        buttons.find('.ui-icon').remove();
        buttons.eq(0).append('<i class="icon-chevron-left"></i>');
        buttons.eq(1).append('<i class="icon-chevron-right"></i>');
    }

    function style_delete_form(form) {
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
        buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
        buttons.eq(1).prepend('<i class="icon-remove"></i>')
    }

    function style_search_filters(form) {
        form.find('.delete-rule').val('X');
        form.find('.add-rule').addClass('btn btn-xs btn-primary');
        form.find('.add-group').addClass('btn btn-xs btn-success');
        form.find('.delete-group').addClass('btn btn-xs btn-danger');
    }
    function style_search_form(form) {
        var dialog = form.closest('.ui-jqdialog');
        var buttons = dialog.find('.EditTable')
        buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
        buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
        buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
    }

    function beforeDeleteCallback(e) {
        var form = $(e[0]);
        if(form.data('styled')) return false;

        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_delete_form(form);

        form.data('styled', true);
    }

    function beforeEditCallback(e) {
        var form = $(e[0]);
        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_edit_form(form);
    }



    //it causes some flicker when reloading or navigating grid
    //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
    //or go back to default browser checkbox styles for the grid
    function styleCheckbox(table) {
        /**
         $(table).find('input:checkbox').addClass('ace')
         .wrap('<label />')
         .after('<span class="lbl align-top" />')


         $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
         .find('input.cbox[type=checkbox]').addClass('ace')
         .wrap('<label />').after('<span class="lbl align-top" />');
         */
    }


    //unlike navButtons icons, action icons in rows seem to be hard-coded
    //you can change them like this in here if you want
    function updateActionIcons(table) {
        /**
         var replacement =
         {
             'ui-icon-pencil' : 'icon-pencil blue',
             'ui-icon-trash' : 'icon-trash red',
             'ui-icon-disk' : 'icon-ok green',
             'ui-icon-cancel' : 'icon-remove red'
         };
         $(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
         */
    }

    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
        {
            'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
            'ui-icon-seek-next' : 'icon-angle-right bigger-140',
            'ui-icon-seek-end' : 'icon-double-angle-right bigger-140',
            'ui-icon-triangle-1-e tree-plus treeclick' : 'icon-double-angle-left bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        });
        /**
        $('#grid-table > tbody > tr > > .tree-wrap .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        }); **/
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');


});
</script>




