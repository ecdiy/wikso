<!--test ${testva}  --> 
<div id="box-tabs" class="box">
	<div class="title">
		<h5>${scheme.name} ${testva}</h5>
		<ul class="links">
			<li><a href="#box-DIY">关系图</a></li>
			<li><a href="#box-Factor">因子属性</a></li>
			<li><a href="#box-FactorRelation">因子参数</a></li>
			<li><a href="#box-FactorAttr">因子关系</a></li>
			<li><a href="#box-RelationTable">关系表</a></li>
		</ul>
	</div>
	<div id="box-DIY">
		<jsp:include page="schemeDiy.jsp" />
	</div>
	<div id="box-Factor">
		<jsp:include page="/tbl.jspa?tbl=Factor&id=${param.id }" />
	</div>

	<div id="box-FactorAttr"><jsp:include
			page="/tbl.jspa?tbl=FactorAttr&id=${param.id }" /></div>

	<div id="box-FactorRelation"><jsp:include
			page="/tbl.jspa?tbl=FactorRelation&id=${param.id}" /></div>
			
			
	<div id="box-RelationTable"><jsp:include
			page="/RelationTable.jsp?id=${param.id}" /></div>
</div>

<script type="text/javascript">		var schemeId = "${param.id}";
	function deleteScheme() {
		dwra.deleteScheme(schemeId, {
			callback : function() {
				window.location = "craeteScheme.jsp";
			}
		});
	}

	function delTableDate(tbl, id) {
		dwra.sqlExec("delete from " + tbl + " where id=" + id, {
			callback : function() {
				window.location.reload(true);
			}
		});
	}
	function tableChangeValue(tbl, id, field, obj) {
		var td = $("#" + tbl + field + id);
		td.attr("val", $(obj).val());
		td.text($(obj).val());
		dwra.sqlExec("update " + tbl + " set `" + field + "`='"
				+ td.attr("val") + "' where id=" + id, {
			callback : function() {
				window.location.reload(true);
			}
		});
	}
	$("#tbl-Factor table tbody td").dblclick(
			function(e) {
				var td = $(e.target);
				td.html('<input onchange="tableChangeValue(\'Factor\','
						+ td.attr("data_id") + ',\'' + td.attr("name")
						+ '\',this)" value="' + td.attr("val") + '">');
			});
</script>