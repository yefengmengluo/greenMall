<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<jsp:include page="../info/home/uCenterHead.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/user/css/changeMess.css">
    <!-- 页面右侧 -->
		<div class="body1-right">
			<div class="body1-right-top">
				<div class="body1-right-top1">
					<img src="${ctxStatic}/modules/user/images/User-avatar.png">		
					<span class="f16 ml10 mt5">修改信息</span>
				</div>
			</div>
			<div class="pt20">
				<img src="${ctxStatic}/modules/user/images/jindutiao2.png" class="ml150 mt50 ">
				<div class="ml150 mt10 mb30">
					<span >填写公司信息</span>
					<span class="ml150">审核公司信息</span>
					<span class="ml150">审核结果</span>
				</div>
				<div >
				
					<div class="ml150">
						<img src="${ctxStatic}/modules/user/images/Bok.png" class="fl mr20">					
						<dl>
							<dt  >
								您的公司信息已提交成功，请耐心等待审核。
								<br>如注册时间为非工作时间，我们将在顺延工作日为您审核
							</dt>

						</dl>
					</div>	
					
				
					<div class="cb"></div>
					
				</div>
			</div>
		</div>
	</div>
	<!-- 页面内容 结束 -->
	<div class="clear"></div>
<jsp:include page="../info/home/uCenterFoot.jsp"></jsp:include>