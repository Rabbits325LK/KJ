(function(){
	var Cookies = function(){};
	/**
	 * 存入cookies
	 * @param name
	 * @param value
	 * @param time 过期时间 //s20是代表20秒 //h是指小时，如12小时则是：h12 //d是天数，30天则：d30
	 */
	Cookies.setCookie = function(name,value,time){
		var exp = new Date();
		if(time){
			var strsec = getsec(time);
			exp.setTime(exp.getTime() + strsec*1);
		}else{
			var Days = 30;
			exp.setTime(exp.getTime() + Days*24*60*60*1000);
		}
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	};
	/**
	 * 读取cookies
	 * @param name
	 */
	Cookies.getCookie = function(name) {
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg)) return unescape(arr[2]);
		else return null;
	}
	/**
	 * 删除cookies
	 * @param name
	 */
	Cookies.delCookie = function(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
	
	window.Cookies = Cookies;
})();