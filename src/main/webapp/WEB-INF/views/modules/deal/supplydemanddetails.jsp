<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
		
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$.ajax({
				   type: "POST",
				   url: "${ctx}/deal/tradeOrder/detailsmatched",
				   data: "pageNo="+$("#pageNo").val()+"&pageSize="+$("#pageSize").val()+"&demandid="+$("#demandId_").val()+"&supplyid="+$("#supplyId_").val(),		
				   success: function(msg){
					   $("#matched_remove").remove();
					   $("#matched").append(msg);
				   }
				});			
        }
				
		function page1(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$.ajax({
				   type: "POST",
				   url: "${ctx}/deal/tradeOrder/detailsmatching",
				   data: "pageNo="+$("#pageNo").val()+"&pageSize="+$("#pageSize").val()+"&demandid="+$("#demandId_").val()+"&supplyid="+$("#supplyId_").val(),
				   success: function(msg){
				     $("#matching_remove").remove();
				     $("#matching").append(msg);
				   }
				});
        }
		
		//确认撮合
		function entermatch(idn,sdid,ordernumber){
			$.ajax({
				   type: "POST",
				   url: "${ctx}/deal/tradeOrder/match",
				   data: "ordernumber="+ordernumber+"&tosay="+$("#tosay").text()+"&id="+sdid+"&oid="+idn,
				   dataType:"json",
				   success: function(msg){
				     alert("success:"+msg.success);
				   }
				});
		}
		//查询物流
		function querylogistics(){
			return;
		}
		//交易关闭
		function orderclose(){
			return;
		}
		//取消撮合
		function cancelmatch(){
			return;
		}
		//删除进货单
		function deleteJhds(t){
			if(confirmx('确认要删除本进货单吗?')){
				$.ajax({
					   type: "POST",
					   url: "${ctx}/deal/tradeOrder/deletejhds",
					   data: "id="+t,
					   dataType:"json",
					   success: function(msg){
					     alert("success:"+msg.msg);
					   },error:function(){
						   alert("请求失败，请检查网络.");
					   }
					});
			}else{
				return false;
			}
			
		}
	</script>
	<style type="text/css">	     
		.tdcenter td{text-align:center;}
	    .thcenter th{text-align:center;}
	    body { width:1368px;}
	</style>
</head>
<body>
   <div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/deal/tradeOrder/details">交易详情</a></li>
	</ul>
	<%-- <form:f orm class="form-horizontal"> --%>
	<%-- <a href="${ctx}/deal/tradeOrder/queryOrder?pids=1">测试queryOrderList接口</a> --%>
	<form:form id="searchForm" modelAttribute="details" action="${ctx}/deal/tradeOrder/details?flag=${ok}" method="post" class="form-horizontal form-search">
	    <%-- <input id="pageNo" name="pageNo" type="hidden" value="${supplydemandpage.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${supplydemandpage.pageSize}"/> --%>
		<input type="hidden" name="supplId" id="supplyId_" value="${supplyId}"/>
		<input type="hidden" name="demandId" id="demandId_" value="${demandId}"/>
		<c:if test="${okshow eq 'show'}">
			<ul class="ul-form" style="/* display:none; */">
				<c:if test="${ok eq 'demand' }">
				<li><label>采购单号：</label>
				<form:input path="demandid" htmlEscape="false" maxlength="64" class="input-xlarge" />
				  <input type="hidden" name="querysupplydemandid" id="querysupplydemandid" value=""/>
				</li>
				<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit"
					class="btn btn-primary" type="submit" value="查询" /></li>
				<li class="clearfix"></li>
				</c:if>
				<c:if test="${ok eq 'supply' }">
				<li><label>供应单号：</label>
				<form:input path="supplyid" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>
				<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
				<li class="clearfix"></li>		
	            </c:if>				
			</ul>
		</c:if>
		<sys:message content="${message}"/>
		</form:form>
		<div id="matched"><jsp:include page="matchedlist.jsp" /></div>
		<div id="matching"><jsp:include page="matchinglist.jsp" /></div>
	<script type="text/javascript">
	//订单详情
	function orderdetails(showid,orderid){	
		$.ajax({
			   type: "POST",
			   url: "${ctx}/deal/tradeOrder/orderdetails",
			   data: "orderid="+orderid,
			   dataType:"json",
			   success: function(msg){
				  $("#order"+showid+" .re").remove();
			      $("#order"+showid).append(msg.success);	
			      //msg.appendTo(this.parent().parent().parent());
			      //alert(this);
			   }
			});
	}
	</script>
</div>	
</body>
</html>
<script type="text/javascript">
    document.body.overflowX="hidden";
</script>	
