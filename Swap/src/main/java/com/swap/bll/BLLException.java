package com.swap.bll;

import java.sql.SQLException;

public class BLLException extends SQLException {
	private static final long serialVersionUID = 1L;

	public BLLException() {
		super();
	}

	public BLLException(String reason) {
		super(reason);
	}

	public BLLException(String reason, SQLException e) {
		super(reason);
		e.printStackTrace();
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("BLL - ");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
