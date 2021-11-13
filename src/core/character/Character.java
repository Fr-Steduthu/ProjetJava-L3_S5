package core.character;

import core.Game;
import core.items.Item;
import core.places.Place;

//import javax.annotation.ParametersAreNonnullByDefault;
public abstract class Character {
	private final String NAME;
	private Place location;
	
	protected float maxHP;
	protected float maxAbilityRessource;
	
	protected float hp;
	protected float ar;
	
	protected float manaRegen = 0.0f;
	
	protected Inventory inventory;
	
	//@ParametersAreNonnullByDefault
	public Character(String name, float maxHP, float maxAbilityRessource, int inventoryCapacity) {
		this.NAME = name;
		this.maxHP = maxHP;
		this.maxAbilityRessource = maxAbilityRessource;
		
		this.hp = this.maxHP;
		this.ar = this.maxAbilityRessource;
		
		this.inventory = new Inventory(inventoryCapacity);
	}

	public String getNAME() {
		return NAME;
	}
	
	public Item[] getInventory() {
		return this.inventory.toArray();
	}
	
	public Place getLocation() {
		return this.location;
	}

	public void setLocation(Place location) {
		this.location = location;
	}
	
	
	public void heal(int amount) {
		this.hp += amount;
		if(this.hp > this.maxHP) {
			this.hp = this.maxHP;
		}
	}
	public void hurt(int dammageTaken) {
		this.hp -= dammageTaken;
		if(this.hp < 1) {
			Game.end("You lost. No health remaining", false);
		}
	}
	public int getHP() {
		return (int)(this.hp +0.5f);
	}
	
	public int getAP() {
		return (int) (this.ar +0.5f);
	}
	public void useMana(float amount) {
		this.ar -= amount;
	}
	public void giveMana(float amount) {
		this.ar += amount;
	}
	public void regenMana() {
		this.giveMana(this.manaRegen);
	}
}
