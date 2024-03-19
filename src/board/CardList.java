package board;

import java.util.ArrayList;
import cards.*;

public class CardList {
	private ArrayList<Card> cList;

	public CardList() {
		cList = new ArrayList<Card>();
	}

	public void add(Card cardIn) {
		cList.add(0, cardIn);
	}

	public int size() {
		return cList.size();
	}

	public Card getElementAt(int n) {
		return cList.get(n);
	}

	// use visual representation index
	public Card removeCardAt(int n) {
		int index = cList.size() - n;
		return cList.remove(index);
	}
}