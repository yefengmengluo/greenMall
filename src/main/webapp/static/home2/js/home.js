//JavaScript Document

/*品牌等3个切换*/
// JavaScript Document
 function func(tt,nn,i,tta,ttb,count){
   for(m=1;m<=count;m++){
  if(m==i){
  document.getElementById(tt+m).className = tta;
  document.getElementById(nn+m).style.display='block';
  }else{
   document.getElementById(tt+m).className = ttb;
  document.getElementById(nn+m).style.display='none';
 }
 }
  }

/*果品价格检测预警切换*/
function tab(sName){
sName.each(function(a){
$(this).mouseover(function(){
$(this).siblings().removeClass("newHover");
$(this).addClass("newHover");
$(this).parent().siblings().hide();
$(this).parent().parent().find(".table1").eq(a).show();
})
})	
}
$(function(){
tab($(".newTabFour li"))
})

/*产销对接*/




/*轮播*/
function banner($bannerPic,Icon){
	var num=1;
	var timer=null;
	$("ul",$bannerPic).append($("ul li",$bannerPic).clone());
	var liNum=$("ul li",$bannerPic).length/2;
	var liW=$("ul li:eq(0)",$bannerPic).height();//一个图片的宽度
	//$("ul",$bannerPic).css("top",-liW+"px")
	$("ul",$bannerPic).css("height",(liNum+1)*liW);//赋值图片div的总宽	
	$("ol li",$bannerPic).hover(function(){
		$(this).addClass(Icon).siblings().removeClass(Icon);
		var index=$(this).index();
		$(this).parent().siblings("ul").animate({top:-(index)*liW+"px"},300)	
		num=index;	
	})
	function palyBanner(){
		if(num>=liNum){
			$("ul",$bannerPic).animate({top:-liNum*liW},500,function(){$("ul",$bannerPic).css("top",-0+"px")})
			$("ol li",$bannerPic).eq(0).addClass(Icon).siblings().removeClass(Icon);	
			num=0;
		}else{
			//alert(num)
			$("ol li",$bannerPic).eq(num).addClass(Icon).siblings().removeClass(Icon);	
			$("ul",$bannerPic).animate({top:-num*liW},500)
			num++;	
		}
	}
	var timer=setInterval(palyBanner,3000);
	$bannerPic.hover(
		function(){
			clearInterval(timer);
		},
		function(){
			timer=setInterval(palyBanner,3000);
		}
	)			
}


$(function(){
	

//轮播	
	banner(
		$bannerPic=$(".t2021-ind-rf-ad-con .advertising-common-slide"),
		Icon="on"
	)

})



	
	

function selectNum(name1,name2){
	$(name1).click(function(){
		if($(this).siblings("ul").is(":visible")){
			$(this).siblings("ul").slideUp("fast");
		}else{
			$(this).siblings("ul").slideDown("fast");	
		}
	});

	$(name2 +" ul li").click(function() {
		$(name2).children("ul").slideUp("fast");
		var cur_val=$(this).attr("name");
		$(name2).children("div").html($(this).html());
		$(name2).children("div").css({"color":"#333"});

		$(name2).children("input").val(cur_val);
	})

}


/*顶部广告*/
$(function(){
	function enlargeAd(){
		$("#t1127-ind-full-ad1,#t1127-ind-full-ad1-big").animate({"height":"400px"},1000);
	}
	function narrowAd(){
		$("#t1127-ind-full-ad1-big").animate({"height":"0px"},1000);
		$(".t1127-ind-full-ad1-close").hide(500);
		$("#t1127-ind-full-ad1,#t1127-ind-full-ad1-sm,#t1127-ind-full-ad1-sm img").animate({"height":"40px"},1000);
	}
	$(".t1127-ind-full-ad1-close").click(function(){
		narrowAd();
	});
	enlargeAd();
	setTimeout(narrowAd,5000);
})

$(function(){
		function isHide(obj){
			$(obj).each(function(){
				var ad_con=$.trim($(this).html());
				var ad_con_in=$.trim($(this).children().first().html());
				if(  ad_con == "" || ad_con_in == "" ){
					$(this).parent().hide();
				}else{
					$(this).parent().show();
				}
			
			})
			
		}
		
		isHide(".t1127-ind-full-adcolumn2-con");
		isHide(".t1124-ind-full-column5-con");
		isHide(".t1022-ind-bottom-ad-kinds");
		isHide(".t1127-ind-full-adcolumn1-con")

})



/*采购专场*/	
	$(function(){
		var setName
	$(".scrollList").hover(function () {
		  clearInterval(setName)
	 },function () {
			  setName=setInterval(function () {
			  $(".scrollList table").animate({marginTop:"-34px"},1000,function () {
			  	$(".scrollList table").css("margin-top","0").find("tr:first").appendTo(".scrollList table")
			  })
	  },2000)
			  }).trigger("mouseleave")
	});
	
