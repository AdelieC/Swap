package com.swap.bll;

import java.util.List;

import com.swap.bo.Image;
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

	private boolean isValid(Image image) {
		// TODO complete
		return true;
	}

	public void create(Image image) throws BLLException {
		if (!isValid(image))
			throw new BLLException("Invalid image");
		try {
			this.imageDAO.create(image);
			this.imageFile.create(image);// TODO create new DAO impl for saving files into folders
		} catch (DALException e) {
			throw new BLLException("Couldn't create image", e);
		}
	}

	public void update(Image image) throws BLLException {
		if (isValid(image)) {
			try {
				this.imageDAO.update(image);
			} catch (DALException e) {
				throw new BLLException("UPDATE AUCTION failure");
			}
		}
	}

	public void delete(Image image) throws BLLException {
		try {
			this.imageDAO.delete(image);
		} catch (DALException e) {
			throw new BLLException("Failed to delete image", e);
		}
	}

	public List<Image> getByAuctionId(int auctionId) throws BLLException {
		List<Image> images = null;
		try {
			images = this.imageDAO.selectByAuctionId(auctionId);
		} catch (DALException e) {
			throw new BLLException("Failed to get images for auction with id = " + auctionId, e);
		}
		return images;
	}
}
