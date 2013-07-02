
<div id="box-tabs" class="box">
	<!-- box / title -->
	<div class="title">
		<h5>${scheme.name}
			(<a style="color: #fff" target="_blank"
				href="shw-db.jsp?id=${param.id}">关系图</a>)
		</h5>
		<ul class="links">
			<li><a href="#box-Factor">因子属性</a></li>
			<li><a href="#box-other">因子参数</a></li>
			<li><a href="#box-dialogs">因子关系</a></li>
		</ul>
	</div>
	<!-- box / title -->
	<div id="box-Factor">
		<div class="table">
			<table>
				<thead>
					<tr>
						<th class="left">Name</th>
						<th>x</th>
						<th>y</th>
						<th class="selected last"><input type="checkbox"
							class="checkall" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="f" items="${factors}">
						<tr>
							<th class="left">${f.name}</th>
							<th>${f.x }</th>
							<th>${f.y }</th>
							<th class="selected last"><input type="checkbox"
								class="checkall" /></th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>	<br>
			<div id="box-left-forms">
				<form action="addScheme.jspa" method="post">
					<div class="form">
						<div class="fields">
							<div class="field field-first">
								<div class="label">
									<label for="input">方案名称:</label>
								</div>
								<div class="input">
									<input type="text" id="name" name="name" />
								</div>
							</div>

							<div class="buttons">
								<input type="submit" name="submit" value="创建" /> <input
									type="reset" name="reset" value="取消" />
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="box-other"></div>
	<div id="box-dialogs"></div>
</div>