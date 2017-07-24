<%@ page import="com.tc.ta.core.user.pojo.User" %>
<%@ page import="com.tc.ta.common.web.JspKeys" %>
<%@ page import="com.tc.ta.interfaces.http.UserController" %>
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
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body >
<div class="container">

            <div class="box box-primary">
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="txtUserName">用户名</label>
                                <input type="email" class="form-control" id="txtUserName" placeholder="输入用户名">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="txtFullName">姓名</label>
                                <input type="password" class="form-control" id="txtFullName" placeholder="输入用户姓名">
                            </div>
                        </div>
                        <div class="col-md-4">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary">搜 索</button>
                            <button type="submit" id="btnAddUser" class="btn btn-primary pull-right ">新增用户</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.box -->
            <div class="box box-primary">
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="dg" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>用户名</th>
                            <th>角色</th>
                            <th>姓名</th>
                            <th>电话</th>
                            <th>状态</th>
                            <th>添加时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>dsfasfadsfsafasfd</td>
                            <td>Netscape Browser 8</td>
                            <td>Win 98SE+</td>
                            <td>1.7</td>
                            <td>A</td>
                            <td>A</td>
                            <td>A</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
</div>

<!-- ./wrapper -->

<div class="modal fade" id="modelContainer" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

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
    $(function () {
        $('#dg').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });


        $("#btnAddUser").click(function () {
            parent.addTab({id: 4, title: "新增用户", url:"${path}<%=UserController.URL_GOTO_ADD_PAGE%>", close: true, single:true});
        });
    });
</script>
</body>
</html>
