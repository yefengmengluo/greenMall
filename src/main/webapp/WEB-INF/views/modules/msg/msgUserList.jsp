<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存用户配置成功管理</title>
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
		<li class="active"><a href="${ctx}/msg/msgUser/">用户配置列表</a></li>
		<shiro:hasPermission name="msg:msgUser:edit"><li><a href="${ctx}/msg/msgUser/form">用户配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="msgUser" action="${ctx}/msg/msgUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <ul class="ul-form">
			<li class="btns">平台id：<form:input path="plateformid" htmlEscape="false" maxlength="64" class="input-xlarge "/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="msg:msgUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msgUser">
			<tr>
				<td><a href="${ctx}/msg/msgUser/form?id=${msgUser.id}">
					<fmt:formatDate value="${msgUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${msgUser.remarks}
				</td>
				<shiro:hasPermission name="msg:msgUser:edit"><td>
    				<a href="${ctx}/msg/msgUser/form?id=${msgUser.id}">修改</a>
					<a href="${ctx}/msg/msgUser/delete?id=${msgUser.id}" onclick="return confirmx('确认要删除该用户配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>