package com.keepjob.common.exception;

public class RecordNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(){
		super();
	}
	
	public RecordNotFoundException(String message){
		super(message);
	}
	
	public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public RecordNotFoundException(Throwable cause) {
        super(cause);
    }

}
