(function(){var a=function(){};a.setCookie=function(b,e,f){var g=new Date();if(f){var c=getsec(f);g.setTime(g.getTime()+c*1)}else{var d=30;g.setTime(g.getTime()+d*24*60*60*1000)}document.cookie=b+"="+escape(e)+";expires="+g.toGMTString()};a.getCookie=function(c){var b,d=new RegExp("(^| )"+c+"=([^;]*)(;|$)");if(b=document.cookie.match(d)){return unescape(b[2])}else{return null}};a.delCookie=function(b){var d=new Date();d.setTime(d.getTime()-1);var c=getCookie(b);if(c!=null){document.cookie=b+"="+c+";expires="+d.toGMTString()}};window.Cookies=a})();