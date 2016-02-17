<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/1/19 0019
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script type="text/javascript">
  var type=0
  function setSearchType(val){
    type = val
    if(val == 1){
      $("#searchText").attr("placeholder","请输入查询的商品类别")
    } else if(val == 2){
      $("#searchText").attr("placeholder","请输入查询的商家名称")
    } else if(val == 3){
      $("#searchText").attr("placeholder","请输入查询的交易地点")
    }
    $(".head_middle_content a").css("color","black");
    $("#topSearch"+val).css("text-decoration", "none");
    $("#topSearch"+val).css("color", "#f08300");
  }
  function search(){
    if(type == 0){
      location.href="${front}/supplyList?type=supply&goodsName="+$("#searchText").val()
    }
    //类别
    if(type == 1){
      location.href="${front}/supplyList?type=supply&goodsName="+$("#searchText").val()
    }
    //公司(商家)
    if(type == 2){
      location.href="${front}/supplyList?type=supply&organizationName="+$("#searchText").val()
    }
    //交易地点
    if(type == 3){
      location.href="${front}/supplyList?type=supply&detailArea="+$("#searchText").val()
    }
  }
  function cancelSearch(){
    $("#searchText").val("");
  }
  $(function(){
    setTimeout(function () {
      $(".leftTable1 tr:eq(5)").css("border-bottom","none");
      $(".leftTable2 tr:eq(5)").css("border-bottom","none");
      $(".leftTable3 tr:eq(5)").css("border-bottom","none");
//                $(".head_middle_content a:eq(0)").css("color","#f08300");
//                $(".bottomImg").hide();
//                $($(".bottomImg")[0]).show();

    }, 10);
  })

  function inputGoodsGcategory(obj){
    var inputStr = $(obj).val()
    if(inputStr){
      $("#xShowId").show()
    }else{
      $("#xShowId").hide()
    }
  }

</script>
<div class="head_middle body">
  <a href="${pageContext.request.contextPath}/${fns:getFrontPath()}">
    <img src="${ctxStatic}/home/images/guoguo/guoguo.png" alt="picture miss" class="logo"></a>
  <div class="head_middle_content">
     		<span>
     			 <a href="javascript:void(0)" onclick="setSearchType(1)" id="topSearch1" style="color:#f08300">水果</a>
     		</span>
    <span class="line">|</span>
    <%--<span class="ml20">--%>
    <%--<a href="##">求购</a>--%>
    <%--</span>--%>
    <%--<span class="line">|</span>--%>
     		<span class="ml20">
     			<a href="javascript:void(0)" onclick="setSearchType(2)" id="topSearch2">供应商</a>
     		</span>
    <span class="line">|</span>
     		<span class="ml20">
     			<a href="javascript:void(0)" onclick="setSearchType(3)" id="topSearch3">基地</a>
     		</span>
  </div>
  <br>
  <input type="text" placeholder="请输入查询的商品类别"  id="searchText" onkeyup="inputGoodsGcategory(this)"/>
  <img src="${ctxStatic}/home/images/guoguo/x.png" class="x-no" id="xShowId" onclick="cancelSearch()" style="display:none">
  <a href="##" >
    <input type="button" class="buttonOne" onclick="search()" style="cursor:pointer"/>
  </a>
  <a href="${front}/entrustFindGoods">
    <input type="button" class="buttonTwo" value="发布采购需求" style="cursor:pointer"/>
  </a>
  <img src="${ctxStatic}/home/images/guoguo/header_qr.png" class="twoMa">
  <div class="styleOne ">
    果果网水果基地
    <br>尽享最新水果供应</div>
</div>



<div class="head_menu">
  <div class="head_menu_content body">
     <span class="menu_one" id="sometimeShow"><img src="${ctxStatic}/home/images/guoguo/nav_icon.png" class="ml20"><span class="pl10">所有水果分类</span></span>
    <ul>

      <li><a href="${pageContext.request.contextPath}/${fns:getFrontPath()}">首页</a></li>

      <li id="supplyTab"><a href="${front}/supplyList?type=supply">水果供应</a></li>

      <li id="demandTab"><a href="${front}/toDemandList?type=demand">水果采购</a></li>

      <li><a href="#">行情报价</a></li>
      <li><a href="#">找加工</a></li>
      <li><a href="#">找技术</a></li>
      <li><a href="#">找物流</a></li>
      <li><a href="#">找仓储</a></li>
      <li><a href="#">果品病虫防治</a></li>



      <%--<li><a href="##">行情分析</a></li>

      <li><a href="##">企业黄页</a></li>

      <li><a href="business.html">招商中心</a></li>--%>

    </ul>
  </div>
  <script type="text/javascript">
    $(function(){
      var url = window.location.href
      var rootUrl = getRootPath()
      var flag =((url == rootUrl+"/")||url.indexOf("index")>0);
      if(url.indexOf(rootUrl) >= 0 && flag){
          $("#sometimeShow").show()
      }else if(rootUrl == 'http:'){
        $("#sometimeShow").show()
      }else{
        $("#sometimeShow").hide()
      }
      if('${info.type}'=='supply'){
        $("#supplyTab").css("background-color", "#e96300");
      }else if('${info.type}'=='demand'){
        $("#demandTab").css("background-color", "#e96300");
      }

    })

    function getRootPath() {
      var curWwwPath = window.document.location.href;
      var pathName = window.document.location.pathname;
      var pos = curWwwPath.indexOf(pathName);
      var localhostPath = curWwwPath.substring(0, pos);
      var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
      return (localhostPath + projectName);
    }
  </script>
</div>
