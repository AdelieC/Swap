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

	public UserManager() {
		this.UserDAO = DAOFactory.getUserDAO();
	}

	public void create(User u) throws BLLException {
		if (!isValid(u))
			throw new BLLException("User is not valid");
		try {
			if (UserDAO.exists(u))
				throw new BLLException("User already exists");
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

	public void updatePassword(User user) throws BLLException {
		try {
			this.UserDAO.updatePassword(user);
		} catch (DALException e) {
			throw new BLLException("Failed to update password", e);
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

	public List<User> searchByUsername(String username) throws BLLException {
		List<User> users = new ArrayList<>();
		try {
			users = UserDAO.searchByUsername(username);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch user with username = " + username, e);
		}
		return users;
	}

	public List<User> searchByCity(String city) throws BLLException {
		List<User> users = new ArrayList<>();
		try {
			users = UserDAO.searchByCity(city);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch users with city = " + city, e);
		}
		return users;
	}

	public List<User> getAllAdmins() throws BLLException {
		List<User> admins = new ArrayList<>();
		try {
			admins = UserDAO.selectAllAdmins();
		} catch (DALException e) {
			throw new BLLException("Failed to fetch all admins", e);
		}
		return admins;
	}

	public boolean isValid(User u) {
		return BLLValidator.isValidUsername(u.getUsername()) && BLLValidator.isValidName(u.getLastName())
				&& BLLValidator.isValidName(u.getFirstName()) && BLLValidator.isValidEmail(u.getEmail())
				&& BLLValidator.isValidTelephone(u.getTelephone()) && BLLValidator.isValidStreet(u.getStreet())
				&& BLLValidator.isValidPostCode(u.getPostcode()) && BLLValidator.isValidCity(u.getCity())
				&& BLLValidator.isValidPassword(u.getPassword()) && BLLValidator.isValidAmount(u.getBalance());
	}

	public boolean couldFetchPasswordData(User user) throws BLLException {
		boolean usernameIsValid = false;
		try {
			usernameIsValid = UserDAO.successfullySetPasswordData(user);
		} catch (DALException e) {
			throw new BLLException("Log in failed", e);
		}
		return usernameIsValid;
	}

	public void credit(int userId, int amount) throws BLLException {
		try {
			this.UserDAO.credit(userId, amount);
		} catch (DALException e) {
			throw new BLLException("Failed to credit user " + userId, e);
		}

	}
}
