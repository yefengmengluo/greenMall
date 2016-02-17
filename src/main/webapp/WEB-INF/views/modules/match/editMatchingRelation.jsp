<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>匹配规则添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function checkReamrk() {
			var remark="";
			var obj=$('input[name="checkmatch"]:checked');
			obj.each(function(){ 
				remark+=$(this).next().text()+",";
			}); 
			$("#relationRemark").val(remark.substring(0, remark.length-1));
			var remarkFlag=true;
			if(remark.substring(0, remark.length-1)==""){
				remarkFlag=false;
				if($("#relationRemarkError").val()==null){
					$("#relationRemark").after("<label for='relationRemarkError' id='relationRemarkError' class='error'>请选择匹配字段</label>");
				}
			}else{
				$("#relationRemarkError").remove();
				remarkFlag=true;
			}
			var relationNameFlag=true;
			if($("#relationName").val()==""){
				if($("#relationNameError").val()==null){
					$("#relationName").after("<label for='relationNameError' id='relationNameError' class='error'>请输入规则名称</label>");
				}
				relationNameFlag=false;
			}else{
				$("#relationNameError").remove();
				relationNameFlag=true;
			}
			var sortOrderFlag=true;
			if($("#sortOrder").val()==""){
				if($("#sortOrderError").val()==null){
					$("#sortOrder").after("<label for='sortOrderError' id='sortOrderError' class='error'>请输入排序字段</label>");
				}
				ortOrderFlag=false;
			}else{
				$("#sortOrderError").remove();
				ortOrderFlag=true;
			}
			var ifMatching=$('input[name="ifMatching"]:checked');
			var ifMatchingFlag=true;
			if(ifMatching.length==0){
				if($("#ifMatchingError").val()==null){
					$("#ifMatching").after("<label for='ifMatchingError' id='ifMatchingError' class='error'>请选择是否开启使用</label>");
				}
				ifMatchingFlag=false;
			}else{
				$("#ifMatchingError").remove();
				ifMatchingFlag=true;
			}
			var isEntrust=$('input[name="isEntrust"]:checked');
			var isEntrustFlag=true;
			if(ifMatching.length==0){
				if($("#isEntrustError").val()==null){
					$("#isEntrust").after("<label for='isEntrustError' id='isEntrustError' class='error'>请选择是否开启使用</label>");
				}
				isEntrustFlag=false;
			}else{
				$("#isEntrustError").remove();
				isEntrustFlag=true;
			}
			var relationWeightFlag=true;
			if(remarkFlag){
				var relationWeight=$('input[name="relationWeight"]');
				var sum=0;
				$.each(relationWeight,function(i,n){
					sum+=parseFloat(n.value);
				});
				if(sum!=100){
					$("#relationRemark").after("<label for='relationRemarkError' id='relationRemarkError' class='error'>字段所占百分比总和加起来必须是100%</label>");
					relationWeightFlag=false;
				}
			}
			if(remarkFlag&&relationNameFlag&&sortOrderFlag&&ifMatchingFlag&&isEntrustFlag&&relationWeightFlag){
				//$("#matchingForm").submit();
				
				if(ifMatching.val()=="0"){
					$("#ifCover").val("2");
				}
				$.ajax({
				   type: "POST",
				   url: "saveMatchingRelation",
				   data: $("#matchingForm").serialize(),/* {
					   'checkmatch':obj,
					   'matchingObject':$("#matchingObject").val(),
					   'relationWeight':$('input[name="relationWeight"]'),
					   'relationName':$("#relationName").val(),
					   'remarks':$("#remarks").val(),
					   'isEntrust':isEntrust,
					   'ifMatching':ifMatching
					} */
					dataType:"text",
					async:true,
				   success: function(msg){
					   if(msg=='nameError'){
						   $("#relationName").after("<label for='relationNameError' id='relationNameError' class='error'>规则名已存在</label>");
					   }else if(msg=="relationError1"){
						   alert("请选择匹配字段");
					   }else if(msg=="relationError2"){
						   alert("字段所占百分比总和加起来必须是100%");
					   }else{
						   if(msg=="1"){
							   if(confirm('所添加或修改的规则已有启用，是否替换？')){
						    		$("#ifCover").val("1");
						    		checkReamrk();
						    		/* $("#matchingForm").attr("action","${ctx}/match/saveMatchingRelation");
						    		$("#matchingForm").submit();
						    		self.location.href="matchRelationList"; */
						    	}else{
						    		$("#ifCover").val("2");
						    		checkReamrk();
						    		/* $("#matchingForm").attr("action","${ctx}/match/saveMatchingRelation");
						    		$("#matchingForm").submit();
						    		self.location.href="matchRelationList"; */
						    	}
						   }else{
							   self.location.href="matchRelationList";
						   }/* else{
							   $("#matchingForm").attr("action","match/saveMatchingRelation");
				    		   $("#matchingForm").submit();
				    		   self.location.href="matchRelationList";						   
						   } */
				   	}
				   }
				});
			}
		}
		function enableWeight(ifChecked){
			if(ifChecked.checked){
				$(ifChecked).next().next().attr("disabled",false);
				$(ifChecked).next().next().attr("name","relationWeight")
			}else{
				$(ifChecked).next().next().attr("disabled",true);
				$(ifChecked).next().next().attr("value","");
				$(ifChecked).next().next().removeAttr("name");
			}
		}
	</script>
</head>
<body>
	<form:form class="form-horizontal" action="${ctx}/match/saveMatchingRelation" method="post"  id="matchingForm">
		<input type="hidden" name="ifCover" id="ifCover">
		<input type="hidden" name="ifUpdate" value="${ifUpdate }">
		<input type="hidden" name="id" value="${demandRelationMatching.id }">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<td>*规则所属类别：</td>
				<td>
					<select name="matchingObject" id="matchingObject">
						<option value="supply"  <c:if test="${demandRelationMatching.matchingObject=='supply' }">selected="selected"</c:if>>供应商推荐规则</option>
						<option value="demand"  <c:if test="${demandRelationMatching.matchingObject=='demand' }">selected="selected"</c:if>>求购商推荐规则</option>
					</select>
					<span>供应商规则是针对用户发布供应后匹配的规则,求购商规则是针对用户发布求购后匹配的规则</span><br/>
				</td>
			</tr>
			<tr>
				<td>*规则名称：</td>
				<td><input type="text" id="relationName" name="relationName" value="${demandRelationMatching.relationName }"></td>
			</tr>		
			<tr>
				<td>*排序数字：</td>
				<td><input type="text" id="sortOrder" name="sortOrder" value="${demandRelationMatching.sortOrder }"></td>
			</tr>		
			<tr>
				<td>*选择字段：</td>
				<td>
					<c:forEach items="${matchingAttrList }" var="attr">
						<c:set var="flag" value="false"></c:set>
						<c:set var="weight" value=""></c:set>
						<c:forEach items="${relationMatchList }" var="match">
							<c:if test="${match.id==attr.id }">
								<c:set var="flag" value="true"></c:set>
								<c:set var="weight" value="${match.weight }"></c:set>
							</c:if>
						</c:forEach>
							<input type="checkbox" name="checkmatch" value="${attr.id }" <c:if test="${flag }">checked="checked"</c:if> onclick="enableWeight(this)">
							<span>${attr.remark }</span>
							&emsp;&emsp;&emsp;
							<input type="text" <c:if test="${!flag }">disabled="disabled"</c:if> <c:if test="${flag }">name="relationWeight"</c:if> value="${weight}">%
						<br/>
					</c:forEach>
					<input type="hidden" class="btn btn-primary" name="relationRemark" id="relationRemark">
				</td>
			</tr>	
			<tr>
				<td>备注信息:</td>
				<td><textarea rows="" cols="" name="remarks" id="remarks">${demandRelationMatching.remarks }</textarea></td>
			</tr>	
			<tr>
				<td>*是否开启使用:</td>
				<td>
					<input type="radio" name="ifMatching" value="1" <c:if test="${demandRelationMatching.ifMatching=='1' }">checked="checked"</c:if>>是
			 		<input type="radio" name="ifMatching" value="0" <c:if test="${demandRelationMatching.ifMatching=='0' }">checked="checked"</c:if>>否
			 		<span id="ifMatching"></span>
			 	</td>
			</tr>	
			<tr>
				<td>*是否匹配委托:</td>
				<td>
					<input type="radio" name="isEntrust" value="1" <c:if test="${demandRelationMatching.isEntrust=='1' }">checked="checked"</c:if>>是
			 		<input type="radio" name="isEntrust" value="0" <c:if test="${demandRelationMatching.isEntrust=='0' }">checked="checked"</c:if>>否
			 		<span id="isEntrust"></span>
			 	</td>
			</tr>	
			<tr>
				<td colspan="21"><input type="button" value="提交保存" onclick="checkReamrk()"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>