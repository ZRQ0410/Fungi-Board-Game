package cards;

public abstract class EdibleItem extends Card {
	protected int flavourPoints;

	public EdibleItem(CardType typeIn, String cardNameIn) {
		super(typeIn, cardNameIn);
	}

	public int getFlavourPoints() {
		return flavourPoints;
	}
}