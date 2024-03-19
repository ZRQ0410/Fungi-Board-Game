package board;

import java.util.ArrayList;
import cards.*;

public class Board {
	private static CardPile forestCardsPile;
	private static CardList forest;
	private static ArrayList<Card> decayPile;

	public static void initialisePiles() {
		forestCardsPile = new CardPile();
		forest = new CardList();
		decayPile = new ArrayList<Card>();
	}

	public static void setUpCards() {
		Stick stick = new Stick();
		// 2 other pans are on displays
		for (int i=0; i<11; i++) {
			forestCardsPile.addCard(new Pan());
		}
		for (int i=0; i<5; i++) {
			forestCardsPile.addCard(new Basket());
		}
		for (int i=0; i<3; i++) {
			forestCardsPile.addCard(new Cider());
			forestCardsPile.addCard(new Butter());
		}

		// HoneyFungus: 10/1
		for (int i=0; i<10; i++) {
			forestCardsPile.addCard(new HoneyFungus(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new HoneyFungus(CardType.NIGHTMUSHROOM));

		// TreeEar: 8/1
		for (int i=0; i<8; i++) {
			forestCardsPile.addCard(new TreeEar(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new TreeEar(CardType.NIGHTMUSHROOM));

		// LawyersWig: 6/1
		for (int i=0; i<6; i++) {
			forestCardsPile.addCard(new LawyersWig(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new LawyersWig(CardType.NIGHTMUSHROOM));

		// Shiitake: 5/1
		for (int i=0; i<5; i++) {
			forestCardsPile.addCard(new Shiitake(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new Shiitake(CardType.NIGHTMUSHROOM));

		// HenOfWoods: 5/1
		for (int i=0; i<5; i++) {
			forestCardsPile.addCard(new HenOfWoods(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new HenOfWoods(CardType.NIGHTMUSHROOM));

		// BirchBolete: 4/1
		for (int i=0; i<4; i++) {
			forestCardsPile.addCard(new BirchBolete(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new BirchBolete(CardType.NIGHTMUSHROOM));

		// Porcini: 4/1
		for (int i=0; i<4; i++) {
			forestCardsPile.addCard(new Porcini(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new Porcini(CardType.NIGHTMUSHROOM));

		// Chanterelle: 4/1
		for (int i=0; i<4; i++) {
			forestCardsPile.addCard(new Chanterelle(CardType.DAYMUSHROOM));
		}
		forestCardsPile.addCard(new Chanterelle(CardType.NIGHTMUSHROOM));

		// Morel: 3/0
		for (int i=0; i<3; i++) {
			forestCardsPile.addCard(new Morel(CardType.DAYMUSHROOM));
		}
	}

	public static CardPile getForestCardsPile() {
		return forestCardsPile;
	}

	public static CardList getForest() {
		return forest;
	}

	public static ArrayList<Card> getDecayPile() {
		return decayPile;
	}

	// Add card to decayPile, if it already has 4 cards, discard all first
	public static void updateDecayPile() {
		if (decayPile.size() == 4) {
			decayPile.clear();
		}
		decayPile.add(forest.removeCardAt(1));
	}
}
