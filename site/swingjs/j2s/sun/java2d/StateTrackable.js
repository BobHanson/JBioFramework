(function(){var P$=Clazz.newPackage("sun.java2d"),I$=[];
/*i*/var C$=Clazz.newInterface(P$, "StateTrackable", function(){
});
C$.$classes$=[['State',25]];
;
(function(){/*e*/var C$=Clazz.newClass(P$.StateTrackable, "State", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'Enum');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$static$=function(){C$.$static$=0;
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "IMMUTABLE", 0, []);
Clazz.newEnumConst($vals, C$.c$, "STABLE", 1, []);
Clazz.newEnumConst($vals, C$.c$, "DYNAMIC", 2, []);
Clazz.newEnumConst($vals, C$.c$, "UNTRACKABLE", 3, []);
};

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values$', function() { return $vals }, 1);
Clazz.newMeth(C$, 'valueOf$S', function(name) { for (var val in $vals){ if ($vals[val].name == name) return $vals[val]} return null }, 1);
})()
})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:18:24 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1