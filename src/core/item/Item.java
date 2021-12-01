package core.item;

import java.io.Serializable;

import core.character.Player;
import core.game.Inventory;
import core.hmi.HMI;
import core.place.Place;

public abstract class Item implements Serializable{
	private static final long serialVersionUID = -2738172528031259592L;
	
        /**
         * Item name
         */
	private String name; //Variable pour ne pas avoir a recreer l'objet apres "identification"
        
        /**
         * Item location, can be a Place or an Inventory
         */
	private Object location = null;

        /**
         * Can the item be taken from the room
         */
	protected boolean isTakable = true;
        
        /**
         * Can the item be taken right now from the room
         */
	protected boolean isCurrentlyTakable = true;
        
        /**
         * Can the item be used
         */
	protected boolean isUsable = false;
        
        /**
         * Can the item be used right now
         */
	protected boolean isCurrentlyUsable = false;


        /**
         * Default item constructor
         * 
         * @param name 
         * The item's name
         */
	public Item(String name) {
		this.name = name;
	}
	
        /**
         * @return the item's name
         */
	public final String getName() {
		return this.name;
	}
        
        /**
         * Sets a new item name
         * 
         * @param newName 
         * The new name
         */
	public final void setName(String newName) {
		this.name = newName;
	}
	
        /**
         * Sets a new inventory location for the item
         * 
         * @param inventory 
         * The inventory
         */
	public final void setLocation(Inventory inventory) {
		this.location = inventory;
	}
        
        /**
         * Sets a new place location for the item
         * 
         * @param location 
         * The place
         */
	public final void setLocation(Place location) {
		this.location = location;
	}
        
        /**
         * @return the item's current location
         */
	public final Object getLocation() {
		return this.location;
	}
	
        /**
         * @return a readable string giving info on the item
         */
	public abstract String look();
	
        /**
         * Gives an item to the player
         * 
         * @param player
         * The player who will receive the item
         * 
         * @return if the item was taken
         */
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
	
        /**
         * Uses an item on a target
         * 
         * @param target
         * The target, can be anything
         * 
         * @return if item was used
         */
	public /*final*/ boolean use(Object target) {
		HMI.debug(this.isUsable +"" + this.isCurrentlyUsable);
		if(this.isUsable) {
            if(this.isCurrentlyUsable) {
            	HMI.message("You use the " + this.getName() + ".");
                return this.onUse(target);
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
	protected abstract boolean onUse(Object target);
        
    /**
     * @return the item name
     */
    @Override
    public String toString() {
        return this.name;
    }

        /**
         * @return if the item needs a target
         */
	public boolean needsTarget() {
		return false;
	}
}
