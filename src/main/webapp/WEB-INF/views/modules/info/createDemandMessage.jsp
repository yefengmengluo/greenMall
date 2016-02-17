<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/30 0030
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<jsp:useBean id="info" class="com.wk.p3.greenmall.modules.info.entity.Info" scope="request" ></jsp:useBean>
<html>
<head>
    <title>审核纯信息求购信息</title>
  <script src="${ctxCommon}/common/js/common.js"></script>
  <meta name="decorator" content="default"/>

  <!-- Custom styles for this template -->
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/home/userCenter/css/base.css">
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/home/userCenter/css/sellerCen.css">
  <script type="text/javascript" src="${ctxStatic}/home/userCenter/js/sellerCen.js"></script>



  <script>
    $(function(){
      getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","showAddressId",'${info.productionProvince}','${info.productionCity}','${info.productionArea}',null,null,null);
      <%--${info.provinceName} ${info.cityName} ${info.areaName} ${info.detailArea}--%>
      getProvinces("${pageContext.request.contextPath}${fns:getAdminPath()}","reciveAddressId",'${info.provinceId}','${info.cityId}','${info.areaId}',"provinceShow","cityShow","areaShow");

    })


    function selectedPgoods(pgoodsId,pgoodsName,obj){


      $("#place").hide();
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



    function getSpecValByGcataMap(obj){
      var pgoodsId = $("#pgoodsId").val()
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
    function saveEditInfo(){

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

      if((fromPerPrice || toPerPrice) && !priceUnitId || (!fromPerPrice || !toPerPrice)&&priceUnitId){
        alert("价位和价位单位必须同时填写或者不填写")
        return;
      }

      var validateStartDate = $("#validateStartDate").val()
      var validateEndDate = $("#validateEndDate").val()

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
      //收货地
      var provinceId =  $("#provinceSelect_provinceShow option:selected").val()
      var cityId =  $("#citySelect_cityShow option:selected").val()
      var areaId =  $("#areaSelect_areaShow option:selected").val()
      var provinceName =  $("#provinceSelect_provinceShow option:selected").text()
      var cityName =  $("#citySelect_cityShow option:selected").text()
      var areaName =  $("#areaSelect_areaShow option:selected").text()
      var detailArea =  $("#detailArea").val()

      if(!provinceId || !cityId || !areaId){
        alert("收货地址中的省、市、区都不能为空")
        return;
      }

      var statue = $("#statue option:selected").val()

      var statueIntro =  $("#statueIntro").val()
      var remarks =  $("#remarks").val()
      $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/saveInfo",
        data:{
         infoMessageId:'${infoMessage.id}',
          statueIntro:statueIntro,
          type:"demand",
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
          remarks:remarks
        },
        success:function(data) {
          if(data){
            alert(data)
          }else{
            alert("新增保存成功！")
//            location.reload()
             window.location.href="${pageContext.request.contextPath}${fns:getAdminPath()}/info/demandMessageCheck";
          }
        }
      })
      $("#saveEditForm").submit()
    }
  </script>
</head>
<body>

<div>
  <h3>详细信息</h3>
  <div>
    <table  class="table table-striped table-bordered table-condensed">
      <thead><th>状态</th><th>采购单流水号</th><th>信息</th>
        <th>备注</th><th>操作</th></thead>
      <tbody>
        <tr>

          <td>
              <%--默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功--%>
            <c:if test="${infoMessage.statue==0}">待审核</c:if>
            <c:if test="${infoMessage.statue==1}">审核已通过</c:if>
            <c:if test="${infoMessage.statue==-1}">审核未通过</c:if>
            <c:if test="${infoMessage.statue==-2}">删除</c:if>
            <c:if test="${infoMessage.statue==2}">正在洽谈</c:if>
            <c:if test="${infoMessage.statue==3}">等待打款</c:if>
            <c:if test="${infoMessage.statue==4}">交易成功</c:if>
          </td>
          <td>${infoMessage.id}</td>
          <td>${infoMessage.message}</td>
          <td>
              ${infoMessage.remarks}
          </td>

          <td>
            <a href="${ctx}/infoMessage/checkDemandMessage?id=${infoMessage.id}">编辑</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:" onclick="toDeleteSingle('${infoMessage.id}',this)">删除</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
<br>
<div id="infoDiv">
<table id="contentTable" class="table table-striped table-bordered table-condensed">
  <tr>
    <td> <span style="color:red">*</span>产品 </td>
    <td>
      <input type="hidden" id="pgoodsId" name="pgoodsId" value="">
      <input type="text" name="pgoodsName" id="pgoodsName" value="" readonly onfocus="choosePl()">
      <div  id="showPgoodsSelectDiv">
        <div class="place" id="place">
          <ul class="nav nav-tabs">
            <c:forEach items="${gcategories}" var="cata">
              <li role="presentation" onmouseover="showChildCata(${cata.id})">
                <a href="#">${cata.name}</a>
              </li>
            </c:forEach>
          </ul>

          <c:forEach items="${gcategories}" var="cata2">
            <ul class="nav nav-tabs child_cata" id="${cata2.id}childcata" style="display:none">
              <c:forEach items="${cata2.childList}" var="child">
                <li role="presentation" id="" onmouseover="showGrandCata(${child.id})">
                  <a href="#">${child.name}</a>
                </li>
              </c:forEach>
            </ul>
          </c:forEach>

          <c:forEach items="${gcategories}" var="cata3">
            <c:forEach items="${cata3.childList}" var="child2">
              <div class="place-con grand_cata ${cata3.id}fromtop" id="${child2.id}grandchildcata" style="display:none">
                <p>
                  <c:forEach items="${child2.childList}" var="grandson">
                    &nbsp;&nbsp;&nbsp;<span><a href="javascript:void(0)" onclick="selectedPgoods('${grandson.id}','${grandson.name}',this)">${grandson.name}</a></span>&nbsp;&nbsp;&nbsp;
                  </c:forEach>
                </p>
              </div>
            </c:forEach>
          </c:forEach>
        </div>
      </div>
    </td>
  </tr>
  <tr>
    <td><span style="color:red">*</span> 品种</td>

    <td>
      <input type="hidden" name="goodsId" id="goodsId" value="">
      <input type="text" name="goodsName" id="goodsName" value="" readonly="true" onclick="getGoodsByPgoods(this)">
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
      <input type="hidden" name="checkedSpecsId" id="checkedSpecsId"  value="">
      <input type="hidden" name="specs" id="checkedOtherSpecName" value="">
      <input type="text"  id="checkedSpecs" value="" readonly onclick="getSpecValByGcataMap(this)">
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
    <td>货源地 </td>
    <td id="showAddressId">
      <%--${info.productionProvinceName} ${info.productionCityName} ${info.productionAreaName} ${info.productionDetailArea}--%>
    </td>
  </tr>
  <tr>
    <td>详细地址</td>
    <td>
      <input type="text" id="productionDetailArea" value="" name="productionDetailArea">
    </td>
  </tr>
  <tr>
  <tr>
    <td> 采购重量 </td>
    <td>
      <span style="color:red">*</span>可量化数量：<input type="text" name="number" id="number" value="">
      <%--<form:select path="${info.numberUnitId}" items="${numberUnits}"/>--%>
      <select id="numberUnitId" name="numberUnitId" style="width:100px">

      </select>

      不可量化数量：<input type="text" name="nonstandardNumber" id="nonstandardNumber" value="">
      <select name="nonstandardNumberUnitId" id="nonstandardNumberUnitId" style="width:100px">

      </select>
    </td>
  </tr>
  <tr>
    <td> <span style="color:red">*</span>采购价格</td>
    <td>
      <input type="text" name="fromPerPrice" id="fromPerPrice" value="">
      至
      <input type="text" name="toPerPrice" id="toPerPrice" value="">
      <select name="priceUnitId" id="priceUnitId" style="width:100px">

      </select>

    </td>
  </tr>
  <tr>
    <td><span style="color:red">*</span> 采购有效期 </td>
    <td>
      <input id="validateStartDate" name="validateStartDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
              onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'%y-%M-%d'});"/>
      <label>&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</label><input id="validateEndDate" name="validateEndDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                                                                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'%y-%M-%d'});"/>
    </td>
  </tr>
  <tr>
    <td> <span style="color:red">*</span>联系人 </td>
    <td>
      <input type="text" name="userName" id="userName" value="">
    </td>
  </tr>
  <tr>
    <td> <span style="color:red">*</span>手机</td>
    <td>
      <input type="text" name="telephone" id="telephone" value="">
    </td>
  </tr>
  <tr>
    <td><span style="color:red">*</span>收货地址：</td>
    <td id="reciveAddressId">
      <span id="provinceShow"></span>
      <span id="cityShow"></span>
      <span id="areaShow"></span>
    </td>
  </tr>
  <tr>
    <td>详细地址：</td>
    <td>
      <input type="text" name="detailArea" id="detailArea" value="">
    </td>
  </tr>
  <tr>
    <td>采购备注</td>
    <td>
      <textArea name="remarks" id="remarks"></textArea>
    </td>
  </tr>

  <tr>
    <td>
      状态：
    </td>

    <td>
      <select id="statue" name="statue" class="required input-medium" >
        <%--默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功--%>
        <option value="0">待审核</option>
        <option value="1">审核已通过</option>
        <option value="-1">审核未通过</option>
      </select>
      <textarea name="statueIntro" id="statueIntro"></textarea>
    </td>
  </tr>
  <tr>
    <td><input type="button" class="btn btn-primary btn-sm" value="返回" onclick="history.go(-1)"></td>
    <td><input type="submit" class="btn btn-primary btn-sm" onclick="saveEditInfo()" value="保存"></td>

  </tr>
</table>
</div>
</body>
</html>
