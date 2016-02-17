<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/30 0030
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
  <script>
    function noIntentionMatchingInfo(infoId,score,obj){
      $.ajax({
        type:"post",
        url:"${ctx}/info/noIntentionMatchingInfo",
        data:{
          id:infoId,score:score
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

    function selectAllInfo(obj){
      var f = $(".selectedChecke:checked")

      if($(".selectedChecke")) {
        if (f.length > 0) {
          $(".selectedChecke").removeAttr("checked")
        } else {
          $(".selectedChecke").attr("checked", "checked")

        }
      }
    }

    function sendSelectNews(){
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
      toSendSelectNews(selectedCheckeds)
    }
    function sendSelectNew(id,obj){
      if(!id){
        return;
      }
      var flag = confirm("确定发送此匹配信息到双方人员吗？")
      if(!flag){
        return
      }
      $.ajax({
        type:"post",
        url:"${ctx}/info/sendSelectNews/",
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
    function toSendSelectNews(ids){
      if(!ids){
        return;
      }
      var flag = confirm("确定发送选中匹配信息到双方人员吗？")
      if(!flag){
        return
      }
      $.ajax({
        type:"post",
        url:"${ctx}/info/sendSelectNews/",
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
  </script>
</head>
<body>
<input type="button" name="selectAll" value="全选" onclick="selectAllInfo()"/>
<input type="button" name="sendSelectNews" value="发送" onclick="sendSelectNews()"/>
<table class="table table-striped table-bordered table-condensed">
  <thead>
    <th></th>
    <th>匹配度</th>
    <th>产品</th>
    <th>品种</th>
    <th>规格</th>
    <th>货源地</th>
    <th>供应重量</th>
    <th>供应价格</th>
    <th>供应有效期</th>
    <th>备注</th>
    <th>联系人</th>
    <th>手机</th>
    <th>发货地址</th>
    <th>操作</th>
  </thead>
  <tbody>
    <c:forEach items="${matching}" var="o">
      <tr>
        <td><input type="checkbox" name="manualNews" class="selectedChecke" value="${o.info.id}"></td>
        <td>${o.score}</td>
        <td>${o.info.pgoodsName}</td>
        <td>${o.info.goodsName}</td>
        <td>${o.info.specs}</td>
        <td>${o.info.productionProvinceName} ${o.info.productionCityName} ${o.info.productionAreaName}</td>
        <td>${o.info.number} ${o.info.numberUnitValue}</td>
        <td> ${o.info.fromPerPrice}<c:if test="${o.info.toPerPrice>0}">-${o.info.toPerPrice}</c:if>${o.info.priceUnitValue}</td>
        <td>
          <fmt:formatDate pattern="y-M-d" value="${o.info.validateStartDate}" type="Both"/>
          至
          <fmt:formatDate pattern="y-M-d" value="${o.info.validateEndDate}" type="Both"/>
        </td>
        <td>${o.info.remarks}</td>
        <td>
            ${o.info.userName}
        </td>
        <td>
            ${o.info.telephone}
        </td>
        <td>
            ${o.info.provinceName} ${o.info.cityName} ${o.info.areaName} ${o.info.detailArea}
        </td>
        <td>
          <a  href="javascript:" onclick="generateOrder('${o.info.id}','${detailId}',this)">生成订单</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:" onclick="noIntentionMatchingInfo('${idStatue}','${o.score}',this)">无意向</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:" onclick="sendSelectNew('${o.info.id}',this)">发送消息</a>
        </td>
      </tr>
    </c:forEach>

  </tbody>
</table>

</body>
</html>
