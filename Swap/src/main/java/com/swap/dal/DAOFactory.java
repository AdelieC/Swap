package com.swap.dal;

import com.swap.dal.jdbc.BidDAOJdbc;
import com.swap.dal.jdbc.CategoryDAOJdbc;
import com.swap.dal.jdbc.ItemDAOJdbc;
import com.swap.dal.jdbc.PickUpPointDAOJdbc;

public class DAOFactory {
	public static ItemDAO getItemDAO() {
		return new ItemDAOJdbc();
	}

	public static BidDAO getBidDAO() {
		return new BidDAOJdbc();
	}

	public static PickUpPointDAO getPickUpPointDAO() {
		return new PickUpPointDAOJdbc();
	}

	public static CategoryDAO getCategoryDAO() {
		return new CategoryDAOJdbc();
	}
}
