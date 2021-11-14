package core.items;

import java.io.Serializable;

import core.Inventory;
import core.character.Player;
import core.places.Place;

public abstract class Item implements Serializable{
	private static final long serialVersionUID = -2738172528031259592L;
	
	private String name; //Variable pour ne pas avoir � recreer l'objet apres identification
	private Object location;

	protected Item(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setLocation(Object owner_or_place) {
		if(owner_or_place instanceof Place || owner_or_place instanceof Inventory) {
			this.location = owner_or_place;
		}else {
			throw new IllegalArgumentException("Location of item cannot be other than Place or Container");
		}
	}
	public Object getLocation() {
		return this.location;
	}
	
	public abstract String look();
	
	public String take(Player player) {
		if(this instanceof Takable) {
			if(((Takable)this).isTakable()) {
				player.give(this);
				player.getLocation().removeItem(this);
				return "You take the "+this.name;
			}else {
				return "You may not "+this.name+" this item yet";
			}
		}else {
			return "You can't pick up that !";
		}
	}
	
	public String use() {
		if(this instanceof Usable) {
			return ((Usable)this).onUse();
		}else {
			return "This cannot be used";
		}
	}
}
