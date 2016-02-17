<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<jsp:include page="../info/home/uCenterHead.jsp"></jsp:include>
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-jbox/2.3/Skins2/Green/jbox.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/user/css/changeMess.css">
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.js"></script>
<meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
<meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <script type="text/javascript">
       $(function(){
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

    	   $("#place").mouseleave(function() {
      	   		$("#place").hide();
      	   });
     	 setTimeout(function () {
     		$("#provinceSelect.selectSty").val('${organization.provinceId}');
     		changeProvince();
     		setTimeout(function () {
	     		$("#citySelect.selectSty").val('${organization.cityId}');
	     		changeCity();
	     		setTimeout(function () {
		     		$("#areaSelect.selectSty").val('${organization.area}');
	    		 }, 100);
    		 }, 100);
		 }, 100);
         $("#userForm").validate({
        	submitHandler : function(form) {
	         	var mainGoods=document.getElementsByName("mainGoods");
	         	var organizationType=$("input:radio:checked");
		    	var mainGoodsFlag=true;
		    	if($("#choiceMainGoods").val()==null||$("#choiceMainGoods").val()==""){
	        		if(mainGoods.length==0){
	        			mainGoodsFlag=false;
                        $("#mainGoodsCollection2").after("<label for='mainGoodsError' id='mainGoodsError' class='error'>请选择主营品种</label>");
	        		}else{ 
	        			mainGoodsFlag=true;
	        			$("#mainGoodsError").remove();
	        		}
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
        		/* loginName:{
        			required:true,
                   	username:true
        		}, */
        		organizationName:{
        			required:true,
        			realName:true
        		},
        		name:{
        			required:true,
                   	realName:true
        		},
        		phone:{
        			simplePhone:true
        		},
        		email:{
        			email:true
        		},
        		qq:{
        			qq:true
        		},
        		faxNumber:{
        			fax:true
        		}
        	},
        	
        	messages:{
        		/* loginName:{
        			required:"请输入用户名",
                   	username:"请输入以字母开头的4-10位用户名,允许字母数字和下划线"
        		}, */
        		organizationName:{
        			required:"请输入公司名称",
        			realName:"请输入正确的公司名称"
        		},
        		name:{
        			required:"请输入姓名",
        			realName:"请输入正确的姓名"
        		},
        		phone:{
        			simplePhone:"请正确填写您的电话号码"
        		},
        		email:{
        			email:"请输入正确的电子邮件地址"
        		},
        		qqNumber:{
        			qq:"请输入正确的QQ号码"
        		},
        		faxNumber:{
        			fax:"请输入正确的传真号码"
        		}
        	}
         });
       });
       function changeProvince() {
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
	     };
	    function changeCity() {
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
	     };
       function showChildCata(val){
    		$(".child_cata").hide();
    		$("#" + val + "childcata").show();

    		$(".grand_cata").hide();
    		$($("."+val+"fromtop")[0]).show();
    		//$($(".grand_cata")[0]).show();
    	}

     </script>
		<div class="body1-right">
			<div class="body1-right-top">
				<div class="body1-right-top1">
					<img src="${ctxStatic}/modules/user/images/User-avatar.png">
					<span class="f16 ml10 mt5">修改信息</span>
				</div>
			</div>
			<sys:message content="${message }"></sys:message>
			<div class="pt20">
				<form:form class="form-horizontal" action="${front}/userApi/updateUser" method="post" id="userForm">
				<%-- 	<div class="form-group pr">
						<label  class="col-sm-2 control-label">用户名：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control abc" name="loginName" value="${user.loginName }"  id="loginName" placeholder="请输入用户名">
						</div>
					</div> --%>
							<input type="hidden" name="id" value="${user.id }">
        					<input type="hidden" name="organizationId" value="${organization.id }">
					<div class="form-group">
						<label  class="col-sm-2 control-label">手机号码：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" readonly="readonly" id="mobile" name="mobile" value="${user.mobile }" placeholder="请输入手机号码">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">公司名称：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id=""  name="organizationName" value="${organization.name }" placeholder="请输入公司名称">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">姓名：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control"  name="name" value="${user.name }" id="" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">所在地：</label>
			        	<div class="col-sm-7" id="whereSelected">
	                        <select class="selectSty" id="provinceSelect" name="provinceId" onchange="changeProvince()">
	                        </select>
	                        <select class="selectSty ml10" id="citySelect" name="cityId" onchange="changeCity()">
	                        </select>
	                        <select class="selectSty ml10" id="areaSelect" name="area">
	                        </select>
	                    </div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label"><span class="color6"></span>详细地区：</label>
						<div class="col-sm-7">
							<input type="text" name="detailArea" class="form-control" placeholder="请输入详细地区" value="${organization.detailArea }">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">企业类型：</label>
						<div class="col-sm-7" id="type">
						<c:forEach var="type" items="${organizationType}">
								<label class="radio-inline">
								<input type="radio" name="type" value="${type.id }" <c:if test="${organization.type==type.id }">checked="checked"</c:if> >${type.name }</label>
						</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">主营品种：</label>
						<div class="col-sm-7">
							<%@include file="/WEB-INF/views/include/multiGcategorySelect.jsp" %>
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">固话：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id=""  name="phone" value="${user.phone }" placeholder="请输入固话">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">电子邮箱：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id=""  name="email" value="${user.email }" placeholder="请输入电子邮箱">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">Q Q：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="" name="qq" value="${user.qq }" placeholder="请输入QQ号">
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-2 control-label">传真：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="" name="faxNumber" value="${user.faxNumber }" placeholder="请输入传真号">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button class="button2">保存</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- 页面内容 结束 -->
	<div class="clear">
	</div>
<jsp:include page="../info/home/uCenterFoot.jsp"></jsp:include>