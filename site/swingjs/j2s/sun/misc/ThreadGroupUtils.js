(function(){var P$=Clazz.newPackage("sun.misc"),I$=[[0,'Thread']],I$0=I$[0],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$0[i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "ThreadGroupUtils");

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

Clazz.newMeth(C$, 'c$',  function () {
;C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getRootThreadGroup$',  function () {
var currentTG=$I$(1).currentThread$().getThreadGroup$();
var parentTG=currentTG.getParent$();
while (parentTG != null ){
currentTG=parentTG;
parentTG=currentTG.getParent$();
}
return currentTG;
}, 1);
})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:18:25 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1