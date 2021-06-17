package com.swap.dal.jdbc;

import java.util.List;

import com.swap.bo.Picture;
import com.swap.dal.DALException;
import com.swap.dal.ImageDAO;

public class ImageDAOJdbc implements ImageDAO {

	@Override
	public void create(Picture picture) throws DALException {
		System.out.println("Pic ready to be created : " + picture.getImageName() + picture.getExtension());
	}

	@Override
	public List<Picture> read() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Picture picture) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Picture picture) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Picture> selectByAuctionId(int auctionId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
