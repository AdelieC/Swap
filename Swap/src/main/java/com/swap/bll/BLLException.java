package com.swap.bll;

public class BLLException extends Exception {
	private static final long serialVersionUID = 1L;

	public BLLException() {
		super();
	}

	public BLLException(String message) {
		super(message);
	}

	public BLLException(String message, Exception e) {
		super(message);
		e.printStackTrace();
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("BLL - ");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
