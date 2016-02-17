<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/1/5 0005
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>委托的供应信息对应的求购信息列表</title>
  <meta name="decorator" content="default"/>
  <%--<script>
    function page(n,s){
//      $("#pageNo").val(n);
//      $("#pageSize").val(s);
      alert("00000")
      showEntrustDemands($("#showSupplyEntrustDemand"),'${basicId}',n,s)
//      return false;
    }
  </script>--%>
</head>
<body>
  <%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
  <%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
  <%--<input id="basicId" name="id" type="hidden" value="${basicId}"/>--%>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
  <thead><tr><th>状态</th><th>求购产品</th><th>品种 </th><th>规范</th><th>数量</th><th>单价</th><th>联系人</th><th>联系方式</th><th>求购组织</th><th>求购地</th><th>备注</th><th>委托时间</th><th>操作</th></tr></thead>
  <tbody>
    <c:forEach items="${list}" var="info">
      <tr>
        <td>
            <%--默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功--%>
          <c:if test="${info.statue==0}">待审核</c:if>
          <c:if test="${info.statue==1}">审核已通过</c:if>
          <c:if test="${info.statue==-1}">审核未通过</c:if>
          <c:if test="${info.statue==-2}">删除</c:if>
          <c:if test="${info.statue==2}">正在洽谈</c:if>
          <c:if test="${info.statue==3}">等待打款</c:if>
          <c:if test="${info.statue==4}">交易成功</c:if>

        </td>
        <td>
            ${info.pgoodsName}
        </td>
        <td>
            ${info.goodsName}
        </td>
        <td>${info.checkedSpecs}</td>
        <td>${info.number}${info.numberUnitValue}</td>
        <td>${info.fromPerPrice}<c:if test="${info.toPerPrice>0}">-${info.toPerPrice}</c:if>${info.priceUnitValue}</td>
        <td>${info.userName}</td>
        <td>${info.telephone}</td>
        <td>${info.organizationName}</td>
        <td>${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}</td>
        <td>
            ${info.remarks}
        </td>
        <td>${info.createDate}</td>
        <td>
          <a  href="javascript:" onclick="generateOrder('','${info.id}',this)">生成订单</a>
          <%--<a href="">无意向</a>--%>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<%--<div class="pagination">${page}</div>--%>
</body>
</html>
