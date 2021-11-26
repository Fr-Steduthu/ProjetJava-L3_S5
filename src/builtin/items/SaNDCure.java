package builtin.items;

import core.Inventory;
import core.items.Item;

public class SaNDCure extends Item {

	private static final long serialVersionUID = -666362609539985459L;

	public SaNDCure() {
		super("SaND-cure");
                isUsable = true;
                currentlyUsable = true;
	}

	@Override
	public String look() {
		return "SaND-cure. A cure to the SaND disease. Removes every single SaND from your inventory.";
	}

	@Override
	protected void onUse(Object target) {
		for(Item e : ((Inventory) this.getLocation()).getItems()) {
			if(e instanceof SaND) {
				((Inventory) this.getLocation()).removeItem(e);
			}
		}
	}

}
