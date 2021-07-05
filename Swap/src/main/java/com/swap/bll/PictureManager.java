package com.swap.bll;

import java.util.List;

import com.swap.bo.Picture;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.PictureDAO;
import com.swap.dal.Repository;

public class PictureManager {
	private Repository pictureRepo;
	private PictureDAO pictureDAO;

	public PictureManager() {
		pictureRepo = DAOFactory.getPictureRepository();
		pictureDAO = DAOFactory.getImageDAO();
	}

	private boolean isValid(Picture picture) {
		// TODO complete
		return true;
	}

	public void create(Picture picture) throws BLLException {
		if (!isValid(picture))
			throw new BLLException("Invalid image");
		try {
			pictureRepo.save(picture);
			pictureDAO.create(picture);
		} catch (DALException e) {
			throw new BLLException("Couldn't create image", e);
		}
	}

	public void createAll(List<Picture> pictures, int auctionId) throws BLLException {
		for (Picture picture : pictures) {
			picture.setAuctionId(auctionId);
			create(picture);
		}
	}

	public void update(Picture picture) throws BLLException {
		if (isValid(picture)) {
			try {
				pictureDAO.update(picture);
			} catch (DALException e) {
				throw new BLLException("UPDATE AUCTION failure");
			}
		}
	}

	public void delete(Picture picture) throws BLLException {
		try {
			pictureDAO.delete(picture);
			pictureRepo.remove(picture);
		} catch (DALException e) {
			throw new BLLException("Failed to delete image", e);
		}
	}

	public List<Picture> getByAuctionId(int auctionId) throws BLLException {
		List<Picture> images = null;
		try {
			images = pictureDAO.selectByAuctionId(auctionId);
		} catch (DALException e) {
			throw new BLLException("Failed to get images for auction with id = " + auctionId, e);
		}
		return images;
	}

	public void deleteAllByAuctionId(int auctionId) throws BLLException {
		try {
			List<Picture> pictures = pictureDAO.selectByAuctionId(auctionId);
			for (Picture picture : pictures) {
				delete(picture);
			}
		} catch (DALException e) {
			throw new BLLException("Failed to delete images for auction with id " + auctionId, e);
		}

	}
}
