<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成&quot;业务订单&quot;成功管理</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<meta name="decorator" content="default"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${ctxStatic}/jquery/ystep/ystep.css" type="text/css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/common/js/common.js"></script>
    <style type="text/css">
     .divcenter{text-align:center;}
     .margindiv{margin-bottom:10px;}
    </style>
    <script type="text/javascript">
    $(function(){
        getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","showAddressId",'${demandAddressInfo.provinceId}','${demandAddressInfo.cityId}','${demandAddressInfo.areaId}',null,null,null);
        <%--${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}--%>
        //getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","reciveAddressId",'${info.provinceId}','${info.cityId}','${info.areaId}',"provinceShow","cityShow","areaShow");
    })
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/deal/tradeOrder/list">订单检索<!-- 生成&quot;业务订单&quot;成功列表 --></a></li>
		<li class="active"><a href="${ctx}/deal/tradeOrder/tradeorderdetails?orderId=${orderId}"><shiro:hasPermission name="deal:tradeOrder:edit">${not empty tradeOrder.id?'交易状态':'交易状态'}</shiro:hasPermission><shiro:lacksPermission name="deal:tradeOrder:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<div class="form-horizontal control-group">
	    <div style="margin-bottom:15px;"><h1>订单默认进行状态</h1></div>
	    <div class=" orderflow controls"></div>
	</div><!--${ctx}/deal/tradeOrder/save-->
	
	<form:form id="inputForm" modelAttribute="tradeOrder" method="post" class="form-horizontal">			    	  
		<form:hidden path="id"/>
		<form:hidden path="orderNumber"/>
		<form:hidden path="statue"/>		
		<sys:message content="${message}"/>	
		<div class="control-group">
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
		</div>	
		<!-- 修改中 -->
		<div style="height:10px;"></div>
		  <table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
				     <tr>
					    <th style="text-align:center;width:50%;">买家信息</th>
						<th style="text-align:center;">卖家信息</th>
					 </tr>
				</thead>
				<tbody>
					 <tr>
					    <td style="width:50%;">
							<label class="control-label">联系人：</label>
							<div class="controls margindiv">
								<input type="text" id="username" name="username" value="${demandInfo.user.name}" class="input-xlarge " readonly/> 
							</div>
							<label class="control-label">手机号：</label>
							<div class="controls margindiv">
								<input name="mobile" type="text" id="mobile" value="${demandInfo.user.mobile}" class="input-xlarge " readonly/>
							</div>
							<label  class="col-sm-2 control-label">所在地：</label>							
							<div class="controls margindiv">
					        	<div id="showAddressId"></div>
					        </div>
							<label class="control-label">采购商留言：</label>
							<div class="controls margindiv">
								<textarea name="remarks" id="remarks" style="width:400px;">${tradeOrderSupplyDemand.remarks}</textarea>
							</div>
							<div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_supplydemandremarks('${tradeOrderSupplyDemand.id}');" value="保存"/>
							</div>							
					    </td>
						<td colspan="15">
							<label class="control-label">供方联系人：</label>
							<div class="controls margindiv">
								 <input id=""  type="text" name="supplename" value="${supplyAddressInfo.userName}"  class="input-xlarge " readonly/> 
							</div>
							<label class="control-label">手机：</label>
							<div class="controls margindiv">
								<input id=""  type="text"  name="telephone" value="${supplyAddressInfo.telephone}" class="input-xlarge " readonly/>
							</div>
							<label class="control-label">邮政编码：</label>
							<div class="controls margindiv">
								<input id=""  type="text" name="postcode" value="${supplyAddressInfo.postcode}"  class="input-xlarge " readonly/>
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
								<input id=""  type="text" name="provincename"  value="${supplyAddressInfo.provinceName}" maxlength="255" class="input-xlarge " readonly/>
								<input id=""   type="text" name="cityname"  value="${supplyAddressInfo.cityName}" maxlength="255" class="input-xlarge " readonly/>
								<input id=""   type="text" name="areaname" value="${supplyAddressInfo.areaName}" maxlength="255" class="input-xlarge " readonly/>
							</div>
							<label class="control-label">供应商留言：</label>
							<div class="controls margindiv">
								<textarea name="supplyRemarks" id="supplyRemarks" style="width:400px;">${tradeOrderSupplyDemand.supplyRemarks}</textarea>
							</div>
							<div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_supplydemandremarks('${tradeOrderSupplyDemand.id}');" value="保存"/>
							</div>
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
				<table id="contentTable" class="table table-striped table-bordered table-condensed" style="display:none;">
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
				  <table id="contentTable" class="table table-striped table-bordered table-condensed" style="display:none;">
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
							<div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_order_settlement('${tradeOrderSupplyDemand.id}','${tradeOrder.id}');" value="保存"/>
							</div>
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
								<input id="distribution"  type="text"  name="distribution" value="${tradeOrder.distribution}" class="input-xlarge "/>
							</div>
							<label class="control-label">支付方式：</label>
							<div class="controls margindiv">
							    <c:if test="${tradeOrder.payment eq '1' || tradeOrder.payment eq '2'}">
							    <input type="hidden" id="payment" name="payment" value="${tradeOrder.payment}">
							    <input id="paymentstring"  type="text"  name="paymentstring" value="银行付款" class="input-xlarge "/>
							    </c:if>
							</div>
							<label class="control-label">付款方式：</label>
							<div class="controls margindiv">
							    <c:if test="${tradeOrder.paymenttype eq '1' || tradeOrder.paymenttype eq '2'}">
							    <input type="hidden" id="paymenttype" name="paymenttype" value="${tradeOrder.paymenttype}">
							    <input id="paymenttypestring"  type="text"  name="paymenttypestring" value="先付款后发货" class="input-xlarge "/>
							    </c:if>								
							</div>
							<label class="control-label">订单备注：</label>
							<div class="controls margindiv">
								<textarea name="tosay" id="tosay" style="width:400px;">${tradeOrder.remarks}</textarea>
							</div>
							<div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_order('${tradeOrder.id}');" value="保存"/>
							</div>
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
								<input id="goodstotalmoney"  type="text" name="goodstotalmoney" value="${tradeOrder.goodsAmount}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<label class="control-label">配送费用：</label>
							<div class="controls margindiv">
								<input id="distributionfee"  type="text"  name="distributionfee" value="${tradeOrder.distributionfee}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<label class="control-label">支付手续：</label>
							<div class="controls margindiv">
								<input id="poundage"  type="text"  name="poundage" value="${tradeOrder.poundage}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<label class="control-label">订单总金额：</label>
							<div class="controls margindiv">
								<input id="ordertotal" type="text"   name="ordertotal"  value="${tradeOrder.ordertotal}" class="input-xlarge "/>&nbsp;&nbsp;(元)
							</div>
							<div class="controls margindiv">
								<input id="ordersuccess" class="btn" type="button" onclick="save_order('${tradeOrder.id}');" value="保存"/>
							</div>
				  </td>
				</tbody>
				</table>
		<!-- 修改中结束 -->
		
		<div class="form-actions" style="height :15px;">
			<%--<shiro:hasPermission name="deal:tradeOrder:edit">--%><%--&nbsp;</shiro:hasPermission>--%>			
			<!-- <input id="sub" class="btn btn-primary" type="submit" value="正在撮合" onclick="orderstatus()"/>	 -->
			<input id="orderconfirm" class="btn btn-primary hide" type="submit" value="订单确认" onclick="order_confirm();"/>
			<input id="orderpayment" class="btn btn-primary hide" type="submit" value="提交，等待付款" onclick="order_payment();"/>
			<input id="orderdeliver" class="btn btn-primary hide" type="submit" value="提交，等待发货" onclick="order_deliver()"/>
			<input id="ordercomplete" class="btn btn-primary hide" type="submit" value="交易完成" onclick="order_complete();"/>				
			<input id="ordersuccess" class="btn hide" type="button" value="订单交易成功."/>
			<input id="ordercancel" class="btn hide" type="button" value="此交易被取消." />
			<!-- <input id="ordercancel" class="btn" type="button" value="订单取消" onclick="return confirmx('确认要删除该生成&quot;业务订单&quot;记录吗？', this.href)"/> -->
		</div>
		<div class="" style="height:20px;line-height:20px;margin:10px;">			
		       <h1>手动设置订单进行状态</h1>
		</div>
		<div class="form-actions" style="height:15px;">
		   <!-- 手动更改菜单状态 -->
            <!-- <input id="orderconfirm" class="btn btn-primary" type="submit" value="订单确认" onclick="order_confirm();"/> -->
            <c:if test="${status >= 2}">
			<input id="orderpayment" class="btn btn-primary" type="submit" value="提交，等待付款" onclick="order_payment();"/>
			<input id="orderdeliver" class="btn btn-primary" type="submit" value="提交，等待发货" onclick="order_deliver()"/>
			<input id="ordercomplete" class="btn btn-primary" type="submit" value="交易完成" onclick="order_complete();"/>
			</c:if>
           <!-- 手动更改菜单状态 -->          
		</div>
	</form:form>
	<div class="alert alert-info" style="width:550px;margin-top:20px;margin-left:50px;">
			  <button type="button" class="close" data-dismiss="alert">&times;</button>
			  <strong>Warning!</strong>&nbsp;&nbsp;&nbsp;&nbsp;请先进行订单确认!&nbsp;&nbsp;请不要从新的订单状态到旧的订单状态!&nbsp;&nbsp;【仅供测试】
           </div>
		<!-- 提交确认-->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3 id="myModalLabel">模态对话框头部</h3>
		</div>
		<div class="modal-body">
			
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal">关闭</button>
			<button class="btn btn-primary">Save changes</button>
		</div>
	</div>
<!-- 提交确认 -->
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery/ystep/ystep.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(".orderflow").loadStep({size : "large",color : "green",steps : [ {title : "撮合洽谈",content : "目前交易处于【洽谈中】，请先确认洽谈"}, {title : "订单确认",content : "目前交易处于【订单确认】中，请进行订单确认"}, {title : "等待付款",content : "目前交易处于【等待付款】中，请进行付款"}, {title : "等待发货",content : "目前交易处于【等待发货】中，请进行发货"}, {title : "撮合完成",content : "您的交易已经【完成】"} ]});		
    //显示状态
    function order_confirm(){
			
    	var submitbtn = document.getElementById("inputForm");  	
    	submitbtn.action= '${ctx}/deal/tradeOrder/orderfollow?url=s2&orderId='+$("#id").val();				
            submitbtn.click();   	
    }
		
    function order_payment(){
    	var submitbtn = document.getElementById("inputForm");  	
    	submitbtn.action= '${ctx}/deal/tradeOrder/orderfollow?url=s3&orderId='+$("#id").val();			
            submitbtn.click();   	
    }
    
    function order_deliver(){
    	var submitbtn = document.getElementById("inputForm");  	
    	submitbtn.action= '${ctx}/deal/tradeOrder/orderfollow?url=s4&orderId='+$("#id").val();		
        submitbtn.click();   	
    }

    function order_complete(){
    	var submitbtn = document.getElementById("inputForm");  	
    	submitbtn.action= '${ctx}/deal/tradeOrder/orderfollow?url=s5&orderId='+$("#id").val();		
            submitbtn.click();   	
    }
    
    function order_cancel(){
    	var submitbtn = document.getElementById("inputForm");  	
    	submitbtn.action= '${ctx}/deal/tradeOrder/orderfollow?url=s0&orderId='+$("#id").val();		
            submitbtn.click();   	
    }	
    
    function save_supplydemandremarks(p){
    	$.ajax({
			   type: "POST",
			   url: "${ctx}/deal/tradeOrder/orderdesave?param=supplydemand",
			   data: "&remarks="+$("#remarks").val()+"&supplyremarks="+$("#supplyRemarks").val()+"&supplydemandId="+p,
			   dataType:"json",
			   success: function(msg){
			     alert("success:"+msg.msg);
			   },
			   error:function(){
				   alert("请求失败，请检查网络.");
			   }
			});
    }
    
    function save_order(p){
    	$.ajax({
			   type: "POST",
			   url: "${ctx}/deal/tradeOrder/orderdesave?param=order",
			   data: "&goodstotalmoney="+$("#goodstotalmoney").val()+"&distributionfee="+$("#distributionfee").val()+"&ordertotal="+$("#ordertotal").val()+"&paymenttype="+$("#paymenttype").val()+"&payment="+$("#payment").val()+"&distribution="+$("#distribution").val()+"&tosay="+$("#tosay").val()+"&orderId="+p+"&poundage="+$("#poundage").val(),
			   dataType:"json",
			   success: function(msg){
			     alert("success:"+msg.msg);
			   },
			   error:function(){
				   alert("请求失败，请检查网络.");
			   }
			});
    }
    //
    function save_order_settlement(supplydemandId,orderId){
    	$.ajax({
			   type: "POST",
			   url: "${ctx}/deal/tradeOrder/orderdesave?param=settlement",
			   data: "&goodstotalmoney="+$("#settlement_goodstotalmoney").val()+"&ordertotal="+$("#settlement_ordertotal").val()+"&perPrice="+$("#settlement_perPrice").val()+"&number="+$("#settlement_number").val()+"&supplydemandId="+supplydemandId+"&orderId="+orderId+"&poundage="+$("#poundage").val(),
			   dataType:"json",
			   success: function(msg){
			     alert("success:"+msg.msg);
			   },
			   error:function(){
				   alert("请求失败，请检查网络.");
			   }
			});
    }
	</script>
	${node}
</body>
</html>