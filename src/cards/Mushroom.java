package cards;

public abstract class Mushroom extends EdibleItem {
	protected int sticksPerMushroom;

	public Mushroom(CardType typeIn, String cardNameIn) {
		super(typeIn, cardNameIn);
	}

	public int getSticksPerMushroom() {
		return sticksPerMushroom;
	}
}