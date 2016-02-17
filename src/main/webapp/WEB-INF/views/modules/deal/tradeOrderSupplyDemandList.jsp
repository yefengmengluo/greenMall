<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成&quot;供求信息&quot;成功管理</title>
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
		<li class="active"><a href="${ctx}/deal/tradeOrderSupplyDemand/">生成&quot;供求信息&quot;成功列表</a></li>
		<shiro:hasPermission name="deal:tradeOrderSupplyDemand:edit"><li><a href="${ctx}/deal/tradeOrderSupplyDemand/form">生成&quot;供求信息&quot;成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tradeOrderSupplyDemand" action="${ctx}/deal/tradeOrderSupplyDemand/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="deal:tradeOrderSupplyDemand:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeOrderSupplyDemand">
			<tr>
				<shiro:hasPermission name="deal:tradeOrderSupplyDemand:edit"><td>
    				<a href="${ctx}/deal/tradeOrderSupplyDemand/form?id=${tradeOrderSupplyDemand.id}">修改</a>
					<a href="${ctx}/deal/tradeOrderSupplyDemand/delete?id=${tradeOrderSupplyDemand.id}" onclick="return confirmx('确认要删除该生成&quot;供求信息&quot;成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>