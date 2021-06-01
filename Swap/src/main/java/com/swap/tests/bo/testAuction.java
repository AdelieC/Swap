package com.swap.tests.bo;

import java.time.LocalDate;

import com.swap.bo.Auction;

public class testAuction {

	public static void main(String[] args) {
		Auction auction1 = new Auction();
		Auction auction2 = new Auction("Ball", "used ball", LocalDate.now(), LocalDate.now(), 1, 2, 2);
		Auction auction3 = new Auction("Boots", "Nice pair of boots", LocalDate.now(), LocalDate.now(), 1, 4, 2);
		System.out.println(auction1.toString());
		System.out.println(auction2.toString());
		System.out.println(auction3.toString());
	}

}
