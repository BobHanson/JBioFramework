(function(){var P$=Clazz.newPackage("sun.java2d"),I$=[];
/*i*/var C$=Clazz.newInterface(P$, "StateTracker");

C$.$fields$=[[]
,['O',['ALWAYS_CURRENT','sun.java2d.StateTracker','+NEVER_CURRENT']]]

C$.$static$=function(){C$.$static$=0;
C$.ALWAYS_CURRENT=((P$.StateTracker$1||
(function(){/*a*/var C$=Clazz.newClass(P$, "StateTracker$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'sun.java2d.StateTracker', 1);

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

Clazz.newMeth(C$, 'isCurrent$',  function () {
return true;
});
})()
), Clazz.new_(P$.StateTracker$1.$init$,[this, null]));
C$.NEVER_CURRENT=((P$.StateTracker$2||
(function(){/*a*/var C$=Clazz.newClass(P$, "StateTracker$2", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'sun.java2d.StateTracker', 1);

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$fields$=[[]]

Clazz.newMeth(C$, 'isCurrent$',  function () {
return false;
});
})()
), Clazz.new_(P$.StateTracker$2.$init$,[this, null]));
};
})();
;Clazz.setTVer('3.3.1-v4');//Created 2022-03-19 05:26:41 Java2ScriptVisitor version 3.3.1-v4 net.sf.j2s.core.jar version 3.3.1-v4
