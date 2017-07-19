<%@ page import="com.jack.doormis.core.user.UserStateEnum" %>
<%@ page import="com.jack.doormis.core.user.pojo.User" %>
<%@ page import="com.jack.doormis.common.web.JspKeys" %>
<%@ page import="com.jack.doormis.core.user.RoleEnum" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>修改用户信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%
        String path = request.getContextPath();
        path = "/".equals(path) ? "" : path;
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

        <%
            User user = (User) request.getAttribute(JspKeys.JspParam_UserInfo);
        %>
        <div class="span9">
            <div class="row-fluid">
                <div class="page-header">
                    <h1>修改用户信息</h1>
                </div>
                <form class="form-horizontal">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label" for="userName">用户名</label>
                            <div class="controls">
                                <input type="text" class="input-xlarge" id="userName" value="${userInfo.userName}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="realName">姓名</label>
                            <div class="controls">
                                <input type="text" class="input-xlarge" id="realName" value="${userInfo.realName}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="pwd">密码</label>
                            <div class="controls">
                                <input type="text" class="input-xlarge" id="pwd" value="${userInfo.pwd}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="role">角色</label>
                            <div class="controls">
                                <select id="role">
                                    <option value="EMPL" <%if(RoleEnum.EMPL.toString().equals(user.getRole())){%>selected = "selected"<%}%>>操作员</option>
                                    <option value="ADMIN" <%if(RoleEnum.ADMIN.toString().equals(user.getRole())){%>selected = "selected"<%}%>>管理员</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="phone">电话</label>
                            <div class="controls">
                                <input type="text" class="input-xlarge" id="phone" value="${userInfo.phone}"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="stateId">帐户可用</label>
                            <div class="controls">
                                <input type="checkbox" id="stateId" <%if (user.getStateId()==UserStateEnum.ENABLED.getStateId()){%> checked<%}%>/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="remark">备注</label>
                            <div class="controls">
                                <input type="text" class="input-xlarge" id="remark" value="${userInfo.remark}"/>
                            </div>
                        </div>
                        <div class="form-actions">
                            <input type="button" class="btn btn-success btn-large"  onclick="add()" value="保  存"/>
                            <a class="btn" href="${path}/user/goto_main_page">Cancel</a>
                        </div>
                    </fieldset>
                </form>
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

<script type="text/javascript">
    function add(){
        var userName = $.trim($("#userName").val());
        if(userName == ""){
            alert("请输入用户名");
            return;
        }

        var realName = $.trim($("#realName").val());
        if(realName == ""){
            alert("请输入姓名");
            return;
        }

        var pwd = $.trim($("#pwd").val());
        if(pwd == ""){
            alert("请输入密码");
            return;
        }

        var role = $.trim($("#role").val());
        if(role == ""){
            alert("请选择角色");
            return;
        }

        var phone = $.trim($("#phone").val());
        if(phone == ""){
            alert("请输入电话");
            return;
        }

        var stateId;
        if($('#stateId').is(':checked')){
            stateId = <%=UserStateEnum.ENABLED.getStateId()%>;
        } else {
            stateId = <%=UserStateEnum.DISABLED.getStateId()%>;
        }

        var remark = $.trim($("#remark").val());

        $.ajax({
            url:"${path}/user/update",
            type:"post",
            data:{
                id:${userInfo.id},
                userName:userName,
                realName:realName,
                pwd:pwd,
                role:role,
                phone:phone,
                stateId:stateId,
                remark:remark
            },
            success:function(result){
                var re = JSON.parse(result);
                if(re.returnCode=="0"){
                    window.location.href="${path}/user/goto_main_page";
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
