<%--
  Created by IntelliJ IDEA.
  User: liujiabao
  Date: 2015/12/18
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<jsp:useBean id="gspec" class="com.wk.p3.greenmall.modules.info.entity.Info" scope="request" ></jsp:useBean>
<html>
<head>
  <title>供求信息</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    $(document).ready(function() {

    });
  </script>
</head>
<body>
<ul class="nav nav-tabs">
  <%--<li class="active" style="font-weight: bold;font-size: 16px">${info.GoodName}规格添加</li>--%>
</ul><br/>
${info.goodsName}
<%--<form:form id="inputForm" modelAttribute="gspec" action="${ctx}/info/gspec/save" method="post" class="form-horizontal">--%>

<%--</form:form>--%>
</body>
</html>
