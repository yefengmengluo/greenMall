<%--
  Created by IntelliJ IDEA.
  User: Liujiabao
  Date: 2016/1/22 0022
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script>
    	$(function(){
    	   $("#place2").mouseleave(function() {
      	   		$("#place2").hide();
      	   });
    	});
      function showChildCata2(val){
        $(".child_cata2").hide();
        $("#" + val + "childcata2").show();

        $(".grand_cata2").hide();
        $($("."+val+"fromtop")[0]).show();
        //$($(".grand_cata")[0]).show();
      }
      function showGrandCata2(val){
        $(".grand_cata2").hide();
        $("#" + val + "grandchildcata2").show();
      }
      function choosePl2(obj){
//        var offset = $(obj).offset()
//        $("#showPgoodsSelectDiv2").offset({
//          top:offset.top+140,
//          left:offset.left+100
//        });
        $("#choiceMainGoods").remove();
        $("#mainGoodsCollection2 > span").empty();
        var place = document.getElementById("place2");
        place.style.display = "block";
      }

      function choiceCategory2(pgoodsId,pgoodsName,obj){
        $("#showPgoodsSelectDiv2").offset({
          top:0,
          left:0
        });
        if($("#mainGoods2"+pgoodsId).val()!=null){
          return;
        }
        $("#mainGoodsCollection2").append("<input type='hidden' name='mainGoods' class='mainGoods' value="+pgoodsId+" id='mainGoods2"+pgoodsId+"'>");
        $("#mainGoodsCollection2").append("<input type='button' style=' background-color: #ccc;padding: 3px;' class='btn btn-default'  value='"+pgoodsName+"' onclick='deleteThis(mainGoods2"+pgoodsId+",this)'>");
        $("#goodsId").val("")
        $("#goodsName").val("")
      }
      function deleteThis(id,o){
        id.remove();
        o.remove();
//        $("#place").hide()
      }
      function closePlace(){
//        console.log("close=-===")
        $("#place2").hide()
      }
    </script>
</head>
<body>

<div>
  <div class="place" id="place2" style="display:none">

    <button type="button" class="close" onclick="closePlace()">
      <span aria-hidden="true">&times;</span>
      <a class="sr-only" href="javascript:" >Close</a>
    </button>
    <ul class="nav nav-tabs">
      <c:forEach items="${list}" var="cata">
        <li role="presentation" onmouseover="showChildCata2(${cata.id})">
          <a href="javascript:">${cata.name}</a>
        </li>
      </c:forEach>
    </ul>

    <c:forEach items="${list}" var="cata2">
      <ul class="nav nav-tabs child_cata2" id="${cata2.id}childcata2">
        <c:forEach items="${cata2.childList}" var="child">
          <li role="presentation" id="" onmouseover="showGrandCata2(${child.id})">
            <a href="javascript:">${child.name}</a>
          </li>
        </c:forEach>
      </ul>
    </c:forEach>

    <c:forEach items="${list}" var="cata3">
      <c:forEach items="${cata3.childList}" var="child2" varStatus="statue">
        <%--<div class="place-con grand_cata ${cata3.id}fromtop" id="${child2.id}grandchildcata">--%>
        <ul class="nav nav-tabs grand_cata2 ${cata3.id}fromtop" id="${child2.id}grandchildcata2" <c:if test="${statue.index!=0}">style="display:none"</c:if>>
          <%--<p>--%>
            <c:forEach items="${child2.childList}" var="grandson">
              <%--&nbsp;&nbsp;&nbsp;--%>
              <%--<span>--%>
              <li role="presentation" id="" >
              <a href="javascript:void(0)" onclick="choiceCategory2('${grandson.id}','${grandson.name}',this)">${grandson.name}</a>
              </li>
              <%--</span>--%>
              <%--&nbsp;&nbsp;&nbsp;--%>
            </c:forEach>
          <%--</p>--%>
        <%--</div>--%>
        </ul>
      </c:forEach>
    </c:forEach>
  </div>
  <div class="form-control" style="display:inline-block;" id="mainGoodsCollection2" onclick="choosePl2(this)"><input type="hidden" name="choiceMainGoods" id="choiceMainGoods" value="1"><span>${mainGoods }</span></div>
</div>

</body>
</html>
