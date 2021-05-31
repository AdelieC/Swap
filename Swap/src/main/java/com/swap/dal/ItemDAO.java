package com.swap.dal;

import java.time.LocalDate;
import java.util.List;

import com.swap.bo.Item;

public interface ItemDAO extends DAO<Item> {
	public Item selectById(int id) throws DALException;

	public List<Item> selectByName(String name) throws DALException;

	public List<Item> selectByKeyword(String keyword) throws DALException;

	public List<Item> selectByCategory(int categoryId) throws DALException;

	public List<Item> selectByUser(int userId) throws DALException;

	public List<Item> selectByPrice(int price) throws DALException;

	public List<Item> selectByDate(LocalDate date) throws DALException;

	public List<Item> selectByUserAndDate(int userId, LocalDate date) throws DALException;

}
