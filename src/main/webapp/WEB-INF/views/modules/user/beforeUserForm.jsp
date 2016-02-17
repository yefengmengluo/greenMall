<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<jsp:include page="../info/home/uCenterHead.jsp"></jsp:include>
    <!-- Custom styles for this template -->
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-jbox/2.3/Skins2/Green/jbox.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/user/css/accountMess.css">
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>	
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.js"></script>
<meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
<meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />


<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

	<script type="text/javascript">
		$(function(){
			$("input").attr("autocomplete","off");	
			$("#userForm").validate({
	            rules:{
		        	/* 	loginName:{
		        			required:true,
		                   	username:true
		        		}, */
		        		name:{
		         			required:true,
		                    	realName:true
		         		},
		        		phone:{
		        			simplePhone:true
		        		},
		        		email:{
		        			email:true
		        		},
		        		qq:{
		        			qq:true
		        		},
		        		faxNumber:{
		        			fax:true
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
		        		phone:{
		        			simplePhone:"请正确填写您的电话号码"
		        		},
		        		email:{
		        			email:"请输入正确的电子邮件地址"
		        		},
		        		qqNumber:{
		        			qq:"请输入正确的QQ号码"
		        		},
		        		faxNumber:{
		        			fax:"请输入正确的传真号码"
		        		}
		        	}
	         });
		});
	</script>
    <!-- 页面右侧 -->
		<div class="body1-right">
			<div class="body1-right-top">
				<div class="body1-right-top1">
					<img src="${ctxStatic}/modules/user/images/User-avatar.png">		
					<span class="f16 ml10 mt5">账户信息<a href="${pageContext.request.contextPath}${fns:getFrontPath()}/userApi/perfectUserOrganization" class="f12 color1 ml10" >(申请正式会员，完善更多会员信息)</a></span>
				</div>
			</div>
			<sys:message content="${message }"></sys:message>
			<div class="pt20">
				<form:form class="form-horizontal" action="${front}/userApi/updateUser" method="post" id="userForm">
				<%-- 	<div class="form-group pr">
						<label  class="col-sm-2 control-label">用户名：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="loginName" name="loginName" value="${user.loginName }" placeholder="请输入用户名">
						</div>
					</div> --%>
							<input type="hidden" name="id" value="${user.id }">
        					<input type="hidden" name="organizationId" value="${organization.id }">
        			<div class="form-group">
						<label  class="col-sm-2 control-label"><span class="color6"></span>姓名：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="" value="${user.name }" name="name" placeholder="请输入您的姓名">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">手机号码：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" readonly="readonly" id="mobile" name="mobile" value="${user.mobile }" placeholder="请输入手机号码">
						</div>
						<!-- <div class="col-sm-3">
							<a href="#">点击修改手机号</a>
						</div> -->
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">固话：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="phone"  name="phone" value="${user.phone }" placeholder="请输入固话">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">电子邮箱：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="email"  name="email" value="${user.email }" placeholder="请输入电子邮箱">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">Q Q：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="qq" name="qq" value="${user.qq }" placeholder="请输入QQ号">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">传真：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="faxNumber" name="faxNumber" value="${user.faxNumber }" placeholder="请输入传真号">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button class="button2">修改</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- 页面内容 结束 -->
	<div class="clear"></div>
<jsp:include page="../info/home/uCenterFoot.jsp"></jsp:include>