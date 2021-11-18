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

		this.inventory = new Inventory(this, inventoryCapacity, equipment_size);
	}

	/**SETTERS & GETTERS**/
	public final String getName() {
		return NAME;
	}

	public final Item[] getInventory() {
		return this.inventory.getItems();
	}

	public final Place getLocation() {
		return this.location;
	}

	public final void setLocation(Place location) {
		this.location = location;
	}

	public void heal(float f) {
		this.hp += f;
		if(this.hp > this.maxHP) {
			this.hp = this.maxHP;
		}
	}

	public void hurt(float f) {
		float temp = this.hp - f - this.armor;
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

	public void setArmor(float val){
		this.armor = val;
	}

	public void setState(State state) {
		this.currentState = state;
	}

	public final State getState() {
		return this.currentState;
	}
	
	public final float getDammage() {
		return this.attackDammage;
	}
	
	public void setDammage(float value) {
		this.attackDammage = value;
	}

	/**Methods**/

	public void attack(Character target){
		target.hurt((int) this.attackDammage);
	}
	
	public abstract void interact();
	
	public final void inspect() {
		HMI.message(getName() + " : " + this.getHP() + "/" + ((int)(this.maxHP+0.5)) + "health points\n\t"+(int)this.armor+" armor"); 
	}
        
    public final void use(Item e) {
        if(this.canUseItems) {
            e.use(e);
            //TODO a completer
        } else {
            HMI.message(this.getClass().getSimpleName() + " tried to use an item.. except it can't.");
        }
    }
    
    public final void equip(Equipment e) {
    	this.inventory.equip(e);
    }
    
    public final void unequip(Equipment e) {
    	this.inventory.unequip(e);
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
                        this.inventory.removeItem(i);
                    }
                    for (Equipment e : this.equiped_items) {
                        location.addItem(e);
                    }
		}
		this.currentState = State.DEAD;
	}
	
	public void give(Item item) {
		if(this.inventory.addItem(item)) {
			HMI.message("You couldn't pick" + item.getName() + " up; your inventory is full.");
		}
	}
	
	public void take(Item item) {
		if(!this.inventory.removeItem(item)) {
			HMI.message("\t\t[ERROR>Player] An error has occured, you drop that");
		};
	}

}
