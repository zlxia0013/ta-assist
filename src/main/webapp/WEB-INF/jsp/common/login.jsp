<%@ page import="com.tc.ta.interfaces.http.SysStartupController" %>
<%@ page import="com.tc.ta.interfaces.http.SysLoginController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录 | TA小助手</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%
        String path = request.getContextPath();
        path = "/".equals(path)?"":path;
        request.setAttribute("path", path);
    %>
    <script type="text/javascript">
        var path = "${path}";
    </script>

    <link rel="stylesheet" href="${path}/public/adminlte/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/public/htmleaf/htmleaf-demo.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/login/login.css">
</head>
<body>
<div class="htmleaf-container">
    <header class="htmleaf-header">
    </header>
    <div class="signin">
        <div class="signin-head"><img src="${path}/img/login/head_120.png" alt="" class="img-circle"></div>
        <form class="form-signin">
            <input id="txtUserName" type="text" class="form-control" placeholder="用户名" required autofocus value="admin"/>
            <input id="txtPassword" type="password" class="form-control" placeholder="密码" required value="aa" />
            <input type="button" class="btn btn-lg btn-warning btn-block"  onclick="login()" value="登录"></input>
        </form>
    </div>
</div>


<script type="text/javascript" src="${path}/public/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${path}/public/adminlte/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
    function login(){
        var userName = $("#txtUserName").val();
        if(userName == ""){
            alert("请输入用户名");
            return;
        }

        var password = $("#txtPassword").val();
        if(password == ""){
            alert("请输入密码");
            return;
        }

        $.ajax({
            url:"${path}<%=SysLoginController.URL_LOGIN%>",
            type:"post",
            data:{
                userName:userName,
                password:password
            },
            success:function(result){
                var re = JSON.parse(result);
                if(re.returnCode=="0"){
                    window.location.href="${path}<%=SysStartupController.URL_GOTO_INDEX_PAGE%>";
                }else{
                    alert(re.msg);
                }
            },
            error:function(request) {      // 设置表单提交出错
                alert(request);  //登录错误提示信息
            }
        });

        return false;
    }
</script>

</body>
</html>