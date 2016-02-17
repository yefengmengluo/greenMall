<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存登陆信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/security/securityUserToken/">保存登陆信息列表</a></li>
		<shiro:hasPermission name="security:securityUserToken:edit"><li><a href="${ctx}/security/securityUserToken/form">保存登陆信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="securityUserToken" action="${ctx}/security/securityUserToken/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>信息编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>关联用户：</label>
				<form:input path="uniqueUser" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>登陆类型：</label>
				<form:input path="loginType" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="security:securityUserToken:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="securityUserToken">
			<tr>
				<td><a href="${ctx}/security/securityUserToken/form?id=${securityUserToken.id}">
					<fmt:formatDate value="${securityUserToken.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${securityUserToken.remarks}
				</td>
				<shiro:hasPermission name="security:securityUserToken:edit"><td>
    				<a href="${ctx}/security/securityUserToken/form?id=${securityUserToken.id}">修改</a>
					<a href="${ctx}/security/securityUserToken/delete?id=${securityUserToken.id}" onclick="return confirmx('确认要删除该保存登陆信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>