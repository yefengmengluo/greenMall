$(function(){
	$(".wrapper-right-btn a.login").mousemove(function(){
		$(".wrapper-right-btn img").show();
	})
	$(".wrapper-right-btn a.login").mouseleave(function(){
		$(".wrapper-right-btn img").hide();
	})
});


$(function(){
	$(".scrollCon ul").append($(".scrollCon ul li").clone());
	var liNum=$(".scrollCon ul li").length;
	var liW=$(".scrollCon ul li").width()+2;
	$(".scrollCon ul").css("width",liNum*liW);
	var num=1;
	var timer=null;
	
	function changePic(){
		$(".bigPic img").attr("src",$(".scrollCon ul li").eq(num).children("img").attr("src"));	
	}
	$(".rightBtn").click(function(){
		var nLeft=parseInt($(".scrollCon ul").css("left"));
		if(nLeft<=-liNum/2*liW){
			num++;
			$(".scrollCon ul").stop(true,true).animate({left:-num*liW},300,function(){
				$(".scrollCon ul").css("left",-liW);	
			})
			changePic()
			num=1;	
		}else{
			num++;
			$(".scrollCon ul").stop(true,true).animate({left:-num*liW},300)	;
			changePic()
		}
	})
	$(".leftBtn").click(function(){
		var nLeft=parseInt($(".scrollCon ul").css("left"));
		if(nLeft>=-liW){
			num--;	
			$(".scrollCon ul").stop(true,true).animate({left:0},300,function(){
				$(".scrollCon ul").css("left",-liNum/2*liW);		
			});
			changePic()
			num=liNum/2
		}else{
			num--;
			$(".scrollCon ul").stop(true,true).animate({left:-num*liW},300);
			changePic()
		}	
	})
	$(".scrollCon ul li img").click(function(){
		$(".bigPic img").attr("src",$(this).attr("src"));
	})
	function startMove(){
		if(num==liNum/2){
			num++;
			$(".scrollCon ul").animate({left:-num*liW},500,function(){
				$(".scrollCon ul").css("left",-liW)	;
			})	
			changePic()
			num=1;
		}else{
			num++;
			$(".scrollCon ul").animate({left:-num*liW},500);	
			changePic()
		}

	}
	timer=setInterval(startMove,2000)	
	$(".scrollDiv").hover(function(){clearInterval(timer);},function(){timer=setInterval(startMove,2000)})
})
