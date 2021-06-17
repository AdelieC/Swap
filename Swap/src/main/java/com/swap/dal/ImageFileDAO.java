package com.swap.dal;

import com.swap.bo.Picture;

public interface ImageFileDAO extends DAO<Picture> {
	public void saveAll(Picture... images) throws DALException;

	public void deleteAll(Picture... images) throws DALException;
}
