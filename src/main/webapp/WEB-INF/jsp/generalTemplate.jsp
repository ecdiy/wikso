<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<!-- stylesheets -->
<link rel="stylesheet" type="text/css"
	href="s/smooth/resources/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="s/smooth/resources/css/style.css" media="screen" />
<link id="color" rel="stylesheet" type="text/css"
	href="s/smooth/resources/css/colors/blue.css" />


<!--[if IE]><script language="javascript" type="text/javascript" src="s/smooth/resources/scripts/excanvas.min.js"></script><![endif]-->

<script src="s/smooth/resources/scripts/jquery-1.4.2.min.js"
	type="text/javascript"></script>

<script src="s/smooth/resources/scripts/jquery-ui-1.8.custom.min.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/jquery.ui.selectmenu.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/jquery.flot.min.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/tiny_mce/tiny_mce.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/tiny_mce/jquery.tinymce.js"
	type="text/javascript"></script>



<script src="s/smooth/resources/scripts/smooth.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/smooth.menu.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/smooth.table.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/smooth.form.js"
	type="text/javascript"></script>
<script src="s/smooth/resources/scripts/smooth.dialog.js"
	type="text/javascript"></script>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/dwr/engine.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dwr/util.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dwr/interface/dwra.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		style_path = "s/smooth/resources/css/colors";
		$("#box-tabs, #box-left-tabs").tabs();
	});
</script>
<decorator:head />
</head>
<body>
	<div id="colors-switcher" class="color">
		<a href="" class="blue" title="Blue"></a> <a href="" class="green"
			title="Green"></a> <a href="" class="brown" title="Brown"></a> <a
			href="" class="purple" title="Purple"></a> <a href="" class="red"
			title="Red"></a> <a href="" class="greyblue" title="GreyBlue"></a>
	</div>

	<div id="header">
		<!-- logo -->
		<div id="logo">
			<h1>
				<a href="" title="Smooth Admin"><img
					src="s/smooth/resources/images/logo.png" alt="Smooth Admin" /></a>
			</h1>
		</div>

		<div id="header-inner">
			<div id="home">
				<a href="" title="Home"></a>
			</div>
			<!-- quick -->
			<ul id="quick">
			</ul>
			<!-- end quick -->
			<div class="corner tl"></div>
			<div class="corner tr"></div>
		</div>
	</div>
	<div id="content">
		<div id="left">
			<div id="menu">
				<h6 id="h-menu-links" class="selected">
					<a href="#links"><span>所有方案</span></a>
				</h6>
				<ul id="menu-links" class="opened">
					<c:forEach var="r" items="${schemeRepository.findAll() }">
						<li><a href="scheme.jspa?id=${r.id}">${r.name}</a></li>
					</c:forEach>
					<li>------------------------</li>
					<li><a href="craeteScheme.jsp">创建方案</a></li>
				</ul>


				<h6 id="h-menu-links" class="selected">
					<a href="#tables"><span>表设计</span></a>
				</h6>
				<ul id="menu-links" class="opened">
					<li><a href="designTable.jspa?tbl=Factor">因子属性表</a></li>
					<li><a href="designTable.jspa?tbl=FactorRelation">因子关系表</a></li>
					<li><a href="designTable.jspa?tbl=FactorAttr">因子属性表</a></li>
				</ul>

			</div>
		</div>
		<div id="right">
			<decorator:body />
		</div>
	</div>
	<div id="footer">
		<p>Copyright &copy; 2013-2013 . All Rights Reserved.</p>
	</div>
</body>
</html>