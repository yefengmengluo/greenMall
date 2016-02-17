<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<jsp:include page="../info/home/uCenterHead.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/user/css/changeMess.css">
<meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
<meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

	<script type="text/javascript">
     	function checkNewPassWord(){
     		if(""==$("#newPassword").val()){
     			$("#newPassword").parent().next().text("密码不能为空");
     			return false;
     		}else{
     			$("#newPassword").parent().next().text("");
     			return true;
     		}
     	}
     	function checkPassword(){
     		if(checkNewPassWord()){
	     		if($("#newPassword").val()!=$("#reNewPassword").val()){
	     			$("#reNewPassword").parent().next().text("两次密码输入不一致");
	     			return false;
	     		}else{
	     			$("#reNewPassword").parent().next().text("");
	     		}
     		}else{
     			return false;
     		}
     	}
     </script>
		<div class="body1-right">
			<div class="body1-right-top">
				<div class="body1-right-top1">
					<img src="${ctxStatic}/modules/user/images/User-avatar.png">		
					<span class="f16 ml10 mt5">修改信息</span>
				</div>
			</div>
			<div class="pt20">
				<form class="form-horizontal" action="${front}/userApi/updatePassword" method="post" onsubmit="return checkPassword()">
					<div class="form-group pr">
						<label  class="col-sm-2 control-label">旧密码：</label>
						<div class="col-sm-7">
							<input type="password" style="display:none">
							<input type="password" class="form-control" name="oldPassword" id="oldPassword" autocomplete="off" placeholder="请输入旧密码">
						</div>

						<div class="col-sm-3">
							${message }
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-sm-2 control-label">新密码：</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" name="newPassword" id="newPassword" autocomplete="off" placeholder="请输入新密码">
						</div>
						<div class="col-sm-3">
							${message1 }
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">确认密码：</label>
						<div class="col-sm-7">
							<input type="password" type="password" class="form-control" onblur="checkPassword()" autocomplete="off" name="reNewPassword" id="reNewPassword" placeholder="请再次输入密码">
						</div>
						<div class="col-sm-3">
							${message2 }
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button class="button2">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 页面内容 结束 -->
	<div class="clear">
	</div>
<jsp:include page="../info/home/uCenterFoot.jsp"></jsp:include>