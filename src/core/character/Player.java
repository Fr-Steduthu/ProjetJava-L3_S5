package core.character;

import core.items.Item;
import core.places.Place;

public class Player extends Character{
	public static final float DEFAULT_MAXHP = 20f;
	public static final float DEFAULT_ABILLITYRESOURCES = 20f;
	public static final int DEFAULT_INVENTORY_CAPACITY = 20;
	

	public Player(String name) {
		super(name, Player.DEFAULT_MAXHP, Player.DEFAULT_ABILLITYRESOURCES, Player.DEFAULT_INVENTORY_CAPACITY);
	}

	public void give(Item item) {
		// TODO Auto-generated method stub
		
	}

	public Place getRoom() {
		// TODO Auto-generated method stub
		return null;
	}

}
