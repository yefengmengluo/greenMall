<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商机信息处理</title>
	<title>生成&quot;业务订单&quot;成功管理</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<meta name="decorator" content="default"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function createOrder(t){
			$.ajax({
				   type: "POST",
				   url: "${ctx}/deal/tradeOrder/create",
				   data: "supplyid="+$("#supplyid"+t).val()+"&demandid="+$("#demandid"+t).val(),
				   dataType:"json",
				   success: function(msg){
					   $("#alert_d .alert-info").remove();
				       $("#alert_d").append("<div class=\"alert alert-info\">"+msg.success+"</div>");
				   },
				   error:function(msg){
					   $("#alert_d .alert-info").remove();
				       $("#alert_d").append("<div class=\"alert alert-info\">"+"请求失败，请检查网络。"+"</div>");
				   }
				});
			//window.location.href="${ctx}/deal/tradeOrder/create?supplyid="+$("#supplyid").val()+"&demandid="+$("#demandid").val();
		}
		
	</script>
	<style type="text/css">
	.tdcenter td{text-align:center;}
    .thcenter th{text-align:center;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/deal/tradeOrder/opp?opp=demand">商机信息</a></li>
		<shiro:hasPermission name="deal::edit"><li><a href="${ctx}/deal/tradeOrder/form">生成&quot;供求信息&quot;成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oppVO" action="${ctx}/deal/tradeOrder/opp?opp=demand" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>产品编号：</label>
			<form:input path="id" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>
		    <li><label>产品名称：</label>
			<form:input path="goodsName" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>
			<li><label>数量：</label>
			<form:input path="number" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>
			<li><label>价格：</label>
			<form:input path="priceUnitValue" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>	
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="alert_d" style="margin:10px;"></div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	 <tbody>
	    <tr><td style="text-align:right;background:green;padding-right:160px;"><!-- <a href="javascript:;" onclick="" style="color:#FFF;font-weight:bold;">撮合下单</a> -->&nbsp;&nbsp;<a href="##" onclick="" style="color:#FFF;font-weight:bold;">添加求购信息</a></td></tr>
	 </tbody>
	</table>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr class="thcenter">
			    <th>编号</th>
				<th>产品</th>
				<th>产品别名</th>
				<th>产品父类</th>
				<!-- <th>产品pids代码</th> -->
				<th>价格单元</th>
				<th>数量单元</th>
				<th>数量</th>
				<th>状态</th>
				<th>审核说明</th>
				<th>供求类型</th>
				<th>是否是委托</th>
				<th>消息有效开始时间</th>
				<th>消息有效结束时间</th>
				<th>生产省份名字</th>
				<th>生产城市名字</th>
				<th>生产地区名字</th>
				<th>供应信息编号</th>
				<th>操作</th>
				<th>求购信息备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="info">
				<c:if test="${info.type eq 'demand' }">
				  <tr class="tdcenter">
				    <td style="width:">${info.id}</td>
					<td style="width:">${info.goodsName}</td>
					<td style="width:">${info.pgoodsAlias}</td>
					<td style="width:">${info.pgoodsName}</td>
					<%-- <td style="width:">${info.pids}</td> --%>
					<td style="width:">${info.priceUnitValue}</td>
					<td style="width:">${info.numberUnitValue}</td>
					<td style="width:">${info.number}</td>
					<td style="width:">${info.statue}</td>
					<td style="width:">${info.statueIntro}</td>
					<td style="width:">${info.type}</td>					
					<td style="width:">${info.isEntrust}</td>
					<td style="width:"><fmt:formatDate value="${info.validateStartDate}" pattern="yyyy-MM-dd hh:MM:ss"/></td>
					<td style="width:"><fmt:formatDate value="${info.validateEndDate}" pattern="yyyy-MM-dd hh:MM:ss"/></td>
					<td style="width:">${info.productionProvinceName}</td>
					<td style="width:">${info.productionAreaName}</td>
					<td style="width:">${info.productionCityName}</td>	
					<td>
					 <input type="text" name="supplyid" id="supplyid${info.id}" value="">
					 <input type="hidden" name="demandid" id="demandid${info.id}" value="${info.id}">					 
					</td>
					<td style="width:80px;"> <a href="javascript:;" onclick="createOrder('${info.id}');" style="font-weight:bold;">撮合下单</a></td>				
					<td>${info.remarks}</td>							
					<!-- <td><a href="javascript:;" onclick="createOrder();">撮合下单</a>&nbsp;&nbsp;<a href="##" onclick="">添加供应信息</a></td> -->
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>	
	<div class="alert alert-info" style="width:800px;">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  &nbsp;&nbsp;&nbsp;&nbsp;请先选出供求编号查询出来，并在右则表单中填入对应供求编号，在进行撮合下单!&nbsp;&nbsp;【仅供测试】
</div>
</body>
</html>
