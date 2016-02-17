<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存用户角色管理</title>
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
		<li class="active"><a href="${ctx}/security/securityRole/">保存用户角色列表</a></li>
		<shiro:hasPermission name="security:securityRole:edit"><li><a href="${ctx}/security/securityRole/form">保存用户角色添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="securityRole" action="${ctx}/security/securityRole/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>角色名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>英文名称：</label>
				<form:input path="enname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>角色类型：</label>
				<form:input path="roleType" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>数据范围：</label>
				<form:input path="dataScope" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>是否系统数据：</label>
				<form:input path="isSys" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>角色名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="security:securityRole:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="securityRole">
			<tr>
				<td><a href="${ctx}/security/securityRole/form?id=${securityRole.id}">
					${securityRole.name}
				</a></td>
				<td>
					<fmt:formatDate value="${securityRole.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${securityRole.remarks}
				</td>
				<shiro:hasPermission name="security:securityRole:edit"><td>
    				<a href="${ctx}/security/securityRole/form?id=${securityRole.id}">修改</a>
					<a href="${ctx}/security/securityRole/delete?id=${securityRole.id}" onclick="return confirmx('确认要删除该保存用户角色吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>