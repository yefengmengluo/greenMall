<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存模板成功管理</title>
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
		<li class="active"><a href="${ctx}/msg/msgTemplate/">模板列表</a></li>
		<shiro:hasPermission name="msg:msgTemplate:edit"><li><a href="${ctx}/msg/msgTemplate/form">模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="msgTemplate" action="${ctx}/msg/msgTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名称：</label>
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
				<th>模板名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="msg:msgTemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msgTemplate">
			<tr>
				<td><a href="${ctx}/msg/msgTemplate/form?id=${msgTemplate.id}">
					${msgTemplate.title}
				</a></td>
				<td>
					<fmt:formatDate value="${msgTemplate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>  
					    <c:when test="${fn:length(msgTemplate.remarks) > 20}">  
					        <c:out value="${fn:substring(msgTemplate.remarks, 0, 20)}......" />  
					    </c:when>  
					   <c:otherwise>  
					      <c:out value="${msgTemplate.remarks}" />  
					    </c:otherwise>  
					</c:choose>
				</td>
				<shiro:hasPermission name="msg:msgTemplate:edit"><td>
    				<a href="${ctx}/msg/msgTemplate/form?id=${msgTemplate.id}">修改</a>
					<a href="${ctx}/msg/msgTemplate/delete?id=${msgTemplate.id}" onclick="return confirmx('确认要删除该模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>