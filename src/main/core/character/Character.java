package main.core.character;

import java.io.Serializable;

import main.core.Inventory;
import main.core.Quest;
import main.core.items.Equipment;
import main.core.items.Item;
import main.core.places.Place;
import main.hmi.HMI;

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
	protected double maxHP;
	protected double maxAbilityResource;
	
	protected double hp;
	protected double ar;
	
	protected double arRegen = 0.0;
	
	protected double attackDamage = 1.0;
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

	/**SETTERS & GETTERS**/
	public final String getName() {
			return NAME;
	}

	public final Item[] getItems() {
			return this.inventory.getItems();
	}
        
    public final String getInventoryItemsStr() {
        return this.inventory.invToReadableString();
    }
    
    public final String getInventoryEquippedStr() {
        return this.inventory.equipmentToReadableString();
    }
    
    public final String getInventoryStr() {
        return this.inventory.toString();
    }

	public final Place getLocation() {
			return this.location;
	}

	public final void setLocation(Place location) {
			this.location = location;
	}

	public void heal(double f) {
		this.hp += f;
		
		if(this.hp > this.maxHP) {
				this.hp = this.maxHP;
		}
	}
	
    //peut se blesser lui-meme, a justifier 
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

	public final double getHP() {
			return this.hp;
	}

	public final double getAR() {
			return this.ar;
	}

	public final void useAR(double amount) {
			this.ar -= amount; // On accepte l'energie negative; plus de flexibilite
	}

	public final void giveAR(double amount) {
		this.ar += amount;
		if(this.ar > this.maxAbilityResource) {
				this.ar = this.maxAbilityResource;
		}
	}

	public final void regenAR() {
		this.giveAR(this.arRegen);
	}

	public void setArmor(double val){
		this.armor = val;
	}
	
	public final double getArmor() {
		return this.armor;
	}

	public void setState(State state) {
		this.currentState = state;
	}

	public final State getState() {
		return this.currentState;
	}

	public final double getDamage() {
		return this.attackDamage;
	}

	public void setDamage(double value) {
		this.attackDamage = value;
	}

	/**Methods**/

	public void attack(Character target){
			target.hurt((int) this.attackDamage);
	}

	public void kill() {
			this.hp = 0.0f;
	}

	public abstract void interact(Quest context);

	public final void inspect() {
			HMI.message(getName() + " : " + this.getHP() + "/" + ((int)(this.maxHP+0.5)) + "health points\n\t"+(int)this.armor+" armor"); 
	}

	public final boolean use(Item e, Object target) {
		if(this.canUseItems) {
			return e.use(target);
		} else {
			HMI.message(this.getClass().getSimpleName() + " tried to use an item... except it can't.\nWhat a pitiful display...");
			return false;
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
			this.speak(this.dialogue);
		}else {
			HMI.message("It doesn't seem to respond");
		}
	}
	
	public final void speak(String message) {
		HMI.message("["+this.NAME+"]" + message);
	}
	
	public final boolean ask(String message) {
		return HMI.confirm("[" + this.NAME + "]" + message);
	}
	
	public final Item[] getLoot(){
		return this.getItems();
	}
	
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
	
	public boolean addItem(Item item) {
		return this.inventory.addItem(item);
	}
	
	public boolean removeItem(Item item) {
		return this.inventory.removeItem(item);
	}

	public boolean canTalk() {
		return this.isAbleToSpeak;
	}
}
