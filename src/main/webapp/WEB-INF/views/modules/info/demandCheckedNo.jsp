<%--
  Created by IntelliJ IDEA.
  User: liujiabao
  Date: 2015/12/21 0021
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>审核未通过的求购信息</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    function page(n,s){
      $("#pageNo").val(n);
      $("#pageSize").val(s);
      $("#searchForm").submit();
      return false;
    }

    function selectAllInfo(){
      var f = $(".selectedChecke:checked")
      if(f.length>0){
        $(".selectedChecke").removeAttr("checked")
      }else{
        $(".selectedChecke").attr("checked","checked")

      }

    }

    function deleteSelectedInfo(){
      var selectedCheckeds = ""
      var i=0
      $(".selectedChecke:checked").each(function(){
        if(i==0) {
          selectedCheckeds += $(this).attr("value")

        }else{
          selectedCheckeds += ";"+$(this).attr("value")
        }
        i++
      })
      toDelete(selectedCheckeds)
    }
    function toDeleteSingle(id,obj){
      var flag = confirm("确定删除此信息吗？")
      if(!flag){
        return
      }
      $.ajax({
        type:"post",
        url:"${ctx}/info/deleteDemands/",
        data:{
          ids:id
        },
        success:function(data){
          if(data){
            alert(data)
          }else{
           $(obj).parent().parent().remove()
          }

        }
      })
    }
    function toDelete(ids){
      var flag = confirm("确定删除选中的信息吗？")
      if(!flag){
        return
      }
      $.ajax({
        type:"post",
        url:"${ctx}/info/deleteDemands/",
        data:{
          ids:ids
        },
        success:function(data){
          if(data){
            alert(data)
          }else{
            $(".selectedChecke:checked").each(function(){
              $(this).parent().parent().remove()
            })
          }

        }
      })
    }

    function changeStatue(){
      var selectedStatue = $("#statue option:selected").val()
      var url = "${ctx}/info/demandCheck/"
      if(selectedStatue==0){
        url = "${ctx}/info/demandCheck/"
      }else if(selectedStatue==100){
        url = "${ctx}/info/demandMessageCheck/"
      }else if(selectedStatue==1){
        url = "${ctx}/info/demandChecked/"
      }else if(selectedStatue==-1){
        url = "${ctx}/info/demandCheckedNo/"
      }else if(selectedStatue==-2){
        url = "${ctx}/info/demandDelete/"
      }else if(selectedStatue==2){
        url = "${ctx}/info/demandTalking/"
      }else if(selectedStatue==3){
        url = "${ctx}/info/demandWaitingMoney/"
      }else if(selectedStatue==4){
        url = "${ctx}/info/demandSuccess/"
      }
      $("#searchForm").attr("action",url)
      $("#searchForm").submit()
    }

  </script>
</head>
<body>

  <div class="ibox-tools text-right">
    <a href="${pageContext.request.contextPath}${fns:getAdminPath()}/info/createDemand" class="btn btn-primary btn-xs"  > 新增</a>
  </div>

<form:form id="searchForm" modelAttribute="info" action="${ctx}/info/demandCheckedNo/" method="post" class="breadcrumb form-search">
  <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
  <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

  <input type="button" name="selectAll" value="全选" onclick="selectAllInfo()"/>
  <input type="button" name="deleteSelected" value="删除" onclick="deleteSelectedInfo()"/>
  状态：<select id="statue" name="statue" class="required input-medium" onchange="changeStatue()">
    <%--默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功--%>
      <option value="0" <c:if test="${info.statue==0}">selected</c:if>>待审核</option>
  <option value="100" <c:if test="${info.statue==100}">selected</c:if>>待审核-纯信息</option>
      <option value="1" <c:if test="${info.statue==1}">selected</c:if>>审核已通过</option>
      <option value="-1" <c:if test="${info.statue==-1}">selected</c:if>>审核未通过</option>
      <option value="-2" <c:if test="${info.statue==-2}">selected</c:if>>删除</option>
      <option value="2" <c:if test="${info.statue==2}">selected</c:if>>正在洽谈</option>
      <option value="3" <c:if test="${info.statue==3}">selected</c:if>>等待打款</option>
      <option value="4" <c:if test="${info.statue==4}">selected</c:if>>交易成功</option>
  </select>
  <label>联系人：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <label>联系手机：</label><form:input path="telephone" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <label>采购产品：</label><form:input path="pgoodsName" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <label>采购品种：</label><form:input path="goodsName" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
</form:form>
<sys:message content="${message}"/>

<div id="infoDiv">
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr><th></th><th>状态</th><th>采购单流水号</th><th>联系方式 </th><th>产品</th><th>品种</th><th>规格</th><th>数量</th><th>单价</th>
      <th>采购组织</th><th>采购地</th>
      <th>发布时间</th>
      <th>备注</th><th>操作</th></thead>
    <tbody>
    <c:forEach items="${page.list}" var="info">
      <tr>
        <td>
          <input type="checkbox" name="selectedChecke" class="selectedChecke" value="${info.id}"/>
        </td>
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
        <td>${info.id}</td>
        <td>${info.userName}  ${info.telephone}</td>
        <td>
          ${info.pgoodsName}
        </td>
        <td>
          ${info.goodsName}
        </td>
        <td>${info.checkedSpecs}</td>
        <td>${info.number}${info.numberUnitValue}</td>
        <td>${info.fromPerPrice}<c:if test="${info.toPerPrice>0}">-${info.toPerPrice}</c:if>${info.priceUnitValue}</td>
        <td>${info.organizationName}</td>
        <td>${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}</td>
        <td>${fns:formatDate(info.createDate,"yyyy-MM-dd HH:mm:ss")}</td>
        <td>
            ${info.remarks}
        </td>

        <td>
          <%--<a href="${ctx}/info/checkDemand?id=${info.id}">编辑</a>--%>
          <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
          <a href="javascript:" onclick="toDeleteSingle('${info.id}',this)">删除</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<div class="pagination">${page}</div>
<%--<c:forEach items="${infos}" var="info">--%>
  <%--<c:out value="${info.id}"></c:out>--%>
<%--</c:forEach>--%>

</body>
</html>
