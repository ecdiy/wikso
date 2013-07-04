<div class="table">
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
							<td class="left">${f.get(b.name)}</td>
						</c:if>
					</c:forEach>
					<td><a href="#" onclick="del('${param.tbl}',${f.get('id')})">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	
	<script type="text/javascript">
	function del(tbl,id){
		dwra.sqlExec("delete from "+tbl+" where id="+id,{
		  	callback: function(){
		  	window.location.reload(true);
		  	}
		  }) ;
	}	
	</script>
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