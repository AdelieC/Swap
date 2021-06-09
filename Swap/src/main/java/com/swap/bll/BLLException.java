package com.swap.bll;

import java.sql.SQLException;

public class BLLException extends SQLException {
	private static final long serialVersionUID = 1L;
	
	public BLLException() {
		super();
	}
	
	public BLLException(String message) {
		super(message);
	}
	
	public BLLException(String message, SQLException e) {
		super(message);
		e.printStackTrace();
	}
	
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("BLL - ");
		sb.append(super.getMessage());		
		return sb.toString() ;
	}
}
