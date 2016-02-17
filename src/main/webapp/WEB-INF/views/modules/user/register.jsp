<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
    <meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${ctxStatic}/home/images/guoguo/ico1.ico"/>
    <link rel="bookmark" href="${ctxStatic}/home/images/guoguo/ico1.ico"/>
    <!-- Bootstrap core CSS -->
    <link href="${modules}/user/css/bootstrap.min.css" rel="stylesheet">

    <%--<script src="${modules}/user/js/respond.min.js"></script>--%>
    <%--<script src="${modules}/user/js/html5.js"></script>--%>

    <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="${modules}/user/css/starter-template.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${modules}/user/css/zhuce.css">
    <link rel="stylesheet" type="text/css" href="${modules}/user/css/base.css">
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>	
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js"></script>
     <script type="text/javascript">
	    $(function(){
			$("input").attr("autocomplete","off");
			$("input[class='form-control']").css("display","inline");
			$("#inputForm").validate({
	        	submitHandler : function(form) {
	        		if(jQuery("#checkout")[0].checked==false){
	                    showMessage("请阅读用户协议，并确认同意");
	                    return;
	                }
	                var param = {}
	                jQuery.each(jQuery("form input"),function(i,k){
	                    param[k.name] = k.value
	                })
	                if(param["confirmNewPassword"] && (param["confirmNewPassword"] == param["newPassword"])){
	                    jQuery.post("${front}/userApi/user",param,function(data){
                            //任何返回均更新验证码
                            jQuery('#validateCodeImg').trigger("click");
	                        if(typeof data =="object"){
	                            if(data.status<=0){
	                                showMessage(data.message)
	                            }else{
	                                showMessage(null)
	                                location.href ="${front}/userApi/organizationForm";
	                                return;
	                            }
	                        }else{
	                            showMessage("服务器返回异常")
	                        }
	                    })
	                }else{
	                	if($("#newPassword").val()==""){
	                        showMessage("请先输入密码")
	                    }else{
		                    showMessage("两次密码不一致")
	                    }
	                }
	            },
	        	rules:{
	        	/* 	loginName:{
	        			required:true,
	                   	username:true
	        		}, */
	        		name:{
	         			required:true,
	                    realName:true
	         		},
	         		mobile:{
	         			required:true,
	         			mobile:true
	         		}
	        	},
	        	messages:{
	        		/* loginName:{
	        			required:"请输入用户名",
	                   	username:"请输入以字母开头的4-10位用户名,允许字母数字和下划线"
	        		}, */
	        		name:{
	         			required:"请输入姓名"
	         		},
	         		mobile:{
	         			required:"请输入手机号码",
	         			mobile:"请输入正确的手机号码"
	         		},
	         		confirmNewPassword:{
	         			equalTo:"两次密码输入不一致"
	         		},
	         		newPassword:{
	         			rangelength:"请输入6-18位密码"        			
	        		}
	        	}
		});
        function showMessage(message){
            if(message){
                jQuery("#error").html(message)
                jQuery("#error").show();
                jQuery("#error").fadeOut(3000);
            }else{
                jQuery("#error").hide();
            }
        }
       /*  jQuery(document).ready(function () {

            jQuery("#submit").click(function(e){
                e.preventDefault()

                if(jQuery("#checkout")[0].checked==false){
                    showMessage("请阅读用户协议，并确认同意");
                    return;
                }
                var param = {}
                jQuery.each(jQuery("form input"),function(i,k){
                    param[k.name] = k.value
                })

                if(param["confirmNewPassword"] && (param["confirmNewPassword"] == param["newPassword"])){
                    jQuery.post("${front}/userApi/user",param,function(data){
                        if(typeof data =="object"){
                            if(data.status<=0){
                                showMessage(data.message)
                            }else{
                                showMessage(null)
                                location.href ="${front}/userApi/organizationForm";
                                return;
                            }
                        }else{
                            showMessage("服务器返回异常")
                        }

                    })
                }else{
                	if($("#newPassword").val()==""){
                        showMessage("请先输入密码")
                    }else{
	                    showMessage("两次密码不一致")
                    }
                }

            }) */
            //发送短信验证码
            $("#sendsmsCode").click(function () {
                $.get("${front}/userApi/smsCode", {
                            "validateCode": $("#validateCode").val(),
                            "mobile": $("#mobile").val(),
                            "name": $("#name").val()
                        },
                        function (data) {
                            if (data.status == '-13') {
                                timer();
                            }
                            $("#error").html(data.message)
                            $("#error").show();
                            $("#error").fadeOut(30000);
                        }, "json");
            });
        });
       function timer(){
    	   	var InterValObj; //timer变量，控制时间
			var count = 60; //间隔函数，1秒执行
			var curCount;//当前剩余秒数
			curCount = count;
			$("#sendsmsCode").attr("disabled", "true");
			$("#sendsmsCode").val( + curCount + "秒后重新获取验证码");
			InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//timer处理函数
	        function SetRemainTime() {
	        	if (curCount == 0) {                
	        		window.clearInterval(InterValObj);//停止计时器
	        		$("#sendsmsCode").removeAttr("disabled");//启用按钮
	        		$("#sendsmsCode").val("重新发送验证码");
	        	}
	        	else {
	        		curCount--;
	        		$("#sendsmsCode").val( + curCount + "秒后重新获取验证码");
	        	}
	        }
       }
    </script>
</head>
<body>
<!-- 页头 开始 -->
<%@include file="/WEB-INF/views/include/top.jsp" %>
<!-- 页头 结束 -->
<!-- 页头logo 开始 -->
<div class="container">
    <div class="row h105 body ">
        <div class="col-md-6 ">
            <img src="${modules}/user/images/header_logo.png" alt="picture miss" class="head_bottom_content_img"></div>
        <div class="col-md-6 mt30">

            <img src="${modules}/user/images/Dark-ray.png" class="fl">
            <dl class="fl ml8 mt5 mr40">
                <dt class="f16 fb">更快捷</dt>
                <dd class="f12">帮您快速找水果</dd>
            </dl>

            <img src="${modules}/user/images/Security-Shield.png" class="fl">
            <dl class="fl ml8 mt5 mr40">
                <dt class="f16 fb">更安全</dt>
                <dd class="f12">安全可靠一站式服务</dd>
            </dl>

            <img src="${modules}/user/images/Bag-with-dollar-sign.png" class="fl">
            <dl class="fl ml8 mt5 mr40">
                <dt class="f16 fb">更便宜</dt>
                <dd class="f12">比一比更便宜</dd>
            </dl>

        </div>
    </div>
</div>

<!-- 页头logo 结束 -->
<!-- 页面主体 开始 -->
<div style="position: relative;">
    <img src="${modules}/user/images/zhuce-image/nav1.png" class="jindutiao">
</div>

<div class="body">

    <!--
    <form:form id="inputForm" modelAttribute="frontUser" action="${front}/userApi/User" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>
        <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
        <form:input path="mobile" htmlEscape="false" maxlength="100"/>
        <c:if test="${empty user.mobile}"><span class="help-inline"><font color="red">*</font> </span></c:if>
        <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
        <input id="smsCode" name="smsCode" type="text" value="" maxlength="50" minlength="3"/>
        <input id="sendsmsCode" class="btn btn-primary" type="button" value="发送短信验证码"/>
        <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3"
               class="${empty user.id?'required':''}"/>
        <c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
        <c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
        <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50"
               minlength="3" equalTo="#newPassword"/>
        <c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </form:form>
    -->


    <form:form id="inputForm" modelAttribute="frontUser" method="post" class="form-horizontal col-sm-7 text-left">
        <form:hidden path="id"/>
        <%--<sys:message content="${message}"/>--%>
        <div class="form-group ">
            <label for="name" class="col-sm-2 control-label">姓名：</label>
            <div class="col-sm-10 fl">
                <form:input path="name" htmlEscape="false" maxlength="50" class="form-control"  placeholder="请输入姓名"/>
                <%--<input type="name" class="form-control" id="" placeholder="请输入姓名">--%>
            </div>
            <%--<div class="fl" style="margin-top:-25px;margin-left: 510px;">--%>
                <%--<img src="${modules}/user/images/zhuce-image/chahao.png">--%>
                <%--<span>请输入真实姓名</span><br>--%>
            <%--</div>--%>
        </div>

        <div class="form-group">
            <label for="mobile" class="col-sm-2 control-label">手机号码:</label>
            <div class="col-sm-10 fl">
                <form:input path="mobile" htmlEscape="false" maxlength="100" class="form-control" placeholder="请输入手机号"/>
                <%--<input type="phone" class="form-control" id="" placeholder="请输入手机号">--%>
            </div>
            <%--<div class="fl" style="margin-top:-25px;margin-left: 510px;">--%>
                <%--<img src="${modules}/user/images/zhuce-image/chahao.png">--%>
                <%--<span>请输入正确的手机号</span>--%>
            <%--</div>--%>
        </div>
        <div class="form-group" >
            <label for="${name}" class="col-sm-2 control-label ">图文验证码:</label>
            <div class="col-sm-5">
                <c:set var="name" value="validateCode"/>
                <input placeholder="请输入验证码" type="text" id="${name}" name="${name}" maxlength="5" class="form-control1 " placeholder="请输入验证码" />
                <a id="${name}Refresh" href="javascript:" onclick="jQuery('${name}Img').attr('src','${pageContext.request.contextPath}/servlet/validateCodeServlet?'+new Date().getTime());"
                   style="display: none">看不清</a>
            </div>
            <img id="${name}Img" src="${pageContext.request.contextPath}/servlet/validateCodeServlet" onclick="jQuery('#${name}Refresh').click();" class="imgsize"/>
            <%--<div class="fl" style="margin-top:-25px;margin-left: 510px;">--%>
                <%--<img src="${modules}/user/images/zhuce-image/gantaihao.png">--%>
                <%--<span>验证码有误</span>--%>
            <%--</div>--%>

        </div>
        <div class="form-group">
            <label for="smsCode" class="col-sm-2 control-label">短信验证码:</label>
            <div class="col-sm-5">
            	<input type="text" hidden="hidden">
                <input id="smsCode" name="smsCode" autocomplete="off" type="text"  placeholder="请输入短信验证码"  class="form-control1"/>
                <%--<input type="hidden" id="sendsmsCode" class="btn btn-primary" type="button" value="发送短信验证码"/>--%>
                <%--<input type="email" class="form-control1" id="" >--%>
            </div>
            <input type="button" class="imgsize" id="sendsmsCode" value="点击获取验证码"/>   
            <%--<div class="fl" style="margin-top:-25px;margin-left: 510px;">--%>
                <%--<img src="${modules}/user/images/zhuce-image/gantaihao.png">--%>
                <%--<span>验证码错误或者已失效</span>--%>
            <%--</div>--%>
        </div>

        <div class="form-group">
            <label for="newPassword" class="col-sm-2 control-label">密码:</label>
            <div class="col-sm-10 fl">
            	<input type="password" hidden="hidden">
                <input id="newPassword" name="newPassword" type="password" rangelength="6,18" class="form-control" placeholder="6-18位密码" />
            </div>
        </div>

        <div class="form-group ">
            <label for="confirmNewPassword" class="col-sm-2 control-label fl">确认密码:</label>
            <div class="col-sm-10">
            	<input type="password" hidden="hidden">
                <input id="confirmNewPassword" name="confirmNewPassword" type="password" maxlength="50" equalTo="#newPassword" class="form-control" placeholder="6-18位密码"/>
                <%--<input type="password" class="form-control" id="" placeholder="6-18位密码">--%>
            </div>
            <%--<div class="fl" style="margin-top:-25px;margin-left: 510px;">--%>
                <%--<img src="${modules}/user/images/zhuce-image/chahao.png">--%>
                <%--<span>两次输入的密码不一致</span>--%>
            <%--</div>--%>
        </div>

        <div id="error" class="alert alert-danger alert-dismissable" style="display: none"></div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input id="checkout" type="checkbox" value="true"/>我已阅读并同意<a onclick="javascript:window.open('${front}/userApi/userAgreement','','width=800,height=300');">《果果网用户注册协议》</a>
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group ">
            <div class="col-sm-offset-2 col-sm-10">
                <input id="submit" class="btn btn-default" type="submit" value="同意协议并提交" style="color: white"/>
            </div>
        </div>
    </form:form>

    <div class="col-sm-4  fr">
        <div class="zhuce-left">
            <dl class="mt20 ml20">
                <dt class="f12 color3">已有<span class="color5">果果网</span>账号？</dt>
                <dd class="mt20 12 color3">成为果果网会员，可以免费上传水果供应，查看采购需求，<br>更可免费快速帮助您找到最便宜的水果。</dd>
            </dl>
            <button class="btn btn-default ml20 bg2" type="submit" onclick="javascript:window.location.href='${front}/userApi/loginForm'" style="color: white">立刻登录</button>
            <div class="ml20 line"></div>
            <dl class="mt17 ml20">
                <dt>果果网客服热线</dt> 
                <dd class="mt10">
                    <img src="${modules}/user/images/zhuce-image/Call-Button.png" class="mr10">
                    <span class="f12 color3">400-876-2500<small>（工作日时间09:00-18:00）</small></span>

                </dd>
            </dl>
        </div>
    </div>
</div>
<div class="cb"></div>

<!-- 页面主体 结束 -->
<!-- 页尾 开始 -->
<%@include file="/WEB-INF/views/include/bottom.jsp" %>
<!-- 页尾 结束 -->
</body>
</html>