package com.swap.dal;

import java.util.List;

import com.swap.bo.User;

public interface UserDAO extends DAO<User> {
	public User selectById(int id) throws DALException;

	public User selectByUsername(String username) throws DALException;

	public List<User> searchByUsername(String username) throws DALException;

	public List<User> searchByCity(String city) throws DALException;

	public List<User> selectAllAdmins() throws DALException;

	public void delete(int userId) throws DALException;

	public void updatePasswordAndSalt(User u) throws DALException;

	public boolean successfullySetPasswordData(User user) throws DALException;

	public boolean exists(User u) throws DALException;

	public void updateBalance(int userId, int i) throws DALException;

	public void updateWasDisabled(User user, boolean disable) throws DALException;
}
