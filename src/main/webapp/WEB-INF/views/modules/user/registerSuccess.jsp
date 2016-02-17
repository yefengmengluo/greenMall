<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>成为正式会员</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="水果批发,安塞苹果，果果网，水果采购，水果供应" />
	<meta name="description" content="果果网官方网站提供水果生鲜等优质水果网采购供应服务" />
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="images/ico.ico"/>
	<link rel="bookmark" href="images/ico.ico"/>
	<!-- Bootstrap core CSS -->
	<link href="${modules}/user/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${modules}/user/css/starter-template.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="${modules}/user/css/zhuce.css">
	<link rel="stylesheet" type="text/css" href="${modules}/user/css/base.css">
	<link rel="stylesheet" type="text/css" href="${modules}/user/css/sucess.css">
	<link rel="stylesheet" type="text/css" href="${modules}/user/css/missPassword.css">
	<script type="text/javascript">
		function toIndex(){
			location.href ="${pageContext.request.contextPath}/${fns:getFrontPath()}";
		}
		function toUserCenter(){
			location.href ="${front}/userCenter";
		}
	</script>
</head>
<body>
<!-- 页头 开始 -->
<div class="head_top">
	<div class="head_top_content">
		<img src="${modules}/user/images/logo_small.png">
		<h6>果果网首页</h6>
		<span>Hi!欢迎来到果果网</span>
		<span>|</span>
		<a href="##">
			<span>请登录</span>
		</a>
		<span>|</span>
		<a href="##">
			<span>免费注册</span>
		</a>






		<span style="float: right">18600642261（09:00-18:00）</span>
		<span style="float: right">|</span>
		<a href="##">
			<span style="float: right">会员中心</span>
		</a>
	</div>
</div>
<!-- 页头 结束 -->
<!-- 页头logo 开始 -->
<div class="container">
	<div class="row h105 body ">
		<div class="col-md-6 ">
			<img src="${modules}/user/images/header_logo.png" alt="picture miss" class="head_bottom_content_img"></div>
		<div class="col-md-6 mt30">

			<img src="${modules}/user/images/Dark-ray.png" class="fl">
			<dl class="fl ml8 mt5 mr40">
				<dt class="f16 fb">更快捷</dt>
				<dd class="f12">帮您快速找果果</dd>
			</dl>

			<img src="${modules}/user/images/Security-Shield.png" class="fl">
			<dl class="fl ml8 mt5 mr40">
				<dt class="f16 fb">更安全</dt>
				<dd class="f12">安全可靠一站式服务</dd>
			</dl>

			<img src="${modules}/user/images/Bag-with-dollar-sign.png" class="fl">
			<dl class="fl ml8 mt5 mr40">
				<dt class="f16 fb">更便宜</dt>
				<dd class="f12">比一比更便宜</dd>
			</dl>

		</div>
	</div>
</div>

<!-- 页头logo 结束 -->
<!-- 页面主体 开始 -->
<img src="${modules}/user/images/zhuce-image/nav3.png" class="jindutiao">
<div class="body ">
	<div class="zhuce-padding">
		<img src="${modules}/user/images/zhuce-image/bigduihao.png" class="fl mr40" style="margin-left: 200px;">
		<dl class="fl">
			<dt class=" color5 pb20 zhuce-dt">尊敬的用户，您的资料已提交审核。</dt>
			<dd class="f12">
				通过审核后，成为我们的正式会员。
				<br>正式会员获得专属交易员的特权，还可以享有免费采购、供货服务。</dd>
		</dl>
		<div class="cb"></div>
		<div style="margin-left: -90px;" >
			<button  class="btn btn-default " type="button" onclick="toIndex()">返回首页</button>
			<button  class="btn btn-default "  type="button" onclick="toUserCenter()">进入会员中心</button>
		</div>
	</div>
	<div class="cb"></div>
	<div class="sucess-div" >
		<dl class="fl mr40">
			<dt class=" color7 pb20 zhuce-dt">找供应商</dt>
			<dd class="f12">
				使用强大抓也的搜索功能，轻松从求信息中
				<br>寻找优质供应商。</dd>
			<input type="button" value="立即查找" class="btn1 btn-default " onclick="javascript:location.href='${front}/supplyList?type=supply'">
		</dl>

		<dl class="fl ml100">
			<dt class=" color1 pb20 zhuce-dt">发求购信息</dt>
			<dd class="f12">
				免费发布产品求购信息，将您想要采购的产品发布上网
				<br>让供应商随时找上门。</dd>
			<input type="button" value="立即发布" class="btn1 btn-default " onclick="javascript:location.href='${front}/publishDemands'">
		</dl>
	</div>
</div>
<!-- 页面主体 结束 -->
<!-- 页尾 开始 -->
<div class=" h4 bg4 w"></div>
<div class="container-fluid footer">

	<ul class="row body mt40">
		<li class="col-md-3">
			<img src="${modules}/user/images/baozheng.png" alt="picture miss">
			<span>100%质量保证</span>
		</li>
		<li class="col-md-3">
			<img src="${modules}/user/images/baozheng.png" alt="picture miss">
			<span class="f12 ">100%质量保证</span>
		</li>
		<li class="col-md-3">
			<img src="${modules}/user/images/baozheng.png" alt="picture miss">
			<span class="f12 ">100%质量保证</span>
		</li>
		<li class="col-md-3">
			<img src="${modules}/user/images/baozheng.png" alt="picture miss">
			<span class="f12 ">100%质量保证</span>
		</li>
	</ul>
	<div class="row body">
		<div class="col-md-8">
			<div class="bottom_content1">
                    <span>
                        <a href="##">关于</a>
                    </span>
				<span>|</span>
                    <span>
                        <a href="##">法律声明</a>
                    </span>
				<span>|</span>
                    <span>
                        <a href="##">诚聘英才</a>
                    </span>
				<span>|</span>
                    <span>
                        <a href="##">投资洽谈</a>
                    </span>
				<span>|</span>
                    <span>
                        <a href="##">联系我们</a>
                    </span>
				<span>|</span>
                    <span>
                        <a href="##">常见问题</a>
                    </span>
				<p>COPYRIGHT®果果网www.99114.com京ICP备13013925号-ICP证：京B2-22220148</p>
			</div>
			<div class="imgdiv">
				<img src="${modules}/user/images/img5.png">
				<img src="${modules}/user/images/img6.png">
				<img src="${modules}/user/images/img7.png">
			</div>
		</div>
		<div class="col-md-4">
			<img src="${modules}/user/images/erweima.png" class="mt40 ml20">
		</div>





	</div>

</div>
<!-- 页尾 结束 -->
</body>
</html>