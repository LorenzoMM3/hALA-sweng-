function MyWebApp(){var H='bootstrap',I='begin',J='gwt.codesvr.MyWebApp=',K='gwt.codesvr=',L='MyWebApp',M='startup',N='DUMMY',O=0,P=1,Q='iframe',R='position:absolute; width:0; height:0; border:none; left: -1000px;',S=' top: -1000px;',T='Chrome',U='CSS1Compat',V='<!doctype html>',W='',X='<html><head><\/head><body><\/body><\/html>',Y='undefined',Z='readystatechange',$=10,_='script',ab='javascript',bb='Failed to load ',cb='moduleStartup',db='scriptTagAdded',eb='moduleRequested',fb='meta',gb='name',hb='MyWebApp::',ib='::',jb='gwt:property',kb='content',lb='=',mb='gwt:onPropertyErrorFn',nb='Bad handler "',ob='" for "gwt:onPropertyErrorFn"',pb='gwt:onLoadErrorFn',qb='" for "gwt:onLoadErrorFn"',rb='/MyWebApp.nocache.js',sb='Unable to load Super Dev Mode version of MyWebApp.',tb='selectingPermutation',ub='MyWebApp.devmode.js',vb='0B1183B052C95557E58F783DA7EA084D',wb=':',xb='.cache.js',yb='link',zb='rel',Ab='stylesheet',Bb='href',Cb='head',Db='loadExternalRefs',Eb='gwt/clean/clean.css',Fb='end';var n=window;var o=document;q(H,I);function p(){var a=n.location.search;return a.indexOf(J)!=-1||a.indexOf(K)!=-1}
function q(a,b){if(n.__gwtStatsEvent){n.__gwtStatsEvent({moduleName:L,sessionId:n.__gwtStatsSessionId,subSystem:M,evtGroup:a,millis:(new Date).getTime(),type:b})}}
MyWebApp.__sendStats=q;MyWebApp.__moduleName=L;MyWebApp.__errFn=null;MyWebApp.__moduleBase=N;MyWebApp.__softPermutationId=O;MyWebApp.__computePropValue=null;MyWebApp.__getPropMap=null;MyWebApp.__installRunAsyncCode=function(){};MyWebApp.__gwtStartLoadingFragment=function(){return null};MyWebApp.__gwt_isKnownPropertyValue=function(){return false};MyWebApp.__gwt_getMetaProperty=function(){return null};var r=null;var s=n.__gwt_activeModules=n.__gwt_activeModules||{};s[L]={moduleName:L};MyWebApp.__moduleStartupDone=function(e){var f=s[L].bindings;s[L].bindings=function(){var a=f?f():{};var b=e[MyWebApp.__softPermutationId];for(var c=O;c<b.length;c++){var d=b[c];a[d[O]]=d[P]}return a}};var t;function u(){v();return t}
function v(){if(t){return}var a=o.createElement(Q);a.id=L;a.style.cssText=R+S;a.tabIndex=-1;o.body.appendChild(a);t=a.contentWindow.document;if(navigator.userAgent.indexOf(T)==-1){t.open();var b=document.compatMode==U?V:W;t.write(b+X);t.close()}}
function w(f){function g(a){function b(){if(typeof o.readyState==Y){return typeof o.body!=Y&&o.body!=null}return /loaded|complete/.test(o.readyState)}
var c=b();if(c){a();return}function d(){if(!c){if(!b()){return}c=true;a();if(o.removeEventListener){o.removeEventListener(Z,d,false)}if(e){clearInterval(e)}}}
if(o.addEventListener){o.addEventListener(Z,d,false)}var e=setInterval(function(){d()},$)}
function h(a){var b=u();var c=b.body;var d=b.createElement(_);d.language=ab;if(location.host){d.crossOrigin=W}d.src=a;if(MyWebApp.__errFn){d.onerror=function(){MyWebApp.__errFn(L,new Error(bb+a))}}c.appendChild(d);q(cb,db)}
q(cb,eb);g(function(){h(f)})}
MyWebApp.__startLoadingFragment=function(a){return C(a)};MyWebApp.__installRunAsyncCode=function(a){var b=u();var c=b.body;var d=b.createElement(_);d.language=ab;d.text=a;c.appendChild(d);c.removeChild(d)};function A(){var c={};var d;var e;var f=o.getElementsByTagName(fb);for(var g=O,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(gb),k;if(j){j=j.replace(hb,W);if(j.indexOf(ib)>=O){continue}if(j==jb){k=i.getAttribute(kb);if(k){var l,m=k.indexOf(lb);if(m>=O){j=k.substring(O,m);l=k.substring(m+P)}else{j=k;l=W}c[j]=l}}else if(j==mb){k=i.getAttribute(kb);if(k){try{d=eval(k)}catch(a){alert(nb+k+ob)}}}else if(j==pb){k=i.getAttribute(kb);if(k){try{e=eval(k)}catch(a){alert(nb+k+qb)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};r=d;MyWebApp.__errFn=e}
function B(){n.__gwt_activeModules[L].superdevmode=true;var a=rb;var b=o.getElementsByTagName(_);for(var c=O;;c++){var d=b[c];if(!d){break}var e=d.src;var f=e.lastIndexOf(a);if(f==e.length-a.length){return e.substring(O,f+P)}}n.alert(sb);return null}
function C(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return MyWebApp.__moduleBase+a}
function D(){var f=[];var g=O;var h=[];var i=[];function j(a){var b=i[a](),c=h[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(r){r(a,d,b)}throw null}
__gwt_isKnownPropertyValue=function(a,b){return b in h[a]};MyWebApp.__getPropMap=function(){var a={};for(var b in h){if(h.hasOwnProperty(b)){a[b]=j(b)}}return a};MyWebApp.__computePropValue=j;n.__gwt_activeModules[L].bindings=MyWebApp.__getPropMap;q(H,tb);if(p()){return C(ub)}var k;try{k=vb;var l=k.indexOf(wb);if(l!=-1){g=parseInt(k.substring(l+P),$);k=k.substring(O,l)}}catch(a){}MyWebApp.__softPermutationId=g;return C(k+xb)}
function F(){if(!n.__gwt_stylesLoaded){n.__gwt_stylesLoaded={}}function c(a){if(!__gwt_stylesLoaded[a]){var b=o.createElement(yb);b.setAttribute(zb,Ab);b.setAttribute(Bb,C(a));o.getElementsByTagName(Cb)[O].appendChild(b);__gwt_stylesLoaded[a]=true}}
q(Db,I);c(Eb);q(Db,Fb)}
A();MyWebApp.__moduleBase=B();s[L].moduleBase=MyWebApp.__moduleBase;var G=D();F();q(H,Fb);w(G);return true}
MyWebApp.succeeded=MyWebApp();