package core.items;

public interface Equipable extends Usable{
	@Override
	public default String onUse() {
		return this.equip();
	}
	
	public abstract String equip();
}
