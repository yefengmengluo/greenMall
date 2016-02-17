<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成&quot;业务订单&quot;成功管理</title>
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
<style type="text/css">
.tdcenter td{text-align:center;}
.thcenter th{text-align:center;}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/deal/tradeOrder/list">订单检索</a></li>
		<%-- <shiro:hasPermission name="deal:tradeOrder:edit"><li> <a href="${ctx}/deal/tradeOrder/form">创建订单 <!-- 生成&quot;业务订单&quot;成功添加 --></a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="tradeOrder" action="${ctx}/deal/tradeOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>			
			<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit"
				class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr class="thcenter">
				<th>订单编号</th><th>流水号</th><th>交易方式</th><th>订单状态</th><th>供应商</th><th>采购商</th><th>下单时间</th><th>完成时间</th><th>采购数量</th><th>所在城市</th><th>所在地区</th><th>完成情况</th><th>具体操作</th>												
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeOrder">
			<tr class="tdcenter">
				<td style="width:7%">${tradeOrder.orderNumber}</td>
				<td style="width:7%">${tradeOrder.transactiontype}</td>
				<td style="width:7%">${tradeOrder.remarks}</td>
				<td style="width:5%">${tradeOrder.supplyUserName}</td>
				<td style="width:5%">${tradeOrder.demandName}</td>
				<td style="width:7%"><fmt:formatDate value="${tradeOrder.addDate}" pattern="yyyy-MM-dd"/></td>
				<td style="width:7%"><fmt:formatDate value="${tradeOrder.finishTime}" pattern="yyyy-MM-dd"/></td>
				<td style="width:5%">${tradeOrder.goodsAmount}</td>
				<td style="width:5%">${tradeOrder.receiveCity}</td>
				<td style="width:7%">${tradeOrder.receiveArea}</td>				
				<td>
				    <%-- <c:if test="${tradeOrder.completestatus ne '已通过' && tradeOrder.completestatus ne '交易完成'}"> --%>
				        <a href="${ctx}/deal/tradeOrder/form?id=${tradeOrder.id}">订单跟踪</a>
				    <%-- </c:if> --%>				    			  		     				
    				<a href="${ctx}/deal/tradeOrder/view?id=${tradeOrder.id}">详情</a>
    				<a href="##<%-- ${ctx}/deal/tradeOrder/form?id=${tradeOrder.id} --%>">审核</a>
					<a href="${ctx}/deal/tradeOrder/delete?id=${tradeOrder.id}" onclick="return confirmx('确认要删除该生成&quot;业务订单&quot;成功吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>