	 //将表单数据转为json
	function formToJson(id) {
			var arr = $("#" + id).serializeArray()
		    var jsonStr = "";
		    jsonStr += '{';
		    for (var i = 0; i < arr.length; i++) {
		        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
		    }
		    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
		    jsonStr += '}'
		 
		    var json =eval("("+jsonStr+")"); 
	    return json
	}
	
	function isCardNo(num) {    
	    if (isNaN(num)){        
		     alert("输入的不全是数字");         
		     return false;    
	    }    
	    var len = num.length, re;     
	    if (len == 15)      
	    	 re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);    
	    else if (len == 18)      
	    	 re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);    
	    else {        
		     alert("输入数字位数不对");         
		     return false;    
	    }    
	    var a = num.match(re);    
	    if (a != null) {      
		     if (len==15) {        
			     var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);        
			     var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];      
		     }      
		     else{        
			     var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);        
			     var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];      
		     }      
		     if (!B) {        
			     alert("输入身份证号 "+ a[0] +" 里出生日期对");         
			     return false;        
		     }    
	    }    
	    return true;
	}