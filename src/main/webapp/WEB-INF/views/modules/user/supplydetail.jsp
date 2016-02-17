<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>供应商详情</title>
    <script src="${ctxStatic}/home2/js/common/jquery-1.8.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/home2/js/srk.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/home2/js/xiangqing.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/home2/css/home/supply.css"/>
</head>
<body>
<%@include file="/WEB-INF/views/modules/common/top.jsp" %>
<div class="wrapper clearfix">
    <div class="common-breadcrumb">
        <div class="wrapper-title-con">
            <h6>您的当前位置：<a href="">首页>供应商详情</a></h6>
        </div>
    </div>
    <div class="wrapper-con">
        <div class="wrapper-con-left">
            <table border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td class="td1">${frontUser.name}<span>实名认证</span></td>
                    <td class="td2">${frontUser.mobile}<span>
                            <c:if test='${"" ne (fns:getFrontUser().name) && null==(fns:getFrontUser().name)}'>
                            <a href="${front}/userApi/loginForm">[登录后可看]</a></c:if></span>
                    </td>
                </tr>
                <tr>
                    <td class="td3">${organization.name}</td>
                    <td class="td4">${organization.provinceId}&nbsp;${organization.cityId}&nbsp;${organization.area}&nbsp;${organization.detailArea}</td>
                </tr>
                <tr>
                    <td class="td5">${resultGoods}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="wrapper-con-right">
            <div class="wrapper-con-right-ad">
                <img src="${user.photo}"/>
                <div class="advertising-common-single-html">

                    <p>姓名：${user.name}</p>

                    <p>工号：${user.no}</p>

                    <p>QQ：${user.qq}</p>

                    <p>座机：${user.phone}</p>

                    <p>邮箱：${user.email}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="wrapper-con-middle">
        <div class="wrapper-con-middle-title">
            <h2>企业信息</h2>
            <span></span>
        </div>
        <div class="wrapper-con-middle-con">
            <p>
                <c:if test="${organization.remarks==null&&organization.remarks==''}">暂无信息</c:if>${organization.remarks}
            </p>
        </div>
    </div>
    <div class="wrapper-con-bottom">
        <div class="wrapper-con-bottom-title">
            <h2>供应信息</h2>
            <span></span>
        </div>
        <div class="wrapper-con-bottom-con">
            <table border="0" cellspacing="0" cellpadding="0">
                <thead>
                <tr>
                    <th class="th1">供应产品</th>
                    <th class="th2">规格</th>
                    <th class="th3">价格</th>
                    <th class="th4">产地</th>
                    <th class="th5">供应截至日期</th>
                    <th class="th6">备注</th>
                    <th>供应商</th>
                    <th class="th7">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                <tr>
                    <td>采购苹果 花牛苹果</td>
                    <td>平均果径：70~75mm 是否套袋：膜带 果颜色：片红 包装塑料筐 是否贴字：不贴字 果型：矮桩</td>
                    <td>0.8~1.2元/斤</td>
                    <td>北京市东城区 南锣鼓巷</td>
                    <td>2016年12月</td>
                    <td>最小起订量：2000斤</td>
                    <td>王广华 135****4545</td>
                    <td class="td1"><a href="" class="common-login">我要采购</a><a href="" class="common-register">查看详情</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="compage">
                <div class="pagination">
                    <form action="" method="get">
                        <a href="" class="on">1</a>
                        <a href="">2</a>
                        <a href="">3</a>
                        <a href="">4</a>
                        <a href="">5</a>
                        <a href="">6</a>
                        <a href="">7</a>
                        <a href="">8</a>
                        <a href="" class="next">下一页</a>
                        <a href="">尾页</a>
                        <span>共1215页  转到第</span>
                        <input type="text" value="1"/>
                        <span>页</span>
                        <a href="" class="prev">GO</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="copyright-full-bottom clearfix">
    <%@include file="/WEB-INF/views/modules/common/bottom.jsp" %>
</div>
</body>
</html>
