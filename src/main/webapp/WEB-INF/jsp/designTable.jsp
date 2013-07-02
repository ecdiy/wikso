<!-- table -->
<div class="box">
	<!-- box / title -->
	<div class="title">
		<h5>Products</h5>
		<div class="search">
			<form action="#" method="post">
				<div class="input">
					<input type="text" id="search" name="search" />
				</div>
				<div class="button">
					<input type="submit" name="submit" value="Search" />
				</div>
			</form>
		</div>
	</div>
	<!-- end box / title -->
	<div class="table">
		<form action="" method="post">
			<table>
				<thead>
					<tr>
						<th class="left">Title</th>
						<th>Price</th>
						<th>Added</th>
						<th class="selected last"><input type="checkbox"
							class="checkall" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${cols}">

						<tr>
							<td class="title">${c. name}</td>
							<td class="price">${c.type}</td>
							<td class="date">${c.size}</td>
							<td class="selected last"><input type="checkbox" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</form>
	</div>
</div>


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
							<label for="input">名称:</label>
						</div>
						<div class="input">
							<input type="text" id="name" name="name" />
						</div>
					</div>
					<div class="field ">
						<div class="label">
							<label for="input">类型:</label>
						</div>
						<div class="input">
							<select name="sqltype" id="sqltype">
								<script>
									var sqltypes = [ [ 'VARCHAR', 1, 64 ],
											[ 'text', 0, 0 ] ];
									for ( var i = 0; i < sqltypes.length; i++) {
										document
												.writeln('<option value="'+sqltypes[i][0]+'">'
														+ sqltypes[i][0]
														+ '</option>');
									}
								</script>

								<option value="VARCHAR">VARCHAR</option>
								<option value="text">text</option>
							</select>
						</div>
					</div>
					<div class="buttons">
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
		$("#sql").val(
				"ALTER TABLE  ${param.tbl} ADD COLUMN `" + n + "` "
						+ $("#sqltype").val() + "(50) NULL");
		return true;
	}
</script>