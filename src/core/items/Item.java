package core.items;

import core.*;

public abstract class Item {
	private String name; //Variable pour ne pas avoir à recreer l'objet apres identification

	protected Item(String name) {
		this.name = name;
	}
	
	public abstract String look();
	
	public String take(Player player) {
		if(this instanceof Takable) {
			if(((Takable)this).isTakable()) {
				player.give(this);
				return "You take the "+this.name;
			}else {
				return "You may not "+this.name+" this item yet";
			}
		}else {
			return "It's stuck";
		}
	}
}
