package com.swap.bll;

import java.util.ArrayList;
import java.util.List;

import com.swap.bo.PickUpPoint;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.PickUpPointDAO;

public class PickUpPointManager {
	private PickUpPointDAO pupDAO;

	public PickUpPointManager() {
		this.pupDAO = DAOFactory.getPickUpPointDAO();
	}

	public void create(PickUpPoint pup) throws BLLException {
		if (isValid(pup)) {
			try {
				this.pupDAO.create(pup);
			} catch (DALException e) {
				throw new BLLException("BLL - CREATE PICK UP POINT failure");
			}
		}
	}

	public List<PickUpPoint> getAll() throws BLLException {
		List<PickUpPoint> list = new ArrayList<PickUpPoint>();
		try {
			list = pupDAO.read();
		} catch (DALException e) {
			throw new BLLException("BLL - READ PICK UP POINTS failure");
		}
		return list;
	}

	public void update(PickUpPoint pup) throws BLLException {
		if (isValid(pup)) {
			try {
				this.pupDAO.update(pup);
			} catch (DALException e) {
				throw new BLLException("BLL - UPDATE PICK UP POINT failure");
			}
		}
	}

	public void delete(PickUpPoint pup) throws BLLException {
		try {
			this.pupDAO.delete(pup);
		} catch (DALException e) {
			throw new BLLException("BLL - DELETE PICK UP POINT failure");
		}
	}

	public PickUpPoint getById(int id) throws BLLException {
		PickUpPoint pup = null;
		try {
			pup = this.pupDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("BLL - SELECT PICK UP POINT BY ID failure");
		}
		return pup;
	}

	public PickUpPoint getByAuctionId(int auctionId) throws BLLException {
		PickUpPoint pup = null;
		try {
			pup = this.pupDAO.selectByAuctionId(auctionId);
		} catch (DALException e) {
			throw new BLLException("BLL - SELECT PICK UP POINT BY AUCTION ID failure");
		}
		return pup;
	}

	public List<PickUpPoint> getByStreet(String street) throws BLLException {
		List<PickUpPoint> list = null;
		try {
			list = this.pupDAO.selectByStreet(street);
		} catch (DALException e) {
			throw new BLLException("BLL - SELECT PICK UP POINT BY STREET failure");
		}
		return list;
	}

	public List<PickUpPoint> getByPostcode(String postcode) throws BLLException {
		List<PickUpPoint> list = null;
		try {
			list = this.pupDAO.selectByPostcode(postcode);
		} catch (DALException e) {
			throw new BLLException("BLL - SELECT PICK UP POINT BY POSTCODE failure");
		}
		return list;
	}

	public List<PickUpPoint> getByCity(String city) throws BLLException {
		List<PickUpPoint> list = null;
		try {
			list = this.pupDAO.selectByCity(city);
		} catch (DALException e) {
			throw new BLLException("BLL - SELECT PICK UP POINT BY CITY failure");
		}
		return list;
	}

	private boolean isValid(PickUpPoint pup) {
		// TODO
		return true;
	}
}
