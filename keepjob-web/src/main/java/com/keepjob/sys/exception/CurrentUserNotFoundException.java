package com.keepjob.sys.exception;

import com.keepjob.common.exception.ApplicationException;

public class CurrentUserNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 1L;
	
	public CurrentUserNotFoundException(){
		super();
	}
	
	public CurrentUserNotFoundException(String message){
		super(message);
	}
	
	public CurrentUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public CurrentUserNotFoundException(Throwable cause) {
        super(cause);
    }

}
