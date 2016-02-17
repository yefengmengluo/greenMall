<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date"/>
<html>
<head>
    <title>后台首页</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
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
<div class="row" style="margin: 50px 200px">
    <div class="container-fluid bg-primary">
        <p class="lead">
        您好，${fns:getUser().name},欢迎进入果果网后台系统，今天是<fmt:formatDate value="${now}" type="both" dateStyle="long"
                                                                pattern="yyyy-MM-dd"/>,
        上次登录时间<fmt:formatDate value="${fns:getUser().loginDate}" type="both" dateStyle="long"
                              pattern="yyyy-MM-dd hh:mm:ss"/>,上次登录ip:${fns:getUser().loginIp}
        </p>
    </div>
    <div class="container-fluid">
        <p class="lead">
        网站基本信息：<br>
        网站名称：果果网 &emsp;&emsp;&emsp;域名：<a href="/${fns:getFrontPath()}" target=_blank>www.guoguo114.com</a><br/>
        开通时间：2016-1-23&emsp;&emsp;&emsp;会员数：${userCount==null?0:userCount}个<br/>
        供应信息数：${supplyCount==null?0:supplyCount}条&emsp;&emsp;&emsp;采购信息数：${demandCount==null?0:demandCount}条<br/>
        </p>
    </div>
    <div class="container-fluid">
        <p class="lead">
        系统公告：<br />
            暂无
        </p>
    </div>
    <div class="container-fluid">
        <p class="lead">
        待审核内容：
        待审核会员<a href="">(${beforeFrontuserCount==null?0:beforeFrontuserCount})</a>&nbsp;
            待审核供应信息<a href="${ctx}/info/supplyCheck">(${beforeSupplyCount==null?0:beforeSupplyCount})</a>&nbsp;
            待审核采购信息<a href="${ctx}/info/demandCheck">(${beforeDemandCount==null?0:beforeDemandCount})</a>
            待跟踪订单数<a href="">（先不做）</a>
        </p>
    </div>
    <div class="container-fluid">
        <p class="lead">
        <h1>快捷方式：<small></small></h1>
        <h1><small>
            <a href="${ctx}/user/userAdmin/userList">会员管理</a>
            <a href="${ctx}/info/supplyCheck">供应管理</a>
            <a href="${ctx}/info/demandCheck">求购管理</a>
            <a href="${ctx}/deal/tradeOrder/list">订单管理</a>
        </small></h1>
        </p>
    </div>
    <div class="container-fluid">
        <h1>待审核会员</h1>
        <form:form id="searchForm" modelAttribute="adminUser" action="${ctx}/user/userAdmin/userList" method="post"
                   class="breadcrumb form-search">
            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        </form:form>
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
            <tr>
                <td>审核状态</td>
                <td>姓名</td>
                <td>联系电话</td>
                <td>用户类型</td>
                <td>公司/合作社名称</td>
                <td>经营产品</td>
                <td>品种</td>
                <td>地址</td>
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
    </div>
</div>
</body>
</html>