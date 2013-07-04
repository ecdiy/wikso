<head>
<script src="s/lib/json2.js" type="text/javascript"></script>
<script src="s/lib/raphael.js" type="text/javascript"></script>
<script src="s/src/joint.js" type="text/javascript"></script>
<script src="s/src/joint.arrows.js" type="text/javascript"></script>
<script src="s/src/joint.dia.js" type="text/javascript"></script>
<script src="s/src/joint.dia.serializer.js" type="text/javascript"></script>
<script src="s/src/joint.dia.fsa.js" type="text/javascript"></script>
<script src="s/src/joint.dia.uml.js" type="text/javascript"></script>
<script src="s/src/joint.dia.pn.js" type="text/javascript"></script>
<script src="s/src/joint.dia.devs.js" type="text/javascript"></script>
<script src="s/src/joint.dia.cdm.js" type="text/javascript"></script>
<script src="s/src/joint.dia.erd.js" type="text/javascript"></script>
<script src="s/src/joint.dia.org.js" type="text/javascript"></script>
</head>
<body>
	<style type="text/css">
#world {
	background-color: white;
	margin: 0 auto 0 auto;
	width: 800px;
	height: 1000px;
	border: 3px solid gray;
	border-radius: 30px;
}

#title {
	position: fixed;
	left: 10px;
	top: 10px;
	color: white;
	font-size: 14px;
}

#description {
	position: fixed;
	left: 10px;
	top: 45px;
	color: white;
	font-size: 12px;
	width: 250px;
}

#source {
	left: 20px;
	font-size: 12px;
}
</style>
	<script type="text/javascript">
		function dimension(w, h) {
			var world = document.getElementById('world');
			world.style.width = w + 'px';
			world.style.height = h + 'px';
		}
	</script>
	<div id="world"></div>
	<script
		src="data.jspa?id=${param.id}&role=${param.role}&oid=${param.oid}"
		type="text/javascript"></script>



</body>