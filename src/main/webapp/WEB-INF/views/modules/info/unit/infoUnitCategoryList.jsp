<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>类别单位管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/info/infoUnitCategory?gcategoryId=${infoUnitCategory.gcategoryId}">类别单位列表</a></li>
		<li><a href="${ctx}/info/infoUnitCategory/form?gcategoryId=${infoUnitCategory.gcategoryId}">类别单位添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="infoUnitCategory" action="${ctx}/info/infoUnitCategory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input name="gcategoryId" type="hidden" value="${infoUnitCategory.gcategoryId}">
		名字：<input type="text" name="unitName" value="${infoUnitCategory.unitName}"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		标识：<input type="text" name="unitCode" value="${infoUnitCategory.unitCode}"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		单位说明:
		<select name="statue" style="width:100px">
			<option value="">--请选择--</option>
			<option value="0" <c:if test="${infoUnitCategory.statue==0}">selected</c:if>>可量化</option>
			<option value="-1" <c:if test="${infoUnitCategory.statue==-1}">selected</c:if>>不可量化</option>
		</select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>id</th><th>名字</th><th>标识</th><th>单位说明</th><th>是否是此类型的默认单位</th><th>排序</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="unit">
			<tr>
				<td>${unit.id}</td>
				<td><a href="${ctx}/info/infoUnitCategory/form?id=${unit.id}&gcategoryId=${infoUnitCategory.gcategoryId}">${unit.unitName}</a></td>
				<td>${unit.unitCode}</td>
				<td>
					<c:if test="${unit.statue==0}">
						可量化
					</c:if>
					<c:if test="${unit.statue==-1}">
						不可量化
					</c:if>
				</td>
				<td>
					<c:if test="${unit.isDefault==0}">
						否
					</c:if>
					<c:if test="${unit.isDefault==1}">
						是
					</c:if>
				</td>
				<td>${unit.orderItem}</td>
				<td>
    				<a href="${ctx}/info/infoUnitCategory/form?id=${unit.id}&gcategoryId=${infoUnitCategory.gcategoryId}">修改</a>
					<a href="${ctx}/info/infoUnitCategory/delete?id=${unit.id}&gcategoryId=${infoUnitCategory.gcategoryId}" onclick="return confirmx('确认要删除该单位吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>