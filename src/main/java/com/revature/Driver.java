package com.revature;

import com.revature.layout.*;

public class Driver {
	
	public static void main(String[] args) {
		String title = "PHY-201";
		String[] content = new String[] {"1) poop", "2) caca shit", "3) big whoppin monster dump"};
		Card card = new Card(title, content);
		System.out.println(card.makeCard());
	}

}
