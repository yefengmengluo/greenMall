<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>属性规范列表</title>
<meta name="decorator" content="default"/>
<script type="text/javascript">
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="supplyDemandRelationMatching" action="${ctx}/match/matchRelationList" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form> 
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td>规则名</td>
			<td>规则匹配详情</td>
			<td>使用规则用户</td>
			<td>排序</td>
			<td>备注</td>
			<td>是否启用</td>
			<td>是否匹配委托</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.list }" var="attr">
			<tr>
				<td>${attr.relationName }</td>
				<td>${attr.relationRemark }</td>
				<td>
					<c:if test="${attr.matchingObject=='demand' }">采购商</c:if>
					<c:if test="${attr.matchingObject=='supply' }">供应商</c:if>
				</td>
				<td>${attr.sortOrder }</td>
				<td>${attr.remarks }</td>
				<td>
					<c:if test="${attr.ifMatching==1 }">是</c:if>
					<c:if test="${attr.ifMatching==0 }">否</c:if>
				</td>
				<td>
					<c:if test="${attr.isEntrust==1 }">是</c:if>
					<c:if test="${attr.isEntrust==0 }">否</c:if>
				</td>
				<td>
					<a href="${ctx }/match/toUpdateMatchRelation?id=${attr.id }">修改</a>
					<a href="${ctx }/match/deleteMatchRelation?id=${attr.id }">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
		<div class="pagination">${page}</div>
</body>
</html>