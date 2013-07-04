
<div class="table">
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

#funMenu,#funMenu li {
	width: 80px;
	float: left
}
</style>
	<script type="text/javascript">
		function gup(name) {
			name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
			var regexS = "[\\?&]" + name + "=([^&#]*)";
			var regex = new RegExp(regexS);
			var results = regex.exec(window.location.href);
			if (results == null)
				return "";
			else
				return results[1];
		}
		function init() {
			var srcs = gup("unit").split(',');
			for ( var i = 0, l = srcs.length; i < l; i++) {
				var sel = document.createElement("script");
				sel.type = "text/javascript";
				sel.src = "http://"
						+ document.location.host
						+ "/test/data.jspa?id=${param.id}&role=${param.role}&oid=${param.oid}";
				document.getElementsByTagName("head").item(0).appendChild(sel);
			}
		}

		function dimension(w, h) {
			var world = document.getElementById('world');
			world.style.width = w + 'px';
			world.style.height = h + 'px';
		}
		$(function() {
			init();
		});

		var schemeId = "${param.id}";
		function save() {
			var pos = "";
			for ( var i = 0; i < all.length; i++) {
				var x = (all[i].properties.dx + all[i].properties.position.x);
				var y = (all[i].properties.dy + all[i].properties.position.y);
				if (x < 0)
					x = x * (-1);
				if (y < 0)
					y = y * (-1);
				pos += x + "," + y + ";";
			}
			dwra.savePoints(schemeId, pos);
		}
		function deleteScheme() {
			dwra.deleteScheme(schemeId, {
				callback : function() {
					window.location = "craeteScheme.jsp";
				}
			});
		}
	</script>
	<ul id="funMenu">
		<li><a href="?id=${param.id}&role=0">所有</a></li>
		<li><a href="?id=${param.id}&role=1">过滤孤立点</a></li>

		<c:forEach var="frr"
			items="${factorRelationRepository. findBySchemeId(webs.getLong(param.id))}">
			<li><a href="?id=${param.id}&role=2&oid=${frr.label}">${frr.label}</a></li>
		</c:forEach>


		<li><a id="source" href="#" onclick="save()">保存</a></li>
		<li><a id="source" href="#" onclick="deleteScheme()">删除方案【${scheme.name}】</a></li>
	</ul>

	<div id="world"></div>


</div>