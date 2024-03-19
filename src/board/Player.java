package board;

import java.util.ArrayList;
import cards.*;

public class Player {
	private Hand h;
	private Display d;
	private int score;
	private int handlimit;
	private int sticks;

	public Player() {
		h = new Hand();
		d = new Display();
		d.add(new Pan());
		score = 0;
		handlimit = 8;
		sticks = 0;
	}

	public int getScore() {
		return score;
	}

	public int getHandLimit() {
		return handlimit;
	}

	public int getStickNumber() {
		return sticks;
	}
	
	public void addSticks(int n) {
		for (int i = 0; i < n; i++) {
			d.add(new Stick());
		}
		sticks = sticks + n;
	}

	public void removeSticks(int n) {
		int rmStick = 0;
		for (int i = d.size()-1; i > -1; i--) {
			if (d.getElementAt(i).getType().equals(CardType.STICK)) {
				d.removeElement(i);
				rmStick = rmStick + 1;
			}
			if (rmStick == n) { break; }
		}
		sticks = sticks - n;
	}

	public Hand getHand() {
		return h;
	}

	public Display getDisplay() {
		return d;
	}

	public void addCardtoHand(Card cardIn) {
		if (cardIn.getType().equals(CardType.BASKET)) {
			d.add(cardIn);
			handlimit = handlimit + 2;
		}
		else {
			h.add(cardIn);
		}
	}

	public void addCardtoDisplay(Card cardIn) {
		d.add(cardIn);
	}


	public boolean takeCardFromTheForest(int n) {
		// check if the value is in the range of the size of the forest
		if (n < 1 || n > Board.getForest().size()) {
			return false;
		}

		// take card 1 - 2
		if (n == 1 || n == 2) {
			Card newCard = Board.getForest().getElementAt(Board.getForest().size() - n);
			// if it's a basket -- display
			if (newCard.getType().equals(CardType.BASKET)) {
				d.add(newCard);
				Board.getForest().removeCardAt(n);
				handlimit = handlimit + 2;
				return true;
			}
			// other card -- hand
			else {
				// check handlimit
				if (h.size() < handlimit) {
					h.add(newCard);
					Board.getForest().removeCardAt(n);
					return true;
				}
				return false;
			}
		}

		// take card 3 - 8
		else {
			int costStick = n - 2;
			// if sticks not enough
			if (sticks < costStick) {
				return false;
			}
			else {
				Card newCard = Board.getForest().getElementAt(Board.getForest().size() - n);
				// if it's a basket -- display
				if (newCard.getType().equals(CardType.BASKET)) {
					removeSticks(costStick);
					d.add(newCard);
					Board.getForest().removeCardAt(n);
					handlimit = handlimit + 2;
					return true;
				}
				// other card -- hand
				else {
					if (h.size() < handlimit) {
						removeSticks(costStick);
						h.add(newCard);
						Board.getForest().removeCardAt(n);
						return true;
					}
					return false;
				}
			}
		}
	}


	public boolean takeFromDecay() {
		ArrayList<Card> decayCards = Board.getDecayPile();
		// check if decay pile has basket(s)
		int basketNum = 0;
		int otherCard = 0;
		for (Card item: decayCards) {
			if (item.getType().equals(CardType.BASKET)) {
				basketNum = basketNum + 1;
			}
		}
		otherCard = decayCards.size() - basketNum;
		// if does not exceed handlimit
		if (h.size() + otherCard <= handlimit + basketNum * 2) {
			for (Card item: decayCards) {
				addCardtoHand(item);
			}
			decayCards.clear();
			return true;
		}
		else { return false; }
	}


	public boolean cookMushrooms(ArrayList<Card> cardsIn) {
		// check if has a pan: display / input
		int panNum = 0;
		boolean displyPan = false;
		// number of pans in input array
		for (Card item: cardsIn) {
			if (item.getType().equals(CardType.PAN)) {
				panNum = panNum + 1;
			}
		}
		if (panNum > 1) { return false; }
		// if no pans in input, check display
		if (panNum == 0) {
			for (int i = 0; i < d.size(); i++) {
				if (d.getElementAt(i).getType().equals(CardType.PAN)) {
					displyPan = true;
				}
			}
		}
		if (panNum == 0 && !displyPan) { return false; }

		int ciderNum = 0;
		int butterNum = 0;
		ArrayList<Card> mushrooms = new ArrayList<>();
		for (Card item: cardsIn) {
			if (item.getType().equals(CardType.BASKET)) {
				return false;
			}
			else if (item.getType().equals(CardType.CIDER)) {
				ciderNum = ciderNum + 1;
			}
			else if (item.getType().equals(CardType.BUTTER)) {
				butterNum = butterNum + 1;
			}
			else if (item.getType().equals(CardType.NIGHTMUSHROOM) || item.getType().equals(CardType.DAYMUSHROOM)) {
				mushrooms.add(item);
			}
		}

		// number of mushroom cards should be at least 2
		if (mushrooms.size() <= 1) { return false; }

		String mushroomType = mushrooms.get(0).getName();
		int day = 0;
		int night = 0;
		int totalMushroom = 0;
		for (Card item: mushrooms) {
			// if cook different types
			if (!item.getName().equals(mushroomType)) {
				return false;			
			}
			if (item.getType().equals(CardType.DAYMUSHROOM)) {
				day = day + 1;
			}
			if (item.getType().equals(CardType.NIGHTMUSHROOM)) {
				night = night + 1;
			}
		}
		totalMushroom = day + night * 2;
		if (totalMushroom < 3) { return false; }
		// if has enough mushrooms for sider / butter
		if (totalMushroom >= ciderNum * 5 + butterNum * 4) {

			// get FlavourPoints for corresponding mushroom
			int pt = 0;
			if ((mushroomType).equals("honeyfungus")) {
				HoneyFungus ct = (HoneyFungus) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else if ((mushroomType).equals("treeear")) {
				TreeEar ct = (TreeEar) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else if ((mushroomType).equals("lawyerswig")) {
				LawyersWig ct = (LawyersWig) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else if ((mushroomType).equals("shiitake")) {
				Shiitake ct = (Shiitake) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else if ((mushroomType).equals("henofwoods")) {
				HenOfWoods ct = (HenOfWoods) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else if ((mushroomType).equals("birchbolete")) {
				BirchBolete ct = (BirchBolete) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else if ((mushroomType).equals("porcini")) {
				Porcini ct = (Porcini) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else if ((mushroomType).equals("chanterelle")) {
				Chanterelle ct = (Chanterelle) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}
			else {
				Morel ct = (Morel) mushrooms.get(0);
				pt = ct.getFlavourPoints();
			}	

			score = score + totalMushroom * pt + ciderNum * 5 + butterNum * 3;

			// if cooked successfully, delete all cards
			if (h.size() == 0) { return true; } // if no cards in hand (in testing)
			for (int i = 0; i < cardsIn.size(); i++) {
				for (int j = 0; j < h.size(); j++) {
					if (cardsIn.get(i) == h.getElementAt(j)) {
						h.removeElement(j);
						break;
					}
				}
			}
			// if used the pan in display, delete the pan in display
			if (panNum == 0) {
				for (int i = 0; i < d.size(); i++) {
					if (d.getElementAt(i).getType().equals(CardType.PAN)) {
						d.removeElement(i);
						break;
					}
				}
			}
			return true;
		}
		else { return false; }
	}


	public boolean sellMushrooms(String strIn, int n) {
		if (n < 2) { return false; }
		String name;
		name = strIn.replace(" ", "").replace("'", "").toLowerCase();

		Card c;
		ArrayList<Card> sell = new ArrayList<>();
		for (int i = 0; i < h.size(); i++) {
			c = h.getElementAt(i);
			if (c.getName().equals(name)) {
				sell.add(c);
			}
		}

		// cannot sell mushrooms you don't have or don't exist
		if (sell.size() == 0) { return false; }

		int day = 0;
		int night = 0;
		int total = 0; // mushrooms
		for (Card item: sell) {
			if (item.getType().equals(CardType.DAYMUSHROOM)) {
				day = day + 1;
			}
			else if (item.getType().equals(CardType.NIGHTMUSHROOM)) {
				night = night + 1;
			}
		}
		total = day + night * 2;
		
		if (total == n) {
			for (int i = 0; i < sell.size(); i++) {
				for (int j = 0; j < h.size(); j++) {
					if (h.getElementAt(j).getName().equals(name)) {
						h.removeElement(j);
						break;
					}
				}
			}
		}
		else if (total > n) {
			int night2 = 0;
			int day2 = 0;
			// assume we sell i night mushrooms, rest are day ones
			for (int i = 0; i <= night; i++) {
				// sell night mushrooms first
				int rest = n - i * 2;
				night2 = i;
				day2 = rest;
				if (rest == 1) { // if sell odd number
					break;
				}
			}
			total = day2 + night2 * 2;

			// delete night cards
			for (int i = 0; i < night2; i++) {
				for (int j = 0; j < h.size(); j++) {
					if (h.getElementAt(j).getName().equals(name) && h.getElementAt(j).getType().equals(CardType.NIGHTMUSHROOM)) {
						h.removeElement(j);
					}
				}
			}
			// delete day cards
			for (int i = 0; i < day2; i++) {
				for (int j = 0; j < h.size(); j++) {
					if (h.getElementAt(j).getName().equals(name) && h.getElementAt(j).getType().equals(CardType.DAYMUSHROOM)) {
						h.removeElement(j);
					}
				}
			}
		}
		// if number of mushrooms is not enough
		else { return false; }

		// update and display sticks
		int sk = 0;
		if ((name).equals("honeyfungus")) {
			HoneyFungus ct = (HoneyFungus) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else if ((name).equals("treeear")) {
			TreeEar ct = (TreeEar) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else if ((name).equals("lawyerswig")) {
			LawyersWig ct = (LawyersWig) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else if ((name).equals("shiitake")) {
			Shiitake ct = (Shiitake) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else if ((name).equals("henofwoods")) {
			HenOfWoods ct = (HenOfWoods) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else if ((name).equals("birchbolete")) {
			BirchBolete ct = (BirchBolete) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else if ((name).equals("porcini")) {
			Porcini ct = (Porcini) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else if ((name).equals("chanterelle")) {
			Chanterelle ct = (Chanterelle) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}
		else {
			Morel ct = (Morel) sell.get(0);
			sk = ct.getSticksPerMushroom();
		}

		addSticks(sk * total);
		return true;
	}


	public boolean putPanDown() {
		for (int i = 0; i < h.size(); i++) {
			if (h.getElementAt(i).getType().equals(CardType.PAN)) {
				d.add(h.removeElement(i));
				return true;
			}
		}
		return false;
	}
}
