package com.swap.tests.bo;

import com.swap.bo.Category;

public class testCategory {

	public static void main(String[] args) {
		Category cat1 = new Category();
		Category cat2 = new Category(1, "Furnitures");
		System.out.println(cat1.toString());
		System.out.println(cat2.toString());

	}

}
