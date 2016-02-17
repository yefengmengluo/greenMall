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
  <title>委托的供应正在洽谈状态管理</title>
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
        url:"${ctx}/info/deleteSupplys/",
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
        url:"${ctx}/info/deleteSupplys/",
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
      var url = "${ctx}/info/entrustBeforeSupplyCheck/"
      if(selectedStatue==0){
        url = "${ctx}/info/entrustBeforeSupplyCheck/"
      }else if(selectedStatue==1){
        url = "${ctx}/info/entrustAfterSupplyCheck/"
      }else if(selectedStatue==-1){
        url = "${ctx}/info/entrustSupplyCheckedNo/"
      }else if(selectedStatue==-2){
        url = "${ctx}/info/entrustSupplyDelete/"
      }else if(selectedStatue==2){
        url = "${ctx}/info/entrustSupplyTalking/"
      }else if(selectedStatue==3){
        url = "${ctx}/info/entrustSupplyWaitingMoney/"
      }else if(selectedStatue==4){
        url = "${ctx}/info/entrustSupplySuccess/"
      }
      console.log(url)
      $("#searchForm").attr("action",url)
      $("#searchForm").submit()
    }
  </script>
</head>
<body>
<form:form id="searchForm" modelAttribute="info" action="${ctx}/info/entrustSupplyTalking/" method="post" class="breadcrumb form-search">
  <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
  <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

  <input type="button" name="selectAll" value="全选" onclick="selectAllInfo()"/>
  <input type="button" name="deleteSelected" value="删除" onclick="deleteSelectedInfo()"/>
  状态：<select id="statue" name="statue" class="required input-medium" onchange="changeStatue()">
  <%--默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功--%>
  <option value="0" <c:if test="${info.statue==0}">selected</c:if>>待审核</option>
  <option value="1" <c:if test="${info.statue==1}">selected</c:if>>审核已通过</option>
  <option value="-1" <c:if test="${info.statue==-1}">selected</c:if>>审核未通过</option>
  <option value="-2" <c:if test="${info.statue==-2}">selected</c:if>>删除</option>
  <option value="2" <c:if test="${info.statue==2}">selected</c:if>>正在洽谈</option>
  <option value="3" <c:if test="${info.statue==3}">selected</c:if>>等待打款</option>
  <option value="4" <c:if test="${info.statue==4}">selected</c:if>>交易成功</option>
  </select>
  <label>联系人：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <label>联系手机：</label><form:input path="telephone" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <label>供应产品：</label><form:input path="pgoodsName" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <label>供应品种：</label><form:input path="goodsName" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
</form:form>
<sys:message content="${message}"/>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
  <thead><tr><th></th><th>状态</th><th>供应单流水号</th><th>联系方式 </th><th>采购内容</th><th>供应内容</th><th>状态说明</th><th>备注</th><th>操作</th></tr></thead>
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
        <c:if test="${info.entrustSupplyDemand!=null}">

          <table class="table table-striped table-bordered table-condensed thWidth">

            <tbody>
            <tr>
              <th>品种</th>
              <td>${info.entrustSupplyDemand.pgoodsName}-${info.entrustSupplyDemand.goodsName}</td>
            </tr>
            <tr>
              <th>意向价格</th>
              <td>${info.entrustSupplyDemand.fromPerPrice}<c:if test="${info.entrustSupplyDemand.toPerPrice>0}">-${info.entrustSupplyDemand.toPerPrice}</c:if>/${info.entrustSupplyDemand.priceUnitValue}</td>
            </tr>
            <tr>
              <th>供应日期</th>
              <td>
                <fmt:formatDate pattern="y-M-d" value="${info.entrustSupplyDemand.validateStartDate}" type="Both"/>
                至
                <fmt:formatDate pattern="y-M-d" value="${info.entrustSupplyDemand.validateEndDate}" type="Both"/>
              </td>
            </tr>
            <tr>
              <th>规格</th>
              <td>${info.entrustSupplyDemand.checkedSpecs}</td>
            </tr>
            <tr>
              <th>收货地址</th>
              <td>${info.entrustSupplyDemand.provinceName} ${info.entrustSupplyDemand.cityName} ${info.entrustSupplyDemand.areaName} ${info.entrustSupplyDemand.detailArea}</td>
            </tr>
            </tbody>
          </table>
        </c:if>
      </td>
      <td>
        <table class="table table-striped table-bordered table-condensed">

          <tbody>
          <tr>
            <th>品种</th>
            <td>${info.pgoodsName}-${info.goodsName}</td>
          </tr>
          <tr>
            <th>意向价格</th>
            <td>${info.fromPerPrice}<c:if test="${info.toPerPrice>0}">-${info.toPerPrice}</c:if>${info.priceUnitValue}</td>
          </tr>
          <tr>
            <th>供应日期</th>
            <td>
              <fmt:formatDate pattern="y-M-d" value="${info.validateStartDate}" type="Both"/>
              至
              <fmt:formatDate pattern="y-M-d" value="${info.validateEndDate}" type="Both"/>
            </td>
          </tr>
          <tr>
            <th>规格</th>
            <td>${info.checkedSpecs}</td>
          </tr>
          <tr>
            <th>收货地址</th>
            <td>${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}</td>
          </tr>
          </tbody>
        </table>
      </td>
      <td>
          ${info.statueIntro}
      </td>
      <td>
          ${info.remarks}
      </td>

      <td>
        <a href="${ctx}/info/checkSupply?id=${info.id}">编辑</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${ctx}/info/supplyInfo?id=${info.id}">详情</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:" onclick="toDeleteSingle('${info.id}',this)">删除</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div class="pagination">${page}</div>
<%--<c:forEach items="${infos}" var="info">--%>
<%--<c:out value="${info.id}"></c:out>--%>
<%--</c:forEach>--%>

</body>
</html>
