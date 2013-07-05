<div class="table" id="tblrelations">
	<table>
		<thead id="relationThead">
		</thead>
		<tbody id="relationTbody"></tbody>
	</table>

	<script type="text/javascript">
	var colums =[];
	<c:forEach var="f"
		items="${TFactorRepository.findBySchemeId(webs.getLong(param.id))}">
		colums[colums.length]=[${f.id},'${f.name}'];
	</c:forEach>
	
	var relations =[];
	<c:forEach var="r"
		items="${factorRelationRepository.findBySchemeId(webs.getLong(param.id))}">
		relations[relations.length]=[${r.fid1},${r.fid2},'${r.label}'];
	</c:forEach>
	
	var hds="",tbs="";
	for(var i=0;i<colums.length;i++){
		hds+="<th>"+colums[i][1]+"</th>";
		tbs+="<tr><td>"+colums[i][1]+"</td>";
		for(var j=0;j<colums.length;j++){
			var fid=false;
			for(var jj=0;jj<relations.length;jj++){
				if(relations[jj][0]==colums[i][0] && relations[jj][1]==colums[j][0])
				{
					tbs+="<td val=\""+relations[jj][2]+"\" fid1=\""+colums[i][0]+"\" fid2=\""+colums[j][0]+"\">"+relations[jj][2]+"</td>";
					fid=true;break;
				}
			}
			if(!fid)
			tbs+="<td insert='1' val=\"-\" fid1=\""+colums[i][0]+"\"  fid2=\""+colums[j][0]+"\">-</td>";
		}
		tbs+="</tr>";
	}
	$("#relationThead").html("<tr><th>-</th>"+hds+"</tr>");
	$("#relationTbody").html(tbs);
	
	</script>
</div>

<script>
function tblrelationsChangeValue(fid1,fid2,obj){
	dwra.sqlExec("update FactorRelation set `label`='"
			+ $(obj).val() + "' where fid1=" + fid1 +" and fid2="+fid2, {
		callback : function() {
			window.location.reload(true);
		}
	});
}

function tblrelationsInsertValue(fid1,fid2,obj){
	dwra.sqlExec("insert into FactorRelation(label,fid1,fin2)values('"
			+ $(obj).val() + "' ," + fid1 +","+fid2+")", {
		callback : function() {
			window.location.reload(true);
		}
	});
}

$("#tblrelations table tbody td").dblclick(
		function(e) {
			var td = $(e.target);
			td.html('<input onchange="tblrelationsChangeValue('
					+ td.attr("fid1") + ',' + td.attr("fid2")
					+ ',this)" value="' + td.attr("val") + '">');
		});

</script>