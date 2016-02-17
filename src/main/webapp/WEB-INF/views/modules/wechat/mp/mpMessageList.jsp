<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存消息成功管理</title>
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
		<li class="active"><a href="${ctx}/wechat/mp/mpMessage/">保存消息成功列表</a></li>
		<shiro:hasPermission name="wechat:mp:mpMessage:edit"><li><a href="${ctx}/wechat/mp/mpMessage/form">保存消息成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mpMessage" action="${ctx}/wechat/mp/mpMessage/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="wechat:mp:mpMessage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mpMessage">
			<tr>
				<td><a href="${ctx}/wechat/mp/mpMessage/form?id=${mpMessage.id}">
					${mpMessage.title}
				</a></td>
				<td>
					<fmt:formatDate value="${mpMessage.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mpMessage.remarks}
				</td>
				<shiro:hasPermission name="wechat:mp:mpMessage:edit"><td>
    				<a href="${ctx}/wechat/mp/mpMessage/form?id=${mpMessage.id}">修改</a>
					<a href="${ctx}/wechat/mp/mpMessage/delete?id=${mpMessage.id}" onclick="return confirmx('确认要删除该保存消息成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>