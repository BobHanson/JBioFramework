(function(){var P$=Clazz.newPackage("java.lang.ref"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "WeakReference", null, 'java.lang.ref.Reference');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$fields$=[['O',['o','java.lang.Object']]
,['Z',['ok']]]

Clazz.newMeth(C$, 'c$$O',  function (referent) {
;C$.superclazz.c$$O.apply(this,[null]);C$.$init$.apply(this);
if (C$.ok) {

this.o = new WeakMap();
this.o.set(this, referent);
} else {
this.o=referent;
}}, 1);

Clazz.newMeth(C$, 'c$$O$java_lang_ref_ReferenceQueue',  function (referent, q) {
C$.c$$O.apply(this, [referent]);
}, 1);

Clazz.newMeth(C$, 'get$O',  function (referent) {
return (C$.ok ? this.o.get(this) :this.o);
});

C$.$static$=function(){C$.$static$=0;
C$.ok=true;
{
try {

new WeakMap()
} catch (t) {
C$.ok=false;
}
};
};

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.3.1-v4');//Created 2022-03-19 05:25:25 Java2ScriptVisitor version 3.3.1-v4 net.sf.j2s.core.jar version 3.3.1-v4
