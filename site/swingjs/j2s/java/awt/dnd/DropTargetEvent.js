(function(){var P$=Clazz.newPackage("java.awt.dnd"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "DropTargetEvent", null, 'java.util.EventObject');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$fields$=[['O',['context','java.awt.dnd.DropTargetContext']]]

Clazz.newMeth(C$, 'c$$java_awt_dnd_DropTargetContext',  function (dtc) {
;C$.superclazz.c$$O.apply(this,[dtc.getDropTarget$()]);C$.$init$.apply(this);
this.context=dtc;
}, 1);

Clazz.newMeth(C$, 'getDropTargetContext$',  function () {
return this.context;
});

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.3.1-v4');//Created 2022-03-19 05:25:10 Java2ScriptVisitor version 3.3.1-v4 net.sf.j2s.core.jar version 3.3.1-v4
