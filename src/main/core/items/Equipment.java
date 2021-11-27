package core.items;

import hmi.HMI;
import core.character.Character;

public abstract class Equipment extends Item {

	private static final long serialVersionUID = 739031092041156358L;

	public Equipment(String name) {
		super(name);
		this.isTakable = true;
		this.isCurrentlyTakable = true;
	}

	@Override
	public abstract String look();

	@Override
	protected final boolean onUse(Object target) {
		HMI.message("This item is an equipment and cannot be used");
		return false;
	}
	
	public abstract void onEquip(Character target);
	
	public abstract void onUnequip(Character target);

}