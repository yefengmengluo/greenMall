<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发送短信保存管理</title>
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
		<li class="active"><a href="${ctx}/msg/msgSms/">短信列表</a></li>
		<shiro:hasPermission name="msg:msgSms:edit"><li><a href="${ctx}/msg/msgSms/form">短信添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="msgSms" action="${ctx}/msg/msgSms/" method="post" class="breadcrumb form-search">
		<%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
		<%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
		<%--手机号：<input type="text" name="mobile" value="${msgSms.tele }">--%>
		<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>--%>
		<%--<ul class="ul-form">--%>
			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
			<%--<li class="clearfix"></li>--%>
		<%--</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>手机号</th>
				<th>发送成功号码</th>
				<th>内容</th>
				<shiro:hasPermission name="msg:msgSms:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msgSms">
			<tr>
				<td><a href="${ctx}/msg/msgSms/form?id=${msgSms.id}">
					<fmt:formatDate value="${msgSms.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${msgSms.tele}
				</td>
				<td>
					${msgSms.result}
				</td>
				<td>
					${msgSms.msg}
				</td>
				<shiro:hasPermission name="msg:msgSms:edit"><td>
    				<a href="${ctx}/msg/msgSms/form?id=${msgSms.id}">修改</a>
					<a href="${ctx}/msg/msgSms/delete?id=${msgSms.id}" onclick="return confirmx('确认要删除该短信吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>