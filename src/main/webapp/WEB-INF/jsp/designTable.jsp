<!-- table -->
<div class="box">
	<!-- box / title -->
	<div class="title">
		<h5>表[${param.tbl }]</h5>
		 
	</div>
	<!-- end box / title -->
	<div class="table">
		<form action="" method="post">
			<table>
				<thead>
					<tr>
						<th class="left">字段名</th>
						<th>类型</th>
						<th>长度</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${cols}">
						<tr>
							<td class="title">${c.name}</td>
							<td class="price">${c.type}</td>
							<td class="date">${c.size}</td>
							<td><c:if
									test="${c.name!='id' && c.name!='schemeId'  && c.name!='x' && c.name!='label' && c.name!='y' && c.name!='fid1' && c.name!='fid2' }">
									<a href="#" onclick="del('${param.tbl}','${c.name}')">删除</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</form>
	</div>
</div>

<script type="text/javascript">
	function del(tbl, id) {
		dwra.sqlExec("ALTER TABLE `" + tbl + "` DROP COLUMN `" + id + "`", {
			callback : function() {
				window.location.reload(true);
			}
		});
	}
</script>
<!-- end table -->
<div id="box-left-tabs" class="box box-left box-padding">
	<!-- box / title -->
	<div class="title">
		<h5>表设计[${param.tbl }]</h5>
	</div>

	<!-- end box / title -->
	<div id="box-left-forms">
		<form action="modifyTable.jspa" method="post" id="#modifyTableForm">
			<div class="form">
				<div class="fields">
					<div class="field field-first">
						<div class="label">
							<label for="input">字段名:</label>
						</div>
						<div class="input">
							<input type="text" id="name" name="name" />
						</div>
					</div>
					<style>
#dateTypeDiv,#dateTypeDiv a,#dateTypeDiv span {
	float: left;
}
</style>
					<div class="field " id="dateTypeDiv">
						<div class="label">
							<label for="input">类型:</label>
						</div>
						<div class="input" style="float: left">
							<select name="sqltype" id="sqltype" onchange="changeSqlType()">
								<script>
									var sqltypes = [ [ 'VARCHAR', 1, 64 ],
											[ 'text', 0, 0 ] ];
									for ( var i = 0; i < sqltypes.length; i++) {
										document
												.writeln('<option value="'+sqltypes[i][0]+'">'
														+ sqltypes[i][0]
														+ '</option>');
									}
									function changeSqlType() {
										var v = $("#sqltype").val();
										for ( var i = 0; i < sqltypes.length; i++) {
											if (sqltypes[i][0] == v) {
												if (sqltypes[i][1] == 1) {
													$("#lengthDIV").css(
															"display", "");
												} else {
													$("#lengthDIV").css(
															"display", "none");
												}
											}
										}
										console.log(v);
									}
								</script>

							</select>
							<div id="lengthDIV" style="float: left">
								<span style="line-height: 30px; padding: 0 15px;">长度: </span><input
									type="text" id="len" name="len"
									style="border: 1px solid #000000; height: 30px; position: relative; width: 60px;" />
							</div>

						</div>
					</div>
					<div class="buttons" style="clear: both">
						<input type="submit" id="submit" value="添加"
							onclick="return postSQL()" />
					</div>
				</div>
			</div>
			<input type="hidden" id="sql" name="sql" /> <input type="hidden"
				id="tbl" name="tbl" value="${param.tbl}" />
		</form>
	</div>
</div>

<script>
	function postSQL() {
		var n = $("#name").val();
		var sql = "ALTER TABLE  ${param.tbl} ADD COLUMN `" + n + "` "
				+ $("#sqltype").val();
		for ( var i = 0; i < sqltypes.length; i++) {
			if (sqltypes[i][0] == n) {
				if (sqltypes[i][1] == 1) {
					sql += "(" + $("#len").val() + ")";
				}
			}
		}
		$("#sql").val(sql + " NULL");
		return true;
	}
</script>