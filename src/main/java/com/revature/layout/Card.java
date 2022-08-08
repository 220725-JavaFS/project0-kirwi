package com.revature.layout;

import com.revature.layout.utils.Utils;

public class Card {
	
	private final String title;
	private final String[] content;
	
	public static Element space(int n) {
		return new Element(Utils.arrFill(' ', n, 1));
	}
	
	public Element makeCard() {
		Element cardTitle = new Element(title);
		Element innards = new Element(content);
		
		int widthOfContent = Math.max(cardTitle.width, innards.width) + 4;
		Element horizontalBorder = new Element(Utils.stringMult("-", widthOfContent));
		
		int heightOfContent = Math.max(cardTitle.height, innards.height) + 7;
		Element verticalBorder = new Element(Utils.arrFill("|", heightOfContent));
		
		Element middle = horizontalBorder.above(space(1)).above(cardTitle).above(space(1))
						.above(horizontalBorder).above(space(1)).above(innards).above(space(1))
						.above(horizontalBorder);
		
		return verticalBorder.beside(middle).beside(verticalBorder);
	}
	
	public static Element rowOfCards(Card[] cards) {
		int i = 1;
		Element out = cards[0].makeCard();
		while (i < cards.length) {
			out = out.beside(space(3)).beside(cards[i].makeCard());
		}
		return out;
	}
	
	public Card(String title, String[] content) {
		super();
		this.title = title;
		this.content = content;
	}

}
