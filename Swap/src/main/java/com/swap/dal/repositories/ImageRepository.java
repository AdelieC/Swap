package com.swap.dal.repositories;

import java.util.List;

import com.swap.bo.Image;
import com.swap.dal.DALException;
import com.swap.dal.ImageFileDAO;

public class ImageRepository implements ImageFileDAO {
	private final static String IMAGES_PATH = "/Swap/src/main/webapp/auctions-img";
	private final static String THUMBNAILS_PATH = "/Swap/src/main/webapp/auctions-thumbnails";

	@Override
	public void create(Image s) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Image> read() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Image s) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Image s) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll(Image... images) throws DALException {
		for (Image image : images)
			create(image);
	}

	@Override
	public void deleteAll(Image... images) throws DALException {
		for (Image image : images)
			delete(image);
	}

}
