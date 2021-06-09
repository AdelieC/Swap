package com.swap.bo;

public class BOException extends Exception {
	private static final long serialVersionUID = 1L;

	public BOException() {
		super();
	}

	public BOException(String message) {
		super(message);
	}

	public BOException(String message, Exception e) {
		super(message);
		e.printStackTrace();
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("BO - ");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
