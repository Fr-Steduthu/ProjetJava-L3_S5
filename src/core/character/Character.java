package core.character;

import java.io.Serializable;

import core.Inventory;
import core.Quest;
import core.items.Equipment;
import core.items.Item;
import core.places.Place;
import hmi.HMI;

//import javax.annotation.ParametersAreNonnullByDefault;
public abstract class Character implements Serializable{

	private static final long serialVersionUID = 2171784611978154697L;
	
	//Misc
	private final String NAME;
	private Place location;
	
	protected String dialogue = "[ERROR] No dialogue set";
	
	protected Inventory inventory;
	
	protected State currentState = State.ALIVE;
	
	//Combat
	protected float maxHP;
	protected float maxAbilityRessource;
	
	protected float hp = this.maxHP;
	protected float ar = this.maxAbilityRessource;
	
	protected float arRegen = 0.0f;
	
	protected float attackDammage = 1.0f;
	protected float armor = 0.0f;
	
	protected boolean isLootable = false;
	protected boolean isAbleToSpeak = false;
        protected boolean canUseItems = false;
	
	protected Equipment[] equiped_items;
	
	
	//@ParametersAreNonnullByDefault
	public Character(String name, float maxHP, float maxAbilityRessource, int inventoryCapacity, int equipment_size) {
		this.NAME = name;
		this.maxHP = maxHP;
		this.maxAbilityRessource = maxAbilityRessource;

		this.inventory = new Inventory(inventoryCapacity);
		this.equiped_items = new Equipment[equipment_size];
	}

	/**SETTERS & GETTERS**/
	public final String getName() {
		return NAME;
	}

	public final Item[] getInventory() {
		return this.inventory.toArray();
	}

	public final Place getLocation() {
		return this.location;
	}

	public final void setLocation(Place location) {
		this.location = location;
	}

	public void heal(int amount) {
		this.hp += amount;
		if(this.hp > this.maxHP) {
			this.hp = this.maxHP;
		}
	}

	public void hurt(int dammageTaken) {
		float temp = this.hp - dammageTaken - this.armor;
		if(temp < this.hp) {//On evite les soins par armure trop forte
			this.hp = temp;
			if(this.hp < 1) {
				this.hp = 0;
				this.currentState = State.DEAD;
			}
		}
	}

	public final float getHP() {
		return this.hp;
	}

	public final float getAR() {
		return this.ar;
	}

	public final void useAR(float amount) {
		this.ar -= amount; // On accepte l'energie negative; plus de flexibilite
	}

	public final void giveAR(float amount) {
		this.ar += amount;
		if(this.ar > this.maxAbilityRessource) {
			this.ar = this.maxAbilityRessource;
		}
	}

	public final void regenAR() {
		this.giveAR(this.arRegen);
	}

	public final void setArmor(float val){
		this.armor = val;
	}

	public final void setState(State state) {
		this.currentState = state;
	}

	public final State getState() {
		return this.currentState;
	}

	/**Methods**/

	public void attack(Character target){
		target.hurt((int) this.attackDammage);
	}
	
	public abstract void interact();
	
	public final void inspect() {
		HMI.message(getName() + " : " + this.getHP() + "/" + ((int)(this.maxHP+0.5)) + "health points\n\t"+(int)this.armor+" armor"); 
	}
        
        public final void use() {
            if (this.canUseItems) {
                
            } else {
                HMI.message(this.getClass().getSimpleName() + " tried to use an item.. except it can't.");
            }
        }
	
	public final void speak() {
		if(this.isAbleToSpeak) {
			HMI.message(this.dialogue);
		}else {
			HMI.message("It doesn't seem to respond");
		}
	}
	
	public final Item[] getLoot(){
		return ((Character)this).getInventory();	
	}
	
	public void onDeath(Quest context, Player p){
		if(this.isLootable) {
	        Item[] items = this.getLoot();
	        Place location = this.getLocation();
	        for (Item i : items) {
	            location.addItem(i);
	        }
		}
		
		this.currentState = State.DEAD;
	}

}
