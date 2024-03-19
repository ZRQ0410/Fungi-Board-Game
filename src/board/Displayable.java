package board;

import java.util.ArrayList;
import cards.*;

public interface Displayable {
	
	public void add(Card cardIn);
	public int size();
	public Card getElementAt(int n);
	public Card removeElement(int n);
}