package com.swap.dal;

import com.swap.bo.Image;

public interface ImageFileDAO extends DAO<Image> {
	public void saveAll(Image... images) throws DALException;

	public void deleteAll(Image... images) throws DALException;
}
