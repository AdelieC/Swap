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
import com.swap.dal.Repository;

public class PictureRepository implements Repository {
	private final static File ROOT = new File("./git/Swap/Swap/src/main/webapp");
	// change ROOT according to the location of your project
	private final static File PICTURES_DIR = new File(ROOT, "/resources/auctions-img/");
	private final static File THUMBNAILS_DIR = new File(ROOT, "/resources/auctions-thumbnails/");

	/**
	 * The idea was to create a resources folder with 2 subfolders to store images.
	 * Should get created inside webapp (ROOT must be the path to webapp on your
	 * machine!)
	 */
	public PictureRepository() {
		if (!PICTURES_DIR.exists())
			PICTURES_DIR.mkdirs();
		if (!THUMBNAILS_DIR.exists())
			THUMBNAILS_DIR.mkdirs();
	}

	@Override
	public void save(Picture picture) throws DALException {
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
		// 1) Scale picture to a width of 200px
		int newWidth = 200;
		int newHeight = picture.getHeight() * 200 / picture.getWidth();

		// 2) Get new scaled bufferedImage
		Image tempThumbnail = pictureB.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		BufferedImage thumbnail = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		thumbnail.getGraphics().drawImage(tempThumbnail, 0, 0, null);

		// 3) Write it to a file in the thumbnail folder
		File outputfile = new File(THUMBNAILS_DIR, picture.getName() + "." + picture.getExtension());
		ImageIO.write(thumbnail, picture.getExtension(), outputfile);
	}

	@Override
	public void remove(Picture p) throws DALException {
		File picture = new File(PICTURES_DIR, p.getName());
		File thumbnail = new File(THUMBNAILS_DIR, p.getName());
		try {
			picture.delete();
			thumbnail.delete();
		} catch (Exception e) {
			throw new DALException("Failed to delete picture named " + p.getName(), e);
		}
	}

	@Override
	public void saveAll(List<Picture> pictures) throws DALException {
		for (Picture picture : pictures)
			save(picture);
	}

	@Override
	public void removeAll(List<Picture> pictures) throws DALException {
		for (Picture picture : pictures)
			remove(picture);
	}

}
