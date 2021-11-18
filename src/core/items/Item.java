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

	protected boolean isTakable = true;
	protected boolean currentlyTakable = true;
	protected boolean isUsable = false;
	protected boolean currentlyUsable = false;


	public Item(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return this.name;
	}
	public final void setName(String newName) {
		this.name = newName;
	}
	
	public final void setLocation(Inventory inventory) {
		this.location = inventory;
	}
	public final void setLocation(Place location) {
		this.location = location;
	}
	public final Object getLocation() {
		return this.location;
	}
	
	public abstract String look();
	
	public final void take(Player player) {
		if(this.isTakable) {
			if(this.currentlyTakable) {
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
                    if(this.currentlyUsable) {
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
