package com.swap.dal;

import java.util.List;

import com.swap.bo.Category;

public interface CategoryDAO extends DAO<Category> {
	public Category selectById(int categoryId) throws DALException;

	public List<Category> selectByLabel(String label) throws DALException;

}
