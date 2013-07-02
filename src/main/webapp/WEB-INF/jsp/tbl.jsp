<div class="table">
	<table>
		<thead>
			<tr>
				<c:forEach var="b" items="${cols }">
					<th>${b.name}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="f" items="${lists}">
				<tr>
					<c:forEach var="b" items="${cols }">
						<td class="left">${f.get(b.name)}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br> <br>
	<div id="box-left-forms">
		<form action="addTblData.jspa" method="post">
			<input type="hidden" name="tbl" value="${param.tbl}"> <input
				type="hidden" name="schemeId" value="${param.id}">
			<div class="form">
				<div class="fields">
					<c:forEach var="b" items="${cols }">
						<c:if test="${b.name != 'id' }">
							<div class="field field-first">
								<div class="label">
									<label for="input">${b.name}:</label>
								</div>
								<div class="input">
									<input type="text" id="${b.name}" name="${b.name}" />
								</div>
							</div>
						</c:if>
					</c:forEach>
					<div class="buttons">
						<input type="submit" name="submit" value="数据添加" /> <input
							type="reset" name="reset" value="取消" />
					</div>
				</div>
			</div>
		</form>
	</div>
</div>