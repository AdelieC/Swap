package com.swap.bll;

import java.util.ArrayList;
import java.util.List;

import com.swap.bo.User;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.UserDAO;

//TODO : add clauses to check if not null where they should be
public class UserManager {
	private UserDAO UserDAO;

	public UserManager() throws BLLException {
		try {
			this.UserDAO = DAOFactory.getUserDAO();
		} catch (DALException e) {
			throw new BLLException("Couldn't instantiate UserManager", e);
		}
	}

	public void create(User u) throws BLLException {
		if (exists(u))
			throw new BLLException("User already exists");
		if (!isValid(u))
			throw new BLLException("User is not valid");
		try {
			UserDAO.create(u);
		} catch (DALException e) {
			throw new BLLException("Failed to create user", e);
		}
	}

	public List<User> getAll() throws BLLException {
		List<User> list = new ArrayList<User>();
		try {
			list = UserDAO.read();
		} catch (DALException e) {
			throw new BLLException("Failed to get all users", e);
		}
		return list;
	}

	public void update(User u) throws BLLException {
		if (isValid(u)) {
			try {
				this.UserDAO.update(u);
			} catch (DALException e) {
				throw new BLLException("Failed to update user", e);
			}
		}
	}

	public void delete(int id) throws BLLException {
		try {
			this.UserDAO.delete(id);
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
			user = UserDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch user with id = " + id, e);
		}
		return user;
	}

	public User getByUsername(String username) throws BLLException {
		User user = null;
		try {
			user = UserDAO.selectByUsername(username);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch user with username = " + username, e);
		}
		return user;
	}

	public User getByEmail(String email) throws BLLException {
		User user = null;
		try {
			user = UserDAO.selectByEmail(email);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch user with email " + email, e);
		}
		return user;
	}

	public List<User> getByCity(String city) throws BLLException {
		List<User> users = null;
		try {
			users = UserDAO.selectByCity(city);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch users with city = " + city, e);
		}
		return users;
	}

	public List<User> getAllAdmins() throws BLLException {
		List<User> admins = null;
		try {
			admins = UserDAO.selectAllAdmins();
		} catch (DALException e) {
			throw new BLLException("Failed to fetch all admins", e);
		}
		return admins;
	}

	private boolean isValid(User u) {
		Boolean allGood = true;
		allGood = BLLValidator.isValidUsername(u.getUsername());
		if (allGood)
			allGood = BLLValidator.isValidName(u.getLastName());
		if (allGood)
			allGood = BLLValidator.isValidName(u.getFirstName());
		if (allGood)
			allGood = BLLValidator.isValidEmail(u.getEmail());
		if (allGood)
			allGood = BLLValidator.isValidTelephone(u.getTelephone());
		if (allGood)
			allGood = BLLValidator.isValidStreet(u.getStreet());
		if (allGood)
			allGood = BLLValidator.isValidPostCode(u.getPostcode());
		if (allGood)
			allGood = BLLValidator.isValidCity(u.getCity());
		if (allGood)
			allGood = BLLValidator.isValidPassword(u.getPassword());
		if (allGood)
			allGood = BLLValidator.isValidAmount(u.getCredit());
		return allGood;
	}

	private boolean exists(User u) throws BLLException {
		Boolean found = false;
		try {
			found = UserDAO.exists(u);
		} catch (DALException e) {
			throw new BLLException("Failed to check if user exists in db", e);
		}
		return found;
	}
}
