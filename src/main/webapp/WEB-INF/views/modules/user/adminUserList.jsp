<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存唯一用户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function approveUser(statue, userId) {
            $.ajax({
                url: "${ctx}/user/userAdmin/approveUser",    //请求的url地址
                dataType: "text",   //返回格式为text
                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                data: {statue: statue, userId: userId},    //参数值
                type: "GET",   //请求方式
                success: function (req) {
                    //请求成功时处理
                    self.location.reload();
                },
                error: function () {
                    //请求出错处理
                    alert("系统出错，请稍后再试！");
                }
            });
        }
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="adminUser" action="${ctx}/user/userAdmin/userList" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			手机号：<input type="text" name="mobile" value="${adminUser.mobile }">
			姓名：<input type="text" name="userName" value="${adminUser.userName }">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td>审核状态</td>
			<td>真实姓名</td>
			<td>联系电话</td>
			<td>用户类型</td>
			<td>公司/合作社名称</td>
			<td>主营产品</td>
			<td>地址</td>
			<td>备注</td>
			<td>注册时间</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.list }" var="attr">
			<tr>
				<td>
                    <button class="btn btn-primary"
                            <c:if test="${attr.statue==0 }">disabled="true"</c:if>
                            onclick="approveUser('0','${attr.userId}')">禁用
                    </button>
                    <button class="btn btn-primary"
                            <c:if test="${attr.statue==1 }">disabled="true"</c:if>
                            onclick="approveUser('1','${attr.userId}')">待审核
                    </button>
                    <button class="btn btn-primary"
                            <c:if test="${attr.statue==2 }">disabled="true"</c:if>
                            onclick="approveUser('2','${attr.userId}')">审核通过
                    </button>
				</td>
				<td>${attr.userName }</td>
				<td>${attr.mobile }</td>
				<td>${attr.organizationType }</td>
				<td>${attr.organizationName }</td>
				<td>${attr.mainGoods }</td>
				<td>${attr.province } ${attr.city } ${attr.area } ${attr.detailArea }</td>
				<td>${attr.remarks }</td>
				<td><fmt:formatDate value="${attr.createDate }" pattern='yyyy-MM-dd HH:mm:ss'/></td>
                <td>
                    <a href="${ctx}/user/userAdmin/findUserInfoById?id=${attr.userId}">修改</a>
                </td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>