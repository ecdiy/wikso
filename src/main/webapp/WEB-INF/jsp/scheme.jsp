
<div id="box-tabs" class="box">
	<!-- box / title -->
	<div class="title">
		<h5>${scheme.name}
			(<a style="color: #fff" target="_blank"
				href="shw-db.jsp?id=${param.id}">关系图</a>)
		</h5>
		<ul class="links">
			<li><a href="#box-Factor">因子属性</a></li>
			<li><a href="#box-FactorRelation">因子参数</a></li>
			<li><a href="#box-FactorAttr">因子关系</a></li>
		</ul>
	</div>
	<!-- box / title -->
	<div id="box-Factor">
		<jsp:include page="/tbl.jspa?tbl=Factor&id=${param.id }" />
	</div>
	<div id="box-FactorRelation">
		<jsp:include page="/tbl.jspa?tbl=FactorRelation&id=${param.id }" />
	</div>
	<div id="box-FactorAttr"><jsp:include
			page="/tbl.jspa?tbl=FactorAttr&id=${param.id }" /></div>
</div>