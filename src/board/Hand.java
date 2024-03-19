package board;

import java.util.ArrayList;
import cards.*;

public class Hand implements Displayable {
	private ArrayList<Card> handList = new ArrayList<>();

	@Override
	public void add(Card cardIn) {
		handList.add(cardIn);
	}

	@Override
	public int size() {
		return handList.size();
	}

	@Override
	public Card getElementAt(int n) {
		return handList.get(n);
	}

	@Override
	public Card removeElement(int n) {
		return handList.remove(n);
	}
}