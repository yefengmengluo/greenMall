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

		// 	url : '',														// 接口地址
		// 	type : 'post',											// 发送方式
		// 	data : {},													// 发送数据
		// 	dataType : 'json',									// 传输数据类型
		// 	beforeSend : function(){						// 发送之前
		// 		$('.loading').addClass('active');											// 显示loading
		// 	}
		// 	success : function(data){						// 请求成功回调函数

		// 	},	
		// 	error : function(){									// 错误信息

		// 	}

		// });

		var th = $(this);																			// 获取需要操作的dom元素  考虑性能先获取
		th.addClass('active');																// 增加class类 active 改变背景颜色
		var time = 60;																				// 定义等待时间	单位秒	
		th.html(time+'后重新发送');														// 改变元素的内容为  多少秒后重新获取
		var setI = setInterval(function(){										// 增加 名为 setI 的定时器  每隔一秒改变一次dom元素内容
			time--;																							// 没执行一次时间减少一秒
			th.html(time+'后重新发送');													// 同时每秒改变一次内容
			if(time === 0){																			// 如果等到0秒时  清除定时器 并改变背景颜色为可以点击的颜色（去除active类名）
				clearInterval(setI);
				th.removeClass('active').html('点击获取')
			}
		},1000)
	});


	$('.next a').on('click',function(){											// 点击下一步
		if($(this).hasClass('active')){												// 通过判断是否有class类active 
			return false;													// 阻止
		}else{
			$(this).addClass('active');													// 没有active类 进行的操作 增加active

			// 此处写接口判断
			// $.ajax({

			// 	url : '',																								// 接口地址
			// 	type : 'post',																					// 发送方式
			// 	data : {},																							// 发送数据
			// 	dataType : 'json',																			// 传输数据类型
			// 	beforeSend : function(){																// 发送之前
			// 		$('.loading').addClass('active');											// 显示loading
			// 	}
			// 	success : function(data){																// 请求成功回调函数


			// 		$('.error_bg').addClass('active');										// 出现弹出层

			// 	},	
			// 	error : function(){																			// 错误信息

			// 	}

			// });



		};



	});

	$('.error a').on('click',function(){										// 点击确定关闭弹出层
		$('.error_bg').removeClass('active');
	});


})
