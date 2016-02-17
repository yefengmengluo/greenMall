<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户发布的信息保存管理</title>
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
		<li class="active"><a href="${ctx}/wechat/mp/mpInformation/">用户发布的信息保存列表</a></li>
		<shiro:hasPermission name="wechat:mp:mpInformation:edit"><li><a href="${ctx}/wechat/mp/mpInformation/form">用户发布的信息保存添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mpInformation" action="${ctx}/wechat/mp/mpInformation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>信息名称：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>信息名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="wechat:mp:mpInformation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mpInformation">
			<tr>
				<td><a href="${ctx}/wechat/mp/mpInformation/form?id=${mpInformation.id}">
					${mpInformation.title}
				</a></td>
				<td>
					<fmt:formatDate value="${mpInformation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mpInformation.remarks}
				</td>
				<shiro:hasPermission name="wechat:mp:mpInformation:edit"><td>
    				<a href="${ctx}/wechat/mp/mpInformation/form?id=${mpInformation.id}">修改</a>
					<a href="${ctx}/wechat/mp/mpInformation/delete?id=${mpInformation.id}" onclick="return confirmx('确认要删除该用户发布的信息保存吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>