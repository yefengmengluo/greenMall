<%--
  Created by IntelliJ IDEA.
  User: liujiabao
  Date: 2015/12/22 0022
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<jsp:useBean id="info" class="com.wk.p3.greenmall.modules.info.entity.Info" scope="request" ></jsp:useBean>
<html>
<head>
  <script src="${ctxCommon}/common/js/common.js"></script>
  <title>供应审核</title>
  <meta name="decorator" content="default"/>
  <script>
    $(function(){
      getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","showAddressId",'${info.productionProvince}','${info.productionCity}','${info.productionArea}',null,null,null);
      <%--${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}--%>
      getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","reciveAddressId",'${info.provinceId}','${info.cityId}','${info.areaId}',"provinceShow","cityShow","areaShow");

    })
    function getPgoodsInfos(goodsId,obj){
      $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/gcategory/getGcategorysByPgoodsId",
        data:{
          goodsId:goodsId
        },
        success:function(data){
          if(data){

            var parentSiblings = eval(data.parentSiblings)
            var siblings = eval(data.siblings)
            $("#gcategoryParentSiblings").empty()
            var offset = $("#pgoodsName").offset()
            $("#showPgoodsSelectDiv").offset({
              left:offset.left,
              top:offset.top+30
            })
            $("#showPgoodsSelectDiv").show()
            for(var i=0;i<parentSiblings.length;i++){
              if(i==0) {
                $("#gcategoryParentSiblings").append('<li class="active" onclick="showSiblings('+parentSiblings[i].id+')"><a href="#tab_' + parentSiblings[i].id + '" data-toggle="tab">' + parentSiblings[i].name + '</a></li>')
              }else{
                $("#gcategoryParentSiblings").append('<li onclick="showSiblings('+parentSiblings[i].id+')"><a href="#tab_' + parentSiblings[i].id + '" data-toggle="tab">' + parentSiblings[i].name + '</a></li>')
              }
            }
            toShowSiblings(siblings,parentSiblings[0].id)

          }

        }
      })
    }

    function showSiblings(id){
      $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/gcategory/getChildCategories",
        data:{
          id:id
        },
        success:function(data) {
          if (data) {
            toShowSiblings(data,id)
          }
        }
      })
    }

    function toShowSiblings(siblings,pid){
      $("#gcategorySiblings").empty()
      var s = ""
      s = '<div class="tab-pane fade in active" id="tab_'+pid+'"><table>'
      var j=13//一行显示多少个
      for(var i=0;i<siblings.length;i++){
        if(i!=0){
          if(i%j==0) {
            s += '</tr>'

            s += '<tr><td><a href="javascript:" onclick="selectedPgoods('+siblings[i].id+',\''+siblings[i].name+'\',this)">'+siblings[i].name+'</a></td>'
          }else{
            s += '<td><a href="javascript:" onclick="selectedPgoods('+siblings[i].id+',\''+siblings[i].name+'\',this)">'+siblings[i].name+'</a></td>'
          }
        }else{
          s += '<tr><td><a href="javascript:" onclick="selectedPgoods('+siblings[i].id+',\''+siblings[i].name+'\',this)">'+siblings[i].name+'</a></td>'
        }
      }
      if(siblings.length>0){
        s += '</tr>'
      }
      s += '</table></div>'
      $("#gcategorySiblings").append(s)
    }

    function selectedPgoods(pgoodsId,pgoodsName,obj){

      $("#showPgoodsSelectDiv").offset({
        top:0,
        left:0
      })
      $("#showPgoodsSelectDiv").hide()
      $("#pgoodsId").val(pgoodsId)
      $("#pgoodsName").val(pgoodsName)
      $("#goodsId").val("")
      $("#goodsName").val("")

      queryAllNumberUnitByGcategory(pgoodsId)
      queryAllNonstandardNumberUnitByGcategory(pgoodsId)

    }

    function queryAllNumberUnitByGcategory(id){
      $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/queryAllNumberUnitByGcategory",
        data:{
          id:id
        },
        success:function(data) {
          $("#numberUnitId").empty().append("<option value=''>--请选择--</option>")
          $("#priceUnitId").empty().append("<option value=''>--请选择--</option>")
          for(var i=0;i<data.length;i++){
            $("#numberUnitId").append("<option value='"+data[i].id+"'>"+data[i].unitName+"</option>")
            $("#priceUnitId").append("<option value='"+data[i].id+"'>元/"+data[i].unitName+"</option>")
          }
        }
      })
    }

    function queryAllNonstandardNumberUnitByGcategory(id){
      $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/queryAllNonstandardNumberUnitByGcategory",
        data:{
          id:id
        },
        success:function(data) {
          $("#nonstandardNumberUnitId").empty().append("<option value=''>--请选择--</option>")
          for(var i=0;i<data.length;i++){
            var s = "<option value='"+data[i].id+"'"
            if("${info.nonstandardNumberUnitId}"==data[i].id){
              s += " selected "
            }
            $("#nonstandardNumberUnitId").append(s+">"+data[i].unitName+"</option>")
          }
        }
      })
    }
    function getGoodsByPgoods(obj){
      var pgoodsId = $("#pgoodsId").val()
      if(pgoodsId){
        $.ajax({
          type:"post",
          url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/gcategory/getChildCategories",
          data:{
            id:pgoodsId
          },
          success:function(data) {
            if (data) {
              $("#showGoodsDiv").empty()
              var s = ""
              s = '<table>'
              var j=13//一行显示多少个
              for(var i=0;i<data.length;i++){
                if(i!=0){
                  if(i%j==0) {
                    s += '</tr>'

                    s += '<tr><td><a href="javascript:" onclick="selectedGoods('+data[i].id+',\''+data[i].name+'\',this)">'+data[i].name+'</a></td>'
                  }else{
                    s += '<td><a href="javascript:" onclick="selectedGoods('+data[i].id+',\''+data[i].name+'\',this)">'+data[i].name+'</a></td>'
                  }
                }else{
                  s += '<tr><td><a href="javascript:" onclick="selectedGoods('+data[i].id+',\''+data[i].name+'\',this)">'+data[i].name+'</a></td>'
                }
              }
              if(data.length>0){
                s += '</tr>'
              }
              s += '</table>'
              $("#showGoodsDiv").append(s)
              var offset = $(obj).offset()
              $("#showGoodsSelectDiv").offset({
                left:offset.left,
                top:offset.top+30
              })
              $("#showGoodsSelectDiv").show()

            }
          }
        })
      }
    }

    function selectedGoods(id,name,obj){
      $("#goodsId").val(id)
      $("#goodsName").val(name)

      $("#showGoodsSelectDiv").offset({
        top:0,
        left:0
      })
      $("#showGoodsSelectDiv").hide()


    }

    function writeOtherGoods(){
      var otherGoodsName = $("#otherGoodsName").val()
      selectedGoods("",otherGoodsName,null)
    }



    function getSpecValByGcataMap(pgoodsId,obj){
      $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/gspec/getSpecValByGcataMap",
        data:{
          id:pgoodsId
        },
        success:function(data) {
          var selectedSpecs = data.specList
          if (selectedSpecs) {
//            $("#showCheckedSpecsDiv").show()
            $("#showCheckedSpecs").empty()
            var s = ""
            s = '<table>'
            var j=6//一行显示多少个
            for(var i=0;i<selectedSpecs.length;i++){
              var specStrsArray = selectedSpecs[i].specStrs.split(",")
              var specStrsArrayId = selectedSpecs[i].specStrsId.split(",")
              var n = specStrsArray.length
              s+='<tr>'
                s += '<th>'+selectedSpecs[i].name+':<input class="spectIds" type="hidden" id="spectId_' + selectedSpecs[i].id + '" name="spectId_' + selectedSpecs[i].id + '"></th><input class="spectNames" type="hidden" id="spectName_' + selectedSpecs[i].id + '" name="spectName_' + selectedSpecs[i].id + '"></th>'
                for(var m=0;m<n;m++) {
                  if(m%j==0) {
                    if(m!=0){
                      s+='</tr><tr><td></td>'
                    }
                  }
                  if(specStrsArray[m]) {
                    s += '<td><input type="button" name="specValue" class="btn  spect_' + selectedSpecs[i].id + '" onclick="selectedSpect(\'' + selectedSpecs[i].id + '\',\'' + specStrsArrayId[m] + '\',\'' + specStrsArray[m] + '\',this)" value="' + specStrsArray[m] + '"></td>'
                  }
                }
              s+='</tr>'
            }

            s += '</table>'
            $("#showCheckedSpecs").append(s)
            var offset = $(obj).offset()
            $("#showCheckedSpecsDiv").offset({
              left:offset.left,
              top:offset.top+30
            })
            $("#showCheckedSpecsDiv").show()
          }
        }
      })
    }

    function selectedSpect(selectedSpecsId,selectedSpecsValueId,selectedSpecsValueName,obj){
      $(".spect_"+selectedSpecsId).removeClass("btn-primary")
      $("#spectId_"+selectedSpecsId).val(selectedSpecsValueId)
      $("#spectName_"+selectedSpecsId).val(selectedSpecsValueName)
      $(obj).addClass("btn-primary")
//      console.log($("#spectId_"+selectedSpecsId).val())
    }

    function writeOtherSpecName(){
      var ids = ""
      var names = ""
      var otherSpecName = $("#otherSpecName").val()
      $("#checkedOtherSpecName").val(otherSpecName)
      $(".spectIds").each(function(){
        if($(this).val()){
          ids+=$(this).val()+","
        }
      })
      if(ids){
        ids = ids.substr(0,ids.length-1)
      }
      $(".spectNames").each(function(){
        if($(this).val()){
          names+=$(this).val()+","
        }
      })
      if(names){
        if(otherSpecName){
          names += otherSpecName
        }else{
          names = names.substr(0,names.length-1)
        }
      }else{
        names = otherSpecName
      }
      $("#showCheckedSpecsDiv").offset({
        left:0,
        top:0
      })
      $("#showCheckedSpecsDiv").hide()
      $("#checkedSpecs").val(names)
      $("#checkedSpecsId").val(ids)

    }
    function saveEditInfo(id,addressId){

      var pgoodsId = $("#pgoodsId").val()
      var pgoodsName =  $("#pgoodsName").val()
      if(!pgoodsId){
        alert("产品不允许为空！")
        return;
      }
      var goodsId = $("#goodsId").val()
      if(!goodsId && !pgoodsName){
        alert("品种不允许为空！")
        return;
      }
      var goodsName =  $("#goodsName").val()
      var checkedSpecsId =  $("#checkedSpecsId").val()
      var checkedSpecs = $("#checkedSpecs").val()
      var specs =  $("#checkedOtherSpecName").val()
//      $("#specs").val(specs)
      //货源地id
      var productionProvince =  $("#provinceSelect_null option:selected").val()
      var productionCity =  $("#citySelect_null option:selected").val()
      var productionArea =  $("#areaSelect_null option:selected").val()

      /*if(!productionProvince || !productionCity || !productionArea){
        alert("省市区都不允许为空！")
        return;
      }*/

      //货源地name
      var productionProvinceName =  $("#provinceSelect_null option:selected").text()
      var productionCityName =  $("#citySelect_null option:selected").text()
      var productionAreaName =  $("#areaSelect_null option:selected").text()
      var productionDetailArea =  $("#productionDetailArea").val()


      //数量
      var number =  $("#number").val()
      var numberUnitId =  $("#numberUnitId option:selected").val()
      var numberUnitValue =  $("#numberUnitId option:selected").text()
      if(!number || !numberUnitId){
        alert("可量化的数量和单位都不允许为空")
        return;
      }
      var nonstandardNumber = $("#nonstandardNumber").val()
      var nonstandardNumberUnitId =  $("#nonstandardNumberUnitId option:selected").val()
      var nonstandardNumberUnitName =  $("#nonstandardNumberUnitId option:selected").text()

      if(nonstandardNumber && !nonstandardNumberUnitId || !nonstandardNumber && nonstandardNumberUnitId  ){
        alert("不可量化的单位和数量，必须同时填写或者不填写")
        return;
      }

      //价钱
      var fromPerPrice =  $("#fromPerPrice").val()
      var toPerPrice =  $("#toPerPrice").val()
      var priceUnitId = $("#priceUnitId option:selected").val()
      var priceUnitValue = $("#priceUnitId option:selected").text()

      if(priceUnitValue=="--请选择--"){
        priceUnitValue=''
      }

      var validateStartDate = $("#validateStartDate").val()
      var validateEndDate = $("#validateEndDate").val()

      if((fromPerPrice || toPerPrice) && !priceUnitId || (!fromPerPrice || !toPerPrice)&&priceUnitId){
        alert("价位和价位单位必须同时填写或者不填写")
        return;
      }

      if(!validateStartDate){
        alert("有效起始日期不能为空")
        return;
      }
      if(!validateEndDate){
        alert("有效结束日期不能为空")
        return;
      }

      if(validateStartDate>validateEndDate){
        alert("起始日期不能大于结束日期")
        return;
      }
      var userName = $("#userName").val()
      var telephone = $("#telephone").val()
      if(!userName){
        alert("联系人不能为空")
        return;
      }

      if(!telephone){
        alert("联系人手机号不能为空")
        return;
      }
      //发货地
      var provinceId =  $("#provinceSelect_provinceShow option:selected").val()
      var cityId =  $("#citySelect_cityShow option:selected").val()
      var areaId =  $("#areaSelect_areaShow option:selected").val()
      var provinceName =  $("#provinceSelect_provinceShow option:selected").text()
      var cityName =  $("#citySelect_cityShow option:selected").text()
      var areaName =  $("#areaSelect_areaShow option:selected").text()
      var detailArea =  $("#detailArea").val()

      if(!provinceId || !cityId || !areaId){
        alert("发货地址中的省、市、区都不能为空")
        return;
      }

      var statue = $("#statue option:selected").val()

      var statueIntro =  $("#statueIntro").val()
      var remarks =  $("#remarks").val()

      $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/saveEditInfo",
        data:{
          id:id,
          statueIntro:statueIntro,
          type:"supply",
          pgoodsId:pgoodsId,
          statue:statue,
          pgoodsName:pgoodsName,
          goodsId:goodsId,
          goodsName:goodsName,
          checkedSpecsId:checkedSpecsId,
          checkedSpecs:checkedSpecs,
          specs:specs,
          productionProvince:productionProvince,
          productionCity:productionCity,
          productionArea:productionArea,
          productionProvinceName:productionProvinceName,
          productionCityName:productionCityName,
          productionAreaName:productionAreaName,
          productionDetailArea:productionDetailArea,
          number:number,
          numberUnitId:numberUnitId,
          numberUnitValue:numberUnitValue,
          nonstandardNumber:nonstandardNumber,
          nonstandardNumberUnitId:nonstandardNumberUnitId,
          nonstandardNumberUnitName:nonstandardNumberUnitName,
          fromPerPrice:fromPerPrice,
          toPerPrice:toPerPrice,
          priceUnitId:priceUnitId,
          priceUnitValue:priceUnitValue,
          validateStartDate:validateStartDate,
          validateEndDate:validateEndDate,
          userName:userName,
          telephone:telephone,
          provinceId:provinceId,
          cityId:cityId,
          areaId:areaId,
          provinceName:provinceName,
          cityName:cityName,
          areaName:areaName,
          detailArea:detailArea,
          remarks:remarks,
          addressId:addressId
        },
        success:function(data) {
          if(data){
            alert(data)
          }else{
            alert("保存成功！")
            location.reload()
          }
        }
      })
      $("#saveEditForm").submit()
    }
  </script>
</head>
<body>

<div id="infoDiv">
<%--<form:form id="saveEditForm" modelAttribute="info" action="${ctx}/info/saveEditInfo/" method="post" class="breadcrumb form-search">--%>
  <%--<input type="hidden" id="specs" name="specs">--%>
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
    <tr>
      <td>
        供应信息
      </td>
      <td style="width:40%">
        <table>
          <tr>
            <td> 产品 </td>
            <td>
              ${info.pgoodsName}
            </td>
          </tr>
          <tr>
            <td> 品种</td>
            <td>
              ${info.goodsName}
            </td>
          </tr>
          <tr>
            <td>规格</td>
            <td>
              ${info.checkedSpecs}
            </td>
          </tr>
          <tr>
            <td> 货源地 </td>
            <td>
              ${info.productionProvinceName} ${info.productionCityName} ${info.productionAreaName}
            </td>
          </tr>
          <tr>
            <td>详细地址</td>
            <td>
              ${info.productionDetailArea}
            </td>
          </tr>
          <tr>
            <td> 供应重量 </td>
            <td>
              ${info.number} ${info.numberUnitValue}
            </td>
          </tr>
          <tr>
            <td> 供应价格</td>
            <td>
              ${info.fromPerPrice}<c:if test="${info.toPerPrice>0}">-${info.toPerPrice}</c:if>${info.priceUnitValue}
            </td>
          </tr>
          <tr>
            <td> 供应有效期 </td>
            <td>
              <fmt:formatDate pattern="y-M-d" value="${info.validateStartDate}" type="Both"/>
              至
              <fmt:formatDate pattern="y-M-d" value="${info.validateEndDate}" type="Both"/>
            </td>
          </tr>
        </table>
      </td>
      <td>确认后的供应信息</td>
      <td>
        <table>
          <tr>
            <td> <span style="color:red">*</span>产品 </td>
            <td>
              <input type="hidden" id="pgoodsId" name="pgoodsId" value="${info.pgoodsId}">
              <input type="text" name="pgoodsName" id="pgoodsName" value="${info.pgoodsName}" readonly onfocus="getPgoodsInfos(${info.pgoodsId},this)">

              <div  id="showPgoodsSelectDiv" style="position: absolute;width: 28%;top: 0px;left: 0px;background-color:#cc9966;display:none;">
                <div style="margin:20px;">
                  <ul class="nav nav-tabs" id="gcategoryParentSiblings">



                  </ul>
                  <div class="tab-content" id="gcategorySiblings">

                  </div>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td><span style="color:red">*</span> 品种</td>
            <td>
                <input type="hidden" name="goodsId" id="goodsId" value="${info.goodsId}">
                <input type="text" name="goodsName" id="goodsName" value="${info.goodsName}" readonly="true" onclick="getGoodsByPgoods(this)">
                <div  id="showGoodsSelectDiv" style="position: absolute;width: 28%;top: 0px;left: 0px;background-color:#cc9966;display:none;">
                  <div style="margin: 20px;" id="showGoodsDiv">

                  </div>
                  <div style="margin-left: 20px;">
                    <input type="text" placeholder="其他(请填写)" id="otherGoodsName" style="display:none">
                    <input type="button" value="确认" onclick="writeOtherGoods()" style="display:none">
                  </div>
                </div>
            </td>
          </tr>
          <tr>
            <td>规格</td>
            <td>
              <input type="hidden" name="checkedSpecsId" id="checkedSpecsId"  value="${info.checkedSpecsId}">
              <input type="hidden" name="specs" id="checkedOtherSpecName" value="${info.specs}">
              <input type="text"  id="checkedSpecs" value="${info.checkedSpecs}" readonly onclick="getSpecValByGcataMap('${info.pgoodsId}',this)">
              <div  id="showCheckedSpecsDiv" style="position: absolute;width: 40%;top: 0px;left: 0px;background-color:#cc9966;display:none;Z-INDEX: 999">
                <div style="margin: 20px;" id="showCheckedSpecs">

                </div>
                <div style="margin-left: 20px;">
                  <input type="text" placeholder="其他(请填写)" id="otherSpecName">
                  <input type="button" value="保存" onclick="writeOtherSpecName()">
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td> 货源地 </td>
            <td id="showAddressId">
              <%--${info.productionProvinceName} ${info.productionCityName} ${info.productionAreaName} ${info.productionDetailArea}--%>
            </td>
          </tr>
          <tr>
            <td>详细地址</td>
            <td>
              <input type="text" id="productionDetailArea" value="${info.productionDetailArea}" name="productionDetailArea">
            </td>
          </tr>
          <tr>
          <tr>
            <td> 供应重量 </td>
            <td>
              <span style="color:red">*</span>可量化数量：<input type="text" name="number" id="number" value="${info.number}">
              <%--<form:select path="${info.numberUnitId}" items="${numberUnits}"/>--%>
              <select id="numberUnitId" name="numberUnitId" style="width:100px">
                <option value="">--请选择--</option>
                <c:forEach items="${numberUnits}" var="numberU">
                  <option value="${numberU.id}" <c:if test="${info.numberUnitId==numberU.id}">selected</c:if>>${numberU.unitName}</option>
                </c:forEach>
              </select>

              不可量化数量：<input type="text" name="nonstandardNumber" id="nonstandardNumber" value="${info.nonstandardNumber}">
              <select name="nonstandardNumberUnitId" id="nonstandardNumberUnitId" style="width:100px">
                <option value="">--请选择--</option>
                <c:forEach items="${nonstandardNumberUnits}" var="nonNumberU">
                  <option value="${nonNumberU.id}" <c:if test="${info.nonstandardNumberUnitId==nonNumberU.id}">selected</c:if>>${nonNumberU.unitName}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td> 供应价格</td>
            <td>
              <input type="text" name="fromPerPrice" id="fromPerPrice" value="${info.fromPerPrice}">
              至
              <input type="text" name="toPerPrice" id="toPerPrice" value="${info.toPerPrice}">
              <select name="priceUnitId" id="priceUnitId" style="width:100px">
                <option value="">--请选择--</option>
                <c:forEach items="${numberUnits}" var="numberU">
                  <option value="${numberU.id}" <c:if test="${info.priceUnitId==numberU.id}">selected</c:if>>元/${numberU.unitName}</option>
                </c:forEach>
              </select>

            </td>
          </tr>
          <tr>
            <td><span style="color:red">*</span> 供应有效期 </td>
            <td>
              <input id="validateStartDate" name="validateStartDate" type="text" style="width:157px" readonly="readonly" maxlength="20" class="input-mini Wdate"
                     value="<fmt:formatDate value="${info.validateStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
              <label>&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</label><input id="validateEndDate" style="width:157px" name="validateEndDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                                                                          value="<fmt:formatDate value="${info.validateEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>

    <%--------------------------------------------------------------------%>
    <tr>
      <td>
        发货人信息
      </td>
      <td>
        <table>
          <tr>
            <td> 联系人 </td>
            <td>
              ${info.userName}
            </td>
          </tr>
          <tr>
            <td> 手机</td>
            <td>
              ${info.telephone}
            </td>
          </tr>
          <tr>
            <td>发货地址</td>
            <td>
              ${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}
            </td>

          </tr>

        </table>
      </td>
      <td>
        确认后发货人信息
      </td>
      <td>
        <table>
          <tr>
            <td> <span style="color:red">*</span>联系人 </td>
            <td>
              <input type="text" name="userName" id="userName" value="${info.userName}">
            </td>
          </tr>
          <tr>
            <td> <span style="color:red">*</span>手机</td>
            <td>
              <input type="text" name="telephone" id="telephone" value="${info.telephone}">
            </td>
          </tr>
          <tr>
            <td><span style="color:red">*</span>发货地址：</td>
            <td id="reciveAddressId">
              <span id="provinceShow"></span>
              <span id="cityShow"></span>
              <span id="areaShow"></span>
            </td>
            </tr>
          <tr>
            <td>详细地址：</td>
            <td>
              <input type="text" name="detailArea" id="detailArea" value="${info.detailArea}">
            </td>
          </tr>

        </table>
      </td>
    </tr>
    <%---------------------------------------------------------------------------------------------------%>
    <tr>
      <td>
        其他
      </td>
      <td>
        <table>
          <tr>
            <td>供应备注</td>
            <td>
              ${info.remarks}
            </td>
          </tr>
        </table>
      </td>
      <td>确认后的其他</td>
      <td>
        <table>
          <tr>
            <td>供应备注</td>
            <td>
              <textArea name="remarks" id="remarks">${info.remarks}</textArea>
            </td>
          </tr>
        </table>
      </td>
    </tr>
<%---------------------------------------------------------------------------%>
    <tr>
      <td>
        状态：
      </td>
      <td>
        <c:if test="${info.statue==0}">待审核</c:if>
        <c:if test="${info.statue==1}">审核已通过</c:if>
        <c:if test="${info.statue==-1}">审核未通过</c:if>
        <c:if test="${info.statue==-2}">删除</c:if>
        <c:if test="${info.statue==2}">正在洽谈</c:if>
        <c:if test="${info.statue==3}">等待打款</c:if>
        <c:if test="${info.statue==4}">交易成功</c:if>
        &nbsp;&nbsp;
        ${info.statueIntro}
      </td>
      <td>审核后的状态：</td>
      <td>
        <select id="statue" name="statue" class="required input-medium" >
          <%--默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功--%>
          <option value="0" <c:if test="${info.statue==0}">selected</c:if>>待审核</option>
          <option value="1" <c:if test="${info.statue==1}">selected</c:if>>审核已通过</option>
          <option value="-1" <c:if test="${info.statue==-1}">selected</c:if>>审核未通过</option>
          <option value="-2" <c:if test="${info.statue==-2}">selected</c:if>>删除</option>
        <%--  <option value="2" <c:if test="${info.statue==2}">selected</c:if>>正在洽谈</option>
          <option value="3" <c:if test="${info.statue==3}">selected</c:if>>等待打款</option>
          <option value="4" <c:if test="${info.statue==4}">selected</c:if>>交易成功</option>--%>
        </select>
        <textarea name="statueIntro" id="statueIntro">${info.statueIntro}</textarea>
      </td>
    </tr>
    <tr>
      <td><input type="button" class="btn btn-primary btn-sm" value="返回" onclick="history.go(-1)"></td>
      <td><input type="submit" class="btn btn-primary btn-sm" onclick="saveEditInfo('${info.id}','${info.addressId}')" value="保存"></td>

    </tr>
  </table>
  <%--</form:form>--%>
</div>

</body>
</html>
