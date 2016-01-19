Date.prototype.formatDate = function() {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var date = this.getDate();
	if (month <= 9) {
		month = "0" + month;
	}
	if (date <= 9) {
		date = "0" + date;
	}
	return year + "-" + month + "-" + date;
}

Date.prototype.formatLongDate = function() {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var date = this.getDate();
	var hour = this.getHours();
	var min = this.getMinutes();
	var sec = this.getSeconds();
	if (month <= 9)
		month = "0" + month;
	if (date <= 9)
		date = "0" + date;
	if (hour <= 9)
		hour = "0" + hour;
	if (min <= 9)
		min = "0" + min;
	if (sec <= 9)
		sec = "0" + sec;
	return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
}

Date.prototype.nextDate = function() {
	var d = this.getDate();
	var m = this.getMonth();
	var y = this.getYear();
	var nextDate = new Date(y, m, d + 1);
	return nextDate;
}

String.prototype.toDate = function() {
	var m = this
			.match(new RegExp("^((\\d{4})|(\\d{2}))(-)(\\d{1,2})\\4(\\d{1,2})$"));
	if (m == null)
		return new Date();
	day = m[6];
	month = --m[5];
	year = m[2];
	month = month == 12 ? 0 : month;
	return new Date(year, month, day);
};
function getBirthDay(idCard){
	var result = "";
	if(idCard.length != 15 && idCard.length != 18){
		alert("身份证号格式错误.");
	}
	
	if(idCard.length() == 15){
		result = "19" + idCard.substring(6, 12);
	}else{
		result = idCard.substring(6, 14);
	}
	result = result.substring(0, 4) + "-" + result.substring(4, 6) + "-" + result.substring(6);
	return result;
}

(function($){
	//正数值类型
	jQuery.fn.onlypressPositiveNumber = function() { 
		$(this).css({imeMode:"disabled",'-moz-user-select':"none"}); 
		$(this).bind("keypress",function(e){
			if(e.ctrlKey == true || e.shiftKey == true) {
				return false; 
			}
			if((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) 
				|| e.which == 0 || e.which == 8 || e.which == 46){ 
				return true; 
			}else if(e.ctrlKey == true && (e.which == 99 || e.which == 118)){
				return false;	
			}else{
				return false;
			}
		}).bind("contextmenu",function(){return false;}) 
		.bind("selectstart",function(){return false;}) 
		.bind("paste",function(){return false;}); 
	}; 	
	
	
	//数值类型 包括负数
	jQuery.fn.onlypressNumber = function() { 
		$(this).css({imeMode:"disabled",'-moz-user-select':"none"}); 
		$(this).bind("keypress",function(e){ 
			if(e.ctrlKey == true || e.shiftKey == true) {
				return false; 
			}
			if((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) 
				|| e.which == 0 || e.which == 8 || e.which == 46 || e.which == 45) {
				return true; 
			}else if(e.ctrlKey == true && (e.which == 99 || e.which == 118)) {
				return false; 
			}else{ 
				return false;
			}
		}).bind("contextmenu",function(){return false;}) 
		.bind("selectstart",function(){return false;}) 
		.bind("paste",function(){return false;}); 
	}; 	
	
	
	//正整型  
	jQuery.fn.onlypressPositiveInteger = function() { 
		$(this).css({imeMode:"disabled",'-moz-user-select':"none"}); 
		$(this).bind("keypress",function(e){ 
			if(e.ctrlKey == true || e.shiftKey == true){ 
				return false; 
			}
			if((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) || e.which == 0 || e.which == 8){
				return true; 
			}else if(e.ctrlKey == true && (e.which == 99 || e.which == 118)){
				return false; 
			}else {
				return false;
			}
		}).bind("contextmenu",function(){return false;}) 
		.bind("selectstart",function(){return false;}) 
		.bind("paste",function(){return false;}); 
	};
	
	//整型  包括负数
	jQuery.fn.onlypressInteger = function() { 
		$(this).css({imeMode:"disabled",'-moz-user-select':"none"}); 
		$(this).bind("keypress",function(e){ 
			if(e.ctrlKey == true || e.shiftKey == true){
				return false;
			}
			if((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) 
				|| e.which == 0 || e.which == 8 || e.which == 45){
				return true; 
			}else if(e.ctrlKey == true && (e.which == 99 || e.which == 118)) {
				return false; 
			}else {
				return false;
			}
		}).bind("contextmenu",function(){return false;}) 
		.bind("selectstart",function(){return false;}) 
		.bind("paste",function(){return false;}); 
	}; 	
})(jQuery);