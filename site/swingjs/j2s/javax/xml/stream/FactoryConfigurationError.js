(function(){var P$=Clazz.newPackage("javax.xml.stream"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "FactoryConfigurationError", null, 'Error');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$fields$=[['O',['nested','Exception']]]

Clazz.newMeth(C$, 'c$',  function () {
Clazz.super_(C$, this);
}, 1);

Clazz.newMeth(C$, 'c$$Exception',  function (e) {
Clazz.super_(C$, this);
this.nested=e;
}, 1);

Clazz.newMeth(C$, 'c$$Exception$S',  function (e, msg) {
;C$.superclazz.c$$S.apply(this,[msg]);C$.$init$.apply(this);
this.nested=e;
}, 1);

Clazz.newMeth(C$, 'c$$S$Exception',  function (msg, e) {
;C$.superclazz.c$$S.apply(this,[msg]);C$.$init$.apply(this);
this.nested=e;
}, 1);

Clazz.newMeth(C$, 'c$$S',  function (msg) {
;C$.superclazz.c$$S.apply(this,[msg]);C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getException$',  function () {
return this.nested;
});

Clazz.newMeth(C$, 'getCause$',  function () {
return this.nested;
});

Clazz.newMeth(C$, 'getMessage$',  function () {
var msg=C$.superclazz.prototype.getMessage$.apply(this, []);
if (msg != null ) return msg;
if (this.nested != null ) {
msg=this.nested.getMessage$();
if (msg == null ) msg=this.nested.getClass$().toString();
}return msg;
});
})();
;Clazz.setTVer('3.3.1-v4');//Created 2022-03-19 05:26:31 Java2ScriptVisitor version 3.3.1-v4 net.sf.j2s.core.jar version 3.3.1-v4
