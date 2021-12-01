package core.item;

import core.game.Inventory;
import core.hmi.HMI;
import core.place.Place;

public abstract class Consumable extends Usable {

	private static final long serialVersionUID = -8702740635104341473L;

	public Consumable(String name) {
		super(name);
	}

	@Override
	public abstract String look();
	
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

	@Override
	protected abstract boolean onUse(Object target);

}
