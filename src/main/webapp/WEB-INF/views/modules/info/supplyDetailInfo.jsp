<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/30 0030
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>供应详情信息</title>
  <script src="${ctxCommon}/common/js/common.js"></script>
  <meta name="decorator" content="default"/>

  <script>
    $(function(){
      showSystemMatchingDemands()
    })
    function showSystemMatchingDemands(obj){
      if($(obj)) {
        $(obj).parent().siblings().removeClass("active")
        $(obj).parent().addClass("active")
      }
      $.ajax({
        type:"post",
        url:"${ctx}/info/querySystemMatchingDemands",
        data:{
          id:'${info.id}${info.statue}',
          detailId:'${info.id}'
        },
        success:function(data){
          if(data){
            $("#showInfo").empty().append(data)
          }

        }
      })
    }
    function showEntrustDemands(obj,id,pageNo,pageSize){
      $(obj).parent().siblings().removeClass("active")
      $(obj).parent().addClass("active")
      $.ajax({
        type:"post",
        url:"${ctx}/info/showEntrustDemands",
        data:{
          id:id,
          pageNo:pageNo,
          pageSize:pageSize
        },
        success:function(data){
          if(data){
            $("#showInfo").empty().append(data)
          }

        }
      })
    }

    function generateOrder(supplyid,demandid,obj){
      supplyid = '${info.id}'
      console.log(supplyid)
      console.log(demandid)
      $.ajax({
        type:"post",
        url:"${ctx}/deal/tradeOrder/createOrder",
        data:{
          supplyid:supplyid,
          demandid:demandid
        },
        success:function(data){
          if(data=="0"){
            alert("生成订单错误！")
          }else{
            alert("生成订单成功！")
            location.reload()
          }

        }
      })
    }
  </script>
</head>
<body>
<div id="infoDiv">
  <div>
    <h3>详情</h3>
    <table class="table table-striped table-bordered table-condensed">
      <thead>
      <th>供应单号</th>
      <th>产品</th>
      <th>品种</th>
      <th>规格</th>
      <th>货源地</th>
      <th>供应重量</th>
      <th>供应价格</th>
      <th>供应有效期</th>
      <th>供应备注</th>
      <th>联系人</th>
      <th>手机</th>
      <th>发货地址</th>
      <th>发布时间</th>
      <th>操作</th>
      </thead>
      <tbody>
      <tr>
        <td>${info.id}</td>
        <td>${info.pgoodsName}</td>
        <td>${info.goodsName}</td>
        <td>${info.specs}</td>
        <td>${info.productionProvinceName} ${info.productionCityName} ${info.productionAreaName}</td>
        <td>${info.number} ${info.numberUnitValue}</td>
        <td> ${info.fromPerPrice}<c:if test="${info.toPerPrice>0}">-${info.toPerPrice}</c:if>${info.priceUnitValue}</td>
        <td>
          <fmt:formatDate pattern="y-M-d" value="${info.validateStartDate}" type="Both"/>
          至
          <fmt:formatDate pattern="y-M-d" value="${info.validateEndDate}" type="Both"/>
        </td>
        <td>${info.remarks}</td>
        <td>
          ${info.userName}
        </td>
        <td>
          ${info.telephone}
        </td>
        <td>
          ${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}
        </td>
        <td>${fns:formatDate(info.createDate,"yyyy-MM-dd HH:mm:ss")}</td>
        <td>
          <a href="${ctx}/info/checkSupply?id=${info.id}">编辑</a>
        </td>
      </tr>
      </tbody>
    </table>

  </div>
  <div id="showOrderInfo">
    <h3>订单信息</h3>
    <iframe src="${ctx}/deal/tradeOrder/details?supplyid=${info.id}" style="width: 100%;height: 500px;">

    </iframe>
  </div>
  <div id="supplyInfos">
    <h3>意向单</h3>
    <div>
      <ul class="nav nav-tabs" id="myTab">
        <li class="active"><a href="javascript:" onclick="showSystemMatchingDemands(this)">系统匹配的采购</a></li>
        <li><a href="javascript:" id="showSupplyEntrustDemand" onclick="showEntrustDemands(this,'${info.id}',null,null)">委托的采购</a></li>
      </ul>
      <div id="showInfo">

      </div>


    </div>
  </div>
</div>
</body>
</html>
