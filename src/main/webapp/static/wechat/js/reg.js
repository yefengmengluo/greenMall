// JavaScript Document
$(function(){

	// 手机号只能输入数字
	$('#phone').on('keyup',function(){
		$(this).val($(this).val().replace(/[^\d]/g,''));
	});

	// 点击X清空已输入的手机号
	$('.vlel').on('click',function(){
		$('#phone').val('').focus();
	});

	// 点击获取验证码按钮   改变样式
	$('.hcode').on('click',function(){


			/**
	     * 手机号码: 
	     * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[6, 7, 8], 18[0-9], 170[0-9]
	     * 移动号段: 134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
	     * 联通号段: 130,131,132,155,156,185,186,145,176,1709
	     * 电信号段: 133,153,180,181,189,177,1700
	     */
		if($("#phone").val().match("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$")){
			console.log(1);
		}else{
			console.log(2);
		}

		// $.ajax({

		// 	url : '',										
		// 	type : 'post',								    
		// 	data : {},										
		// 	dataType : 'json',							    
		// 	beforeSend : function(){						
		// 		$('.loading').addClass('active');			
		// 	}
		// 	success : function(data){						

		// 	},	
		// 	error : function(){							    

		// 	}

		// });

		var th = $(this);									
		th.addClass('active');								
		var time = 60;										
		th.html(time+'后重新发送');							
		var setI = setInterval(function(){					
			time--;											
			th.html(time+'后重新发送');						
			if(time === 0){									
				clearInterval(setI);
				th.removeClass('active').html('点击获取')
			}
		},1000)
	});


	$('.next a').on('click',function(){											   
		if($(this).hasClass('active')){											   
			return false;													      
		}else{
			$(this).addClass('active');											   

			// 此处写接口判断
			// $.ajax({

			// 	url : '',															
			// 	type : 'post',														
			// 	data : {},															
			// 	dataType : 'json',													
			// 	beforeSend : function(){											
			// 		$('.loading').addClass('active');								
			// 	}
			// 	success : function(data){											


			// 		$('.error_bg').addClass('active');								

			// 	},	
			// 	error : function(){													

			// 	}

			// });

		};
	});

	$('.error a').on('click',function(){										
		$('.error_bg').removeClass('active');
	});

})
