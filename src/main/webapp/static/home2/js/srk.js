$(function(){
				$("input.com-search-con-text").focus(function(){
					if($("input.com-search-con-text").val()=='热词：红富士苹果  皇冠  北京三号' || $("input.com-search-con-text").val()==''){
						
					$("#xShowId").hide()
				}else{
					$("#xShowId").show();
				};
				});				
			});