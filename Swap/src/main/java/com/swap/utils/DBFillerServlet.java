package com.swap.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.PickUpPointManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.PickUpPoint;
import com.swap.bo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BDDFillerServlet
 */
@WebServlet("/fill-DB")
public class DBFillerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int i = 35; // TODO CHANGE TO ZERO ADELIE !!
		int userId = 8; // TODO CHANGE TO ZERO !!
		int fashion = 1 + i;
		int home = 2 + i;
		int sports = 3 + i;
		int electronics = 4 + i;
		int pets = 6 + i;
		int motors = 5 + i;
		int art = 7 + i;
		int health = 8 + i;
		int media = 9 + i;
		int supplies = 10 + i;
		int others = 11 + i;
		// USERS
		List<User> userList = new ArrayList<User>();
		User user1 = new User("UncleScrooge", "Mc Duck", "Scrooge", "rich@mcduck.com", "09876544567", "1 Fort Hill",
				"99999", "Duckburg", "Pa$$w0rd", 1000, false);
		userList.add(user1);
		User user2 = new User("DonyTheDuck", "Duck", "Donald", "dduck@duckmail.com", "0567844987", "42 Workers Drive",
				"99999", "Duckburg", "Pa$$w0rd", 10, false);
		userList.add(user2);
		User user3 = new User("Superhue", "Duck", "Huey", "hueyduck@duckmail.com", "0567844987", "42 Workers Drive",
				"99999", "Duckburg", "Pa$$w0rd", 10, false);
		userList.add(user3);
		User user4 = new User("MountainDew", "Duck", "Dewey", "deweyduck@duckmail.com", "0567844987",
				"42 Workers Drive", "99999", "Duckburg", "Pa$$w0rd", 10, false);
		userList.add(user4);
		User user5 = new User("Lou", "Duck", "Louie", "louieduck@duckmail.com", "0567844987", "42 Workers Drive",
				"99999", "Duckburg", "Pa$$w0rd", 10, false);
		userList.add(user5);
		User user6 = new User("TheRealMickey", "Mouse", "Mickie", "mmouseofficial@mousemail.com", "0567844987",
				"36 Treasure Lane", "88888", "Mouseton", "Pa$$w0rd", 10, false);
		userList.add(user6);
		User user7 = new User("MinniePie", "Mouse", "Minnie", "minniemouse@mousemail.com", "0567844987",
				"11 Pretty Grove", "88888", "Mouseton", "Pa$$w0rd", 10, false);
		userList.add(user7);
		User user8 = new User("Goofiest", "Goof", "Goofy", "thegoof@mousemail.com", "0567844987", "15 Workers Drive",
				"88888", "Mouseton", "Pa$$w0rd", 10, false);
		userList.add(user8);
		UserManager usmng = new UserManager();
		user1.setUserId(userId + 1);
		user2.setUserId(userId + 2);
		user3.setUserId(userId + 3);
		user4.setUserId(userId + 4);
		user5.setUserId(userId + 5);
		user6.setUserId(userId + 6);
		user7.setUserId(userId + 7);
		user8.setUserId(userId + 8);
//		try {
//			// USERS
//			for (User user : userList) {
//				usmng.create(user);
//			}
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
		// AUCTIONS
		List<Auction> auctionList = new ArrayList<Auction>();
		Auction auction1 = new Auction("Vintage hawaiian shirt", "Collector vintage hawaiian shirt",
				LocalDate.parse("2021-05-15"), LocalDate.parse("2021-06-15"), fashion, 5, user2.getUserId());
		auctionList.add(auction1);
		Auction auction2 = new Auction("Vintage shorts", "Collector vintage hawaiian shorts",
				LocalDate.parse("2021-05-15"), LocalDate.parse("2021-06-15"), fashion, 5, user2.getUserId());
		auctionList.add(auction2);
		Auction auction3 = new Auction("Vintage striped shirt", "Collector vintage striped shirt",
				LocalDate.parse("2021-06-05"), LocalDate.parse("2021-07-05"), fashion, 5, user2.getUserId());
		auctionList.add(auction3);
		Auction auction4 = new Auction("Sailor beret", "Collector blue sailor beret", LocalDate.parse("2021-06-15"),
				LocalDate.parse("2021-07-15"), fashion, 5, user2.getUserId());
		auctionList.add(auction4);
		Auction auction5 = new Auction("Sailor shirt", "Collector vintage marine blue sailor shirt",
				LocalDate.parse("2021-06-15"), LocalDate.parse("2021-07-15"), fashion, 5, user2.getUserId());
		auctionList.add(auction5);
		Auction auction6 = new Auction("Wooden chair", "Classic wooden chair", LocalDate.parse("2021-06-10"),
				LocalDate.parse("2021-07-10"), home, 6, user2.getUserId());
		auctionList.add(auction6);
		Auction auction7 = new Auction("Wooden table", "Classic wooden table", LocalDate.parse("2021-06-10"),
				LocalDate.parse("2021-07-10"), home, 7, user2.getUserId());
		auctionList.add(auction7);
		Auction auction8 = new Auction("Round alarm clock", "Classic alarm clock, red", LocalDate.parse("2021-06-10"),
				LocalDate.parse("2021-07-10"), electronics, 6, user2.getUserId());
		auctionList.add(auction8);
		Auction auction9 = new Auction("Gold and emerald necklace",
				"Beautiful necklace, pure gold, with an almond-sized emerald", LocalDate.parse("2021-06-10"),
				LocalDate.parse("2021-08-10"), fashion, 50, user1.getUserId());
		auctionList.add(auction9);
		Auction auction10 = new Auction("Gold pocket watch", "Collector gold pocket watch, with a gold chain",
				LocalDate.parse("2021-06-09"), LocalDate.parse("2021-06-11"), fashion, 35, user1.getUserId());
		auctionList.add(auction10);
		Auction auction11 = new Auction("Antique alarm clock", "Copper and wood antique alarm clock",
				LocalDate.parse("2021-06-07"), LocalDate.parse("2021-06-10"), art, 25, user1.getUserId());
		auctionList.add(auction11);
		Auction auction12 = new Auction("Ming vase", "Authentic Ming dinasty vase", LocalDate.parse("2021-06-10"),
				LocalDate.parse("2021-08-09"), art, 55, user1.getUserId());
		auctionList.add(auction12);
		Auction auction13 = new Auction("Remote controlled car", "Almost new remote controlled sports car",
				LocalDate.parse("2021-06-10"), LocalDate.parse("2021-07-10"), electronics, 10, user4.getUserId());
		auctionList.add(auction13);
		Auction auction14 = new Auction("Wolf print cast", "Real wolf print cast, molded in Duckburg woods",
				LocalDate.parse("2021-05-10"), LocalDate.parse("2021-06-10"), others, 5, user4.getUserId());
		auctionList.add(auction14);
		Auction auction15 = new Auction("Sailor costume", "Adult size sailor costume", LocalDate.parse("2021-05-10"),
				LocalDate.parse("2021-06-10"), fashion, 5, user5.getUserId());
		auctionList.add(auction15);
		Auction auction16 = new Auction("Tobacco pipe", "Hand carved", LocalDate.parse("2021-05-10"),
				LocalDate.parse("2021-06-10"), others, 5, user3.getUserId());
		auctionList.add(auction16);
		Auction auction17 = new Auction("Old car", "American car, red", LocalDate.parse("2021-06-10"),
				LocalDate.parse("2021-08-15"), motors, 100, user6.getUserId());
		auctionList.add(auction17);
		Auction auction18 = new Auction("Red shorts", "Clasic red shorts with golden buttons",
				LocalDate.parse("2021-06-10"), LocalDate.parse("2021-07-10"), fashion, 5, user6.getUserId());
		auctionList.add(auction18);
		Auction auction19 = new Auction("Leather leash", "Red leather leach, a bit chewed",
				LocalDate.parse("2021-06-09"), LocalDate.parse("2021-07-10"), pets, 3, user6.getUserId());
		auctionList.add(auction19);
		Auction auction20 = new Auction("Golf club", "Brand new", LocalDate.parse("2021-05-10"),
				LocalDate.parse("2021-06-10"), sports, 10, user6.getUserId());
		auctionList.add(auction20);
		Auction auction21 = new Auction("Bow", "Red, with white polka dots", LocalDate.parse("2021-05-10"),
				LocalDate.parse("2021-07-10"), fashion, 5, user7.getUserId());
		auctionList.add(auction21);
		Auction auction22 = new Auction("Dresser", "Beautiful pink dresser", LocalDate.parse("2021-05-10"),
				LocalDate.parse("2021-06-10"), home, 15, user7.getUserId());
		auctionList.add(auction22);
		Auction auction23 = new Auction("Manucure kit",
				"Complete manucure kit. Everything you need to obtain perfect nails !", LocalDate.parse("2021-05-10"),
				LocalDate.parse("2021-06-10"), health, 8, user7.getUserId());
		auctionList.add(auction23);
		Auction auction24 = new Auction("Tennis racket", "Pink", LocalDate.parse("2021-05-10"),
				LocalDate.parse("2021-06-10"), sports, 6, user7.getUserId());
		auctionList.add(auction24);
		Auction auction25 = new Auction("Vintage Tetris cartridge", "No box", LocalDate.parse("2021-06-10"),
				LocalDate.parse("2021-07-10"), electronics, 7, user8.getUserId());
		auctionList.add(auction25);
		AuctionManager aucmng = new AuctionManager();
		PickUpPointManager pupmng = new PickUpPointManager();
		try {
			for (Auction auction : auctionList) {
				aucmng.create(auction);
				User user = usmng.getById(auction.getUserId());
				pupmng.create(new PickUpPoint(auction.getId(), user.getStreet(), user.getPostcode(), user.getCity()));
			}
			// AUCTIONS & PICK-UP POINTS
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
