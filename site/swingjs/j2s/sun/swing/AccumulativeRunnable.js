(function(){var P$=Clazz.newPackage("sun.swing"),p$1={},I$=[[0,'java.util.ArrayList','java.util.Collections','javax.swing.SwingUtilities']],I$0=I$[0],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$0[i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "AccumulativeRunnable", null, null, 'Runnable');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
this.$arguments=null;
},1);

C$.$fields$=[['O',['$arguments','java.util.List']]]

Clazz.newMeth(C$, 'run$',  function () {
this.run$java_util_List(p$1.flush.apply(this, []));
});

Clazz.newMeth(C$, 'add$OA',  function (args) {
var isSubmitted=true;
if (this.$arguments == null ) {
isSubmitted=false;
this.$arguments=Clazz.new_($I$(1,1));
}$I$(2).addAll$java_util_Collection$OA(this.$arguments, args);
if (!isSubmitted) {
this.submit$();
}});

Clazz.newMeth(C$, 'submit$',  function () {
$I$(3).invokeLater$Runnable(this);
});

Clazz.newMeth(C$, 'flush',  function () {
var list=this.$arguments;
this.$arguments=null;
return list;
}, p$1);

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:18:28 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1
