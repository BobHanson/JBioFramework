(function(){var P$=Clazz.newPackage("java.util.concurrent"),I$=[[0,'swingjs.JSUtil']],I$0=I$[0],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$0[i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "ConcurrentSkipListSet", null, 'java.util.HashSet');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

Clazz.newMeth(C$, 'c$',  function () {
;C$.superclazz.c$.apply(this,[]);C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Comparator',  function (comparator) {
Clazz.super_(C$, this);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection',  function (c) {
;C$.superclazz.c$$java_util_Collection.apply(this,[c]);C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_SortedSet',  function (s) {
;C$.superclazz.c$.apply(this,[]);C$.$init$.apply(this);
this.addAll$java_util_Collection(s);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_concurrent_ConcurrentNavigableMap',  function (m) {
Clazz.super_(C$, this);
$I$(1).notImplemented$S(null);
}, 1);
})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:17:33 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1