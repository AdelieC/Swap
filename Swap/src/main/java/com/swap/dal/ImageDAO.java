package com.swap.dal;

import java.util.List;

import com.swap.bo.Picture;

public interface ImageDAO extends DAO<Picture> {
	public List<Picture> selectByAuctionId(int auctionId) throws DALException;
}
