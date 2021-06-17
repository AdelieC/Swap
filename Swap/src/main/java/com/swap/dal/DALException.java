package com.swap.dal;

public class DALException extends Exception {
	private static final long serialVersionUID = 1L;

	public DALException() {
		super();
	}

	public DALException(String message) {
		super(message);
	}

	public DALException(String message, Exception e) {
		super(message);
		e.printStackTrace();
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("DAL - ");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
