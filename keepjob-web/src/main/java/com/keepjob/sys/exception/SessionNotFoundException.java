package com.keepjob.sys.exception;

import com.keepjob.common.exception.ApplicationException;

public class SessionNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 1L;
	
	public SessionNotFoundException(){
		super();
	}
	
	public SessionNotFoundException(String message){
		super(message);
	}
	
	public SessionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public SessionNotFoundException(Throwable cause) {
        super(cause);
    }

}
