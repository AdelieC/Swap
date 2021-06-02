package com.swap.dal;

import java.util.List;

import com.swap.bo.User;

public interface UserDAO extends DAO<User> {
	public User selectById(int id) throws DALException;

	public User selectByUsername(String username) throws DALException;

	public User selectByEmail(String email) throws DALException;

	public List<User> selectByCity(String city) throws DALException;

	public List<User> selectAllAdmins() throws DALException;

	public void delete(int userId) throws DALException;

	public boolean exists(User u) throws DALException;
}