<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户关系表管理</title>
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
		<li class="active"><a href="${ctx}/user/userRelation/">用户关系列表</a></li>
		<shiro:hasPermission name="user:userRelation:edit"><li><a href="${ctx}/user/userRelation/form">用户关系表成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userRelation" action="${ctx}/user/userRelation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户：</label>
				<sys:treeselect id="theUser" name="theUser" value="${userRelation.theUser}" labelName="" labelValue="${userRelation.otherUser}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>关联用户：</label>
				<sys:treeselect id="otherUser" name="otherUser" value="${userRelation.otherUser}" labelName="" labelValue="${userRelation.theUser}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>关联类型：</label>
				<form:radiobuttons path="relationType" items="${fns:getDictList('user_relation_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户</th>
				<th>关联用户</th>
				<th>关联类型</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="user:userRelation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userRelation">
			<tr>
				<td><a href="${ctx}/user/userRelation/form?id=${userRelation.id}">
					${userRelation.theUser}
				</a></td>
				<td>
					${userRelation.otherUser}
				</td>
				<td>
					${fns:getDictLabel(userRelation.relationType, 'user_relation_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${userRelation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${userRelation.remarks}
				</td>
				<shiro:hasPermission name="user:userRelation:edit"><td>
    				<a href="${ctx}/user/userRelation/form?id=${userRelation.id}">修改</a>
					<a href="${ctx}/user/userRelation/delete?id=${userRelation.id}" onclick="return confirmx('确认要删除该用户关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>