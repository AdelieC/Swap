package com.swap.dal;

import java.util.List;

public interface DAO<T> {
	public void create(T s) throws DALException;
	public List<T> read() throws DALException;
	public void update(T s) throws DALException;	
	public void delete(T s) throws DALException;
}
