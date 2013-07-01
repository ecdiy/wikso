 
 
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

 
s1.joint(s2, (fsa.arrow.label = "泄", fsa.arrow)).register(all);
s1.joint(s3, (fsa.arrow.label = "损", fsa.arrow)).register(all);
s1.joint(s4, (fsa.arrow.label = "克", fsa.arrow)).register(all);
s1.joint(s5, (fsa.arrow.label = "生", fsa.arrow)).register(all);


//s2.joint(s1, (fsa.arrow.label = "生", fsa.arrow)).register(all);
s2.joint(s3, (fsa.arrow.label = "损", fsa.arrow)).register(all);
s2.joint(s4, (fsa.arrow.label = "克", fsa.arrow)).register(all);
s2.joint(s5, (fsa.arrow.label = "生", fsa.arrow), { endArrow: { type: 'hand' }}).register(all);

  
s3.joint(s4, (fsa.arrow.label = "克", fsa.arrow)).register(all);
s3.joint(s5, (fsa.arrow.label = "生", fsa.arrow), { endArrow: { type: 'hand' }}).register(all); 
 

s4.joint(s5, (fsa.arrow.label = "生", fsa.arrow), { endArrow: { type: 'hand' }}).register(all); 

fsa.arrow.label = null;	// empty label

