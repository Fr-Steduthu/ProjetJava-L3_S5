package core.character;

import java.io.Serializable;

import core.items.Item;
import core.places.Place;

//import javax.annotation.ParametersAreNonnullByDefault;
public abstract class Character implements Serializable{

	private static final long serialVersionUID = 2171784611978154697L;
	
	private final String NAME;
	private Place location;
	
	protected float maxHP;
	protected float maxAbilityRessource;
	
	protected float hp;
	protected float ar;
	
	protected float attackDammage = 1.0f;
	protected float defence = 0.0f;
	
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
		this.hp -= dammageTaken - this.defence;
		if(this.hp < 1) {
			this.hp = 0;
		}
	}
	public int getHP() {
		return (int)(this.hp +0.5f);
	}
	
	public int getAR() {
		return (int) (this.ar +0.5f);
	}
	public void useAR(float amount) {
		this.ar -= amount; // On accepte l'energie negative
	}
	public void giveAR(float amount) {
		this.ar += amount;
		if(this.ar > this.maxAbilityRessource) {
			this.ar = this.maxAbilityRessource;
		}
	}
	public void regenAR() {
		this.giveAR(this.manaRegen);
	}
	
	public void attack(Character target){
		target.hurt((int) this.attackDammage);
	}
	public float getAttackDammage(){
		return this.attackDammage;
	}
	public void setAttackDammage(float val){
		this.attackDammage = val;
	}
	
	public void setDefence(float val){
		this.defence = val;
	}
	/*//Considere inutile
	public int getDenfence(){
		return this.defence;
	}
	/**/
}
