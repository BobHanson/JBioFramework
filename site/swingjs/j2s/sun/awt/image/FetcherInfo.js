(function(){var P$=Clazz.newPackage("sun.awt.image"),p$1={},I$=[[0,'sun.awt.image.FetcherInfo','Thread','sun.awt.AppContext','java.util.Vector']],I$0=I$[0],$I$=function(i,n){return((i=(I$[i]||(I$[i]=Clazz.load(I$0[i])))),!n&&i.$load$&&Clazz.load(i,2),i)};
/*c*/var C$=Clazz.newClass(P$, "FetcherInfo");

C$.$clinit$=2;

Clazz.newMeth(C$, '$init$', function () {
},1);

C$.$fields$=[['I',['numFetchers','numWaiting'],'O',['fetchers','Thread[]','waitList','java.util.Vector']]
,['O',['FETCHER_INFO_KEY','java.lang.Object']]]

Clazz.newMeth(C$, 'c$',  function () {
;C$.$init$.apply(this);
this.fetchers=Clazz.array($I$(2), [4]);
this.numFetchers=0;
this.numWaiting=0;
this.waitList=Clazz.new_($I$(4,1));
}, 1);

Clazz.newMeth(C$, 'getFetcherInfo$',  function () {
var appContext=$I$(3).getAppContext$();
{
var info=appContext.get$O(C$.FETCHER_INFO_KEY);
if (info == null ) {
info=Clazz.new_(C$);
appContext.put$O$O(C$.FETCHER_INFO_KEY, info);
}return info;
}}, 1);

C$.$static$=function(){C$.$static$=0;
C$.FETCHER_INFO_KEY=("ImageFetcher_FetcherInfo");
};
})();
;Clazz.setTVer('3.3.1-v1');//Created 2021-01-14 18:18:21 Java2ScriptVisitor version 3.3.1-v1 net.sf.j2s.core.jar version 3.3.1-v1