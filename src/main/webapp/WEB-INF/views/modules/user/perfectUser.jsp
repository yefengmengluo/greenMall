<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<jsp:include page="../info/home/uCenterHead.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-jbox/2.3/Skins2/Green/jbox.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/user/css/changeMess.css">
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>	
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.js"></script>
<meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
<meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />
     <script type="text/javascript">
       $(function(){
    	 $("input").attr("autocomplete","off");	
   	   	$("#place").mouseleave(function() {
   	   		$("#place").hide();
   		});
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
         $("#userForm").validate({
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
       });



	   	 function subForm(){
	   		 
	   	 }
     </script>

		<!-- 页面右侧 -->
		<div class="body1-right">
			<div class="body1-right-top">
				<div class="body1-right-top1">
					<img src="${ctxStatic}/modules/user/images/User-avatar.png">		
					<span class="f16 ml10 mt5">修改信息</span>
				</div>
			</div>
			<sys:message content="${message }"></sys:message>
			<div class="pt20">
				<img src="${ctxStatic}/modules/user/images/jindutiao1.png" class="ml150 mt50 ">
				<div class="ml150 mt10 mb30">
					<span >填写公司信息</span>
					<span class="ml150">审核公司信息</span>
					<span class="ml150">审核结果</span>
				</div>
				<form:form id="userForm" action="${front}/userApi/perfectUser" method="post" class="form-horizontal">
					<div class="form-group">
						<label  class="col-sm-2 control-label">手机号码:</label>
						<div class="col-sm-7">
							<span class="pt20">${user.mobile }</span>
						</div>
					</div>
					<div class="form-group pr">
						<label  class="col-sm-2 control-label"><span class="color6">*</span>公司名称：</label>
						<div class="col-sm-7" style="width: 80%">
							<input type="text" class="form-control" value="${name }"  name="name" placeholder="请输入公司名称" style="display: inline;">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label"><span class="color6">*</span>地区：</label>
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
						<label  class="col-sm-2 control-label"><span class="color6"></span>详细地区：</label>
						<div class="col-sm-7">
							<input type="text" name="detailArea" class="form-control" placeholder="请输入详细地区">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label"><span class="color6">*</span>主营品种：</label>
						<div class="col-sm-7">
							<%@include file="/WEB-INF/views/include/multiGcategorySelect.jsp" %>
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-sm-2 control-label"><span class="color6">*</span>企业类型：</label>
						<div class="col-sm-7" id="type">
						<c:forEach items="${organizationType}"  var="type" >
							<label class="radio-inline">
								<input type="radio" name="type" id="inlineRadio1" value="${type.id }">${type.name }</label>
						</c:forEach>
						</div>
						
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label"></label>
						<div class="col-sm-7">
							（注：带<span class="color6">*</span>为必填选项）
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button class="button2">提交</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- 页面内容 结束 -->
	<div class="clear"></div>
<jsp:include page="../info/home/uCenterFoot.jsp"></jsp:include>