$(function(){

	$(".wrapper-con-middle-con p a").click(function(){
		if($(this).siblings("span").is(":visible")){
			$(".wrapper-con-middle-con p a").text("[查看详情]");
			$(".wrapper-con-middle-con p a").siblings("span").hide();
		}else{
			$(".wrapper-con-middle-con p a").text("收起");	
			$(".wrapper-con-middle-con p a").siblings("span").show();
		}	
	})
});


$(function(){

	$(".wrapper-supply-con ul li a.in").click(function(){
		if($(this).siblings("span.on").is(":visible")){
			$(".wrapper-supply-con ul li a.in").text("显示全文");
			$(".wrapper-supply-con ul li a.in").siblings("span.on").hide();
		}else{
			$(".wrapper-supply-con ul li a.in").text("收起");	
			$(".wrapper-supply-con ul li a.in").siblings("span.on").show();
		}	
	})
});