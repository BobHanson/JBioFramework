(function(){var P$=Clazz.newPackage("javax.swing.text.html"),I$=[];
/*c*/var C$=Clazz.newClass(P$, "NoFramesView", null, 'javax.swing.text.html.BlockView');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$fields$=[['Z',['visible']]]

Clazz.newMeth(C$, 'c$$javax_swing_text_Element$I',  function (elem, axis) {
;C$.superclazz.c$$javax_swing_text_Element$I.apply(this,[elem, axis]);C$.$init$.apply(this);
this.visible=false;
}, 1);

Clazz.newMeth(C$, 'paint$java_awt_Graphics$java_awt_Shape',  function (g, allocation) {
var host=this.getContainer$();
if (host != null  && this.visible != (host).isEditable$()  ) {
this.visible=(host).isEditable$();
}if (!this.isVisible$()) {
return;
}C$.superclazz.prototype.paint$java_awt_Graphics$java_awt_Shape.apply(this, [g, allocation]);
});

Clazz.newMeth(C$, 'setParent$javax_swing_text_View',  function (p) {
if (p != null ) {
var host=p.getContainer$();
if (host != null ) {
this.visible=(host).isEditable$();
}}C$.superclazz.prototype.setParent$javax_swing_text_View.apply(this, [p]);
});

Clazz.newMeth(C$, 'isVisible$',  function () {
return this.visible;
});

Clazz.newMeth(C$, 'layout$I$I',  function (width, height) {
if (!this.isVisible$()) {
return;
}C$.superclazz.prototype.layout$I$I.apply(this, [width, height]);
});

Clazz.newMeth(C$, 'getPreferredSpan$I',  function (axis) {
if (!this.visible) {
return 0;
}return C$.superclazz.prototype.getPreferredSpan$I.apply(this, [axis]);
});

Clazz.newMeth(C$, 'getMinimumSpan$I',  function (axis) {
if (!this.visible) {
return 0;
}return C$.superclazz.prototype.getMinimumSpan$I.apply(this, [axis]);
});

Clazz.newMeth(C$, 'getMaximumSpan$I',  function (axis) {
if (!this.visible) {
return 0;
}return C$.superclazz.prototype.getMaximumSpan$I.apply(this, [axis]);
});

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.3.1-v4');//Created 2022-03-19 05:26:26 Java2ScriptVisitor version 3.3.1-v4 net.sf.j2s.core.jar version 3.3.1-v4
