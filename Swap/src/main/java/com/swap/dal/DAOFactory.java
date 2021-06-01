package com.swap.dal;

import com.swap.dal.jdbc.AuctionDAOJdbc;
import com.swap.dal.jdbc.BidDAOJdbc;
import com.swap.dal.jdbc.CategoryDAOJdbc;
import com.swap.dal.jdbc.PickUpPointDAOJdbc;

public class DAOFactory {
	public static AuctionDAO getAuctionDAO() {
		return new AuctionDAOJdbc();
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
