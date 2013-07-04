dimension(800, 600);

var fsa = Joint.dia.fsa;
Joint.paper("world", 800, 600);

var all = [];
 
<c:set var="sid" value="${webs.getLong(param.id)}"/> 

 
<c:forEach var="f" items="${list}">
var s${f.id} = fsa.State.create({
  position: {x: ${f.x}, y: ${f.y}},
  label: "${f.name}"
});

all[all.length] =  s${f.id};
</c:forEach>

<c:forEach var="r" items="${frlist}">
s${r.fid1}.joint(s${r.fid2}, (fsa.arrow.label = "${r.label}", fsa.arrow)).register(all);
</c:forEach>