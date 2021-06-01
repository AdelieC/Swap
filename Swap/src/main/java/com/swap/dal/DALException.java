package com.swap.dal;

import java.sql.SQLException;

public class DALException extends SQLException {
	private static final long serialVersionUID = 1L;

	public DALException() {
		super();
	}

	public DALException(String reason) {
		super(reason);
	}

	public DALException(String reason, SQLException e) {
		super(reason);
		e.printStackTrace();
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("DAL - ");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
