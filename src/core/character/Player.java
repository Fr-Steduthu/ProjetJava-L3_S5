package core.character;


import core.game.Quest;
import core.hmi.HMI;

public class Player extends Character{

	private static final long serialVersionUID = -4378126790245045248L;
	
        /**
         * The player's default health points
         */
	public static final transient double DEFAULT_MAXHP = 20.0;
        
        /**
         * The player's default ressource points
         */
	public static final transient double DEFAULT_ABILLITYRESOURCES = 20.0;
        
        /**
         * The player's default inventory capacity
         */
	public static final transient int DEFAULT_INVENTORY_CAPACITY = 20;

        /**
         * The player's default equipment capacity
         */
	private static final int DEFAULT_EQUIPMENT_SIZE = 3;

	/**
         * The player's default constructor, using default values
         * 
         * @param name
         * The player's name
         */
	public Player(String name){
		super(name, Player.DEFAULT_MAXHP, Player.DEFAULT_ABILLITYRESOURCES, Player.DEFAULT_INVENTORY_CAPACITY, Player.DEFAULT_EQUIPMENT_SIZE);
                this.isLootable = true;
                this.canUseItems = true;
	}
	
        /**
         * The player's custom constructor, using custom values but no equipment capacity
         * 
         * @param name
         * The player's name
         * 
         * @param hp
         * The player's health points
         * 
         * @param mana
         * The player's ressource points
         * 
         * @param inventorySize
         * The player's inventory size
         */
	public Player(String name, double hp, double mana, int inventorySize) {
		super(name, hp, mana, inventorySize, 0);
                this.isLootable = true;
                this.canUseItems = true;
	}
        
        /**
         * The player's custom constructor, using custom values
         * 
         * @param name
         * The player's name
         * 
         * @param hp
         * The player's health points
         * 
         * @param mana
         * The player's ressource points
         * 
         * @param inventorySize
         * The player's inventory size
         * 
         * @param equipmentSize
         * The player's equipment size
         */
        public Player(String name, double hp, double mana, int inventorySize, int equipmentSize) {
            super(name, hp, mana, inventorySize, equipmentSize);
            this.isLootable = true;
            this.canUseItems = true;
	}
        
        /**
         * Player interaction, should never happen
         * 
         * See character's interact for more info
         */
	@Override
	public void interact(Quest context) {
		HMI.message("\t\t[ERROR>Player] UNREACHABLE CODE REACHED");
	}
        
        /**
         * @return the player's readable data
         */
        @Override
        public String toString() {
            String playerData = "This is your current player :\n\n";
            
            playerData+= "Name : " + this.getName() + "\n";
            playerData+= "Health : " + this.hp + "/" + this.maxHP + "\n";
            playerData+= "Energy : " + this.ar + "/" + this.maxAbilityResource + "\n";
            playerData+= "Attack Damage : " + this.attackDamage + "\n";
            playerData+= "Armor : " + this.armor + "\n\n";
            playerData+= this.inventory.toString() + "\n";
            
            return playerData;
        }
        
	/**
         * FOR TESTING ONLY
         * 
         * @return the player's inventory class
         **/
	public Object getClassInventory() {
		return super.inventory;
	}
}
