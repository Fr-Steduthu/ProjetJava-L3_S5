package core.character;

import java.io.Serializable;

import core.game.Inventory;
import core.game.Quest;
import core.hmi.HMI;
import core.item.Equipment;
import core.item.Item;
import core.place.Place;

//import javax.annotation.ParametersAreNonnullByDefault;
public abstract class Character implements Serializable{

	private static final long serialVersionUID = 2171784611978154697L;
	
	//Misc
	private final String NAME;
        
        /**
         * The character's location. Can be modified
         * 
         * @see setLocation
         */
	private Place location;
	
	protected String dialogue = "[ERROR] No dialogue set";
	
        /**
         * The character's inventory. It's content can be modified
         * 
         * @see use
         * @see equip
         * @see unequip
         * @see addItem
         * @see removeItem
         */
	protected Inventory inventory;
	
        /**
         * The character's current state or immunities. Can be modified
         * 
         * @see setState
         */
	protected State currentState = State.ALIVE;
	
	//Combat
	protected double maxHP;
	protected double maxAbilityResource;
	
        /**
         * The character's health points. Can be modified
         * 
         * @see heal
         * @see hurt
         */
	protected double hp;
        
        /**
         * The character's ressource points. Can be modified
         * 
         * @see useAR
         * @see giveAR
         * @see regenAR
         */
	protected double ar;
	
	protected double arRegen = 0.0;
	
        /**
         * The character's attack damage. Can be modified
         * 
         * @see setDamage
         */
	protected double attackDamage = 1.0;
        
        /**
         * The character's armor value. Can be modified
         * 
         * @see setArmor
         */
	protected double armor = 0.0;
	
	protected boolean isLootable = false;
	protected boolean isAbleToSpeak = false;
	protected boolean canUseItems = false;

	//@ParametersAreNonnullByDefault
	public Character(String name, double maxHP, double maxAbilityResource, int inventoryCapacity, int equipment_size) {
			this.NAME = name;
			this.maxHP = maxHP;
			this.maxAbilityResource = maxAbilityResource;
			
			this.hp = maxHP;
			this.ar = maxAbilityResource;

			this.inventory = new Inventory(this, inventoryCapacity, equipment_size);
	}

	/**
         * @return the character's name
         */
	public final String getName() {
			return NAME;
	}

        /**
         * @return an item array made from the character's inventory
         */
	public final Item[] getItems() {
			return this.inventory.getItems();
	}
        
    /**
    * @return a readable string of the character's inventory items
    */
    public final String getInventoryItemsStr() {
        return this.inventory.invToReadableString();
    }
    
    /**
    * @return a readable string of the character's inventory equipped items
    */
    public final String getInventoryEquippedStr() {
        return this.inventory.equipmentToReadableString();
    }
    
    /**
    * @return a readable string of the character's inventory
    */
    public final String getInventoryStr() {
        return this.inventory.toString();
    }

        /**
         * @return the character's current location
         */
	public final Place getLocation() {
			return this.location;
	}

        /**
         * Updates the character's location
         * 
         * @param location
         * The new location of the character
         */
	public final void setLocation(Place location) {
			this.location = location;
	}

         /**
         * Updates the character's health by adding some value to it
         * 
         * @param f
         * The hp quantity to add to the character
         */
	public void heal(double f) {
		this.hp += f;
		
		if(this.hp > this.maxHP) {
				this.hp = this.maxHP;
		}
	}
	
        /**
         * Updates the character's health by removing some value to it
         * 
         * @param f
         * The hp quantity to remove from the character
         */
	public void hurt(double f) {
			double temp = this.hp - f + this.armor;
			if(temp < this.hp) {//On evite les soins par armure trop forte
					this.hp = temp;
					if(this.hp < 1) {
							this.hp = 0;
							this.currentState = State.DEAD;
					}
			}
	}

        /**
         * @return the character's current health points
         */
	public final double getHP() {
			return this.hp;
	}

        /**
         * @return the character's current ressource points
         */
	public final double getAR() {
			return this.ar;
	}

        /**
         * Updates the character's ressource points by removing some value to it
         * 
         * @param amount
         * The AR points to deduce from the character
         */
	public final void useAR(double amount) {
			this.ar -= amount; // On accepte l'energie negative; plus de flexibilite
	}

        /**
         * Updates the character's ressource points by adding some value to it
         * 
         * @param amount
         * The AR points to give to the character
         */
	public final void giveAR(double amount) {
		this.ar += amount;
		if(this.ar > this.maxAbilityResource) {
				this.ar = this.maxAbilityResource;
		}
	}

        /**
         * Regenerates the character's ressource points by using a set attribute
         */
	public final void regenAR() {
		this.giveAR(this.arRegen);
	}

        /**
         * Updates the character's armor
         * 
         * @param val
         * The new armor points
         */
	public void setArmor(double val){
		this.armor = val;
	}
	
        /**
         * @return the character's current armor points
         */
	public final double getArmor() {
		return this.armor;
	}

        /**
         * Updates the character's state
         * 
         * @param state
         * The new state
         */
	public void setState(State state) {
		this.currentState = state;
	}

        /**
         * @return the character's current state
         */
	public final State getState() {
		return this.currentState;
	}

        /**
         * @return the character's current attack damage
         */
	public final double getDamage() {
		return this.attackDamage;
	}

        /**
         * Updates the character's attack damage
         * 
         * @param value
         * The new attack damage
         */
	public void setDamage(double value) {
		this.attackDamage = value;
	}

	/**Methods**/

        /**
         * Hurt's the targeted character with the current's attack damage
         * 
         * @param target
         * The character's target
         */
	public void attack(Character target){
			target.hurt((int) this.attackDamage);
	}

        /**
         * Kills the character
         */
	public void kill() {
			this.hp = 0.0f;
                        this.currentState = State.DEAD;
	}

        /**
         * The character's interaction, usually it's some text
         * 
         * @param context
         * The current quest
         */
	public abstract void interact(Quest context);

        /**
         * Prints to the user the targeted character's name, hp and others
         */
	public final void inspect() {
			HMI.message(getName() + " : " + this.getHP() + "/" + ((int)(this.maxHP+0.5)) + "health points\n\t"+(int)this.armor+" armor"); 
	}

        /**
         * Uses an item from the character's inventory
         * 
         * @param e
         * The item to use
         * 
         * @param target
         * The target, can be anything, a Place, a Monster, a NPC, etc.
         * 
         * @return if the item was successfully used
         */
	public final boolean use(Item e, Object target) {
		if(this.canUseItems) {
			return e.use(target);
		} else {
			HMI.message(this.getClass().getSimpleName() + " tried to use an item... except it can't.\nWhat a pitiful display...");
			return false;
		}
	}

        /**
         * Equips the given equipment on the character
         * 
         * @param e
         * The equipment to equip
         */
	public final void equip(Equipment e) {
		this.inventory.equip(e);
	}

        /**
         * Unequips the given equipment on the character
         * 
         * @param e
         * The equipment to unequip
         */
	public final void unequip(Equipment e) {
		this.inventory.unequip(e);
	}

        /**
         * Makes the character speak
         */
	public final void speak() {
		if(this.isAbleToSpeak) {
			this.speak(this.dialogue);
		}else {
			HMI.message("It doesn't seem to respond");
		}
	}
	
        /**
         * Makes the character speak using a message
         * 
         * @param message
         * The message sent by the caracter to the player
         */
	public final void speak(String message) {
		HMI.message("["+this.NAME+"]" + message);
	}
	
        /**
         * Makes the character ask a question
         * 
         * @param message
         * The message sent by the caracter to the player
         * 
         * @return the result of HMI.confirm, can be true or false depending on the player's choice
         */
	public final boolean ask(String message) {
		return HMI.confirm("[" + this.NAME + "]" + message);
	}
	
        /**
         * @return an array of items that the character had on him
         */
	public final Item[] getLoot(){
		return this.getItems();
	}
	
        /**
         * Empties the caracter's inventory on it's death
         * 
         * @param context
         * The current quest
         */
	public void onDeath(Quest context){
		if(this.isLootable) {
					Item[] items = this.getLoot();
					Equipment[] equipped = this.inventory.getEquipment();
					Place location = this.getLocation();
					for (Item i : items) {
						location.addItem(i);
						this.inventory.removeItem(i);
					}
					for (Equipment e : equipped) {
						location.addItem(e);
					}
		}
		this.currentState = State.DEAD;
	}
	
        /**
         * Adds an item to the character's inventory
         * 
         * @param item
         * The item to add
         * 
         * @return if the function sucedded to add the given item to the character's inventory
         */
	public boolean addItem(Item item) {
		return this.inventory.addItem(item);
	}
	
        /**
         * Removes an item to the character's inventory
         * 
         * @param item
         * The item to remove
         * 
         * @return if the function sucedded to remove the given item from the character's inventory
         */
	public boolean removeItem(Item item) {
		return this.inventory.removeItem(item);
	}

        /**
         * @return if the character is able to talk or not
         */
	public boolean canTalk() {
		return this.isAbleToSpeak;
	}
}
