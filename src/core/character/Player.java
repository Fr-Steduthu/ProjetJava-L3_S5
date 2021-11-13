package core.character;

import core.items.Item;
import core.places.Place;

public class Player extends Character{
	public static final float DEFAULT_MAXHP = 20f;
	public static final float DEFAULT_ABILLITYRESOURCES = 20f;
	public static final int DEFAULT_INVENTORY_CAPACITY = 20;
	
	private Place location = null;
	

	public Player(String name) {
		super(name, Player.DEFAULT_MAXHP, Player.DEFAULT_ABILLITYRESOURCES, Player.DEFAULT_INVENTORY_CAPACITY);
	}
	
	public Player(String name, float hp, float mana, int inventorySize) {
		super(name, hp, mana, inventorySize);
	}

	public void give(Item item) {
		// TODO Auto-generated method stub
		
	}
	
	public void setLocation(Place location) {
		this.location = location;
	}

	public Place getLocation() {
		// TODO Auto-generated method stub
		return this.location ;
	}
	
	public void moveTo(Place l) {
		this.location = l;
	}

}
