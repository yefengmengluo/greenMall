<%--
  Created by IntelliJ IDEA.
  User: zhuyanqing
  Date: 2015/12/16
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<jsp:useBean id="gspec" class="com.wk.p3.greenmall.modules.info.entity.Gspec" scope="request" ></jsp:useBean>
<html>
<head>
  <title>规格编辑</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    $(document).ready(function() {

    });
  </script>
</head>
<body>
<ul class="nav nav-tabs">
  <li class="active" style="font-weight: bold;font-size: 16px">${empty gcategoryName?"":gcategoryName}规格编辑</li>
</ul><br/>

<form:form id="inputForm" modelAttribute="gspec" action="${ctx}/info/gspec/save" method="post" class="form-horizontal">
  <input type="hidden" name="gcategory.id"  value="${gcategoryId}">
  <form:hidden path="id"/>
  <%--<c:if test="${empty gcategory.id}">--%>
  <%--<div class="control-group">--%>
    <%--<label class="control-label">商品类别:</label>--%>
      <%--<div class="controls">--%>
        <%--<sys:treeselect id="gcategory" name="gcategory.id" value="${gspec.gcategory.id}" labelName="gcategory.name" labelValue="${gspec.gcategory.name}"--%>
                        <%--title="商品类别" url="/info/gcategory/treeData?showSpec=1" cssClass="required"/>--%>
      <%--</div>--%>
  <%--</div>--%>
  <%--</c:if>--%>
  <%--<c:if test="${not empty gcategory.id}">--%>
    <%--<div class="control-group">--%>
      <%--<label class="control-label">商品类别:</label>--%>
      <%--<div class="controls">--%>
        <%--<form:input path="gcategory.id" htmlEscape="false" maxlength="50" class="required" value="${gcategory.name}" readonly="true"/>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</c:if>--%>
  <div class="control-group">
    <label class="control-label">规格名称:</label>
    <div class="controls">
      <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
      <span class="help-inline"><font color="red">*</font> </span>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">规格代码:</label>
    <div class="controls">
      <form:input path="code" htmlEscape="false" maxlength="50" class="required"/>
      <span class="help-inline"><font color="red">*</font> </span>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">排序:</label>
    <div class="controls">
      <form:input path="orderItem" htmlEscape="false" maxlength="50" class="required digits input-small"/>
      <span class="help-inline">排列顺序，升序。</span>
    </div>
  </div>
  <div class="control-group">
  <label class="control-label">备注:</label>
  <div class="controls">
    <form:textarea path="remarks"/>
  </div>
  </div>
  <shiro:hasPermission name="info:gcategory:edit">
  <div class="control-group">
    <label class="control-label">是否禁用:</label>
    <div class="controls">
      <form:select path="delFlag">
        <form:options items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
      </form:select>
      <span class="help-inline"><font color="red">*</font> “正常”代表此类别可用，“删除”则表示此规格不可用</span>
    </div>
  </div>
  </shiro:hasPermission>
  <div class="form-actions">
    <shiro:hasPermission name="info:gcategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
  </div>
</form:form>
</body>
</html>
