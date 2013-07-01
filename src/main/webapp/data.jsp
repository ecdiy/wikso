 
 
dimension(800, 600);

var fsa = Joint.dia.fsa;
Joint.paper("world", 800, 600);

var all = [];
 
<c:forEach var="f" items="${TFactorRepository.findAll() }">
var s${f.fid} = fsa.State.create({
  position: {x: ${f.x}, y: ${f.y}},
  label: "${f.name}"
});

all[all.length] =  s${f.fid};
</c:forEach>

<c:forEach var="r" items="${factorRelationRepository.findAll() }">
s${r.fid1}.joint(s${r.fid2}, (fsa.arrow.label = "${r.label}", fsa.arrow)).register(all);
</c:forEach>