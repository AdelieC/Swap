package com.swap.dal;

import java.util.List;

import com.swap.bo.Auction;

public interface AuctionDAO extends DAO<Auction> {

	public Auction selectById(int id) throws DALException;

	public List<Auction> selectByName(String name) throws DALException;

	public List<Auction> selectByKeyword(String keyword) throws DALException;

	public List<Auction> selectByCategory(int categoryId) throws DALException;

	public List<Auction> selectByUser(int userId) throws DALException;

	public List<Auction> selectByPrice(int price) throws DALException;

	public List<Auction> selectByStatusAndUser(String status, int userId) throws DALException;

	public List<Auction> searchByNameAndCategory(String name, int categoryId) throws DALException;

	public List<Auction> selectAllByStatus(String status) throws DALException;

	public List<Auction> selectAllNotOver() throws DALException;

	public List<Auction> selectNotOverByUser(int userId) throws DALException;

	public List<Auction> selectByWinner(int userId) throws DALException;

	public List<Auction> selectNotOverByCategory(int categoryId) throws DALException;
}
