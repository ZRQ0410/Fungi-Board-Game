package cards;

public class Butter extends EdibleItem {
	public Butter() {
		super(CardType.BUTTER, "butter");
		// if mushroom >= 4
		flavourPoints = 3;
	}
}