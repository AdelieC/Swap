package com.swap.dal.repositories;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.swap.bo.BOException;
import com.swap.bo.Picture;
import com.swap.dal.DALException;
import com.swap.dal.ImageFileDAO;

public class ImageRepository implements ImageFileDAO {
	private final static File PICTURES_DIR = new File("./resources/auctions-img/");
	private final static File THUMBNAILS_DIR = new File("./resources/auctions-thumbnails/");

	/**
	 * The idea was to create a resources folder with 2 subfolders to store images.
	 * Should get created at the root of user account on your machine, and hopefully
	 * at the root of the host machine when the app is deployed...
	 */
	public ImageRepository() {
		if (!PICTURES_DIR.exists())
			PICTURES_DIR.mkdirs();
		if (!THUMBNAILS_DIR.exists())
			THUMBNAILS_DIR.mkdirs();
	}

	@Override
	public void create(Picture picture) throws DALException {
		System.out.println("trying to create image files");
		try {
			BufferedInputStream bis = new BufferedInputStream(picture.getImageFile().getInputStream());
			BufferedImage pictureB = ImageIO.read(bis);
			picture.setWidth(pictureB.getWidth(null));
			picture.setHeight(pictureB.getHeight(null));
			create100pxThumbnail(picture, pictureB);
			createImageFile(picture, pictureB);
		} catch (IOException | BOException e) {
			throw new DALException("Image couldn't be copied to file", e);
		}
	}

	private void createImageFile(Picture picture, BufferedImage pictureB) throws IOException {
		File outputfile = new File(PICTURES_DIR, picture.getName() + "." + picture.getExtension());
		ImageIO.write(pictureB, picture.getExtension(), outputfile);
	}

	private void create100pxThumbnail(Picture picture, BufferedImage pictureB) throws IOException {
		// 1) Scale picture to a width of 150px
		int newWidth = 150;
		int newHeight = picture.getHeight() * 150 / picture.getWidth();

		// 2) Get new scaled bufferedImage
		Image tempThumbnail = pictureB.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		BufferedImage thumbnail = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		thumbnail.getGraphics().drawImage(tempThumbnail, 0, 0, null);

		// 3) Write it to a file in the thumbnail folder
		File outputfile = new File(THUMBNAILS_DIR, picture.getName() + "." + picture.getExtension());
		ImageIO.write(thumbnail, picture.getExtension(), outputfile);
	}

	@Override
	public List<Picture> read() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Picture s) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Picture s) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll(Picture... images) throws DALException {
		for (Picture image : images)
			create(image);
	}

	@Override
	public void deleteAll(Picture... images) throws DALException {
		for (Picture image : images)
			delete(image);
	}

}
