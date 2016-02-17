<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/1/19 0019
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/common/top.css">
</head>
<body>
<div class="head_top">
  <div class="head_top_content body">
    <span>
    <img src="${ctxStatic}/home/images/guoguo/Home-Interface.png" class="img1">
    <a href="${pageContext.request.contextPath}/${fns:getFrontPath()}"><h6 class="color5">果果网首页</h6></a>
            <span>Hi!&nbsp;
                <c:choose>
                    <c:when test='${"" ne (fns:getFrontUser().name) && null!=(fns:getFrontUser().name)}'>
                        ${fns:getFrontUser().name}
                  </c:when>
                  <c:otherwise>
                      ${fns:getFrontUser().mobile}
                  </c:otherwise>
                </c:choose>
            &nbsp;欢迎来到果果网</span>
    <span>|</span>
    <c:if test="${fns:getFrontUser().name eq null}">
      <a href="${front}/userApi/loginForm"><span class="color5">请登录</span></a>
      <span>|</span>
      <a href="${front}/userApi/register"><span class="color5">免费注册</span></a>
    </c:if>
    <c:if test="${fns:getFrontUser().name ne null}">
      <a href="${ctx}/logout"><span style="color: #f08300;">注销</span></a>
      <span>|</span>
    </c:if>
    </span>
    <span style="float: right;">
      <a href="${front}/userCenter"><span class="space">会员中心</span></a>
      <span>|</span>
      <span><img src="${ctxStatic}/home/images/guoguo/Telephone-location.png" class="mr5">18600642261（09:00-18:00）</span>
    </span>
  </div>
</div>
</body>
</html>
