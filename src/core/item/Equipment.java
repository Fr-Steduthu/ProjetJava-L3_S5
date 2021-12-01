package core.item;

import core.character.Character;
import core.hmi.HMI;

public abstract class Equipment extends Item {

	private static final long serialVersionUID = 739031092041156358L;

        /**
         * Default constructor for an equipment
         * 
         * @param name
         * The equipment's name
         */
	public Equipment(String name) {
		super(name);
		this.isTakable = true;
		this.isCurrentlyTakable = true;
	}

        /**
         * @return a readable string giving info on the equipment
         */
	@Override
	public abstract String look();

        /**
         * What to do on the equipment use
         * 
         * @param target
         * The target
         * 
         * @return if the item was used
         */
	@Override
	protected final boolean onUse(Object target) {
		HMI.message("This item is an equipment and cannot be used");
		return false;
	}
	
        /**
         * What to do when the equipment is equipped
         * 
         * @param target 
         * The character to target
         */
	public abstract void onEquip(Character target);
	
        /**
         * What to do when the equipment is unequipped
         * 
         * @param target 
         * The character to target
         */
	public abstract void onUnequip(Character target);

}
