package cards;

public abstract class Card {
	protected CardType type;
	protected String cardName;

	public Card(CardType typeIn, String cardNameIn) {
		type = typeIn;
		cardName = cardNameIn;
	}

	public CardType getType() {
		return type;
	}

	public String getName() {
		return cardName;
	}
}