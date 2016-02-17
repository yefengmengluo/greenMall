<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/2/15
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
  <title>忘记密码</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/home2/css/common/module.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/home2/css/top/top3.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/home2/css/home/password.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/home2/css/bottom/bottom.css"/>
    <script src="${ctxStatic}/home2/js/common/jquery-1.8.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/home2/js/srk.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#pwdForm").validate({
                submitHandler : function(form){

                },
                rules:{
                    validateCode: {
                        required: true
                    },
                    mobile:{
                        mobile:true,
                        required: true
                    }
                },
                messages:{
                    validateCode: {
                        required: "验证码不能为空"
                    },
                    mobile:{
                        mobile:"请输入正确的手机号",
                        required: "请输入正确的手机号"
                    }
                }
            });
            //发送短信验证码
            $("#sendsmsCode").click(function () {
                $.get("${front}/userApi/smsCode", {
                            "validateCode": $("#validateCode").val(),
                            "mobile": $("#mobile").val(),
                            "name": $("#name").val()
                        },
                        function (data) {
                            if (data.status == '-13') {
                                timer();
                            }
                            $("#error").html(data.message)
                            $("#error").show();
                            $("#error").fadeOut(30000);
                        }, "json");
            });
        });
        function timer(){
            var InterValObj; //timer变量，控制时间
            var count = 60; //间隔函数，1秒执行
            var curCount;//当前剩余秒数
            curCount = count;
            $("#sendsmsCode").attr("disabled", "true");
            $("#sendsmsCode").val( + curCount + "秒后重新获取验证码");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
            //timer处理函数
            function SetRemainTime() {
                if (curCount == 0) {
                    window.clearInterval(InterValObj);//停止计时器
                    $("#sendsmsCode").removeAttr("disabled");//启用按钮
                    $("#sendsmsCode").val("重新发送验证码");
                }
                else {
                    curCount--;
                    $("#sendsmsCode").val( + curCount + "秒后重新获取验证码");
                }
            }
        }
    </script>
</head>
<body>
    <!--头部开始-->
    <div class="t2029-top">
        <div class="t2029-common-nav-category-w1190 clearfix">
            <div class="common-nav-left"></div>
            <div class="common-nav-right">
                <span class="title">忘记密码</span>
                <ul>
                    <li class="li1"><span>更快捷</span><p>帮您快速找水果</p></li>
                    <li class="li2"><span>更安全</span><p>安全可靠一站式服务</p></li>
                    <li class="li3"><span>更便宜</span><p>比一比更便宜</p></li>
                </ul>
            </div>
        </div>
    </div>
    <!--头部结束-->
    <!--正文开始-->
    <div class="t2029-wrapper">
        <div class="t2029-wrapper-con">
            <div class="advertising-common-single-img">
                <img src="${ctxStatic}/home2/images/home/2_2.png"/>
            </div>
            <form method="post" id="pwdForm" action="ffffffffffffffffffffffff">
                <div class="t2029-wrapper-con-middle">
                    <div class="t2029-wrapper-con-middle-form">
                        <div class="t2029-wrapper-con-middle-form-tel">
                            <label for="">&nbsp;手机号码：</label>
                            <input type="tel" name="mobile" placeholder="请输入手机号"/>
                            <img src="${ctxStatic}/home2/images/home/cha.png"/>
                            <label>请输入正确的手机号</label>
                        </div>
                        <div class="wrapper-con-middle-form-pic">
                            <label for="">图文验证码：</label>
                            <input placeholder="请输入验证码" type="text" id="validateCode" name="validateCode" maxlength="5" class="form-control1 " placeholder="请输入验证码" />
                            <img id="${name}Img" src="${pageContext.request.contextPath}/servlet/validateCodeServlet" onclick="jQuery('#${name}Refresh').click();" class="imgsize"/>
                            <img src="${ctxStatic}/home2/images/home/gth.png" class="img2"/><img src="${ctxStatic}/home2/images/home/cha.png" class="img1"/>
                            <label class="kong">验证码不能为空</label>
                            <label class="wu">验证码有误</label>
                        </div>
                        <div class="wrapper-con-middle-form-xinxi">
                            <label for="">短信验证码：</label>
                            <input type="text"/>
                            <input type="button" class="imgsize" id="sendsmsCode" value="点击获取验证码"/>
                            <img src="${ctxStatic}/home2/images/home/gth.png"/>
                            <label class="label1">验证码错误或者已经失效</label>
                        </div>
                    </div>
                    <div class="wrapper-con-middle-btn">
                        <a href="">下一步</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--正文结束-->
    <!--底部开始-->
    <div class="copyright-full-bottom clearfix">
        <div class="com-footer">
            <div class="com-footer-top">
                <p>
                    <a href="">关于我们</a>
                    <a href="">|</a>
                    <a href="">法律声明</a>
                    <a href="">|</a>
                    <a href="">诚聘英才</a>
                    <a href="">|</a>
                    <a href="">投资洽谈</a>
                    <a href="">|</a>
                    <a href="">联系我们</a>
                    <a href="">|</a>
                    <a href="">常见问题</a>
                </p>
            </div>
        </div>
        <div class="copyright-footer">
            <div class="copyright-footer-con">
                <p>
                    COPYRIGHT <span>©</span>蔬菜网 www.99114.com 京ICP备13013925号-ICP证：京B2-22220148
                </p>
            </div>
        </div>
    </div>
    <!--底部结束-->
</body>
</html>
