(function(){var P$=Clazz.newPackage("javax.xml.bind.annotation.adapters"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "NormalizedStringAdapter", null, 'javax.xml.bind.annotation.adapters.XmlAdapter');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

Clazz.newMeth(C$, ['unmarshal$S','unmarshal$O'],  function (text) {
if (text == null ) return null;
var i=text.length$() - 1;
while (i >= 0 && !C$.isWhiteSpaceExceptSpace$C(text.charAt$I(i)) )--i;

if (i < 0) return text;
var buf=text.toCharArray$();
buf[i--]=" ";
for (; i >= 0; i--) if (C$.isWhiteSpaceExceptSpace$C(buf[i])) buf[i]=" ";

return  String.instantialize(buf);
});

Clazz.newMeth(C$, ['marshal$S','marshal$O'],  function (s) {
return s;
});

Clazz.newMeth(C$, 'isWhiteSpaceExceptSpace$C',  function (ch) {
if (ch.$c() >= 32 ) return false;
return ch.$c() == 9  || ch.$c() == 10   || ch.$c() == 13  ;
}, 1);

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.3.1-v4');//Created 2022-03-19 05:26:29 Java2ScriptVisitor version 3.3.1-v4 net.sf.j2s.core.jar version 3.3.1-v4
