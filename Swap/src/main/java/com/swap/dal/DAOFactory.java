package com.swap.dal;

import com.swap.dal.jdbc.AuctionDAOJdbc;
import com.swap.dal.jdbc.BidDAOJdbc;
import com.swap.dal.jdbc.CategoryDAOJdbc;
import com.swap.dal.jdbc.ImageDAOJdbc;
import com.swap.dal.jdbc.PickUpPointDAOJdbc;
import com.swap.dal.jdbc.UserDAOJdbc;
import com.swap.dal.repositories.ImageRepository;

public class DAOFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOJdbc();
	}

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

	public static ImageDAO getImageDAO() {
		return new ImageDAOJdbc();
	}

	public static ImageFileDAO getImageFileDAO() {
		return new ImageRepository();
	}
}
