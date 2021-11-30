package main.builtin.items;

import main.core.Inventory;
import main.core.items.Item;

public class SaNDCure extends Item {

	private static final long serialVersionUID = -666362609539985459L;

	public SaNDCure() {
		super("SaND-cure");
                isUsable = true;
                isCurrentlyUsable = true;
	}

	@Override
	public String look() {
		return "SaND-cure. A cure to the SaND disease. Removes every single SaND from your inventory.";
	}

	@Override
	protected boolean onUse(Object target) {
		if(target != null) {
			return false;
		}
		
		for(Item e : ((Inventory) this.getLocation()).getItems()) {
			if(e instanceof SaND) {
				((Inventory) this.getLocation()).removeItem(e);
			}
		}
		
		return true;
	}

}
