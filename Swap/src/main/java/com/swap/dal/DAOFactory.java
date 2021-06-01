package com.swap.dal;

import com.swap.dal.jdbc.UserDAOjdbc;

public class DAOFactory {
	public static UserDAO getUserDAO() throws DALException {
		return new UserDAOjdbc();
	}
}
