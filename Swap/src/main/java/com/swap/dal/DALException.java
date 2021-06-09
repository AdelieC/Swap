package com.swap.dal;

import java.sql.SQLException;

public class DALException extends SQLException {
	private static final long serialVersionUID = 1L;
	
	public DALException() {
		super();
	}
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, SQLException e) {
		super(message);
		e.printStackTrace();
	}
	
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("DAL - ");
		sb.append(super.getMessage());		
		return sb.toString() ;
	}
}
