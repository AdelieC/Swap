package com.swap.dal;

import java.util.List;

import com.swap.bo.PickUpPoint;

public interface PickUpPointDAO extends DAO<PickUpPoint> {
	public PickUpPoint selectById(int id) throws DALException;

	public PickUpPoint selectByAuctionId(int auctionId) throws DALException;

	public List<PickUpPoint> selectByStreet(String street) throws DALException;

	public List<PickUpPoint> selectByPostcode(String postcode) throws DALException;

	public List<PickUpPoint> selectByCity(String city) throws DALException;

	public void deleteByAuctionId(int auctionId) throws DALException;
}
