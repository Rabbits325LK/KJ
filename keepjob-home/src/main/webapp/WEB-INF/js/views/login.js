/*获取根目录
 ---------------------------------------------------------------------------------*/
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //js获取项目根路径，如： http://localhost:8083/uimcardprj
    return(localhostPaht+projectName);
}
var path = getRootPath();

/*开关设置
 ---------------------------------------------*/
var emailFlag = false;
var phoneFlag = false;
var passwordFlag = false;


/*================================================================================*/
$(function() {

		/*登录
		--------------------------------------------------------*/
		$('#loginSubmit')
				.click(
						function() {
							var options = {
								url : path + '/main/login.json',
								type : 'post',
								dataType : 'json',
								data : $("#loginForm").serialize(),
								success : function(data) {
									//alert(data.success);
									if (data.success) {
										console.info(data.success);
										window.location.href = path + "/user/index.html";
									}else{
										console.info(data.message);
										LogoinTreatmentResult(data.message);
									}
								}
							};
							$.ajax(options);
						})

		/*注册
		--------------------------------------------------------*/
		$('#registerSubmit').click(function() {
			var options = {
				url : path + '/main/register.json',
				type : 'post',
				dataType : 'json',
				data : $("#registerForm").serialize(),
				success : function(data) {

					if (data.success) {
						//alert(data.success);
						$('#RegisterModal').modal('hide');
						$('#MsgModal').modal('show');
						console.info("MsgModal->Show");
					} else {
						alert(data.messge);
					}
				}
			};
			$.ajax(options);
		});
	});
	/*================================================================================*/

	var emailFlag = false;
    var phoneFlag = false;
    var passwordFlag = false;
    
    function changeButton(){
    		if(emailFlag == true && phoneFlag == true && passwordFlag == true){
    			$('#registerSubmit').removeAttr("disabled");
    		}else{
    			$('#registerSubmit').attr("disabled","disabled");
    		}
    }
	
	
	/*验证邮箱
	--------------------------------------------------------*/
	function checkEmail(){
		var email = $('#email').val();
		if(email == '' || email == null){
			$('#RE').removeClass();
			$('#RE').addClass("input-group has-warning has-feedback");
			emailFlag = false;
		}else{
			$.ajax({
				url : path + '/main/checkEmail.json',
				type : 'post',
				dataType : 'json',
				data : { email : email},
				success : function(data){
					if(data.success){
						$('#RE').removeClass();
						$('#RE').addClass("input-group has-success has-feedback");
						emailFlag = true;
						changeButton();
					}else{
						$('#RE').removeClass();
						$('#RE').addClass("input-group has-error has-feedback");
						emailFlag = false;
					}
				}
			});
		}
	}
	
	/*验证手机号码
	--------------------------------------------------------*/
	function checkPhone(){
		var phone = $('#phone').val();
		if(phone == '' || phone == null){
			$('#RP').removeClass();
			$('#RP').addClass("input-group has-warning has-feedback");
			phoneFlag = false;
		}else{
			$.ajax({
				url : path + '/main/checkPhone.json',
				type : 'post',
				dataType : 'json',
				data : { phone : phone},
				success : function(data){
					if(data.success){
						$('#RP').removeClass();
						$('#RP').addClass("input-group has-success has-feedback");
						phoneFlag = true;
						changeButton();
					}else{
						$('#RP').removeClass();
						$('#RP').addClass("input-group has-error has-feedback");
						phoneFlag = false;
					}
				}
			});
		}
	}
	
	/*验证密码是否一致
	--------------------------------------------------------*/
	function checkPassword(){
		var password = $('#password').val();
		var rpassword = $('#rpassword').val();
		if(6 > password.length ){
			console.info(password.length);
			$('#RPW').removeClass();
			$('#RPW').addClass("input-group has-warning has-feedback");
			passwordFlag = false;
		}else{
			if(password == rpassword){
				$('#RPW').removeClass();
				$('#RRPW').removeClass();
				$('#RPW').addClass("input-group has-success has-feedback");
				$('#RRPW').addClass("input-group has-success has-feedback");
				passwordFlag = true;
				changeButton();
			}else{
				$('#RPW').removeClass();
				$('#RRPW').removeClass();
				$('#RPW').addClass("input-group has-error has-feedback");
				$('#RRPW').addClass("input-group has-error has-feedback");
				passwordFlag = false;
			}
		}
	}
	
	function LogoinTreatmentResult(str){
		$('#ErrorMsgLoginDiv').html("<div class='alert alert-danger' role='alert' id='ErrorAlert'>"+str+"</div>");
		//setInterval("HideErrorAlert()",2000);
	}
	
	function RegisterTreatmentResult(str){
		$('#ErrorMsgRegisterDiv').html("<div class='alert alert-danger' role='alert' id='ErrorAlert'>"+str+"</div>");
		//setInterval("HideErrorAlert()",2000);
	}
	
	function HideErrorAlert(){
		$('#ErrorAlert').alert('close');
	}