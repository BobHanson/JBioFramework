(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "PDFXref");

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$fields$=[['I',['id','offset','generation']]]

Clazz.newMeth(C$, 'c$$I$I',  function (id, offset) {
C$.c$$I$I$I.apply(this, [id, offset, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I',  function (id, offset, generation) {
;C$.$init$.apply(this);
this.id=id;
this.offset=offset;
this.generation=generation;
}, 1);

Clazz.newMeth(C$, 'toString',  function () {
var of=Integer.toString$I(this.offset);
var ge=Integer.toString$I(this.generation);
var rs="0000000000".substring$I$I(0, 10 - of.length$()) + of + " " + "00000".substring$I$I(0, 5 - ge.length$()) + ge ;
if (this.generation == 65535) return rs + " f ";
return rs + " n ";
});

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:16:56 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1
