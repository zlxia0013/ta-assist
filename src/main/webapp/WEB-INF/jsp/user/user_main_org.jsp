<%@ page import="com.tc.ta.core.user.pojo.User" %>
<%@ page import="com.tc.ta.common.web.JspKeys" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%
        String path = request.getContextPath();
        path = "/".equals(path)?"":path;
        request.setAttribute("path", path);
    %>
    <script type="text/javascript">
        var path = "${path}";
    </script>

    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-responsive.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/site.css">

    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">${sessionUserInfo.userName}-${sessionUserInfo.role}-${sessionUserInfo.realName}</a>
        </div>
    </div>
</div>

<%
    User sessionUserInfo = (User) request.getAttribute(JspKeys.JspParam_SessionUserInfo);
%>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3">
            <div class="well sidebar-nav">
                <ul class="nav nav-list">
                    <li class="nav-header"><i class="icon-wrench"></i> Administration</li>
                    <li><a href="${path}/client/goto_main_page">客户管理</a></li>

                    <%
                        if(RoleEnum.ADMIN.toString().equals(sessionUserInfo.getRole()))
                        {

                    %>
                    <li><a href="${path}/user/goto_main_page">用户管理</a></li>
                    <%
                        }
                    %>

                    <li class="nav-header"><i class="icon-user"></i> Profile</li>
                    <li><a href="${path}/user/goto_update_pwd_page">修改密码</a></li>
                    <li><a href="${path}/logout">退出</a></li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <div class="row-fluid">
                <div class="page-header">
                    <h1>用户管理</h1>
                </div>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>角色</th>
                        <th>电话</th>
                        <th>状态</th>
                        <th>备注</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        UserMainPageModel model = (UserMainPageModel) request.getAttribute(JspKeys.JspParam_UserMainPageModel);
                        List<User> userList = model.getUserList();
                        for (User user : userList) {
                    %>
                    <tr class="list-users">
                        <td><%=user.getUserName()%></td>
                        <td><%=user.getRealName()%></td>
                        <td><%=RoleEnum.getLabel(user.getRole())%></td>
                        <td><%=user.getPhone()%></td>
                        <td><%=UserStateEnum.getLabel(user.getStateId())%></td>
                        <td><%=user.getRemark()%></td>
                        <td>
                            <div class="btn-group">
                                <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">操作<span
                                        class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${path}/user/goto_update_page?userId=<%=user.getId()%>"><i class="icon-pencil"></i> 修改</a></li>
                                    <li><a href="javascript:void(0)" onclick="deleteUser(<%=user.getId()%>)"><i class="icon-trash"></i> 删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <div class="pagination">
                    <ul>
                        <%
                            for(int i=1; i<=model.getPageCount(); i++)
                            {
                        %>
                        <li <%if(model.getCurPage()==i){%>class="active"<%}%>>
                            <a href="javascript:void(0)" onclick="gotoPage(<%=i%>)"><%=i%></a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
                <a href="${path}/user/goto_add_page" class="btn btn-success">新增</a>
            </div>
        </div>
    </div>

    <hr>

    <footer class="well">
        &copy; 客户管理系统
    </footer>

</div>


<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $('.dropdown-menu li a').hover(
            function() {
                $(this).children('i').addClass('icon-white');
            },
            function() {
                $(this).children('i').removeClass('icon-white');
            });

        if($(window).width() > 760)
        {
            $('tr.list-users td div ul').addClass('pull-right');
        }
    });


    function deleteUser(userId){
        var r=confirm("你确定要删除吗？")
        if (r==false)
        {
            return;
        }

        $.ajax({
            url:"${path}/user/delete",
            type:"post",
            data:{
                userId:userId
            },
            success:function(result){
                var re = JSON.parse(result);
                if(re.returnCode=="0"){
                    window.location.reload();
                }else{
                    alert(re.msg);
                }
            },
            error:function(request) {      // 设置表单提交出错
                alert(request);  //登录错误提示信息
            }
        });
    }

    function gotoPage(curPage){
        window.location.href="${path}/user/goto_main_page?pageSize=20&curPage="+curPage;
    }
</script>
</body>
</html>
