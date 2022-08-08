package com.revature.layout;

import com.revature.layout.utils.Utils;

public class Element {
	
	public String[] contents;
	public int width;
	public int height;
	
	public String toString() {
		return String.join("\n", contents);
	}
	
	private Element heighten(int height) {
		if (height <= this.height) {
			return this;
		} else {
			String[] higher = new String[height];
			String spaces = Utils.stringMult(" ", width);
			for (int i=0; i<height; i++) {
				if (i < this.height) {
					higher[i] = contents[i];
				} else {
					higher[i] = spaces;
				}
			}
			return new Element(higher);
		}
	}
	
	private Element widen(int width) {
		if (width <= this.width) {
			return this;
		} else {
			String[] wider = new String[height];
			int diff = width - this.width;
			String spaces = Utils.stringMult(" ", diff);
			int i = 0;
			while (i < height) {
				wider[i] = contents[i] + spaces;
			}
			return new Element(wider);
		}
	}
	
	public Element beside(Element that) {
		if (this.height  < that.height) {
			return this.heighten(that.height).beside(that);
		} else if (this.height > that.height) {
			return this.beside(that.heighten(height));
		} else {
			return new Element(Utils.arrZipConcat(contents, that.contents));
		}
	}
	
	public Element above(Element that) {
		if (this.width < that.width) {
			return this.widen(that.width).above(that);
		} else if (this.width > that.width) {
			return this.above(that.widen(width));
		} else {
			return new Element(Utils.arrConcat(contents, that.contents));
		}
	}
	
	public Element(String[] contents) {
		super();
		this.contents = contents;
		this.height = contents.length;
		int width = 0;
		for (int i=0; i<contents.length; i++) {
			width = Math.max(contents[i].length(), width);
		}
		this.width = width;
		
	}
	
	public Element(String str) {
		super();
		this.contents = new String[] {str};
		this.width = str.length();
		this.height = 1;
	}

}
