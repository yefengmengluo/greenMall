<%--
  Created by IntelliJ IDEA.
  User: zhuyanqing
  Date: 2015/12/16
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品规格</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
//      $("#addBtn").click(function(){
//
//      })
        });
        function delSpecs() {
            alert("dd")
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active" style="font-weight: bold;font-size: 16px">${gcategory.name}</li>
</ul>
    <a href="${ctx}/info/gspec/form?id=${gcategory.id}" class="btn btn-w-m btn-primary">添加规格</a>
    <a href="#" onclick="delSpecs()" class="btn btn-w-m btn-danger">删除规格</a>
<%--<sys:message content="${message}"/>--%>
<table id="contentTable" class="table table-striped table-bordered table-condensed" style="text-align: center;margin-top: 10px">
    <thead>
    <tr>
        <th style="text-align: center;width: 20px"><input type="checkbox" value=""></th>
        <th>规格名称</th>
        <th>规格代码</th>
        <th>规格值</th>
        <th>描述</th>
        <shiro:hasPermission name="sys:user:edit">
            <th>操作</th>
        </shiro:hasPermission></tr>
    </thead>
    <tbody>
    <c:forEach items="${specList}" var="spec">
        <tr>
            <td style="text-align: center"><input type="checkbox" value="${spec.id}"></td>
            <td><a href="${ctx}/info/gspec/form2?gspecId=${spec.id}">${spec.name}</a></td>
            <td>${spec.code}</td>
            <td>${spec.specStrs}</td>
            <td>${spec.remarks}</td>
            <shiro:hasPermission name="sys:user:edit">
                <td>
                    <a href="${ctx}/info/gspec/form2?gspecId=${spec.id}">编辑规格</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${ctx}/info/gvalue/editSpecVal?id=${spec.id}">编辑规格值</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>