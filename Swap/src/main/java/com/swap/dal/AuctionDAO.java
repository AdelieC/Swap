package com.swap.dal;

import java.time.LocalDate;
import java.util.List;

import com.swap.bo.Auction;

public interface AuctionDAO extends DAO<Auction> {
	public Auction selectById(int id) throws DALException;

	public List<Auction> selectByName(String name) throws DALException;

	public List<Auction> selectByKeyword(String keyword) throws DALException;

	public List<Auction> selectByCategory(int categoryId) throws DALException;

	public List<Auction> selectByUser(int userId) throws DALException;

	public List<Auction> selectByPrice(int price) throws DALException;

	public List<Auction> selectByDate(LocalDate date) throws DALException;

	public List<Auction> selectByUserAndDate(int userId, LocalDate date) throws DALException;

}
