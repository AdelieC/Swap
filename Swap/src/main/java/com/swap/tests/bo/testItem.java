package com.swap.tests.bo;

import java.time.LocalDate;

import com.swap.bo.Item;

public class testItem {

	public static void main(String[] args) {
		Item item1 = new Item();
		Item item2 = new Item("Ball", "used ball", LocalDate.now(), LocalDate.now(), 1, 2, 2);
		Item item3 = new Item("Boots", "Nice pair of boots", LocalDate.now(), LocalDate.now(), 1, 4, 2);
		System.out.println(item1.toString());
		System.out.println(item2.toString());
		System.out.println(item3.toString());
	}

}
