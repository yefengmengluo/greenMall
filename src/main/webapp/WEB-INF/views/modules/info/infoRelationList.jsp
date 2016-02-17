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
  <title>供求关系管理</title>
  <meta name="decorator" content="default"/>
  <style>
    #publishUserName{width: 130px}
    #btnSubmit{margin-left: 82px}

  </style>
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
  </script>
</head>
<%--<h5><a href="${ctx}/info/msgEntrustInfoes?isEntrust=0&statue=0">当前待审核条数<span style="color: red">${count}</span>条</a></h5>--%>

<form:form id="searchForm" modelAttribute="infoRelation" action="${ctx}/infoRelation/relationBetweenInfoes/" method="post" class="breadcrumb form-search">
  <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
  <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
  <ul class="ul-form">
  <li>
    <label>状态：</label>
    <form:select path="statue" class="input-xlarge" cssStyle="width: 150px">
      <form:option value="" label="不限"/>
      <form:options items="${fns:getDictList('infoRelation_statue')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
    </form:select>
  </li>
  <li>
  <label>意向：</label>
    <form:select path="relationType" style="width: 150px">
    <form:option value="">不限</form:option>
    <form:option value="2">双方有意向</form:option>
    <form:option value="1">单方有意向</form:option>
    <form:option value="-1">双方无意向</form:option>
    </form:select>
  </li>
  <%--<li>--%>
    <%--<label>关系程度：</label><select name="relation" style="width: 150px"><option value="">不限</option><option value="1">委托</option><option value="0">有意向</option></select>--%>
  <%--</li>--%>

  <li>
    <label>用户：</label> <sys:treeselect id="publishUser" name="publishUser.id" value="${infoRelation.publishUser.id}" labelName="publishUser.name" labelValue="${infoRelation.publishUser.name}"
                title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" cssStyle="width:100"/>&nbsp;
  </li>
    <li class="clearfix"></li>
  <li>
  <label>供求信息：</label><form:input path="info.id" htmlEscape="false" maxlength="50" class="input-small" cssStyle="width: 138px"/>&nbsp;
  </li>
  <li>
  <label>创建时间始：</label><input id="startDate" name="startDate" type="text" style="width: 135px" readonly="readonly" maxlength="20" class="input-small Wdate"
                             <c:if test="${not empty infoRelation.startDate}">value="${fns:formatDate(infoRelation.startDate,'yyyy-MM-dd')}"</c:if> onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
  <label>创建时间末：</label><input id="endDate" name="endDate" type="text" style="width: 135px" readonly="readonly" maxlength="20" class="input-small Wdate"
                             <c:if test="${not empty infoRelation.endDate}">value="${fns:formatDate(infoRelation.endDate,'yyyy-MM-dd')}"</c:if> onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
  </li>
  <li>
  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
  </li>
  </ul>
</form:form>

<div id="infoMessageDiv">
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr>
      <%--<th></th>--%>
      <th>状态</th>
      <th>甲方</th>
      <th>甲方信息编号</th>
      <th>甲方意向</th>
      <th>乙方</th>
      <th>乙方信息编号</th>
      <th>乙方意向</th>
      <th>关系建立时间</th>
      <th  style="width: 100px">操作</th></thead>
    <tbody>
    <c:forEach items="${page.list}" var="relation">
      <tr>
        <%--<td>--%>
          <%--<input type="checkbox" name="selectedChecke" class="selectedChecke" value="${infoMessage.id}"/>--%>
        <%--</td>--%>
          <td>
              ${fns:getDictLabel(relation.statue,'infoRelation_statue','未知')}
          </td>
        <td>
          ${relation.fromInfo.userName}&nbsp;${relation.fromInfo.telephone}
        </td>
          <td>
             <a href="${ctx}/info/checkSupply?id=${relation.fromInfo.id}">${relation.fromInfo.id}</a>
          </td>
          <td>
            ${relation.fromStatue eq 0?'无意向':'有意向'}
        </td>
          <td>
              ${relation.toInfo.userName}&nbsp;${relation.toInfo.telephone}
          </td>
          <td>
            <a href="${ctx}/info/checkSupply?id=${relation.fromInfo.id}"> ${relation.fromInfo.id}</a>
          </td>
          <td>
              ${relation.toStatue eq 0?'无意向':'有意向'}
          </td>
        <td>
            ${fns:formatDate(relation.createDate,'yyyy-MM-dd')}
        </td>
        <td>
          <a href="${ctx}">详情</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:" onclick="toDeleteSingle()">撮合</a>
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
