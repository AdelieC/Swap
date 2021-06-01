package com.swap.dal;

import com.swap.bo.Category;

public interface CategoryDAO extends DAO<Category> {
	public Category selectById(int categoryId) throws DALException;

	public Category selectByLabel(String label) throws DALException;

}
