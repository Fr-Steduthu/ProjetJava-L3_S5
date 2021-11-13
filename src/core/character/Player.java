package core.character;

import core.items.Item;

public class Player extends Character{

	private static final long serialVersionUID = -4378126790245045248L;
	
	public static final transient float DEFAULT_MAXHP = 20f;
	public static final transient float DEFAULT_ABILLITYRESOURCES = 20f;
	public static final transient int DEFAULT_INVENTORY_CAPACITY = 20;
	
	public Player(String name){
		super(name, Player.DEFAULT_MAXHP, Player.DEFAULT_ABILLITYRESOURCES, Player.DEFAULT_INVENTORY_CAPACITY);
	}
	
	public Player(String name, float hp, float mana, int inventorySize) {
		super(name, hp, mana, inventorySize);
	}

	public void give(Item item) {
		this.inventory.add(item);
	}
}
