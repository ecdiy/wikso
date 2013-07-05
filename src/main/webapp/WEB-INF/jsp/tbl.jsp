<div class="table" id="tbl-${param.tbl}">
	<c:if test="${not empty lists }">
	<table>
		<thead>
			<tr>
				<c:forEach var="b" items="${cols }">
					<c:if test="${b.name != 'id' && b.name != 'schemeId'}">
						<th>${b.name}</th>
					</c:if>
				</c:forEach>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="f" items="${lists}">
				<tr id="${param.tbl}_${f.get('id')}">
					<c:forEach var="b" items="${cols }">
						<c:if test="${b.name != 'id' && b.name != 'schemeId'}">
							<td class="left" id="${param.tbl}${b.name}${f.id}" name="${b.name}"  data_id="${f.id}" val="${f.get(b.name)}">${f.get(b.name)}</td>
						</c:if>
					</c:forEach>
					<td><a href="#" onclick="delTableDate('${param.tbl}',${f.get('id')})">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	

	<br> <br>
	<div>
		<form action="addTblData.jspa" method="post">
			<input type="hidden" name="tbl" value="${param.tbl}"> <input
				type="hidden" name="schemeId" value="${param.id}">
			<div class="form">
				<div class="fields">
					<c:forEach var="b" items="${cols }">
						<c:if test="${b.name != 'id' && b.name != 'schemeId'}">				 
				 ${convert.getDefHtml( b, webs.getLong(param.id) )  }							
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