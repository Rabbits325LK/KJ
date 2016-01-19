package com.keepjob.common.exception;

public class RecordFoundException extends ApplicationException {
	private static final long serialVersionUID = 1L;
	
	public RecordFoundException(){
		super();
	}
	
	public RecordFoundException(String message){
		super(message);
	}
	
	public RecordFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public RecordFoundException(Throwable cause) {
        super(cause);
    }

}
