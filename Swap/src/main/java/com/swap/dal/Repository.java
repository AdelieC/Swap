package com.swap.dal;

import java.util.List;

import com.swap.bo.Picture;

public interface Repository {
	public void save(Picture picture) throws DALException;

	public void remove(Picture picture) throws DALException;

	public void saveAll(List<Picture> pictures) throws DALException;

	public void removeAll(List<Picture> pictures) throws DALException;
}
