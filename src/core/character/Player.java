package core.character;

import core.items.Item;
import hmi.HMI;

public final class Player extends Character{

	private static final long serialVersionUID = -4378126790245045248L;
	
	public static final transient float DEFAULT_MAXHP = 20f;
	public static final transient float DEFAULT_ABILLITYRESOURCES = 20f;
	public static final transient int DEFAULT_INVENTORY_CAPACITY = 20;

	private static final int DEFAULT_EQUIPMENT_SIZE = 3;
	
	public Player(String name){
		super(name, Player.DEFAULT_MAXHP, Player.DEFAULT_ABILLITYRESOURCES, Player.DEFAULT_INVENTORY_CAPACITY, Player.DEFAULT_EQUIPMENT_SIZE);
	}
	
	public Player(String name, float hp, float mana, int inventorySize) {
		super(name, hp, mana, inventorySize, 0);
	}

	public void give(Item item) {
		if(this.inventory.add(item)) {
			HMI.message("You couldn't pick" + item.getName() + " up; your inventory is full.");
		}
	}
	
	public void take(Item item) {
		this.inventory.remove(item);
	}

	@Override
	public void interact() {
		HMI.message("[ERROR] UNREACHABLE CODE REACHED");
	}
}
