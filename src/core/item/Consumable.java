package core.item;

import core.game.Inventory;
import core.hmi.HMI;
import core.place.Place;

public abstract class Consumable extends Usable {

	private static final long serialVersionUID = -8702740635104341473L;

        /**
         * Default constructor of a consumable item
         * 
         * @param name 
         * The item name
         */
	public Consumable(String name) {
		super(name);
	}

        /**
         * @return a readable string giving info on the item
         */
	@Override
	public abstract String look();
	
        /**
         * Uses an item on a target
         * 
         * @param target
         * The target, can be anything
         * 
         * @return if item was used
         */
	@Override
	public boolean use(Object target) {
		if(this.isUsable) {
            if(this.isCurrentlyUsable) {
                if(this.onUse(target)) {
                	if(this.getLocation() instanceof Place) {
                		((Place) this.getLocation()).removeItem(this);
                		
                	}else if(this.getLocation() instanceof Inventory) {
                		((Place) this.getLocation()).removeItem(this);
                		
                	}else {
                		HMI.error("Unreachable code reached!?");
                		assert(false); //Unreachable code
                	}
                	return true;
                }
            } else {
                HMI.message("This item cannot be used yet");
            }
		}else {
			HMI.message("This cannot be used");
		}
		return false;
		
	}

         /**
         * What to do on the item use
         * 
         * @param target
         * The target
         * 
         * @return if the item was used
         */
	@Override
	protected abstract boolean onUse(Object target);

}
