<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>客户管理系统</title>
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

    <link rel="stylesheet" type="text/css" href="${path}/css/login/bootstrap_3_3_6.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/login/htmleaf-demo.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/login/signin.css">
</head>
<body>
<div class="htmleaf-container">
    <header class="htmleaf-header">
        <h1>客户管理系统</h1>
    </header>
    <div class="signin">
        <div class="signin-head"><img src="${path}/img/test/head_120.png" alt="" class="img-circle"></div>
        <form class="form-signin">
            <input id="txtUserName" type="text" class="form-control" placeholder="用户名" required autofocus value=""/>
            <input id="txtPassword" type="password" class="form-control" placeholder="密码" required value="" />
            <input type="button" class="btn btn-lg btn-warning btn-block"  onclick="login()" value="登录"></input>
        </form>
    </div>
</div>


<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/bootstrap.min.js"></script>

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
            url:"${path}/login",
            type:"post",
            data:{
                userName:userName,
                password:password
            },
            success:function(result){
                var re = JSON.parse(result);
                if(re.returnCode=="0"){
                    window.location.href="${path}/client/goto_main_page";
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