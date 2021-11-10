package core.items;

interface Takable {
	public default boolean isTakable() {
		return true;
	}
}
