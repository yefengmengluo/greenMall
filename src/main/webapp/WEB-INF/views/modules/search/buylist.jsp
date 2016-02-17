<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>求购列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<meta name="description" content="">	
	<meta name="author" content="">	
	<link rel="shortcut icon" href="${ctx}/static/modules/search/image/ico.ico"/>	
	<link rel="bookmark" href="${ctx}/static/modules/search/image/ico.ico"/>	
	<!-- Bootstrap core CSS -->	
	<link href="${ctxStatic}/static/home/css/bootstrap.min.css" rel="stylesheet">	

	<!-- Custom styles for this template -->	
	<link rel="stylesheet" type="text/css" href="${ctx}/static/modules/search/css/base.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/modules/search/css/buyList.css">

</head>
<body>
	<!-- head begin -->	
	<div class="head">
		<div class="head_top">
			<div class="head_top_content">
				<img src="${ctx}/static/modules/search/image/logo_small.png">
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
				<a href="##">
					<span class="space">我的果果网</span>
				</a>
				<span>|</span>
				<a href="##">
					<span>我的订单</span>
				</a>
				<span>|</span>
				<a href="##">
					<span>帮助中心</span>
				</a>
				<span>|</span>
				<a href="##">
					<span>在线客服</span>
				</a>
				<span>|</span>
				<span>400-189-91134（09:00-18:00）</span>
			</div>
		</div>
		<div class="head_middle">
			<img src="${ctx}/static/modules/search/image/header_logo.png" alt="picture miss">	
			<div class="head_middle_content">
				
			</div>
			<br>	
			<input type="text" placeholder="白菜"  />	
			<input type="button" class="buttonOne" />	
			<input  type="button" class="buttonTwo" value="发布采购需求"/>	
			
		</div>
		
	</div>
	<!-- head over -->
	<!-- body begin -->
	<div class="body">
		<div class="menu-top">
			<span class="menu-top-con"><a href="##">果果</a></span>
			<span class="menu-top-con"><a href="##">求购</a></span>
			<span class="menu-top-con"><a href="##">供应</a></span>
			<span class="menu-top-con"><a href="##">基地</a></span>
		</div>
		<div class="mt10 f14">
			<span>果果供应信息>果实类>白菜></span>
		</div>
		<div class="menu-mid mt10 pl20">
			<dl class="dl-horizontal p10 border1">
				<dt>品牌</dt>
				<dd style="line-height:20px;">
					<a href="" class="f12 color3">全部</a>
					<c:forEach var="brand" items="${data.brandname}">
					    <c:forEach var="brandkey" items="${brand.keySet()}">
					        <a href="
					         <c:if test="${isbrandname==true}">#</c:if>
					         <c:if test="${isbrandname==false}">${url}&brandname=${brandkey}</c:if>" 
					         class="ml40 f12 color3">${brandkey}</a>
					    </c:forEach>
					</c:forEach>
					
				</dd>
			</dl>
			<dl class="dl-horizontal p10 border1">
				<dt>地点</dt>
				<dd style="line-height:20px;">
				 <c:forEach var="area" items="${data.area}" varStatus="status">
				     <c:forEach var="areakey" items="${area.keySet()}">				     
				        <a href="
				        <c:if test="${isarea==true}">#</c:if>
                        <c:if test="${isarea==false}">${url}&area=${areakey}</c:if>" 
				        class="
				        <c:if test="${status.index==0}">f12 color3</c:if>
				        <c:if test="${status.index!=0}">ml40 f12 color3</c:if>">
				        ${areakey}</a>
				     </c:forEach>
				  </c:forEach>
				</dd>
			</dl>
			<dl class="dl-horizontal p10">
				<dt>筛选条件</dt>
				<dd style="line-height:20px;">
					<span class="span-style">全部</span>
						<c:forEach var="filtermsg" items="${filtermap}">
						   <c:if test="${filtermsg.value!=''}">
						    <img src="${ctx}/static/modules/search/image/jiantou.png" class="ml10">
						    <span class="span-style ml10">
						    <c:if test="${filtermsg.key=='brandname'}">
						                  品牌：
						    </c:if>
						    <c:if test="${filtermsg.key=='area'}">
						               地区：
						    </c:if>
						    <c:if test="${filtermsg.key=='spuname'}">
						             产品：
						    </c:if>
						    <c:if test="${filtermsg.key=='transtionalplace'}">
						            交易地点：
						    </c:if>
						    <c:if test="${filtermsg.key=='supplier'}">
						            交易员：
						    </c:if>
						    <c:if test="${filtermsg.key=='breed'}">
						            品种：
						    </c:if>
						    
						    ${filtermsg.value}
						    <a href="#" onclick="filtersearch('${url}','&${filtermsg.key}=${filtermsg.value}')"><img src="${ctx}/static/modules/search/image/huiX.png" class="ml5 mt-5"></a></span>
						   </c:if>
					</c:forEach>
				</dd>
			</dl>
		</div>
		
		<div class="bottom-left fl">
			<div class="menu-bot mt10 pl20 ">
				<ul class="dl-horizontal p10 border1">
					<li >
						产品：
						<input type="text" name="spuname" value="${spuname}" />			
					</li>
					<li >
						品种：
						<input type="text"  name="breed" value="${breed}"/>			
					</li>
					<input type="hidden" name="brandname" value="${brandname}"/>
					<input type="hidden" name="area" value="${area}"/>
					<%-- <li >
						交货地点：
						<input type="text" name="transtionalplace" value="${transtionalplace}"/>			
					</li>
					<li >
						公司：
						<input type="text"  name="supplier" value="${supplier}"/>			
					</li> --%>
				</ul>
				<button  class="color2 button1" onclick="search()">搜索</button>
				<dl class="dl-horizontal1 pt10 pb10 inline" >
					<dt>供应信息列表</dt>
					<dd>
						共搜索到
						<span class="color6">${data.count}</span>
						件数据
					</dd>
				</dl>
				<div  class="inline ml100">
					<a href="${url}">
						<span class="paixu<c:if test="${orderby==false}"> bg2</c:if>">默认排序</span>
					</a>
					<a href="${url}&orderby=minprice+asc">
						<span class="paixu <c:if test="${orderby==true}"> bg2</c:if>">价格从低到高</span>
					</a>
				</div>
				<div class="inline ml30">
					<span>价格范围</span>
					<input type="text" name="minprice" value="${minprice}" placeholder="最低价" class="w50" />			
					-
					<input type="text" name="maxprice" value="${maxprice}" placeholder="最高价" class="w50"/>			
					<button class="button2" onclick="search()">确定</button>
				</div>

			</div>
			
			
			<c:forEach var="buydata" items="${data.data}">
			<div class="bottom-left-con">
				<div class="media">
					<div class="media-body lh150">
						<h4 class="media-heading color6 f16">
							${buydata.title}
						</h4>
						<p>
							意向价格：0.8-${buydata.maxPrice}元/斤
							<span>地址：${buydata.localPlace}</span>
						</p>
						<p>规格要求：${buydata.spec}</p>
						<p>跟进交易员：${buydata.transactionerName} ${buydata.transactionerTel}</p>
						<p class="mt5">
							<a href="">
								<img src="${ctx}/static/modules/search/image/talk.png"></a>
							<a href="">
								<img src="${ctx}/static/modules/search/image/collect.png"></a>
						</p>
					</div>
					<div class="media-left">
						<p class="color5 f13">正在洽谈</p>
						<p>
							<span class="wgh">我要供货</span>
						</p>
					</div>
				</div>
			</div>
	        </c:forEach> 
		</div>
		<div class="bottom-right fl ml10">
			<div class="mt10">
				<img src="${ctx}/static/modules/search/image/img1.png" >
				<div class="bottom-right-con">
					<p class="f14 color4">写下您的真实需求，包括规格、材质等，收到后我们会立即给您回电确认，剩下的就交给我们吧。</p>
				</div>
				<button class="bottom-right-con2">
					<img src="${ctx}/static/modules/search/image/put.png" >免费发布采购信息
				</button>
			</div>
			<div class="">
				<h5 class="f18 color6 p20">成交动态展示</h5>
				<div class="line3"></div>
				<div class="pl20 pr20 pb20">
					<p class="succe-con"><span class="color3">13天前</span> 王老板成功以<span class="color5">1.25</span>元/斤采购了<span class="color1">50</span>吨番茄</p>
					<p class="succe-con"><span class="color3">13天前</span> 王老板成功以<span class="color5">1.25</span>元/斤采购了<span class="color1">50</span>吨番茄</p>
					<p class="succe-con"><span class="color3">13天前</span> 王老板成功以<span class="color5">1.25</span>元/斤采购了<span class="color1">50</span>吨番茄</p>
					<p class="succe-con"><span class="color3">13天前</span> 王老板成功以<span class="color5">1.25</span>元/斤采购了<span class="color1">50</span>吨番茄</p>
				</div>
		
			</div>
		</div>
		<div class="clear"></div>
		<div class="xian"></div>
		<div class="navs">
			<button onclick="gopage(${page.frontPageNum})">上一页</button>
			<c:forEach var="indexnum" items="${page.listPageNum}">
					<button <c:if test="${page.currentPageNum==indexnum}">class="bg2"</c:if> onclick="gopage(${indexnum})">${indexnum}</button>
			</c:forEach>
			<button onclick="gopage(${page.nextPageNum})">下一页</button>
			<span>共${page.allPageCount}页</span>
			<span>转到第</span>
			<input type="text" />		
			页
			<button onclick="gopage()">GO</button>

		</div>
	</div>
	<!-- body over -->
	<!-- 页尾 开始 -->
    <div class=" h4 bg4 w"></div>
	<div class="container-fluid footer">
         
        <ul class="row body mt40">
            <li class="col-md-3">
                <img src="${ctx}/static/modules/search/image/baozheng.png" alt="picture miss">
                <span>100%质量保证</span>
            </li>
            <li class="col-md-3">
                <img src="${ctx}/static/modules/search/image/baozheng.png" alt="picture miss">
                <span class="f12 ">100%质量保证</span>
            </li>
            <li class="col-md-3">
                <img src="${ctx}/static/modules/search/image/baozheng.png" alt="picture miss">
                <span class="f12 ">100%质量保证</span>
            </li>
            <li class="col-md-3">
                <img src="${ctx}/static/modules/search/image/baozheng.png" alt="picture miss">
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
                        <img src="${ctx}/static/modules/search/image/img5.png">
                        <img src="${ctx}/static/modules/search/image/img6.png">
                        <img src="${ctx}/static/modules/search/image/img7.png">
                </div>
            </div>
            <div class="col-md-4">
                <img src="${ctx}/static/modules/search/image/erweima.png" class="mt40 ml20">
            </div>
        </div>
        
    </div>
    <!-- 页尾 结束 -->	
</body>
<script src="${ctx}/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script>
  function gopage(pagenum){
	  window.location.href="${url}&currentpagenum="+pagenum
  }
  //搜索
  function search(){
	  var url="${url}";
	  var searchurl="";
	  var spuname=$("input[name='spuname']").eq(0).val();
	  var breed=$("input[name='breed']").eq(0).val();
	  var transtionalplace=$("input[name='transtionalplace']").eq(0).val();
	  var supplier=$("input[name='supplier']").eq(0).val();
	  var minprice=$("input[name='minprice']").eq(0).val();
	  var maxprice=$("input[name='maxprice']").eq(0).val();
	  var brandname=$("input[name='brandname']").eq(0).val();
	  var area=$("input[name='area']").eq(0).val();
	 
	  if(spuname!="" &&  typeof(spuname) != "undefined"){
		  var   pattern=/\&spuname=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&spuname="+spuname;
	  }else{
		  var   pattern=/\&spuname=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  if(breed !="" && typeof(breed) != "undefined"){
		  var   pattern=/\&breed=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&breed="+breed;
	  }else{
		  var  pattern=/\&breed=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  if(transtionalplace!="" && typeof(transtionalplace) != "undefined"){
		  var   pattern=/\&transtionalplace=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&transtionalplace="+transtionalplace;
	  }else{
		  var  pattern=/\&transtionalplace=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  if(supplier!="" && typeof(supplier) != "undefined"){
		  var   pattern=/\&supplier=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&supplier="+supplier;
	  }else{
		  var pattern=/\&supplier=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  if(minprice!="" && typeof(minprice) != "undefined"){
		  var   pattern=/\&minprice=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&minprice="+minprice;
	  }else{
		  var pattern=/\&minprice=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  if(maxprice!="" && typeof(maxprice) != "undefined"){
		  var   pattern=/\&maxprice=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&maxprice="+maxprice;
	  }else{
		  var pattern=/\&maxprice=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  if(area!="" && typeof(area) != "undefined"){
		  var   pattern=/\&area=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&area="+area;
	  }else{
		  var pattern=/\&area=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  
	  if(brandname!="" && typeof(brandname) != "undefined"){
		  var   pattern=/\&brandname=.*[\&]*/;
		  url=url.replace(pattern,"");
		  searchurl=searchurl+"&brandname="+brandname;
	  }else{
		  var pattern=/\&brandname=.*[\&]*/;
		  url=url.replace(pattern,"");
	  }
	  window.location.href=url+searchurl;
  }
  //过滤搜索
  function filtersearch(url,replacement){
	  var nurl=url.replace(replacement,"");
	  window.location.href=nurl;
  }
</script>
</html>