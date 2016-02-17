<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成&quot;业务订单&quot;成功管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${ctxStatic}/jquery/ystep/ystep.css" type="text/css" rel="stylesheet" />
    <script src="/greenMall/common/js/common.js"></script>
	<script type="text/javascript">
	 $(function(){
	        getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","showAddressId",'${info.productionProvince}','${info.productionCity}','${info.productionArea}',null,null,null);
	        <%--${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}--%>
	        //getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","reciveAddressId",'${info.provinceId}','${info.cityId}','${info.areaId}',"provinceShow","cityShow","areaShow");
	    })
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	<style type="text/css">
	.display input{
	    disabled:true;
	}
	 .divcenter{text-align:center;}
     .margindiv{margin-bottom:10px;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/deal/tradeOrder/">订单检索<!-- 生成&quot;业务订单&quot;成功列表 --></a></li>
		<li class="active"><a href="${ctx}/deal/tradeOrder/view?orderId=${orderId}">订单详情</a></li>
	</ul><br/>
   <form:form id="inputForm" modelAttribute="tradeOrder" method="post" class="form-horizontal">			    	  
		<form:hidden path="id"/>
		<form:hidden path="orderNumber"/>
		<form:hidden path="statue"/>		
		<sys:message content="${message}"/>	
		<%-- <div class="control-group">
			<table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<tbody>
					<tr>
					    <td style="text-align: center; background: green;color:#FFF;font-size:18px; "><fmt:formatDate value="${tradeOrder.addDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td
							style="text-align: right; background: green; padding-right: 160px;"><input
							id="ordercancel" class="btn btn-primary" type="submit"
							value="交易取消" onclick="order_cancel();" /></td>
					</tr>
				</tbody>
			</table>
		</div>	 --%>
		<!-- 修改中 -->
		<div style="height:10px;"></div>
		  <table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
				     <tr>
					    <th style="text-align:center;">买家信息</th>
						<th style="text-align:center;">卖家信息</th>
					 </tr>
				</thead>
				<tbody>
					 <tr>
					    <td colspan="15">
							<label class="control-label">联系人：</label>
							<div class="controls margindiv">
								<input id="username" name="username" value="${demandInfo.user.name}" class="input-xlarge "/> 
							</div>
							<label class="control-label">手机号：</label>
							<div class="controls margindiv">
								<input name="mobile" id="mobile" value="${demandInfo.user.mobile}" class="input-xlarge "/>
							</div>
							<label  class="col-sm-2 control-label">所在地：</label>							
							<div class="controls margindiv">
					        	<div id="showAddressId"></div>
					        </div>
							<label class="control-label">采购商留言：</label>
							<div class="controls margindiv">
								<textarea name="remarks" id="remarks" style="width:400px;">${tradeOrderSupplyDemand.remarks}</textarea>
							</div>
							<%-- <div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_supplydemandremarks('${tradeOrderSupplyDemand.id}');" value="保存"/>
							</div>	 --%>						
					    </td>
						<td colspan="15">
							<label class="control-label">供方联系人：</label>
							<div class="controls margindiv">
								 <input id="" name="supplename" value="${supplyAddressInfo.userName}"  class="input-xlarge "/> 
							</div>
							<label class="control-label">手机：</label>
							<div class="controls margindiv">
								<input id=""  name="telephone" value="${supplyAddressInfo.telephone}" class="input-xlarge "/>
							</div>
							<label class="control-label">邮政编码：</label>
							<div class="controls margindiv">
								<input id="" name="postcode" value="${supplyAddressInfo.postcode}"  class="input-xlarge "/>
							</div>	
							<%--<label class="control-label">供应日期：</label>
							<div class="controls margindiv">
								<form:input path="demandName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
							</div>--%>
							<!-- 
							    private String provinceName;
						        private String cityName;
						        private String areaName;
							 -->	
							<label class="control-label">发货地址：</label>
							<div class="controls margindiv">
								<input id="" name="provincename"  value="${supplyAddressInfo.provinceName}" maxlength="255" class="input-xlarge "/>
								<input id=""  name="cityname"  value="${supplyAddressInfo.cityName}" maxlength="255" class="input-xlarge "/>
								<input id=""  name="areaname" value="${supplyAddressInfo.areaName}" maxlength="255" class="input-xlarge "/>
							</div>
							<label class="control-label">供应商留言：</label>
							<div class="controls margindiv">
								<textarea name="supplyRemarks" id="supplyRemarks" style="width:400px;">${tradeOrderSupplyDemand.supplyRemarks}</textarea>
							</div>
							<%-- <div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_supplydemandremarks('${tradeOrderSupplyDemand.id}');" value="保存"/>
							</div> --%>
			             </td>
					 </tr>
				</tbody>
			</table>
			<hr/>
			<table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
				  <tr><th>采购单信息</th></tr>
				  <tr>
				    <th style="text-align:center;" width=15%>采购单号</th><th width=8%>产品</th><th width=8%>品种</th><th width=8%>规格</th><th width=8%>货源地</th><th width=5%>采购重量(${supplyDemandInfo[1].numberUnitValue})</th><th width=8%>采购价格(${supplyDemandInfo[1].priceUnitValue})</th><th width=11%>采购有效期</th><th width=8%>采购备注</th>
				  </tr>
				</thead>
				<tbody>
				  <tr><td>${supplyDemandInfo[1].id}</td><td>${supplyDemandInfo[1].pgoodsName}</td><td>${supplyDemandInfo[1].goodsName}</td><td>${supplyDemandInfo[1].specs}</td><td>${supplyDemandInfo[1].productionDetailArea}</td><td>${supplyDemandInfo[1].number}</td><td>${supplyDemandInfo[1].fromPerPrice} ~ ${supplyDemandInfo[1].toPerPrice}</td><td><fmt:formatDate value="${supplyDemandInfo[1].validateEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td><td>${supplyDemandInfo[1].remarks}</td></tr>
				</tbody>
				</table>
				<hr/>
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				 <tr><th>供应单信息</th></tr>
				  <tr>
				    <th style="text-align:center;" width=15%>供应单号</th><th width=8%>产品</th><th width=8%>品种</th><th width=8%>规格</th><th width=8%>货源地</th><th width=5%>供应量(${supplyDemandInfo[0].numberUnitValue})</th><th width=8%>发布价格(${supplyDemandInfo[0].priceUnitValue})</th><th width=11%>供应有效期</th><th width=8%>供应备注</th>
				  </tr>
				</thead>
				<tbody>
				  <tr><td>${supplyDemandInfo[0].id}</td><td>${supplyDemandInfo[0].pgoodsName}</td><td>${supplyDemandInfo[0].goodsName}</td><td>${supplyDemandInfo[0].specs}</td><td>${supplyDemandInfo[0].productionDetailArea}</td><td>${supplyDemandInfo[0].number}</td><td>${supplyDemandInfo[0].fromPerPrice} ~ ${supplyDemandInfo[0].toPerPrice}</td><td><fmt:formatDate value="${supplyDemandInfo[0].validateEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td><td>${supplyDemandInfo[0].remarks}</td></tr>
				</tbody>
				</table>
				<hr/>				
				  <table id="contentTable" class="table table-striped table-bordered table-condensed" style="display:/* none */;">
				<thead>
				  <tr>
				    <th colspan="5">订单结算信息</th>
				  </tr>
				</thead>
				<tbody>
				  <tr><td style="padding-top:15px;">
				    <label class="control-label">产品单价：</label>
							<div class="controls margindiv">
								<input id="settlement_perPrice"  name="perPrice" value="${tradeOrderSupplyDemand.perPrice}" class="input-xlarge "/>&nbsp;&nbsp;(${tradeOrderSupplyDemand.priceUnitValue }) <font style="color:red;">【仅供测试】</font>
								<input type="hidden" id="settlement_priceUnitId" name="priceUnitId" value="${tradeOrderSupplyDemand.priceUnitId }">
								<input type="hidden" id="settlement_priceUnitValue" name="priceUnitValue" value="${tradeOrderSupplyDemand.priceUnitValue }">
							</div>
							<label class="control-label">购买数量：</label>
							<div class="controls margindiv">
								<input id="settlement_number"  name="number" value="${tradeOrderSupplyDemand.number}" class="input-xlarge "/>&nbsp;&nbsp;(${tradeOrderSupplyDemand.numberUnitValue })
								<input type="hidden" id="settlement_numberUnitId" name="numberUnitId" value="${tradeOrderSupplyDemand.numberUnitId }">
								<input type="hidden" id="settlement_numberUnitValue" name="numberUnitValue" value="${tradeOrderSupplyDemand.numberUnitValue }">
							</div>
							<label class="control-label">商品总金额：</label>
							<div class="controls margindiv">
								<input id="settlement_goodstotalmoney"  name="goodstotalmoney" value="${tradeOrder.goodsAmount}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>							
							<label class="control-label">订单总金额：</label>
							<div class="controls margindiv">
								<input id="settlement_ordertotal"  name="ordertotal"  value="${tradeOrder.ordertotal}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<%-- <div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_order_settlement('${tradeOrderSupplyDemand.id}','${tradeOrder.id}');" value="保存"/>
							</div> --%>
				  </td>
				</tbody>
				</table>
				<hr/>
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				  <tr>
				    <th colspan="5">支付物流信息</th>
				  </tr>
				</thead>
				<tbody>
				  <tr><td  style="padding-top:15px;">
				    <label class="control-label">配送方式：</label>
							<div class="controls margindiv">
								<input id="distribution"  name="distribution" value="${tradeOrder.distribution}" class="input-xlarge "/>
							</div>
							<label class="control-label">支付方式：</label>
							<div class="controls margindiv">
							    <c:if test="${tradeOrder.payment eq '1' || tradeOrder.payment eq '2'}">
							    <input type="hidden" id="payment" name="payment" value="${tradeOrder.payment}">
							    <input id="paymentstring"  name="paymentstring" value="银行付款" class="input-xlarge "/>
							    </c:if>
							</div>
							<label class="control-label">付款方式：</label>
							<div class="controls margindiv">
							    <c:if test="${tradeOrder.paymenttype eq '1' || tradeOrder.paymenttype eq '2'}">
							    <input type="hidden" id="paymenttype" name="paymenttype" value="${tradeOrder.paymenttype}">
							    <input id="paymenttypestring"  name="paymenttypestring" value="先付款后发货" class="input-xlarge "/>
							    </c:if>								
							</div>
							<label class="control-label">订单备注：</label>
							<div class="controls margindiv">
								<textarea name="tosay" id="tosay" style="width:400px;">${tradeOrder.remarks}</textarea>
							</div>
							<%-- <div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_order('${tradeOrder.id}');" value="保存"/>
							</div> --%>
				  </td>
				</tbody>
				</table>				
				<hr/>
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				  <tr>
				    <th colspan="5">订单统计</th>
				  </tr>
				</thead>
				<tbody>
				  <tr><td  style="padding-top:15px;">
				            <label class="control-label">商品总金额：</label>
							<div class="controls margindiv">
								<input id="goodstotalmoney"  name="goodstotalmoney" value="${tradeOrder.goodsAmount}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<label class="control-label">配送费用：</label>
							<div class="controls margindiv">
								<input id="distributionfee"  name="distributionfee" value="${tradeOrder.distributionfee}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<label class="control-label">支付手续：</label>
							<div class="controls margindiv">
								<input id="poundage"  name="poundage" value="${tradeOrder.poundage}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<label class="control-label">订单总金额：</label>
							<div class="controls margindiv">
								<input id="ordertotal"  name="ordertotal"  value="${tradeOrder.ordertotal}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<%-- <div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_order('${tradeOrder.id}');" value="保存"/>
							</div> --%>
				  </td>
				</tbody>
				</table>
		<!-- 修改中结束 -->
<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</form:form>
</body>
</html>