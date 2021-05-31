package com.swap.tests.bo;

import com.swap.bo.PickUpPoint;

public class testPickUpPoint {

	public static void main(String[] args) {
		PickUpPoint pu1 = new PickUpPoint();
		PickUpPoint pu2 = new PickUpPoint(1, "Sesame Street", "DA68HG", "Supercity");
		System.out.println(pu1.toString());
		System.out.println(pu2.toString());

	}

}
