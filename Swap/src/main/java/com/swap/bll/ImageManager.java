package com.swap.bll;

import java.util.List;

import com.swap.bo.Picture;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.ImageDAO;
import com.swap.dal.ImageFileDAO;

public class ImageManager {
	private ImageDAO imageDAO;
	private ImageFileDAO imageFile;

	public ImageManager() {
		this.imageDAO = DAOFactory.getImageDAO();
		this.imageFile = DAOFactory.getImageFileDAO();
	}

	private boolean isValid(Picture image) {
		// TODO complete
		return true;
	}

	public void create(Picture image) throws BLLException {
		if (!isValid(image))
			throw new BLLException("Invalid image");
		try {
			this.imageDAO.create(image);
			this.imageFile.create(image);
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
