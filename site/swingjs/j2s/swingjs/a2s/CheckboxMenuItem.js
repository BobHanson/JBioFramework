(function(){var P$=Clazz.newPackage("swingjs.a2s"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "CheckboxMenuItem", null, 'javax.swing.JCheckBoxMenuItem');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

Clazz.newMeth(C$, 'isAWT$',  function () {
});

Clazz.newMeth(C$, 'c$$S',  function (string) {
;C$.superclazz.c$$S.apply(this,[string]);C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$',  function () {
Clazz.super_(C$, this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Z',  function (string, b) {
;C$.superclazz.c$$S$Z.apply(this,[string, b]);C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getState$',  function () {
return C$.superclazz.prototype.isSelected$.apply(this, []);
});

Clazz.newMeth(C$, 'setState$Z',  function (b) {
if ((this.model).setStateNoFire$Z(b)) this.秘getUI$().updateDOMNode$();
});
})();
;Clazz.setTVer('3.3.1-v4');//Created 2022-03-19 05:27:07 Java2ScriptVisitor version 3.3.1-v4 net.sf.j2s.core.jar version 3.3.1-v4
