package com.revature.layout;

import com.revature.layout.utils.Utils;

public class Histogram {
	
	int[] x;
	int[] data;
	
	private Element bar(char style, int width, int height) {
		return new Element(Utils.arrFill(style, width, height));
	}
	
	private Element space(int n) {
		return new Element(Utils.arrFill(' ', n, 1));
	}
	
//	private Element xAxis(int[] xTicks, int width) {
//		// TODO write implementation for x-axis
//	}
//	
//	private Element yAxis(int[] yTicks, int height) {
//		// TODO write implementation for y-axis
//	}
	
	public Element histogram() {
		int i=1;
		Element out = bar((char) 0x2588, 3, data[0]);
		while (i<data.length) {
			out = out.beside(space(1)).beside(bar((char) 0x2588, 3, data[i]));
			i++;
		}
		return new Element(Utils.reverse(out.contents));
	}
	
	public Histogram(int[] x, int[] data) {
		super();
		this.data = data;
		this.x = x;
	}
	
	public Histogram(int[][] data) {
		super();
		int[] x = new int[data.length];
		int[] y = new int[data.length];
		for(int i=0; i<data.length; i++) {
			x[i] = data[i][0];
			y[i] = data[i][1];
		}
		this.x = x;
		this.data = y;
	}
	
	public Histogram(int[] data) {
		super();
		int[] xs = new int[data.length];
		for (int i=0; i<data.length; i++) {
			xs[i] = i+1;
		}
		this.x = xs;
		this.data = data;
	}

}
