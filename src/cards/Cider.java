package cards;

public class Cider extends EdibleItem {
	public Cider() {
		super(CardType.CIDER, "cider");
		// if mushroom >= 5
		flavourPoints = 5;
	}
}