package com.swap.bo;

import java.io.Serializable;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	private String imageName, orientation;
	private int imageId, auctionId;

	enum Orientation {
		PORTRAIT,
		LANDSCAPE;
	}

	public Image() {

	}

	public Image(int auctionId, String auctionName, double width, double height) throws BOException {
		try {
			this.setAuctionId(auctionId);
			this.setImageName(auctionName);
			this.setOrientation(width, height);
		} catch (BOException e) {
			throw new BOException("Image couldn't be created", e);
		}
	}

	public Image(int imageId, int auctionId, String auctionName, double width, double height) throws BOException {
		this(auctionId, auctionName, width, height);
		try {
			this.setImageId(imageId);
		} catch (BOException e) {
			throw new BOException("Image couldn't be created", e);
		}
	}

	public void setAuctionId(int auctionId) throws BOException {
		if (auctionId <= 0)// || !Auction.exists(auctionId))//TODO : find a way to make this happen
			throw new BOException(
					"Auction with id " + auctionId + "doesn't exist. Couldn't set auction id in new image.");
		this.auctionId = auctionId;
	}

	public void setImageName(String auctionName) throws BOException {
		if (auctionName == null || auctionName.isBlank()) {
			throw new BOException("Auction name is invalid. Couldn't set image name.");
		}
		this.imageName = createImageName(auctionName);
	}

	public void setOrientation(double width, double height) throws BOException {
		if (width <= 0)
			throw new BOException("Image width is invalid. Couldn't set image orientation.");
		if (height <= 0)
			throw new BOException("Image height is invalid. Couldn't set image orientation.");
		if (width >= height) {
			orientation = Orientation.PORTRAIT.name();
		} else {
			orientation = Orientation.LANDSCAPE.name();
		}
	}

	public void setImageId(int imageId) throws BOException {
		if (imageId <= 0)
			throw new BOException("Image id is invalid.");
		this.imageId = imageId;
	}

	private String createImageName(String auctionName) {
		return auctionName.toLowerCase().replaceAll("[-'\\s]", "-");
	}
}