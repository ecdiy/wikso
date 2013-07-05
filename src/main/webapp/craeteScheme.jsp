
<div  class="box">
	<!-- box / title -->
	<div class="title">
		<h5>创建方案</h5>
	</div>
	<!-- end box / title -->
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

	<!-- box / title -->
	<div class="title">
		<h5>创建方案 上传CSV文件</h5>
	</div>
	<!-- end box / title -->
	<div id="box-left-forms">
		<form action="uploadScheme.jspa" method="post" enctype="multipart/form-data">
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
					<div class="field">
						<div class="label">
							<label for="input">选择文件:</label>
						</div>
						<div class="input input-file">
							<input type="file" id="file" name="file" />
						</div>
					</div>
					<div class="buttons">
						<input type="submit" name="submit" value="上传" />
					</div>
				</div>
			</div>
		</form>
	</div>
</div>