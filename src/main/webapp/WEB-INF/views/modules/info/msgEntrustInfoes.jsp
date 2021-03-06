<%--
  Created by IntelliJ IDEA.
  User: zhuyanqing
  Date: 2016/1/12 0021
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>供应审核管理</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    function page(n,s){
      $("#pageNo").val(n);
      $("#pageSize").val(s);
      $("#searchForm").submit();
      return false;
    }

    function selectAllinfoMessage(){
      var f = $(".selectedChecke:checked")
      if(f.length>0){
        $(".selectedChecke").removeAttr("checked")
      }else{
        $(".selectedChecke").attr("checked","checked")

      }

    }

    function deleteSelectedinfoMessage(){
      var selectedCheckeds = ""
      var i=0
      $(".selectedChecke:checked").each(function(){
        if(i==0) {
          selectedCheckeds += $(this).attr("value")

        }else{
          selectedCheckeds += ";"+$(this).attr("value")
        }
        i++
      })
      toDelete(selectedCheckeds)
    }
    function toDeleteSingle(id,obj){
      var flag = confirm("确定删除此信息吗？")
      if(!flag){
        return
      }
      $.ajax({
        type:"post",
        url:"${ctx}/info/deleteSupplyMessages/",
        data:{
          ids:id
        },
        success:function(data){
          if(data){
            alert(data)
          }else{
           $(obj).parent().parent().remove()
          }

        }
      })
    }
    function toDelete(ids){
      var flag = confirm("确定删除选中的信息吗？")
      if(!flag){
        return
      }
      $.ajax({
        type:"post",
        url:"${ctx}/info/deleteSupplyMessages/",
        data:{
          ids:ids
        },
        success:function(data){
          if(data){
            alert(data)
          }else{
            $(".selectedChecke:checked").each(function(){
              $(this).parent().parent().remove()
            })
          }

        }
      })
    }

    function changeStatue(){
      <%--var selectedStatue = $("#statue option:selected").val()--%>
      <%--var url = "${ctx}/info/supplyMessageCheck/"--%>
      <%--if(selectedStatue==0){--%>
        <%--url = "${ctx}/info/supplyMessageCheck/"--%>
      <%--}else if(selectedStatue==1){--%>
        <%--url = "${ctx}/info/supplyChecked/"--%>
      <%--}else if(selectedStatue==-1){--%>
        <%--url = "${ctx}/info/supplyCheckedNo/"--%>
      <%--}else if(selectedStatue==-2){--%>
        <%--url = "${ctx}/info/supplyDelete/"--%>
      <%--}else if(selectedStatue==2){--%>
        <%--url = "${ctx}/info/supplyTalking/"--%>
      <%--}else if(selectedStatue==3){--%>
        <%--url = "${ctx}/info/supplyWaitingMoney/"--%>
      <%--}else if(selectedStatue==4){--%>
        <%--url = "${ctx}/info/supplySuccess/"--%>
      <%--}--%>
      <%--$("#searchForm").attr("action",url)--%>
      $("#searchForm").submit()
    }
    $(function(){
      setInterval(circulationQuery, 30000);
    })
    function circulationQuery(){
      $("#btnSubmit").submit()
    }
  </script>
</head>
<h5><a href="${ctx}/info/msgEntrustInfoes?isEntrust=0&statue=0">当前待审核信息<span style="color: red">${count}</span>条</a></h5>
  <%--<div class="ibox-tools text-right">--%>
    <%--<a href="${pageContext.request.contextPath}${fns:getAdminPath()}/info/createSupply" class="btn btn-primary btn-xs"  > 新增</a>--%>
  <%--</div>--%>

<form:form id="searchForm" modelAttribute="infoMessage" action="${ctx}/info/msgEntrustInfoes/" method="post" class="breadcrumb form-search">
  <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
  <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

  <input type="button" name="selectAll" value="全选" onclick="selectAllinfoMessage()"/>
  <input type="button" name="deleteSelected" value="删除" onclick="deleteSelectedinfoMessage()"/>
  <label>状态：</label>
  <form:select path="statue" class="input-xlarge" id="selectStatue" cssStyle="width: 150px">
  <form:option value="" label="不限"/>
  <form:options items="${fns:getDictList('infoMessage_statue')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
  </form:select>
  <label>委托种类：</label><form:select path="type" cssStyle="width: 150px"><form:option value="">不限</form:option><form:option value="supply">供应</form:option><form:option value="demand">求购</form:option></form:select>
  <label>信息：</label><form:input path="message" htmlEscape="false" maxlength="50" class="input-small" cssStyle="width: 200px"/>&nbsp;
  <label>用户：</label> <sys:treeselect id="publishUser" name="publishUser.id" value="${infoMessage.publishUser.id}" labelName="publishUser.name" labelValue="${infoMessage.publishUser.name}"
                title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" cssStyle="width:180"/>&nbsp;
  <label>电话：</label><form:input path="publishUser.mobile" htmlEscape="false" maxlength="50" class="input-small" cssStyle="width: 200px"/>&nbsp;
  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
</form:form>
<sys:message content="${message}"/>

<div id="infoMessageDiv">
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr>
      <th></th>
      <th>状态</th>
      <th>发布的信息</th>
      <th>信息编号</th>
      <th>被委托商机编号</th>
      <th>生成的商机编号</th>
      <th>发布时间</th>
      <th>发布用户</th>
      <th>用户电话</th>
      <th  style="width: 100px">操作</th></thead>
    <tbody>
    <c:forEach items="${page.list}" var="infoMessage">
      <tr>
        <td>
          <input type="checkbox" name="selectedChecke" class="selectedChecke" value="${infoMessage.id}"/>
        </td>
        <td>
              <%--默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功--%>
            <c:if test="${infoMessage.statue==0}">待审核</c:if>
            <c:if test="${infoMessage.statue==1}">审核已通过</c:if>
            <c:if test="${infoMessage.statue==-1}">审核未通过</c:if>
            <c:if test="${infoMessage.statue==-2}">删除</c:if>
            <c:if test="${infoMessage.statue==2}">正在洽谈</c:if>
            <c:if test="${infoMessage.statue==3}">等待打款</c:if>
            <c:if test="${infoMessage.statue==4}">交易成功</c:if>
        </td>
        <td>${infoMessage.message}</td>
        <td>
          <a href="${ctx}/info/checkSupplyMessage?id=${infoMessage.id}">${infoMessage.id}</a>
        </td>
        <td>
            <a href="${ctx}/info/${infoMessage.type eq 'supply'?'checkDemand':'checkSupply'}?id=${infoMessage.entrustSupplyDemandId}">${infoMessage.entrustSupplyDemandId}</a>
        </td>
        <td>
          <a href="${ctx}/info/${infoMessage.type eq 'supply'?'checkSupply':'checkDemand'}?id=${infoMessage.supplyDemandId}">${infoMessage.supplyDemandId}</a>
        </td>
        <td>
            ${fns:formatDate(infoMessage.createDate,"yyyy-MM-dd HH:mm:ss")}
        </td>
        <td>
            <a href="#">${infoMessage.publishUser.name}</a>
        </td>
        <td>
            ${infoMessage.publishUser.mobile}
        </td>

        <%--<td><a href="${ctx}/info/checkSupplyMessage?id=${infoMessage.id}">${infoMessage.id}</a></td>--%>

        <td>
          <a href="${ctx}/info/checkSupplyMessage?id=${infoMessage.id}">审核</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:" onclick="toDeleteSingle('${infoMessage.id}',this)">删除</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<div class="pagination">${page}</div>
<%--<c:forEach items="${infoMessages}" var="infoMessage">--%>
  <%--<c:out value="${infoMessage.id}"></c:out>--%>
<%--</c:forEach>--%>

</body>
</html>
