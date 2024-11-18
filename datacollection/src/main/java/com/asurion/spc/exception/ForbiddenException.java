package com.asurion.spc.exception;

public class ForbiddenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6658772947317052551L;

	public ForbiddenException(String reason) {
		super(reason);
	}

	public ForbiddenException(String reason, Object... args) {
		super(String.format(reason, args));
	}
}
