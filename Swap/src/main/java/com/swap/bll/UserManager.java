package com.swap.bll;

import java.util.ArrayList;
import java.util.List;

import com.swap.bo.User;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.UserDAO;

//TODO : add clauses to check if not null where they should be
public class UserManager {
	private UserDAO userDAO;

	public UserManager() {
		this.userDAO = DAOFactory.getUserDAO();
	}

	public void create(User u) throws BLLException {
		if (exists(u))
			throw new BLLException("User already exists");
		if (!isValid(u))
			throw new BLLException("User is not valid");
		try {
			userDAO.create(u);
		} catch (DALException e) {
			throw new BLLException("Failed to create user", e);
		}
	}

	public List<User> getAll() throws BLLException {
		List<User> list = new ArrayList<User>();
		try {
			list = userDAO.read();
		} catch (DALException e) {
			throw new BLLException("Failed to get all users", e);
		}
		return list;
	}

	public void update(User u) throws BLLException {
		if (isValid(u)) {
			try {
				this.userDAO.update(u);
			} catch (DALException e) {
				throw new BLLException("Failed to update user", e);
			}
		}
	}

	public void delete(int id) throws BLLException {
		try {
			this.userDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("Failed to delete user with id = " + id, e);
		}
	}

	public void delete(User u) throws BLLException {
		this.delete(u.getUserId());
	}

	public User getById(int id) throws BLLException {
		User user = null;
		try {
			user = userDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch user with id = " + id, e);
		}
		return user;
	}

	public User getByUsername(String username) throws BLLException {
		User user = null;
		try {
			user = userDAO.selectByUsername(username);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch user with username = " + username, e);
		}
		return user;
	}

	public User getByEmail(String email) throws BLLException {
		User user = null;
		try {
			user = userDAO.selectByEmail(email);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch user with email " + email, e);
		}
		return user;
	}

	public List<User> getByCity(String city) throws BLLException {
		List<User> users = null;
		try {
			users = userDAO.selectByCity(city);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch users with city = " + city, e);
		}
		return users;
	}

	public List<User> getAllAdmins() throws BLLException {
		List<User> admins = null;
		try {
			admins = userDAO.selectAllAdmins();
		} catch (DALException e) {
			throw new BLLException("Failed to fetch all admins", e);
		}
		return admins;
	}

	public boolean isValid(User u) {
		Boolean allGood = true;
		// TODO : test once BLLValidator is completed
		/*
		 * allGood = BLLValidator.isValidUsername(u.getUsername()); if (allGood) allGood
		 * = BLLValidator.isValidName(u.getLastName()); if (allGood) allGood =
		 * BLLValidator.isValidName(u.getFirstName()); if (allGood) allGood =
		 * BLLValidator.isValidEmail(u.getEmail()); if (allGood) allGood =
		 * BLLValidator.isValidTelephone(u.getTelephone()); if (allGood) allGood =
		 * BLLValidator.isValidStreet(u.getStreet()); if (allGood) allGood =
		 * BLLValidator.isValidPostCode(u.getPostcode()); if (allGood) allGood =
		 * BLLValidator.isValidCity(u.getCity()); if (allGood) allGood =
		 * BLLValidator.isValidPassword(u.getPassword()); if (allGood) allGood =
		 * BLLValidator.isValidAmount(u.getCredit());
		 * 
		 * return allGood;
		 */
		return u.getUserId() > 0;
	}

	private boolean exists(User u) throws BLLException {
		Boolean found = false;
		try {
			found = userDAO.exists(u);
		} catch (DALException e) {
			throw new BLLException("Failed to check if user exists in db", e);
		}
		return found;
	}

	public User login(String username, String password) throws BLLException {
		User user = null;
		try {
			user = userDAO.selectByUsername(username);
			if (user == null)
				throw new BLLException("Invalid username");
			if (!user.getPassword().equals(password))
				throw new BLLException("Invalid password");
		} catch (DALException | BLLException e) {
			throw new BLLException("Log in failed", e);
		}
		return user;
	}
}