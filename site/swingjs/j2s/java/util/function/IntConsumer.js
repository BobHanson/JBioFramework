(function(){var P$=Clazz.newPackage("java.util.function"),I$=[[0,'java.util.Objects']],I$0=I$[0],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$0[i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*i*/var C$=Clazz.newInterface(P$, "IntConsumer");
C$.$defaults$ = function(C$){

Clazz.newMeth(C$, 'andThen$java_util_function_IntConsumer',  function (after) {
$I$(1).requireNonNull$O(after);
return ((P$.IntConsumer$lambda1||
(function(){/*m*/var C$=Clazz.newClass(P$, "IntConsumer$lambda1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.util.function.IntConsumer', 1);

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);
/*lambda_E*/
Clazz.newMeth(C$, ['accept$I','accept$O'],  function (t) {
this.accept$I(t);
this.$finals$.after.accept$I(t);
});
})()
), Clazz.new_(P$.IntConsumer$lambda1.$init$,[this, {after:after}]));
});
};})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:17:35 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1
