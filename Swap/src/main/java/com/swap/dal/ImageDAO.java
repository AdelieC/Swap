package com.swap.dal;

import java.util.List;

import com.swap.bo.Image;

public interface ImageDAO extends DAO<Image> {
	public List<Image> selectByAuctionId(int auctionId) throws DALException;
}
