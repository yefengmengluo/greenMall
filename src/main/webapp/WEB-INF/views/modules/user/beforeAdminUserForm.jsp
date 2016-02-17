<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改会员</title>
	<meta name="decorator" content="default"/>
	  <script type="text/javascript">
  		$(function(){
  			var message='${message}';
  			if(message!=""){
  				$("#contentTable").before("<div id='messageBox' class='alert alert-error'><button data-dismiss='alert' class='close'>×</button>"+message+"</div>");
  			}
  		});
  		function adminPerfectUser(){
  			self.location.href="${ctx}/user/userAdmin/perfectUserOrganization?userId="+$("#userId").val();
  		}
	  </script>
</head>
<body>
	<form:form id="userForm" action="${ctx }/user/userAdmin/updateUser" method="post" class="breadcrumb form-search">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<input type="hidden" name="id" value="${user.id }" id="userId">
			<tr>
				<td>姓名</td>
				<td>
					<input type="text" name="name" value="${user.name }">
				</td>
			</tr>
			<tr>
				<td>手机号</td>
				<td>
					<input type="text" name="mobile" value="${user.mobile }">
				</td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<input type="text" name="password"><span style="color: red">*不修改则留空</span>
				</td>
			</tr>
			<tr>
				<td>座机号码</td>
				<td>
					<input type="text" name="phone" value="${user.phone }">
				</td>
			</tr>
			<tr>
				<td>电子邮箱</td>
				<td>
					<input type="text" name="email" value="${user.email }">
				</td>
			</tr>
			<tr>
				<td>QQ号码</td>
				<td>
					<input type="text" name="qq" value="${user.qq }">
				</td>
			</tr>
			<tr>
				<td>传真</td>
				<td>
					<input type="text" name="faxNumber" value="${user.faxNumber }">
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td>
				</td>
			<tr>
				<td>注册时间</td>
				<td>
					<fmt:formatDate value="${user.createDate }" pattern='yyyy-MM-dd HH:mm:ss'/>
				</td>
			</tr>
			<tr>
				<td colspan="23">
					<button class="btn btn-primary btn-sm">保存</button>
					<input type="button" class="btn btn-primary" onclick="adminPerfectUser()" value="帮助用户完善公司信息">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>