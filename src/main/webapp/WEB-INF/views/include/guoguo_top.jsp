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
</head>
<body>

<div class="head_top">
  <div class="head_top_content body">
    <img src="${ctxStatic}/home/images/guoguo/Home-Interface.png" class="img1">
    <a href="${pageContext.request.contextPath}${fns:getFrontPath()}"><h6>果果网首页</h6></a>
            <span>Hi!
                <c:choose>

                    <c:when test='${"" ne (fns:getFrontUser().name) && null!=(fns:getFrontUser().name)}'>
                    <span style="margin-left: 10px;width: 55px;height: 13px;display: inline-block;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">
                            ${fns:getFrontUser().name}
                    </span>
                    </c:when>
                    <c:when test='${"" ne (fns:getFrontUser().mobile) && null!=(fns:getFrontUser().mobile)}'>
                     <span style="margin-left: 10px;width: 55px;height: 13px;display: inline-block;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">
                             ${fns:getFrontUser().mobile}
                     </span>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>

                </c:choose>


             欢迎来到果果网</span>
    <span>|</span>
      <c:if test="${fns:getFrontUser().id eq null}">
      <a href="${front}/userApi/loginForm"><span class="color7">请登录</span></a>
      <span>|</span>
      <a href="${front}/userApi/register"><span class="color7">免费注册</span></a>
    </c:if>
      <c:if test="${fns:getFrontUser().id ne null}">
      <a href="${ctx}/logout"><span class="color7">注销</span></a>
      <span>|</span>
    </c:if>
    <a href="${front}/userCenter"><span class="space">会员中心</span></a>
    <span>|</span>
    <span><img src="${ctxStatic}/home/images/guoguo/Telephone-location.png" class="mr5">400-189-91134（09:00-18:00）</span>
  </div>
</div>
</body>
</html>
