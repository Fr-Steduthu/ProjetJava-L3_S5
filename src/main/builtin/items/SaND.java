package main.builtin.items;

import main.core.Inventory;
import main.core.items.Equipment;
import main.core.items.Item;
import main.hmi.HMI;
import main.core.character.Character;

public class SaND extends Item {

	private static final long serialVersionUID = 4110727874882698021L;

	public SaND() {
		super("SaND");
                isUsable = true;
                isCurrentlyUsable = true;
	}

	@Override
	public String look() {
		return "SaND. A special item. Might be useful on some Sentinel or something? Careful, it might.. sAnD you.";
	}

	@Override
	protected boolean onUse(Object target) {
		if(target != null) {
			return false;
		}
		
		if(Math.random() <= 1/16) {
			((Inventory) this.getLocation()).getOwner().kill();
			HMI.message("ThE SaND's pOWeR hAS coRRUPted yOu!");
			
		}else {
			Character npcs[] = ((Inventory)this.getLocation()).getOwner().getLocation().getNpcs();
			for(Character c : npcs) {
				c.kill();
			}
			
			HMI.message("All ennemies have sucumbded to the might of the SaND!");
			
			for(Item e : ((Inventory) this.getLocation()).getItems()) {
				((Inventory) this.getLocation()).removeItem(e);
			}
			
			for(Equipment e : ((Inventory)this.getLocation()).getEquipment()) {
				((Inventory)this.getLocation()).removeItem(e);
			}
			
			while(((Inventory) this.getLocation()).addItem(new SaND())) {}
		}
		return true;
	}

}
