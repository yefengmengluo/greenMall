var type=0
  function setSearchType(val){
    type = val
    if(val == 1){
      $("#searchText").attr("placeholder","请输入查询的商品类别")
    } if(val == 2){
      $("#searchText").attr("placeholder","请输入查询的商家名称")
    } if(val == 2){
      $("#searchText").attr("placeholder","请输入查询的交易地点")
    }
    $(".head_middle_content a").css("color","black");
    $("#topSearch"+val).css("text-decoration", "none");
    $("#topSearch"+val).css("color", "#f08300");
  }
  function search(){
    if(type == 0){
      location.href="/supplyList?type=supply&goodsName="+$("#searchText").val()
    }
    //类别
    if(type == 1){
      location.href="/supplyList?type=supply&goodsName="+$("#searchText").val()
    }
    //公司(商家)
    if(type == 2){
      location.href="/supplyList?type=supply&organizationName="+$("#searchText").val()
    }
    //交易地点
    if(type == 3){
      location.href="/supplyList?type=supply&detailArea="+$("#searchText").val()
    }
  }
  function cancelSearch(){
    alert("ss")
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
