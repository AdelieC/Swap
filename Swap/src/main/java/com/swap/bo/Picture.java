package com.swap.bo;

import java.io.Serializable;

import org.apache.tomcat.util.http.fileupload.FileItem;

public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;
	private String imageName, extension;
	private int imageId, auctionId, width, height;
	private FileItem imageFile;

	enum Orientation {
		PORTRAIT,
		LANDSCAPE;
	}

	public Picture() {

	}

	public Picture(int auctionId, String auctionName, FileItem imageFile, int index) throws BOException {
		try {
			this.setAuctionId(auctionId);
			this.setImageName(auctionName, index);
			this.setImageFile(imageFile);
			this.setExtension(imageFile);
		} catch (BOException e) {
			throw new BOException("Image couldn't be created", e);
		}
	}

	public Picture(int imageId, int auctionId, String imageName, String extension, int width, int height)
			throws BOException {
		try {
			this.setAuctionId(auctionId);
			this.imageName = imageName;
			this.setImageId(imageId);
			this.imageFile = null;
			this.extension = extension;
			this.width = width;
			this.height = height;
		} catch (BOException e) {
			throw new BOException("Image couldn't be created", e);
		}
	}

	private void setImageFile(FileItem imageFile) throws BOException {
		if (imageFile == null)
			throw new BOException("No image file found");
		this.imageFile = imageFile;
	}

	public void setAuctionId(int auctionId) throws BOException {
		if (auctionId <= 0)// || !Auction.exists(auctionId))//TODO : find a way to make this happen
			throw new BOException(
					"Auction with id " + auctionId + "doesn't exist. Couldn't set auction id in new image.");
		this.auctionId = auctionId;
	}

	public void setImageName(String auctionName, int index) throws BOException {
		if (auctionName == null || auctionName.isBlank()) {
			throw new BOException("Auction name is invalid. Couldn't set image name.");
		}
		this.imageName = createImageName(auctionName, index);
	}

	public void setImageId(int imageId) throws BOException {
		if (imageId <= 0)
			throw new BOException("Image id is invalid.");
		this.imageId = imageId;
	}

	private String createImageName(String auctionName, int index) {
		return auctionName.toLowerCase().replaceAll("[-'\\s]", "-") + "-" + index;
	}

	public void setExtension(FileItem imageFile) {
		this.extension = imageFile.getName().substring(imageFile.getName().lastIndexOf(".") + 1);
	}

	public void setWidth(int width) throws BOException {
		if (width <= 0)
			throw new BOException("Image width is invalid. Couldn't set image orientation.");
		this.width = width;
	}

	public void setHeight(int height) throws BOException {
		if (height <= 0)
			throw new BOException("Image height is invalid. Couldn't set image orientation.");
		this.height = height;
	}

	public String getImageName() {
		return imageName;
	}

	public int getImageId() {
		return imageId;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public FileItem getImageFile() {
		return imageFile;
	}

	public String getExtension() {
		return extension;
	}

	public String getOrientation() throws BOException {
		return width >= height ? Orientation.LANDSCAPE.name() : Orientation.PORTRAIT.name();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}