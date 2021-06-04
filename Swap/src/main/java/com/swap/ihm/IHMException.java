package com.swap.ihm;

import java.sql.SQLException;

public class IHMException extends Exception {
	private static final long serialVersionUID = 1L;

	public IHMException() {
		super();
	}

	public IHMException(String message) {
		super(message);
	}

	public IHMException(String message, SQLException e) {
		super(message);
		e.printStackTrace();
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("IHM - ");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
