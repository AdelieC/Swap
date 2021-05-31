package com.swap.tests.bo;

import java.time.LocalDate;

import com.swap.bo.Bid;

public class testBid {

	public static void main(String[] args) {
		Bid bid1 = new Bid();
		Bid bid2 = new Bid(1, 2, 3, LocalDate.now());
		System.out.println(bid1.toString());
		System.out.println(bid2.toString());

	}

}
