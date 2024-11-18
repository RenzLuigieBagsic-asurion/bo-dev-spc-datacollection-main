package com.asurion.spc.exception;

public class ServiceException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6179670306345451502L;

	public ServiceException(Throwable cause) {
        super(cause);
    }
	
	public ServiceException(String message) {
        super(message);
    }
}
