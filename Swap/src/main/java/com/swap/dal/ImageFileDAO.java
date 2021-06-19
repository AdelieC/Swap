package com.swap.dal;

import java.util.List;

import com.swap.bo.Picture;

public interface ImageFileDAO extends DAO<Picture> {
	public void saveAll(List<Picture> pictures) throws DALException;

	public void deleteAll(List<Picture> pictures) throws DALException;
}
