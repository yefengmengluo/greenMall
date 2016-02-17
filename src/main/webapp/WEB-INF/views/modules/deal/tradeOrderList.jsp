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
			<li><input id="orderconfirm" class="btn btn-primary" type="submit" value="全选" onclick=""/>&nbsp;&nbsp;</li>
			<li>&nbsp;<input id="orderconfirm" class="btn btn-primary" type="submit" value="删除" onclick=""/></li>
			<li>					
			<label>订单状态：</label>
			<form:select path="statue" class="input-medium">
			   <option value="">请选择状态</option>
			   <form:option value="0">交易取消</form:option>
			   <form:option value="1">撮合状态</form:option>
			   <form:option value="2">订单确认</form:option>
			   <form:option value="3">等待付款</form:option>
			   <form:option value="4">等待发货</form:option>
			   <form:option value="5">交易完成</form:option>
			</form:select>
			</li>
			<li><label>付款方式：</label>
			  <form:select path="paymenttype" class="input-medium">
					<form:option value="" label="银行转账"/>
					<%-- <form:options items="fdf" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
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
				<th>订单状态</th><th>交易方式</th><th>订单编号</th><th>下单时间  </th><th>买家名称</th><th>卖家名称</th><th>订单总价 </th><th>付款方式</th><th>支付方式  </th><th>具体操作</th>												
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeOrder">
			<tr class="tdcenter">				
				<td style="width:7%">${tradeOrder.remarks}</td>
				<td style="width:7%">
				  <c:if test="${tradeOrder.transactiontype eq '1'}">
				  委托找货
				  </c:if>
				</td>	
				<td style="width:15%">${tradeOrder.orderNumber}</td>		
				<td style="width:12%"><fmt:formatDate value="${tradeOrder.addDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
				<td style="width:8%">${tradeOrder.demandName}</td>
				<td style="width:8%">${tradeOrder.supplyUserName}</td>								
				<%-- <td style="width:7%"><fmt:formatDate value="${tradeOrder.finishTime}" pattern="yyyy-MM-dd"/></td>
				<td style="width:7%"><fmt:formatDate value="${tradeOrder.updateDate}" pattern="yyyy-MM-dd"/></td> --%>
				<td style="width:8%">${tradeOrder.goodsAmount}</td>
				<td style="width:8%">
				  <c:if test="${tradeOrder.paymenttype eq '1' || tradeOrder.paymenttype eq '2'}">
				           先付款后发货
				  </c:if>
				</td>
				<td style="width:8%">
				   <c:if test="${tradeOrder.payment eq '1' || tradeOrder.payment eq '2'}">
				     银行付款
				   </c:if>
				</td>
				<%-- <td style="width:5%">${tradeOrder.receiveCity}</td>
				<td style="width:7%">${tradeOrder.receiveArea}</td>	 --%>			
				<td>
				    <c:choose>
				      <c:when test="${tradeOrder.statue eq '5' or tradeOrder.statue eq '0' }">
				      <a href="${ctx}/deal/tradeOrder/view?orderId=${tradeOrder.id}">详情</a>
				      </c:when>
				      <c:otherwise>
				       <a href="${ctx}/deal/tradeOrder/tradeorderdetails?orderId=${tradeOrder.id}">订单跟踪</a>
				      </c:otherwise>
				    </c:choose>	    			  		     				    				
    				<%-- <a href="##${ctx}/deal/tradeOrder/form?id=${tradeOrder.id}">审核</a> --%>
					<a href="${ctx}/deal/tradeOrder/delete?id=${tradeOrder.id}" onclick="return confirmx('确认要删除该生成&quot;业务订单&quot;成功吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>