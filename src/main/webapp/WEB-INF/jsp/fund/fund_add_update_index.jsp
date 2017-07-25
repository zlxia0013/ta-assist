<%@ page import="com.tc.ta.interfaces.http.FundController" %>
<%@ page import="java.util.Random" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%
        String path = request.getContextPath();
        path = "/".equals(path)?"":path;
        request.setAttribute("path", path);
    %>
    <script type="text/javascript">
        var path = "${path}";
    </script>

    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${path}/public/adminlte/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${path}/public/font-awesome-4.7.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${path}/public/ionicons/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${path}/public/adminlte/plugins/datatables/dataTables.bootstrap.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${path}/public/adminlte/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="${path}/public/adminlte/dist/css/skins/_all-skins.min.css">

    <link rel="stylesheet" href="${path}/css/common.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body >

<!-- Content Wrapper. Contains page content -->

    <div class="nav-tabs-custom">
        <ul class="nav nav-tabs">
        </ul>
        <div class="tab-content">
        </div>
        <!-- /.tab-content -->
    </div>
    <!-- /.content -->

<!-- /.content-wrapper -->


<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="${path}/public/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${path}/public/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${path}/public/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${path}/public/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${path}/public/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${path}/public/adminlte/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${path}/public/adminlte/dist/js/app.min.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
<!-- page script -->
<script>
    var addTab = function (obj) {
        id ="tab_"+ obj.id;

        $(".active").removeClass("active");

//如果TAB不存在，创建一个新的TAB
        if (!obj.single || !$("#"+ id)[0]) {
//固定TAB中IFRAME高度
            mainHeight = $(document.body).height() - 95;
//创建新TAB的title
            title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + obj.title;
//是否允许关闭
            if (obj.close) {
                //title += ' <i class="icon-cancel" tabclose="' + id + '">x</i>';
                title += '<button type="button" class="close" aria-label="Close" tabclose="' + id + '" style="width:0px; margin-left:10px;">';
                title += '<span aria-hidden="true" style="color:red">&times;</span>';
                title += '</button>';
            }
            title += '</a></li>';
//是否指定TAB内容
            if (obj.content) {
                content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + obj.content + '</div>';
            } else {//没有内容，使用IFRAME打开链接
                content = '<div role="tabpanel" class="tab-pane" id="' + id + '"><iframe src="' + obj.url + '" width="100%" height="' + mainHeight +
                    '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
            }
//加入TABS
            $(".nav-tabs").append(title);
            $(".tab-content").append(content);
        }

//激活TAB
        $("#tab_"+ id).addClass('active');
        $("#"+ id).addClass("active");
    };

    var closeTab = function (id) {
//如果关闭的是当前激活的TAB，激活他的前一个TAB
        if ($("li.active").attr('id') =="tab_"+ id) {
            $("#tab_"+ id).prev().addClass('active');
            $("#"+ id).prev().addClass('active');
        }
//关闭TAB
        $("#tab_"+ id).remove();
        $("#"+ id).remove();
    };

    $(function () {
        $(".nav-tabs").on("click","[tabclose]", function (e) {
            id = $(this).attr("tabclose");
            closeTab(id);
        });

        addTab({title: "基金基本信息", url:"${path}<%=FundController.URL_GOTO_ADD_UPDATE_PAGE%>", id: <%=new Random().nextInt()%>, close: false});


    });
</script>
</body>
</html>
