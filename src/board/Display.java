package board;

import java.util.ArrayList;
import cards.*;

public class Display implements Displayable {
	private ArrayList<Card> displayList = new ArrayList<>();

	@Override
	public void add(Card cardIn) {
		displayList.add(cardIn);
	}

	@Override
	public int size() {
		return displayList.size();
	}

	@Override
	public Card getElementAt(int n) {
		return displayList.get(n);
	}

	@Override
	public Card removeElement(int n) {
		return displayList.remove(n);
	}
}