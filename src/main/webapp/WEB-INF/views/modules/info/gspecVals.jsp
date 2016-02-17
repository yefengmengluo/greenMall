<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%--<jsp:useBean id="gspec" class="com.wk.p3.greenmall.modules.info.entity.Gvalue" scope="request" ></jsp:useBean>--%>
<html>
<head>
    <title>规格和值</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/treetable.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function addOne() {
            $("#gvalueId").val(null);
            $("#delFlag").val("0");
            $("#val").val("");
            $("#orderItem").val("");
            $('#editform').modal('show');
        }
        function editBtn(val) {
            $.post("${ctx}/info/gspec/getGvalue?id=" + val , function (result) {
                if (result.error) {
                    alert(result.error)
                } else {
                    $("#delFlag").val(result["delFlag"]);
                    $("#editform form input").val("").each(function (idx, val) {
                        var inp = $(val);
                        inp.val(result[inp.attr("id")])
                    });
                    $("#gvalueId").val(result.id),
                    $("#gcategoryId").val(result.gcategory.id),
                    $("#gspecId").val(result.gspec.id);

                    $("#btnSubmit").val("保 存");
                    $('#btnCancel').val('返 回');
                    $('#editform').modal('show');
                }
            });
        }
        function delBtn(val) {
            if(confirm("确认要删除吗？")){
                location.href = "${ctx}/info/gspec/delGvalue?id="+val
            }
        }
        function submitAjax(){
            $('#editform').modal('hide');

            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}${fns:getAdminPath()}/info/gvalue/saveGvalue",
                data:{
                    id:$("#gvalueId").val(),
                    gcategoryId:$("#gcategoryId").val(),
                    gspecId:$("#gspecId").val(),
                    val:$("#val").val(),
                    delFlag:$("#delFlag").val(),
                    orderItem:$("#orderItem").val(),
                },
                success:function(data) {
                    if(data){
                         location.href="${ctx}/info/gvalue/editSpecVal?id="+data;
                    }else{
                        location.reload()
                    }
                }
            })

        }



    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">规格值列表</a>
    </li>
    <li class=""><a href="${ctx}/info/gspec/getSpecValByGcata?id=${gcategory.id}">规格列表</a>
    </li>
</ul>
<h5>
    类别：${gcategory.name}&nbsp;&nbsp;/&nbsp;&nbsp;规格名称：${gspec.name}
</h5>
<%--<a href="${ctx}/info/gcategory/gvalueForm?" class="btn btn-w-m btn-primary" style="margin-top: 10px">添加规格值</a>--%>
<a href="#" onclick="addOne()" class="btn btn-w-m btn-primary" style="margin-top: 10px">添加规格值</a>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed"
       style="text-align: center;margin-top: 10px">
    <thead>
    <tr>
        <th>规格值</th>
        <th>排序</th>
        <th>状态</th>
        <shiro:hasPermission name="info:gcategory:edit">
            <th>操作</th>
        </shiro:hasPermission></tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="val">
        <tr>
            <td>${val.val}</td>
            <td>${val.orderItem}</td>
            <td>${fns:getDictLabel(val.delFlag, 'del_flag', '')}</td>
            <shiro:hasPermission name="info:gcategory:edit">
                <td>
                    <a href="#" onclick="editBtn(${val.id})" class="btn btn-w-m btn-primary">修改</a>
                    <a href="#" onclick="delBtn(${val.id})" class="btn btn-w-m btn-danger">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<a href='#' onclick='' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#editform' >打开modal</a>--%>
<div id="editform" class="modal fade" aria-hidden="true">
    <div>
        <h4 class="m-t-none m-b" style="text-align: center;margin-top: 25px">规格值编辑</h4>
        <%--<form:form id="inputForm" modelAttribute="gvalue" action="${ctx}/info/gspec/saveGvalue" method="post"--%>
                   <%--class="form-horizontal" cssStyle="margin-top: 15px">--%>
        <form class="form-horizontal" style="margin-top: 25px">
            <input type="hidden" id="gvalueId">
            <input type="hidden" id="gcategoryId" value="${gcategory.id}">
            <input type="hidden" id="gspecId" value="${gspec.id}">
            <%--<form:hidden path="id" id="id"/>--%>
            <%--<form:hidden path="gcategory.id" value="${gcategory.id}"/>--%>
            <%--<form:hidden path="gspec.id" value="${gspec.id}"/>--%>
            <div class="control-group">
                <label class="control-label">规格值:</label>
                <div class="controls">
                    <input id="val"/>
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">是否禁用:</label>

                <div class="controls">
                    <%--<form:select path="delFlag" id="delFlag">--%>
                        <%--<form:options items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value"--%>
                                      <%--htmlEscape="false"/>--%>
                    <%--</form:select>--%>
                    <select id="delFlag">
                        <option value="0">是</option>
                        <option value="1">否</option>
                    </select>
                    <span class="help-inline"><font color="red">*</font> “是”代表此类别可用，“否”则表示此规格不可用</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">排序:</label>

                <div class="controls">
                    <input class="required digits input-small" id="orderItem"/>
                    <span class="help-inline">排列顺序。</span>
                </div>
            </div>
            <div class="form-actions">
                <input id="btnSubmit" class="btn btn-primary" value="保 存" onclick=" submitAjax()" style="width: 100px"/>&nbsp;
                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="$('#editform').modal('hide')" style="width: 120px"/>
            </div>
        </form>
        <%--</form:form>--%>
    </div>
</div>
</body>
</html>