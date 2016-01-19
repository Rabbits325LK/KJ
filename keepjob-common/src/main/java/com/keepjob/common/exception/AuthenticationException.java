package com.keepjob.common.exception;

@SuppressWarnings("serial")
public class AuthenticationException extends ApplicationException {

	public AuthenticationException(){
		super("认证失败");
	}

	public AuthenticationException(String message){
		super(message);
	}

	public AuthenticationException(String message,Throwable cause){
		super(message,cause);
	}

	public AuthenticationException(Throwable cause){
		super(cause);
	}

}
