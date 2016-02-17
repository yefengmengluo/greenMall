<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改会员</title>
	<meta name="decorator" content="default"/>
	<script src="/greenMall/common/js/common.js"></script>
	<!-- Custom styles for this template -->
	  <link rel="stylesheet" type="text/css" href="${ctxStatic}/home/userCenter/css/base.css">
	  <link rel="stylesheet" type="text/css" href="${ctxStatic}/home/userCenter/css/sellerCen.css">
	<meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
	<meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />
	<script type="text/javascript">
       $(function(){
    	   $("#place").mouseleave(function() {
      	   		$("#place").hide();
      		});
         getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","showAddressId",'${organization.provinceId }','${organization.cityId }','${organization.area }',null,null,null);
         var message='${message}';
			if(message!=""){
				$("#contentTable").before("<div id='messageBox' class='alert alert-error'><button data-dismiss='alert' class='close'>×</button>"+message+"</div>");
			}
       });
       function showChildCata(val){
    		$(".child_cata").hide();
    		$("#" + val + "childcata").show();

    		$(".grand_cata").hide();
    		$($("."+val+"fromtop")[0]).show();
    	}
       function choosePl(){
    	   	$("#mainGoodsCollection>span").empty();
    	   	$("#choiceMainGoods").remove();
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
	         $("#mainGoodsCollection").append("<input type='button' class='btn btn-default' value='"+pgoodsName+"' onclick='deleteThis(mainGoods"+pgoodsId+",this)'>"); 
	         $("#goodsId").val("")
	         $("#goodsName").val("")
	   	}	
	   	 function deleteThis(id,o){
	   		 id.remove();
	   		 o.remove();
	   	 }	
     </script>
</head>
<body>
	<form:form class="form-horizontal" action="${ctx }/user/userAdmin/updateUser" method="post" id="userForm">
	<input type="hidden" name="id" value="${user.id }">
    <input type="hidden" name="organizationId" value="${organization.id }">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td>*公司名称</td>
			<td>
				<input type="text" name="organizationName" value="${organization.name }">
			</td>
		</tr>
		<tr>
			<td>*公司类型</td>
			<td>
				<c:forEach var="type" items="${organizationType}">
					<input type="radio" name="type" value="${type.id }" <c:if test="${organization.type==type.id }">checked="checked"</c:if> >${type.name }
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>密码</td>
			<td>
				<input type="text" name="password"><span style="color: red">*不修改则留空</span>
			</td>
		</tr>
		<tr>
			<td>*姓名</td>
			<td>
				<input type="text" name="name" value="${user.name }">
			</td>
		</tr>
		<tr>
			<td>手机号码</td>
			<td>
				<input type="text" name="mobile" value="${user.mobile }">
			</td>
		</tr>
		<tr>
			<td>座机号码</td>
			<td>
				<input type="text" name="phone" value="${user.phone }">
			</td>
		</tr>
		<tr>
			<td>QQ号码</td>
			<td>
				<input type="text" name="qq" value="${user.qq }">
			</td>
		</tr>
		<tr>
			<td>传真号码</td>
			<td>
				<input type="text" name="faxNumber" value="${user.faxNumber }">
			</td>
		</tr>
		<tr>
			<td>*地区：</td>
			<td>
				<div class="col-sm-7">
			        <div id="showAddressId">
			        </div>
				</div>
			</td>
		</tr>
		<tr>
			<td>详细地区：</td>
			<td>
				<input type="text" name="detailArea" value="${organization.detailArea }">
			</td>
		</tr>
		<tr>
			<td>*主营产品：</td>
			<td>
				<div id="showPgoodsSelectDiv">
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
			      </div>
	              <div id="mainGoodsCollection" class="form-control" onclick="choosePl()" style="height: 50px;"><input type="hidden" name="choiceMainGoods" id="choiceMainGoods" value="1"><span>${mainGoods }</span></div>
			</td>
		</tr>
		<tr>
			<td>备注</td>
			<td>
				<textarea rows="" cols="" name="remarks" value="${user.remarks }"></textarea>
			</td>
		</tr>
		<tr>
			<td>注册时间</td>
			<td><fmt:formatDate value="${user.createDate }" pattern='yyyy-MM-dd HH:mm:ss'/></td>
		</tr>
		<tr>
			<td colspan="21">
				<button class="btn btn-primary btn-sm">保存</button>
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>