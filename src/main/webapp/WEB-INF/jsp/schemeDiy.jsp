<style type="text/css">
<!--
#schemeDIYDIV {
	text-align: center;
	background: #DFDFDF;
	margin: 0 30px 0 10px;
}

#schemeDIYDIV h1 {
	font: bold italic 50px Georgia, serif;
}

canvas {
	display: block;
	max-width: 800px;
	background: white;
	border-radius: 20px;
	-moz-border-radius: 20px;
	margin: 10px auto;
}

#schemeDIYDIV div {
	margin: 30px auto;
	text-align: left;
	max-width: 800px;
}

#schemeDIYDIV .error {
	display: block;
	color: red;
	font-size: 28px;
	line-height: 30px;
	padding: 30px;
}

#schemeDIYDIV .center {
	text-align: center;
}

#schemeDIYDIV textarea {
	display: none;
	width: 75%;
	height: 400px;
	margin: 0 auto;
}

#schemeDIYDIV  #funMenu {
	padding-left: 30px;
	float: left;
	text-align: left;
	float: left;
}

#schemeDIYDIV  #funMenu li {
	width: 200px;
}
-->
</style>
<script>
	var fsmDataJSON = {
		"nodes" : [],
		"links" : [ ]
	};
	var xmapids={};
	<c:forEach var="f" items="${list}">
	xmapids.x${f.id}= fsmDataJSON.nodes.length;
	fsmDataJSON.nodes[fsmDataJSON.nodes.length]={
				"x" : ${f.x},
				"y" : ${f.y},
				"text" : "${f.name}",
				"isAcceptState" : false,
				"fid":${f.id}
			};

	</c:forEach>
	<c:forEach var="fr" items="${frlist}">
	fsmDataJSON.links[fsmDataJSON.links.length]= {
			"type" : "Link",
			"nodeA" : xmapids.x${fr.fid1},
			"nodeB" : xmapids.x${fr.fid2},
			"text" : "${fr.label}",
			"lineAngleAdjust" : 0,
			"parallelPart" : 0.5,
			"perpendicularPart" : 0
		}
	</c:forEach>
</script>


<div id="schemeDIYDIV" class="box" style="background: #DFDFDF;">
	<script type="text/javascript" src="s/fsm.js"></script>
	<script type="text/javascript">
	<!--
		if (typeof btoa == 'undefined') {
			function btoa(str) {
				var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
				var encoded = [];
				var c = 0;
				while (c < str.length) {
					var b0 = str.charCodeAt(c++);
					var b1 = str.charCodeAt(c++);
					var b2 = str.charCodeAt(c++);
					var buf = (b0 << 16) + ((b1 || 0) << 8) + (b2 || 0);
					var i0 = (buf & (63 << 18)) >> 18;
					var i1 = (buf & (63 << 12)) >> 12;
					var i2 = isNaN(b1) ? 64 : (buf & (63 << 6)) >> 6;
					var i3 = isNaN(b2) ? 64 : (buf & 63);
					encoded[encoded.length] = chars.charAt(i0);
					encoded[encoded.length] = chars.charAt(i1);
					encoded[encoded.length] = chars.charAt(i2);
					encoded[encoded.length] = chars.charAt(i3);
				}
				return encoded.join('');
			}
		}

		function saveToDB() {
			//alert(localStorage['fsm']);
			dwrdiy.save("${param.id}", localStorage['fsm'],{
			  	callback: function(){
				  	window.location.reload(true);
				  	}
				  });
		}
	// -->
	</script>


	<ul id="funMenu">
		<li><a href="?id=${param.id}&role=0">所有</a></li>

		<li><a href="?id=${param.id}&role=3">自动分布方案--圆、正N边形</a></li>
		<li><a href="?id=${param.id}&role=4">树形分布方案</a></li>

		<li><a href="?id=${param.id}&role=1">过滤孤立点</a></li>

		<c:forEach var="label"
			items="${factorRelationRepository.findLableBySchemeId(webs.getLong(param.id))}">
			<li><a href="?id=${param.id}&role=2&oid=${label}">--
					${label}</a></li>
		</c:forEach>

		<li><a id="source" href="#" onclick="deleteScheme()">删除方案【${scheme.name}】</a></li>



		<li>Export as: <a href="javascript:saveAsPNG()">PNG</a> | <a
			href="javascript:saveAsSVG()">SVG</a> | <a
			href="javascript:saveAsLaTeX()">LaTeX</a>
		</li>
		<li><b>双击添加因子</b></li>
		<li><b>shift+拖曳 添加因子关系</b></li>
		<li><b>Move something:</b> drag it around</li>
		<li><b>Delete something:</b> click it and press the delete key
			(not the backspace key)</li>

		<li><b>Make accept state:</b> double-click on an existing state</li>
		<li><b>Type numeric subscript:</b> put an underscore before the
			number (like "S_0")</li>
		<li><b>Type greek letter:</b> put a backslash before it (like
			"\beta")</li>
		<li><a href="javascript:saveToDB()">保存</a></li>
	</ul>




	<canvas id="canvas" width="800" height="600">
		<span class="error">Your browser does not support<br>the HTML5 &lt;canvas&gt; element</span>
	</canvas>
	<textarea id="output"></textarea>

</div>