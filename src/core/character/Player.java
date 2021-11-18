package core.character;


import hmi.HMI;

public final class Player extends Character{

	private static final long serialVersionUID = -4378126790245045248L;
	
	public static final transient double DEFAULT_MAXHP = 20.0;
	public static final transient double DEFAULT_ABILLITYRESOURCES = 20.0;
	public static final transient int DEFAULT_INVENTORY_CAPACITY = 20;

	private static final int DEFAULT_EQUIPMENT_SIZE = 3;
	
	public Player(String name){
		super(name, Player.DEFAULT_MAXHP, Player.DEFAULT_ABILLITYRESOURCES, Player.DEFAULT_INVENTORY_CAPACITY, Player.DEFAULT_EQUIPMENT_SIZE);
	}
	
	public Player(String name, double hp, double mana, int inventorySize) {
		super(name, hp, mana, inventorySize, 0);
	}

	@Override
	public void interact() {
		HMI.message("\t\t[ERROR>Player] UNREACHABLE CODE REACHED");
	}
}
