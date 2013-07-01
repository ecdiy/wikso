 
 
dimension(800, 600);

var fsa = Joint.dia.fsa;
Joint.paper("world", 800, 600);

var s1 = fsa.State.create({
  position: {x: 399, y: 70},
  label: "金"
});
var s2 = fsa.State.create({
  position: {x: 233, y: 228},
  label: "水"
});
var s3 = fsa.State.create({
  position: {x: 559, y: 228},
  label: "土"
});
var s4 = fsa.State.create({
  position: {x: 263, y: 344},
  label: "木"
});

var s5 = fsa.State.create({
	  position: {x: 520, y: 344},
	  label: "火"
}); 

 

var all = [ s1, s2, s3, s4 , s5];


<c:forEach var="r" items="${factorRelationRepository.findAll() }">
s${r.fid1}.joint(s${r.fid2}, (fsa.arrow.label = "${r.label}", fsa.arrow)).register(all);
</c:forEach>