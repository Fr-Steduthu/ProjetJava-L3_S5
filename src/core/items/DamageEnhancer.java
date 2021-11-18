package core.items;

import core.character.Character;

public abstract class DamageEnhancer extends Equipment {

	private static final long serialVersionUID = -4645746654636214701L;

	private final float dammage;
	
	public DamageEnhancer(String name, float dammage) {
		super(name);
		this.dammage = dammage;
	}

	@Override
	public abstract String look();

	@Override
	public final void onEquip(Character target) {
		target.setDammage(target.getDammage() + this.dammage);
	}

	@Override
	public final void onUnequip(Character target) {
		target.setDammage(target.getDammage() + this.dammage);
	}

}
