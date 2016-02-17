<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
    <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
        <meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />

        <link rel="shortcut icon" href="${ctxStatic}/home/images/guoguo/ico1.ico"/>
        <link rel="bookmark" href="${ctxStatic}/home/images/guoguo/ico1.ico"/>
    <title>果果网登陆页</title>

    <!-- Bootstrap core CSS -->
    <link href="${modules}/user/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
        <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="${modules}/user/css/starter-template.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${modules}/user/css/base.css">
	<link rel="stylesheet" type="text/css" href="${modules}/user/css/login.css">
    <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#validateCodeImg').trigger("click");
        	$("input").attr("autocomplete","off");	
            jQuery("#error").hide()
            jQuery("#submit").click(function(e){
                e.preventDefault()
                jQuery.post("${front}/userApi/userLogin",{
                    "loginType":jQuery("#loginType").val(),
                    "validateCode":jQuery("#validateCode").val(),
                    "username":jQuery("#username").val(),
                    "password":jQuery("#password").val()
                },function(data){
                    if(data=="1"){
                        location.href ="${front}/userCenter";
                    }
                    /*if(typeof data =="object"){
                        if(data.go){
                            location.href ="${front}/"+data.go;
                            return;
                        }
                        if(data.data.isCode){
                            jQuery("#validateDiv").show();
                        }else{
                            jQuery("#validateDiv").hide();
                        }

                        jQuery("#message").html(data.data.message);
                        jQuery("#error").show();
                        jQuery("#error").fadeOut(2000);

                    }else{
                        jQuery("#message").html("服务器返回异常");
                        jQuery("#error").show();
                        jQuery("#error").fadeOut(2000);
                    }*/

                })
            })
        })

    </script>
  </head>

<body>
	<!-- 页头 开始 -->
    <%@include file="/WEB-INF/views/include/top.jsp" %>
    <!-- 页头 结束 -->
    <!-- 页头logo 开始 -->    
    <div class="container">
	    <div class="row h105 body ">
     		<div class="col-md-6 "><img src="${modules}/user/images/header_logo.png" alt="picture miss" class="head_bottom_content_img"></div>
     		<div class="col-md-6 mt30">
				
					
						<img src="${modules}/user/images/Dark-ray.png" class="fl">
						<dl class="fl ml8 mt5 mr40">
							<dt class="f16 fb">更快捷</dt>
							<dd class="f12">帮您快速找果果</dd>
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
    <div class="container-fluid  bg2 ">
    	<div class="row body h490 ">
    		<div class="col-md-4 col-md-offset-8 ">
    			<div class="bg3 h420 mt35 w300 shw1 br2">
    				<div class="w300 h80 bg3 shw1 p1 br2"> 
    					<dl class="ml20 ">                       
    						<dt class="fb f20 color1 pt17">登录果果网</dt>
    						<dd class="color4 f12 ml100 mt5">还没有果果网账号？<a href="${front}/userApi/register" class="color5">30秒注册</a></dd>
    					</dl>
    				</div>
    				<form class="form">
                        <input type="hidden" id="loginType" name="loginType" value="pcWeb">
    					<div class="form-group">
    						<div class="input-group">
    							<div class="input-group-addon"><img src="${modules}/user/images/User-avatar.png"></div>
    							<input type="text" id="username" name="username" class="form-control" placeholder="请输入手机号" ></div>
    					</div>
    					<div class="form-group">
    						<div class="input-group">
    							<div class="input-group-addon"><img src="${modules}/user/images/Closed-Lock.png"></div>
    							<input type="password" type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
                            </div>
    					</div>
                        <div class="form-group" id="validateDiv" style="display: none">
                            <div class="input-group">
                                <%--<c:if test="${isValidateCodeLogin}">--%>
                                    <div class="validateCode">
                                    <label class="input-label mid" for="validateCode">验证码</label>
                                        <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
                                    </div>
                                <%--</c:if>--%>
                            </div>
                        </div>
    					<%--<div class="color4 f12 fr mr20 mt5"><a href="##">忘记密码?</a></div>--%>
    					<button id="submit" type="submit" class="btn btn-default" style="font-size: 16px;color: white">提交</button>

    					<div id="error" class="color4 f12   mt30 shoujiwai" style="display: none;">
    						<span id="message" class="mt15 ml5 shouji" >手机号和密码不正确</span>
    					</div>
                    </form>
    			</div>
    		</div>
    	</div>
    </div>
    <!-- 页面主体 结束 -->
    <!-- 页尾 开始 -->
    <%@include file="/WEB-INF/views/include/bottom.jsp" %>
    <!-- 页尾 结束 -->
</body>
</html>
