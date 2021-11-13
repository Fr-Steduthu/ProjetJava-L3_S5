package core.items;

/*public*/ interface Consumable extends Usable{
	@Override
	public default String onUse() {
		return this.consume();
	}
	
	public abstract String consume();
}
