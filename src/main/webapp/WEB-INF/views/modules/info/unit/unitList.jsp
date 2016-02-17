<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }

		function newUnitRelation(id,name,obj,href){
			$(obj).parent().find("table").append('<tr><td>'+name+'</td><td><select class="toUnitId" name="toUnitId" style="width: 60px;"><c:forEach var="numberUnit" items="${numberUnits}"><option value="${numberUnit.id}">${numberUnit.name}</option></c:forEach></select></td><td><input type="text" class="multiplier" name="multiplier"></td><td><a href="javascript:" onclick="deleteNewUnitRelation(this)">删除</a></td></tr>')
		}


		function deleteNewUnitRelation(obj){
			$(obj).parent().parent().remove()
		}


		function saveNewUnitRelation(id,obj){
			var t =  $(obj).parent().find("table")
			var i = 0
			var multipliers = ""
			var reg=/\d+(\.\d+)?/;
			var flag = true
			t.find(".multiplier").each(function(){
				var str=$(this).val()
				if(!reg.test(str)){
					alert(str+"必须是数字")
					flag = false

				}
				if(i==0){
					multipliers += $(this).val()
				}else{
					multipliers += ";"+$(this).val()
				}
				i++
			})
			if(!flag){
				return;
			}
			i=0
			var toUnitIds = ""
			t.find(".toUnitId option:selected").each(function(){
				if(i==0){
					toUnitIds += $(this).val()
				}else{
					toUnitIds += ";"+$(this).val()
				}
				i++
			})

			if(!multipliers){
				return;
			}

			$.ajax({
				type:"post",
				url:"${ctx}/info/unit/saveNewUnitRelation",
				data:{
					id:id,
					multipliers:multipliers,
					toUnitIds:toUnitIds
				},
				success:function(data){
					if(data){
						alert(data)
					}
						$("#searchForm").submit()


				}
			})
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/info/unit/">单位列表</a></li>
		<li><a href="${ctx}/info/unit/form">单位添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="unit" action="${ctx}/info/unit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		单位：<input type="text" name="name" value="${unit.name}"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		标识：<input type="text" name="code" value="${unit.code}"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		单位说明:
		<select name="statue" style="width:100px ">
			<option value="">--请选择--</option>
			<option value="0" <c:if test="${unit.statue==0}">selected</c:if>>可量化</option>
			<option value="-1" <c:if test="${unit.statue==-1}">selected</c:if>>不可量化</option>
		</select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>id</th><th>单位</th><th>标识</th><th>单位说明</th><th>单位转换</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="unit">
			<tr>
				<td>${unit.id}</td>
				<td><a href="${ctx}/info/unit/form?id=${unit.id}">${unit.name}</a></td>
				<td>${unit.code}</td>
				<td>
					<c:if test="${unit.statue==0}">
						可量化
					</c:if>
					<c:if test="${unit.statue==-1}">
						不可量化
					</c:if>
				</td>
				<td>
					<c:if test="${unit.statue==0}">
						<div>
						<div id="unitRelation_${unit.id}">
							<table class="table table-striped table-bordered table-condensed">
								<c:forEach items="${unit.unitRelations}" var="unitRelation">
									<tr>
										<td>${unitRelation.fromUnitName}</td><td>${unitRelation.toUnitName}</td><td> ${unitRelation.multiplier}</td>
										<td><a href="${ctx}/info/unit/deleteUnitRelation?id=${unitRelation.id}" onclick="return confirmx('确认要删除该转换吗？', this.href)">删除</a></td>
									</tr>
								</c:forEach>

								<%--<tr>
									<td>${unit.name}</td>
									<td>
										<select name="" style="width: 60px;">
											<c:forEach var="numberUnit" items="${numberUnits}">
												<option value="${numberUnit.id}">${numberUnit.name}</option>
											</c:forEach>
										</select>
									</td>
									<td>
										<input type="text" name="multiplier">
									</td>
								</tr>--%>
							</table>
							<input onclick="newUnitRelation('${unit.id}','${unit.name}',this,this.href)" type="button" class="btn btn-mini btn-primary" value="新增转换">
							<input type="button" value="保存" onclick="saveNewUnitRelation('${unit.id}',this)">
						</div>
					</div>
					</c:if>
				</td>
				<td>
    				<a href="${ctx}/info/unit/form?id=${unit.id}">修改</a>
					<a href="${ctx}/info/unit/delete?id=${unit.id}" onclick="return confirmx('确认要删除该单位吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>