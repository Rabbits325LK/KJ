package com.keepjob.common.exception;

public class StatusException extends ApplicationException {
	
	private static final long serialVersionUID = 1L;
	
	public StatusException(){
		super();
	}
	
	public StatusException(String message){
		super(message);
	}
	
	public StatusException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public StatusException(Throwable cause) {
        super(cause);
    }

}
