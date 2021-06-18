package com.swap.bll;

import java.util.List;

import com.swap.bo.Picture;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.ImageDAO;
import com.swap.dal.ImageFileDAO;

public class ImageManager {
	private ImageFileDAO imageFile;
	private ImageDAO imageDAO;

	public ImageManager() {
		this.imageFile = DAOFactory.getImageFileDAO();
		this.imageDAO = DAOFactory.getImageDAO();
	}

	private boolean isValid(Picture image) {
		// TODO complete
		return true;
	}

	public void create(Picture image) throws BLLException {
		if (!isValid(image))
			throw new BLLException("Invalid image");
		try {
			this.imageFile.create(image);
			this.imageDAO.create(image);
		} catch (DALException e) {
			throw new BLLException("Couldn't create image", e);
		}
	}

	public void update(Picture image) throws BLLException {
		if (isValid(image)) {
			try {
				this.imageDAO.update(image);
			} catch (DALException e) {
				throw new BLLException("UPDATE AUCTION failure");
			}
		}
	}

	public void delete(Picture image) throws BLLException {
		try {
			this.imageDAO.delete(image);
		} catch (DALException e) {
			throw new BLLException("Failed to delete image", e);
		}
	}

	public List<Picture> getByAuctionId(int auctionId) throws BLLException {
		List<Picture> images = null;
		try {
			images = this.imageDAO.selectByAuctionId(auctionId);
		} catch (DALException e) {
			throw new BLLException("Failed to get images for auction with id = " + auctionId, e);
		}
		return images;
	}
}
