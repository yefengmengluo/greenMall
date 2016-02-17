<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#no").focus();
            $("#inputForm").validate({
                rules: {
                    loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
                },
                messages: {
                    loginName: {remote: "用户登录名已存在"},
                    confirmNewPassword: {equalTo: "输入与上面相同的密码"}
                },
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            $("#sendsmsCode").click(function(){
                $.get("${front}/userApi/smsCode",{
                    "validateCode":$("#validateCode").val(),
                    "mobile":$("#mobile").val(),
                    "name":$("#name").val()
                },function(data){
                    alert(data.message)
//                    $("#messageBox").text();
                })
            })
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${front }/userApi/register">用户注册</a></li>
    <li><a href="${front }/userApi/userInfo">账户信息</a></li>
    <%--<li><a href="${ctx}/sys/user/list">用户列表</a></li>--%>
    <%--<li class="active"><a href="${ctx}/sys/user/form?id=${user.id}">用户--%>
    <%--<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission>--%>
    <%--<shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission>--%>
    <%--</a></li>--%>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="user" action="${front}/userApi/User" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">姓名:</label>

        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">手机:</label>

        <div class="controls">
            <form:input path="mobile" htmlEscape="false" maxlength="100"/>
            <c:if test="${empty user.mobile}"><span class="help-inline"><font color="red">*</font> </span></c:if>
        </div>
    </div>

    <div class="control-group">
        <%--<label class="control-label">图文验证码:</label>--%>
        <div class="controls">
                <div class="validateCode">
                <label class="input-label mid" for="validateCode">验证码</label>
                    <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
                </div>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">短信验证码:</label>
        <div class="controls">
            <input id="smsCode" name="smsCode" type="text" value="" maxlength="50" minlength="3" />
            <input id="sendsmsCode" class="btn btn-primary" type="button" value="发送短信验证码"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">密码:</label>
        <div class="controls">
            <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3"
                   class="${empty user.id?'required':''}"/>
            <c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
            <c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确认密码:</label>
        <div class="controls">
            <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50"
                   minlength="3" equalTo="#newPassword"/>
            <c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
        </div>
    </div>

    <%--<div class="control-group">--%>
    <%--<label class="control-label">用户类型:</label>--%>
    <%--<div class="controls">--%>
    <%--<form:select path="userType" class="input-xlarge">--%>
    <%--<form:option value="" label="请选择"/>--%>
    <%--<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
    <%--</form:select>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">用户角色:</label>--%>
    <%--<div class="controls">--%>
    <%--&lt;%&ndash;<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>&ndash;%&gt;--%>
    <%--<span class="help-inline"><font color="red">*</font> </span>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">备注:</label>--%>
    <%--<div class="controls">--%>
    <%--<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<c:if test="${not empty user.id}">--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">创建时间:</label>--%>
    <%--<div class="controls">--%>
    <%--<label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">最后登陆:</label>--%>
    <%--<div class="controls">--%>
    <%--<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</c:if>--%>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>