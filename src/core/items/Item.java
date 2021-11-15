package core.items;

import java.io.Serializable;

import core.Inventory;
import core.character.Player;
import core.places.Place;
import hmi.HMI;

public abstract class Item implements Serializable{
	private static final long serialVersionUID = -2738172528031259592L;
	
	private String name; //Variable pour ne pas avoir � recreer l'objet apres "identification"
	private Object location;

	private boolean takable = true;
	private boolean isPickupable = true;
        private boolean isUsable = false;
        private boolean actuallyUsable = false;

	public Item(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return this.name;
	}
	public final void setName(String newName) {
		this.name = newName;
	}
	
	public final void setLocation(Object owner_or_place) {
		if(owner_or_place instanceof Place || owner_or_place instanceof Inventory) {
			this.location = owner_or_place;
		}else {
			throw new IllegalArgumentException("Location of item cannot be other than Place or Container");
		}
	}
	public final Object getLocation() {
		return this.location;
	}
	
	public abstract String look();
	
	public final void take(Player player) {
		if(this.takable) {
			if(this.isPickupable) {
				player.give(this);
				player.getLocation().removeItem(this);
				HMI.message("You take the "+this.name);
			}else {
				HMI.message("You may not take this item yet");
			}
		}else {
			HMI.message("You can't pick up that !");
		}
	}
	
	public final void use(Object target) {
		if(this.isUsable) {
                    if(this.actuallyUsable) {
                        this.onUse(target);
                    } else {
                        HMI.message("This item cannot be used yet");
                    }
		}else {
			HMI.message("This cannot be used");
		}
	}

	protected abstract void onUse(Object target);
}
