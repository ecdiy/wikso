<style type="text/css">
<!--
#box-tabs {
	text-align: center;
	background: #DFDFDF;
	margin: 0 30px 100px 30px; 
}

#box-tabs h1 {
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

#box-tabs div {
	margin: 30px auto;
	text-align: left;
	max-width: 800px;
}

#box-tabs .error {
	display: block;
	color: red;
	font-size: 28px;
	line-height: 30px;
	padding: 30px;
}

#box-tabs .center {
	text-align: center;
}

#box-tabs textarea {
	display: none;
	width: 75%;
	height: 400px;
	margin: 0 auto;
}
-->
</style>


<div id="box-tabs" class="box" style="background: #DFDFDF;">
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
	
	function saveToDB(){
		dwrdiy.save("${param.id}", localStorage['fsm']) ;
	}
	// -->
	</script>

	<canvas id="canvas" width="800" height="600">
		<span class="error">Your browser does not support<br>the HTML5 &lt;canvas&gt; element</span>
	</canvas>
	
	
	<div>
		<p class="center">
		<a href="javascript:saveToDB()">保存</a> |
			Export as: <a href="javascript:saveAsPNG()">PNG</a> | <a
				href="javascript:saveAsSVG()">SVG</a> | <a
				href="javascript:saveAsLaTeX()">LaTeX</a>
		</p>
		<textarea id="output"></textarea>
		 
		<ul>
			<li><b>Add a state:</b> double-click on the canvas</li>
			<li><b>Add an arrow:</b> shift-drag on the canvas</li>
			<li><b>Move something:</b> drag it around</li>
			<li><b>Delete something:</b> click it and press the delete key
				(not the backspace key)</li>
		</ul>
		<ul>
			<li><b>Make accept state:</b> double-click on an existing state</li>
			<li><b>Type numeric subscript:</b> put an underscore before the
				number (like "S_0")</li>
			<li><b>Type greek letter:</b> put a backslash before it (like
				"\beta")</li>
		</ul> 
	</div>
</div>