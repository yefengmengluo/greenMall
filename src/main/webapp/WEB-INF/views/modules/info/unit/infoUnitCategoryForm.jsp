<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>类别单位管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/info/infoUnitCategory?gcategoryId=${infoUnitCategory.gcategoryId}">类别单位列表</a></li>
		<li class="active"><a href="${ctx}/info/infoUnitCategory/form?id=${unit.id}&gcategoryId=${infoUnitCategory.gcategoryId}">类别单位${not empty unit.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="infoUnitCategory" action="${ctx}/info/infoUnitCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="gcategoryId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">默认单位:</label>
			<div class="controls">
				<%--<form:select path="isDefault">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>--%>
				<select name="isDefault">
					<option value="0" <c:if test="${infoUnitCategory.isDefault==0}">selected</c:if>>否</option>
					<option value="1" <c:if test="${infoUnitCategory.isDefault==1}">selected</c:if>>是</option>
				</select>
				<span class="help-inline">是否是此类型下的默认单位,每一个类型下都需要有且仅有一个默认的单位</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="orderItem" htmlEscape="false" maxlength="50" class="required digits input-small"/>
				<span class="help-inline">排列顺序，升序。</span>
			</div>
		</div>
		<%--<c:forEach items="${units}" var="unit">--%>
			<%--${infoUnitCategory.unitId}=====${unit.id}--%>
		<%--</c:forEach>--%>
		<div class="control-group">
			<label class="control-label">单位说明:</label>
			<div class="controls">
				<select name="unitId" style="width:100px ">
					<c:forEach items="${units}" var="unit">
						<option value="${unit.id}" <c:if test="${infoUnitCategory.unitId==unit.id}">selected</c:if>>${unit.name}</option>
					</c:forEach>
				</select>

			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>