package core.item;

import core.character.Character;

public abstract class DamageEnhancer extends Equipment {

	private static final long serialVersionUID = -4645746654636214701L;

        /**
         * Equipment's damage value
         */
	private final double damage;
	
        /**
         * Default constructor for a damageEnchancer equipment
         * 
         * @param name
         * The equipment's name
         * 
         * @param damage 
         * It's damage value
         */
	public DamageEnhancer(String name, double damage) {
		super(name);
		this.damage = damage;
	}

        /**
         * @return a readable string giving info on the equipment
         */
	@Override
	public abstract String look();

        /**
         * What to do when the equipment is equipped
         * 
         * @param target 
         * The character to target
         */
	@Override
	public final void onEquip(Character target) {
		target.setDamage(target.getDamage() + this.damage);
	}

        /**
         * What to do when the equipment is unequipped
         * 
         * @param target 
         * The character to target
         */
	@Override
	public final void onUnequip(Character target) {
		target.setDamage(target.getDamage() - this.damage);
	}
        
        /**
         * @return the damage value of the equipment
         */
        public double getDamage() {
            return this.damage;
        }
}
