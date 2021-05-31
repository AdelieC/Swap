package com.swap.dal;

import java.time.LocalDate;
import java.util.List;

import com.swap.bo.Bid;

public interface BidDAO extends DAO<Bid> {
	public Bid selectById(int id) throws DALException;

	public List<Bid> selectByUser(int userId) throws DALException;

	public List<Bid> selectByItemId(int itemId) throws DALException;

	public List<Bid> selectByPrice(int price) throws DALException;

	public List<Bid> selectByDate(LocalDate date) throws DALException;
}
