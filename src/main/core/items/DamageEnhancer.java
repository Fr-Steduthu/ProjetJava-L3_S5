package core.items;

import core.character.Character;

public abstract class DamageEnhancer extends Equipment {

	private static final long serialVersionUID = -4645746654636214701L;

	private final double damage;
	
	public DamageEnhancer(String name, double damage) {
		super(name);
		this.damage = damage;
	}

	@Override
	public abstract String look();

	@Override
	public final void onEquip(Character target) {
		target.setDamage(target.getDamage() + this.damage);
	}

	@Override
	public final void onUnequip(Character target) {
		target.setDamage(target.getDamage() - this.damage);
	}
        
        public double getDamage() {
            return this.damage;
        }
}