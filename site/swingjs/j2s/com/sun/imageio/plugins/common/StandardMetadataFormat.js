(function(){var P$=Clazz.newPackage("com.sun.imageio.plugins.common"),p$1={},I$=[[0,'java.util.ArrayList']],I$0=I$[0],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$0[i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "StandardMetadataFormat", null, 'javax.imageio.metadata.IIOMetadataFormatImpl');

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

Clazz.newMeth(C$, 'addSingleAttributeElement$S$S$I',  function (elementName, parentName, dataType) {
this.addElement$S$S$I(elementName, parentName, 0);
this.addAttribute$S$S$I$Z$S(elementName, "value", dataType, true, null);
}, p$1);

Clazz.newMeth(C$, 'c$',  function () {
;C$.superclazz.c$$S$I.apply(this,["javax_imageio_1.0", 2]);C$.$init$.apply(this);
var values;
this.addElement$S$S$I("Chroma", "javax_imageio_1.0", 2);
this.addElement$S$S$I("ColorSpaceType", "Chroma", 0);
values=Clazz.new_($I$(1,1));
values.add$O("XYZ");
values.add$O("Lab");
values.add$O("Luv");
values.add$O("YCbCr");
values.add$O("Yxy");
values.add$O("YCCK");
values.add$O("PhotoYCC");
values.add$O("RGB");
values.add$O("GRAY");
values.add$O("HSV");
values.add$O("HLS");
values.add$O("CMYK");
values.add$O("CMY");
values.add$O("2CLR");
values.add$O("3CLR");
values.add$O("4CLR");
values.add$O("5CLR");
values.add$O("6CLR");
values.add$O("7CLR");
values.add$O("8CLR");
values.add$O("9CLR");
values.add$O("ACLR");
values.add$O("BCLR");
values.add$O("CCLR");
values.add$O("DCLR");
values.add$O("ECLR");
values.add$O("FCLR");
this.addAttribute$S$S$I$Z$S$java_util_List("ColorSpaceType", "name", 0, true, null, values);
this.addElement$S$S$I("NumChannels", "Chroma", 0);
this.addAttribute$S$S$I$Z$I$I("NumChannels", "value", 2, true, 0, 2147483647);
this.addElement$S$S$I("Gamma", "Chroma", 0);
this.addAttribute$S$S$I$Z$S("Gamma", "value", 3, true, null);
this.addElement$S$S$I("BlackIsZero", "Chroma", 0);
this.addBooleanAttribute$S$S$Z$Z("BlackIsZero", "value", true, true);
this.addElement$S$S$I$I("Palette", "Chroma", 0, 2147483647);
this.addElement$S$S$I("PaletteEntry", "Palette", 0);
this.addAttribute$S$S$I$Z$S("PaletteEntry", "index", 2, true, null);
this.addAttribute$S$S$I$Z$S("PaletteEntry", "red", 2, true, null);
this.addAttribute$S$S$I$Z$S("PaletteEntry", "green", 2, true, null);
this.addAttribute$S$S$I$Z$S("PaletteEntry", "blue", 2, true, null);
this.addAttribute$S$S$I$Z$S("PaletteEntry", "alpha", 2, false, "255");
this.addElement$S$S$I("BackgroundIndex", "Chroma", 0);
this.addAttribute$S$S$I$Z$S("BackgroundIndex", "value", 2, true, null);
this.addElement$S$S$I("BackgroundColor", "Chroma", 0);
this.addAttribute$S$S$I$Z$S("BackgroundColor", "red", 2, true, null);
this.addAttribute$S$S$I$Z$S("BackgroundColor", "green", 2, true, null);
this.addAttribute$S$S$I$Z$S("BackgroundColor", "blue", 2, true, null);
this.addElement$S$S$I("Compression", "javax_imageio_1.0", 2);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["CompressionTypeName", "Compression", 0]);
this.addElement$S$S$I("Lossless", "Compression", 0);
this.addBooleanAttribute$S$S$Z$Z("Lossless", "value", true, true);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["NumProgressiveScans", "Compression", 2]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["BitRate", "Compression", 3]);
this.addElement$S$S$I("Data", "javax_imageio_1.0", 2);
this.addElement$S$S$I("PlanarConfiguration", "Data", 0);
values=Clazz.new_($I$(1,1));
values.add$O("PixelInterleaved");
values.add$O("PlaneInterleaved");
values.add$O("LineInterleaved");
values.add$O("TileInterleaved");
this.addAttribute$S$S$I$Z$S$java_util_List("PlanarConfiguration", "value", 0, true, null, values);
this.addElement$S$S$I("SampleFormat", "Data", 0);
values=Clazz.new_($I$(1,1));
values.add$O("SignedIntegral");
values.add$O("UnsignedIntegral");
values.add$O("Real");
values.add$O("Index");
this.addAttribute$S$S$I$Z$S$java_util_List("SampleFormat", "value", 0, true, null, values);
this.addElement$S$S$I("BitsPerSample", "Data", 0);
this.addAttribute$S$S$I$Z$I$I("BitsPerSample", "value", 2, true, 1, 2147483647);
this.addElement$S$S$I("SignificantBitsPerSample", "Data", 0);
this.addAttribute$S$S$I$Z$I$I("SignificantBitsPerSample", "value", 2, true, 1, 2147483647);
this.addElement$S$S$I("SampleMSB", "Data", 0);
this.addAttribute$S$S$I$Z$I$I("SampleMSB", "value", 2, true, 1, 2147483647);
this.addElement$S$S$I("Dimension", "javax_imageio_1.0", 2);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["PixelAspectRatio", "Dimension", 3]);
this.addElement$S$S$I("ImageOrientation", "Dimension", 0);
values=Clazz.new_($I$(1,1));
values.add$O("Normal");
values.add$O("Rotate90");
values.add$O("Rotate180");
values.add$O("Rotate270");
values.add$O("FlipH");
values.add$O("FlipV");
values.add$O("FlipHRotate90");
values.add$O("FlipVRotate90");
this.addAttribute$S$S$I$Z$S$java_util_List("ImageOrientation", "value", 0, true, null, values);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["HorizontalPixelSize", "Dimension", 3]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["VerticalPixelSize", "Dimension", 3]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["HorizontalPhysicalPixelSpacing", "Dimension", 3]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["VerticalPhysicalPixelSpacing", "Dimension", 3]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["HorizontalPosition", "Dimension", 3]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["VerticalPosition", "Dimension", 3]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["HorizontalPixelOffset", "Dimension", 2]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["VerticalPixelOffset", "Dimension", 2]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["HorizontalScreenSize", "Dimension", 2]);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["VerticalScreenSize", "Dimension", 2]);
this.addElement$S$S$I("Document", "javax_imageio_1.0", 2);
this.addElement$S$S$I("FormatVersion", "Document", 0);
this.addAttribute$S$S$I$Z$S("FormatVersion", "value", 0, true, null);
this.addElement$S$S$I("SubimageInterpretation", "Document", 0);
values=Clazz.new_($I$(1,1));
values.add$O("Standalone");
values.add$O("SinglePage");
values.add$O("FullResolution");
values.add$O("ReducedResolution");
values.add$O("PyramidLayer");
values.add$O("Preview");
values.add$O("VolumeSlice");
values.add$O("ObjectView");
values.add$O("Panorama");
values.add$O("AnimationFrame");
values.add$O("TransparencyMask");
values.add$O("CompositingLayer");
values.add$O("SpectralSlice");
values.add$O("Unknown");
this.addAttribute$S$S$I$Z$S$java_util_List("SubimageInterpretation", "value", 0, true, null, values);
this.addElement$S$S$I("ImageCreationTime", "Document", 0);
this.addAttribute$S$S$I$Z$S("ImageCreationTime", "year", 2, true, null);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageCreationTime", "month", 2, true, null, "1", "12", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageCreationTime", "day", 2, true, null, "1", "31", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageCreationTime", "hour", 2, false, "0", "0", "23", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageCreationTime", "minute", 2, false, "0", "0", "59", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageCreationTime", "second", 2, false, "0", "0", "60", true, true);
this.addElement$S$S$I("ImageModificationTime", "Document", 0);
this.addAttribute$S$S$I$Z$S("ImageModificationTime", "year", 2, true, null);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageModificationTime", "month", 2, true, null, "1", "12", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageModificationTime", "day", 2, true, null, "1", "31", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageModificationTime", "hour", 2, false, "0", "0", "23", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageModificationTime", "minute", 2, false, "0", "0", "59", true, true);
this.addAttribute$S$S$I$Z$S$S$S$Z$Z("ImageModificationTime", "second", 2, false, "0", "0", "60", true, true);
this.addElement$S$S$I$I("Text", "javax_imageio_1.0", 0, 2147483647);
this.addElement$S$S$I("TextEntry", "Text", 0);
this.addAttribute$S$S$I$Z$S("TextEntry", "keyword", 0, false, null);
this.addAttribute$S$S$I$Z$S("TextEntry", "value", 0, true, null);
this.addAttribute$S$S$I$Z$S("TextEntry", "language", 0, false, null);
this.addAttribute$S$S$I$Z$S("TextEntry", "encoding", 0, false, null);
values=Clazz.new_($I$(1,1));
values.add$O("none");
values.add$O("lzw");
values.add$O("zip");
values.add$O("bzip");
values.add$O("other");
this.addAttribute$S$S$I$Z$S$java_util_List("TextEntry", "compression", 0, false, "none", values);
this.addElement$S$S$I("Transparency", "javax_imageio_1.0", 2);
this.addElement$S$S$I("Alpha", "Transparency", 0);
values=Clazz.new_($I$(1,1));
values.add$O("none");
values.add$O("premultiplied");
values.add$O("nonpremultiplied");
this.addAttribute$S$S$I$Z$S$java_util_List("Alpha", "value", 0, false, "none", values);
p$1.addSingleAttributeElement$S$S$I.apply(this, ["TransparentIndex", "Transparency", 2]);
this.addElement$S$S$I("TransparentColor", "Transparency", 0);
this.addAttribute$S$S$I$Z$I$I("TransparentColor", "value", 2, true, 0, 2147483647);
this.addElement$S$S$I$I("TileTransparencies", "Transparency", 0, 2147483647);
this.addElement$S$S$I("TransparentTile", "TileTransparencies", 0);
this.addAttribute$S$S$I$Z$S("TransparentTile", "x", 2, true, null);
this.addAttribute$S$S$I$Z$S("TransparentTile", "y", 2, true, null);
this.addElement$S$S$I$I("TileOpacities", "Transparency", 0, 2147483647);
this.addElement$S$S$I("OpaqueTile", "TileOpacities", 0);
this.addAttribute$S$S$I$Z$S("OpaqueTile", "x", 2, true, null);
this.addAttribute$S$S$I$Z$S("OpaqueTile", "y", 2, true, null);
}, 1);

Clazz.newMeth(C$, 'canNodeAppear$S$javax_imageio_ImageTypeSpecifier',  function (elementName, imageType) {
return true;
});
})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:16:49 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1