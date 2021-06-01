package com.swap.bll;

//Strictly experimental for now. I wonder if we could manage User login/logout AND Auction inactive/active/completed using this?
public interface StateManager<T> {
	public void updateState(T s) throws BLLException;

	public void getState(T s) throws BLLException;
}
