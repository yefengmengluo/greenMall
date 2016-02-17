<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--<jsp:useBean id="gspec" class="com.wk.p3.greenmall.modules.info.entity.Gcategory" scope="request" ></jsp:useBean>--%>
<html>
<head>
	<title>类别管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					code: {remote: "${ctx}/info/gcategory/ifExistCode?id=${gcategory.id}"}
				},
				messages: {
					code: {remote: "此代码已存在，请替换.", required: "请填写代码."}
				},
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

		function getCode(){
			$.ajax({
				type:"post",
				url:"${ctx}/info/gcategory/autoCode",
				data:"name="+$("#category_name").val(),
				success:function(data){
					$("#code").val(data);
				}
			})
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class=""><a href="${ctx}/info/gcategory/list?id=${gcategory.parent.id}&parentIds=${gcategory.parentIds}">类别列表${gcategory.id}</a></li>
		<li class="active"><a href="${ctx}/info/gcategory/form?id=${gcategory.id}&parent.id=${gcategory.parent.id}">类别<shiro:hasPermission name="info:gcategory:edit">${not empty gcategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="info:gcategory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gcategory" action="${ctx}/info/gcategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">图片:</label>
			<div class="controls">
				<form:hidden id="catalogImage" path="imagePath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="catalogImage" type="images" uploadPath="/gcategory" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级类别:</label>
			<div class="controls">
                <sys:treeselect id="gcategory" name="parent.id" value="${gcategory.parent.id}" labelName="parent.name" labelValue="${gcategory.parent.name}"
					title="类别" url="/info/gcategory/treeData" extId="${gcategory.id}" cssClass="" allowClear="${gcategory.currentUser.admin}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required" onblur="getCode()" id="category_name"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50" class="required gcategory_code" id="code"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否展示规格:</label>
			<div class="controls">
				<form:select path="showSpec">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">“是”代表展示规格，“否”则表示此类别不展示规格</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正常、删除:</label>
			<div class="controls">
				<form:select path="delFlag">
					<form:options items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">“正常”代表此类别可用，“删除”则表示此规格不可用</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50" class="required digits input-small"/>
				<span class="help-inline">排列顺序，升序。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="info:gcategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>