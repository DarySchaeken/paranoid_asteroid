package game.entities;

public enum Size {
	SMALL,
	MEDIUM,
	LARGE;
	
	public Size getSmaller() {
		return (this == SMALL || this == MEDIUM) ? SMALL : MEDIUM;
	}
}

