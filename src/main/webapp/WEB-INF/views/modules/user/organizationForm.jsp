<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>完善资料</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
	<meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="${modules}/user/images/ico.ico"/>
	<link rel="bookmark" href="${modules}/user/images/ico.ico"/>
	<!-- Bootstrap core CSS -->
	<link href="${modules}/user/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<!-- Custom styles for this template -->
	<link href="${modules}/user/css/starter-template.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="${modules}/user/css/zhuce.css">
	<link rel="stylesheet" type="text/css" href="${modules}/user/css/base.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/home/userCenter/css/userCen.css">
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/user/css/changeMess.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/user/css/changeMess.css">
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js"></script>
	<style type="text/css">
	.form-control{width: 455px;}
	</style>
	<script type="text/javascript">
		$(function(){
			$("input").attr("autocomplete","off");	
			$.ajax({
		         type: "post",
		         url: "${pageContext.request.contextPath}${fns:getAdminPath()}/sys/area/ajaxProvince",
		         success: function (data) {
		             var list = eval(data);
		             var selector = $("#provinceSelect");
		             for (var i = 0; i < list.length; i++) {
		                 selector.append('<option value="' + list[i].id + '">' + list[i].name + '</option>');
		             }
		             $.ajax({
		                 type: "post",
		                 url: "${pageContext.request.contextPath}${fns:getAdminPath()}/sys/area/ajaxCity",
		                 data: "provinceId=" + $("#provinceSelect").val(),
		                 success: function (data) {
		                     var list = eval(data);
		                     var selector = $("#citySelect");
		                     if (list != null) {
		                         for (var i = 0; i < list.length; i++) {
		                             selector.append('<option value="' + list[i].id + '">' + list[i].name + '</option>');
		                         }
		                     }
		                     $.ajax({
		                         type: "post",
		                         url: "${pageContext.request.contextPath}${fns:getAdminPath()}/sys/area/ajaxArea",
		                         data: "cityId=" + $("#citySelect").val(),
		                         success: function (data) {
		                             var list = eval(data);
		                             var selector = $("#areaSelect");
		                             if (list != null) {
		                                 for (var i = 0; i < list.length; i++) {
		                                     selector.append('<option value="' + list[i].id + '">' + list[i].name + '</option>');
		                                 }
		                             }
		                         }
		                     })
		                 }
		             });
		         }
		     });

		     $("#provinceSelect").change(function () {
		         $("#citySelect").empty();
		         $.ajax({
		             type: "post",
		             url: "${pageContext.request.contextPath}${fns:getAdminPath()}/sys/area/ajaxCity",
		             data: "provinceId=" + $("#provinceSelect").val(),
		             success: function (data) {
		                 var selector = $("#citySelect");
		                 var list = eval(data);
		                 for (var i = 0; i < list.length; i++) {
		                     selector.append('<option value="' + list[i].id + '">' + list[i].name + '</option>');
		                 }
		                 $("#areaSelect").empty();
		                 $.ajax({
		                     type: "post",
		                     url: "${pageContext.request.contextPath}${fns:getAdminPath()}/sys/area/ajaxArea",
		                     data: "cityId=" + $("#citySelect").val(),
		                     success: function (data) {
		                         var selector = $("#areaSelect");
		                         var list = eval(data);
		                         for (var i = 0; i < list.length; i++) {
		                             selector.append('<option value="' + list[i].id + '">' + list[i].name + '</option>');
		                         }
		                     }
		                 });
		             }
		         });
		     });
		     $("#citySelect").change(function () {
		         $("#areaSelect").empty();
		         $.ajax({
		             type: "post",
		             url: "${pageContext.request.contextPath}${fns:getAdminPath()}/sys/area/ajaxArea",
		             data: "cityId=" + $("#citySelect").val(),
		             success: function (data) {
		                 var selector = $("#areaSelect");
		                 var list = eval(data);
		                 for (var i = 0; i < list.length; i++) {
		                     selector.append('<option value="' + list[i].id + '">' + list[i].name + '</option>');
		                 }
		             }
		         });
		     });
			$("#inputForm").validate({
	         	submitHandler : function(form) {
	 	         	var mainGoods=document.getElementsByName("mainGoods");
	 	         	var organizationType=$("input:radio:checked");
	 		    	var mainGoodsFlag=true;
	         		if(mainGoods.length==0){
	         			mainGoodsFlag=false;
	         			$("#mainGoodsCollection").after("<label for='mainGoodsError' id='mainGoodsError' class='error'>请选择主营品种</label>");
	         		}else{
	         			mainGoodsFlag=true;
	         			$("#mainGoodsError").remove();
	         		}
	         		var typeFlag=true;
	         		if(organizationType.length==0){
	         			typeFlag=false;
	         			$("#type").after("<label for='typeError' id='typeError' class='error'>请选择企业类型</label>");
	         			return;
	         		}else{
	         			typeFlag=true;
	         			$("#typeError").remove();
	         		}
	         		if(mainGoodsFlag&&typeFlag){
	 	                form.submit();
	         		}
	             },
	         	rules:{
	         		name:{
	         			required:true,
	         			organizationName:true
	         		}
	         	},
	         	messages:{
	         		name:{
	         			required:"请输入公司名称"
	         		}
	         	}
			  });
			$("#place").mouseleave(function() {
	   	   		$("#place").hide();
	   		});
			var message='${message}';
			if(message!=""){
				showMessage(message);
			}
		});
		function showChildCata(val){
			$(".child_cata").hide();
			$("#" + val + "childcata").show();

			$(".grand_cata").hide();
			$($("."+val+"fromtop")[0]).show();
			//$($(".grand_cata")[0]).show();
		}
		function choosePl(){
			$("#mainGoodsCollection > span").empty();
			var place = document.getElementById("place");
			place.style.display = "block";
		}
		function showGrandCata(val){
			$(".grand_cata").hide();
			$("#" + val + "grandchildcata").show();
		}
		function choiceCategory(pgoodsId,pgoodsName,obj){
			$("#showPgoodsSelectDiv").offset({
				top:0,
				left:0
			});
			if($("#mainGoods"+pgoodsId).val()!=null){
				return;
			}
			$("#mainGoodsCollection").append("<input type='hidden' name='mainGoods' value="+pgoodsId+" id='mainGoods"+pgoodsId+"'>");
			$("#mainGoodsCollection").append("<input type='button' value='"+pgoodsName+"' onclick='deleteThis(mainGoods"+pgoodsId+",this)'>");
			$("#goodsId").val("")
			$("#goodsName").val("")
		}
		function deleteThis(id,o){
			id.remove();
			o.remove();
		}
		function showMessage(message){
           if(message){
               jQuery("#error").html(message)
               jQuery("#error").show();
               jQuery("#error").fadeOut(3000);
           }else{
               jQuery("#error").hide();
           }
	    }
		function skip(){
			location.href ='${front}/userCenter';
		}
	</script>
</head>
<body>
<!-- 页头 开始 -->
<%@include file="/WEB-INF/views/include/top.jsp" %>
<!-- 页头 结束 -->
<!-- 页头logo 开始 -->
<div class="container">
	<div class="row h105 body ">
		<div class="col-md-6 ">
			<img src="${modules}/user/images/header_logo.png" alt="picture miss" class="head_bottom_content_img"></div>
		<div class="col-md-6 mt30">

			<img src="${modules}/user/images/Dark-ray.png" class="fl">
			<dl class="fl ml8 mt5 mr40">
				<dt class="f16 fb">更快捷</dt>
				<dd class="f12">帮您快速找果果</dd>
			</dl>

			<img src="${modules}/user/images/Security-Shield.png" class="fl">
			<dl class="fl ml8 mt5 mr40">
				<dt class="f16 fb">更安全</dt>
				<dd class="f12">安全可靠一站式服务</dd>
			</dl>

			<img src="${modules}/user/images/Bag-with-dollar-sign.png" class="fl">
			<dl class="fl ml8 mt5 mr40">
				<dt class="f16 fb">更便宜</dt>
				<dd class="f12">比一比更便宜</dd>
			</dl>

		</div>
	</div>
</div>

<!-- 页头logo 结束 -->
<!-- 页面主体 开始 -->
<img src="${modules}/user/images/zhuce-image/nav2.png" class="jindutiao">
<div class="body ">
	<div class="zhuce-padding">
		<dl>
			<dt class=" color5 pb20 zhuce-dt">尊敬的用户，您已经注册为果果网的体验会员。</dt>
			<dd class="f12">请完善企业基本信息，通过审核后，成为我们的正式会员。<br>正式会员获得专属交易员的特权，免费找更便宜的货，可免费发布报价，跟踪采购订单动向。</dd>
		</dl>

		<form:form id="inputForm" action="${front}/userApi/organization" method="post" class="form-horizontal">
			<%--<div class="form-group">--%>
				<%--<label  class="col-sm-2 control-label">手机号码:</label>--%>
				<%--<div class="col-sm-7">--%>
					<%--<span class="pt20">${user.mobile }</span>--%>
				<%--</div>--%>
			<%--</div>--%>
			<div class="form-group pr">
				<label  class="col-sm-4 control-label"><span class="color6">*</span>公司名称：</label>
				<div class="col-sm-7">
					<input type="text" class="form-control"  name="name" placeholder="请输入公司名称">
				</div>
			</div>

			<div class="form-group">
				<label  class="col-sm-4 control-label"><span class="color6">*</span>地区：</label>
				<div class="col-sm-7" id="whereSelected">
	                      <select class="selectSty" id="provinceSelect" name="provinceId">
	                      </select>
	                      <select class="selectSty ml10" id="citySelect" name="cityId">
	                      </select>
	                      <select class="selectSty ml10" id="areaSelect" name="area">
	                      </select>
	            </div> 
			</div>
			<div class="form-group">
				<label  class="col-sm-4 control-label"><span class="color6"></span>详细地区：</label>
				<div class="col-sm-7">
					<input type="text" name="detailArea" class="form-control" placeholder="请输入详细地区">
				</div>
			</div>
			<div class="form-group" style="text-align: left;">
				<label  class="col-sm-4 control-label"><span class="color6">*</span>主营品种：</label>
				<div class="col-sm-7">
					<div class="place" id="place">
						<ul class="nav nav-tabs">
							<c:forEach items="${list}" var="cata">
								<li role="presentation" onmouseover="showChildCata(${cata.id})">
									<a href="#">${cata.name}</a>
								</li>
							</c:forEach>
						</ul>

						<c:forEach items="${list}" var="cata2">
							<ul class="nav nav-tabs child_cata" id="${cata2.id}childcata">
								<c:forEach items="${cata2.childList}" var="child">
									<li role="presentation" id="" onmouseover="showGrandCata(${child.id})">
										<a href="#">${child.name}</a>
									</li>
								</c:forEach>
							</ul>
						</c:forEach>

						<c:forEach items="${list}" var="cata3">
							<c:forEach items="${cata3.childList}" var="child2">
								<div class="place-con grand_cata ${cata3.id}fromtop" id="${child2.id}grandchildcata">
									<p>
										<c:forEach items="${child2.childList}" var="grandson">
											&nbsp;&nbsp;&nbsp;<span><a href="javascript:void(0)" onclick="choiceCategory('${grandson.id}','${grandson.name}',this)">${grandson.name}</a></span>&nbsp;&nbsp;&nbsp;
										</c:forEach>
									</p>
								</div>
							</c:forEach>
						</c:forEach>
					</div>
					<div class="form-control" id="mainGoodsCollection" onclick="choosePl()"><span>${mainGoods }</span></div>
				</div>
			</div>

			<div class="form-group" style="text-align: left;">
				<label  class="col-sm-4 control-label"><span class="color6">*</span>企业类型：</label>
				<div class="col-sm-7">
					<c:forEach items="${organizationType}"  var="type" >
						<label class="radio-inline">
							<input type="radio" name="type" id="inlineRadio1" value="${type.id }">${type.name }</label>
					</c:forEach>
					<input type="hidden" id="type">
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-1 control-label"></label>
				<div class="col-sm-7">
					（注：带<span class="color6">*</span>为必填选项）
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<button class="btn btn-default">保存</button>
					<input type="button" onclick='skip()' class="btn btn-default" value="以后再完善">
				</div>
			</div>
			<div id="error" class="alert alert-danger alert-dismissable" style="display: none"></div>
		</form:form>
	</div>
</div>
<div class="cb"></div>

<!-- 页面主体 结束 -->
<!-- 页尾 开始 -->
	<%@include file="/WEB-INF/views/include/bottom.jsp" %>
<!-- 页尾 结束 -->
</body>
</html>