package core.items;

import core.*;
import core.Character;

public abstract class Item {
	private String name; //Variable pour ne pas avoir à recreer l'objet apres identification
	private Object location;

	protected Item(String name) {
		this.name = name;
	}
	public void setLocation(Object owner_or_place) {
		if(owner_or_place instanceof Place || owner_or_place instanceof Character) {
			this.location = owner_or_place;
		}else {
			throw new IllegalArgumentException();
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
				player.getRoom().removeItem(this);
				return "You take the "+this.name;
			}else {
				return "You may not "+this.name+" this item yet";
			}
		}else {
			return "You can't pick up that !";
		}
	}
}
