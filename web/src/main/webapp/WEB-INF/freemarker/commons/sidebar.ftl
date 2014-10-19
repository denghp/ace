<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <!-- #sidebar-shortcuts -->

    <ul class="nav nav-list">
        <li class="active">
            <a href="javascript:void(0);" id="nav-dashboard" onclick="loadPage('${rc.getContextPath()}/main')">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> Dashboard </span>
            </a>
        </li>
    <#if menuList?exists>
        <#list menuList as menu>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="${menu.icon} "></i>
                    <span class="menu-text"> ${menu.name}  </span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                        <#list menu.getChildren() as children >
                            <li>
                                <#if (children.children?size > 0) >
                                    <a href="#" class="dropdown-toggle">
                                        <i class="icon-double-angle-right"></i>
                                        ${children.name}
                                        <b class="arrow icon-angle-down"></b>
                                    </a>
                                    <ul class="submenu">
                                        <#list children.getChildren() as threeChildren>
                                            <li>
                                                <a href="javascript:void(0);" onclick="loadPage('${rc.getContextPath()}${threeChildren.url}')"  >
                                                    <i class="icon-angle-right"></i>
                                                    ${threeChildren.name}
                                                </a>
                                            </li>
                                        </#list>
                                    </ul>
                                <#else>
                                        <!--
                                        <a href="${rc.getContextPath()}${children.url}" target="main">
                                        -->
                                        <a href="javascript:void(0);" onclick="loadPage('${rc.getContextPath()}${children.url}')"  >
                                        <i class="icon-double-angle-right"></i>
                                        ${children.name}
                                    </a>
                                </#if>
                            </li>
                        </#list>
                </ul>
            </li>
        </#list>
    </#if>
    </ul>

    <!-- /.nav-list -->


    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>