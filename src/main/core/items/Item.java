package main.core.items;

import java.io.Serializable;

import main.core.Inventory;
import main.core.character.Player;
import main.core.places.Place;
import main.hmi.HMI;

public abstract class Item implements Serializable{
	private static final long serialVersionUID = -2738172528031259592L;
	
	private String name; //Variable pour ne pas avoir a recreer l'objet apres "identification"
	private Object location = null;

	protected boolean isTakable = true;
	protected boolean isCurrentlyTakable = true;
	protected boolean isUsable = false;
	protected boolean isCurrentlyUsable = false;


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
	
	public final boolean giveTo(Player player) {
		if(this.isTakable) {
			if(this.isCurrentlyTakable) {
				player.addItem(this);
				player.getLocation().removeItem(this);
				HMI.message("You take the "+this.name);
				return true;
			}else {
				HMI.message("You may not take this item yet");
			}
		}else {
			HMI.message("You can't pick up that !");
		}
		return false;
	}
	
	public /*final*/ boolean use(Object target) {
		if(this.isUsable) {
            if(this.isCurrentlyUsable) {
                return this.onUse(target);
            } else {
                HMI.message("This item cannot be used yet");
            }
		}else {
			HMI.message("This cannot be used");
		}
		return false;
	}

	protected abstract boolean onUse(Object target);
        
    @Override
    public String toString() {
        return this.name;
    }

	public boolean needsTarget() {
		return false;
	}
}
