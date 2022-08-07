package com.revature;

import com.revature.layout.*;

public class Driver {
	
	public static void main(String[] args) {
		int[] data = new int[] {5, 2, 7, 1, 10, 8, 3, 4, 9, 6};		
		Histogram hist = new Histogram(data);
		System.out.println(hist.histogram());
		
		
	}

}
