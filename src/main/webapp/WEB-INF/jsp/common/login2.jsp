<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录 | TA小助手</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/public/adminlte/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/public/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/public/adminlte/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/public/adminlte/plugins/iCheck/square/blue.css">
</head>
<body class="hold-transition login-page" >
<div id="ie_alert" class="alert alert-danger" style="margin:10px;display:none">系统不支持IE浏览器，请使用更为高级的浏览器，例如火狐、谷歌浏览器或360、搜狗浏览器（极速模式）等更为先进的浏览器！</div>
<div class="login-box">
    <div class="login-logo">
        TA小助手
    </div><!-- /.login-logo -->
    <div class="login-box-body">
        <form id="form_login" action="" method="post">
            <div class="form-group has-feedback">
                <input name="jg_login_user" type="text" class="form-control" placeholder="您的用户名">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input name="jg_login_pwd" type="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                </div><!-- /.col -->
                <div class="col-xs-4">
                    <button id="btn_login" type="submit" class="btn btn-primary btn-block btn-flat" data-loading-text="登录中..."  autocomplete="off">登录</button>
                </div><!-- /.col -->
            </div>
        </form>
    </div><!-- /.login-box-body -->
</div><!-- /.login-box -->

<script type="text/javascript" src="/public/jquery/jquery-1.12.4.min.js"></script>
<script>
    //判断是否是IE浏览器
    if(!-[1,]){
        $("#ie_alert").show();
    }
</script>
<!-- Bootstrap 3.3.5 -->
<script type="text/javascript" src="/public/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script type="text/javascript" src="/public/adminlte/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="/public/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="/public/jquery-validation/dist/localization/messages_zh.min.js"></script>
<script>
    //jquery-validator 初始化，兼容bootstrap 样式
    $.validator.setDefaults({
        errorClass:'help-block',
        highlight:function(e,a){
            $(e).closest(".form-group").addClass("has-error");
        },
        success:function(e,a){
            $(e).closest(".form-group").removeClass("has-error");
            $(e).remove();
        },
        errorPlacement:function(error,element){
            var $group=$(element).closest(".form-group");
            if($group.length>0){
                var $col=$group.children("div").first();
                if($col.length>0&&$col.attr("class").indexOf("col-")>=0){
                    $col.append(error);
                }else{
                    $group.append(error);
                }
            }
        }
    });
</script>
<script src="/public/jquery-form/jquery.form.min.js"></script>
<script>
    //为了解决火狐刷新后退按钮不恢复禁用状态
    $(window).on("beforeunload",function(){

    });
    $(function () {
        $("input[name='jg_login_user']").focus();
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
        //表单字段验证初始化
        var validator=$("#form_login").validate({
            rules:{
                jg_login_user:"required",
                jg_login_pwd:"required",
                jg_login_verify:"required",
            },
            messages:{
                jg_login_user:"用户名不能为空，一般与OA账号一致",
                jg_login_pwd:"密码不能为空",
                jg_login_verify:"请填写右边的验证码",
            },
            submitHandler:function(f){
                $(f).ajaxSubmit({
                    url:"/index.php/Admin2/Login/validateLogin",
                    beforeSubmit:function(){
                        $("#btn_login").button("loading");
                    },
                    success:function(r){
                        if(r.success){
                            window.location.href="/index.php/Admin2/Index/index.html";
                        }else{
                            $("#btn_login").button("reset");
                            $("#img_verify").trigger("click");
                            //console.log($("input[name='jg_login_verify']"));
                            switch(r.data){
                                case 0:
                                    validator.showErrors({"jg_login_verify":r.message});
                                    $("input[name='jg_login_verify']").select().focus();
                                    break;
                                case 1:
                                    validator.showErrors({"jg_login_pwd":r.message});
                                    $("input[name='jg_login_pwd']").select().focus();
                                    break;
                                case 2:
                                case 3:
                                    validator.showErrors({"jg_login_user":r.message});
                                    $("input[name='jg_login_user']").select().focus();
                                    break;
                            }
                        }
                    },
                    dataType:"json"
                });
            }
        });
        //验证图片点击后重新获取验证码
        $("#img_verify").on("click",function(){
            $(this).attr("src","/index.php/Admin/Login/verifyImg?t="+new Date().getTime());
        });
    });
</script>
</body>
</html>