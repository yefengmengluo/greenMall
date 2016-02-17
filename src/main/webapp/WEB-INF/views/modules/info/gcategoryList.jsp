<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品类别管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "${not empty gcategory.id ? gcategory.id : '0'}";

			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 5});

		});

		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				//alert(!row?'':!row.parentId?'':row.parentId)
				if ((${fns:jsGetVal('row.parentId')}) == pid ||row.parentId == pid){
					$(list).append(Mustache.render(tpl, {
						spec:{
							content:getContent(row.showSpec),
							unit:getUnit(row.showSpec),
						},pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}

		function getContent(val){
			if(val == "1"){
				return "查看规格"
			}else{
				return ""
			}
		}
		function getUnit(val){
			if(val == "1"){
				return "查看单位"
			}else{
				return ""
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/info/gcategory/list?id=${gcategory.id}&parentIds=${gcategory.parentIds}">类别列表</a></li>
		<shiro:hasPermission name="info:gcategory:edit"><li><a href="${ctx}/info/gcategory/form?parent.id=${gcategory.id}">类别添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>类别名称</th><th>类别编码</th><th>备注</th><shiro:hasPermission name="info:gcategory:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/info/gcategory/form?id={{row.id}}">{{row.name}}</a></td>
			<td>{{row.code}}</td>
			<td style="width:50%">{{row.remarks}}</td>
			<shiro:hasPermission name="info:gcategory:edit"><td>
				<a href="${ctx}/info/gcategory/form?id={{row.id}}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${ctx}/info/gcategory/delete?id={{row.id}}" onclick="return confirmx('要删除该类别及所有子类别吗？', this.href)">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${ctx}/info/gcategory/form?parent.id={{row.id}}">添加下级分类</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<shiro:hasPermission name="info:gcategory:view">
				<a href="${ctx}/info/gspec/getSpecValByGcata?id={{row.id}}" class="">{{spec.content}}</a>
				&nbsp;&nbsp;
				<a href="${ctx}/info/infoUnitCategory/getInfoUnitCategorysByGcategory?gcategoryId={{row.id}}" class="">{{spec.unit}}</a>
			</shiro:hasPermission>
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>