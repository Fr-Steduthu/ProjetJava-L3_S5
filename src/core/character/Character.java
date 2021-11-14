package core.character;

import java.io.Serializable;

import core.Inventory;
import core.items.Item;
import core.places.Place;
import core.quests.Quest;
import hmi.HMI;

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
	protected float armor = 0.0f;
	
	protected float manaRegen = 0.0f;
	
	protected Inventory inventory;
	
	protected State currentState = State.ALIVE;

	protected String dialogue = "[ERROR] No dialogue set";
	
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
		this.hp -= dammageTaken - this.armor;
		if(this.hp < 1) {
			this.hp = 0;
			this.currentState = State.DEAD;
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
	
	public void setArmor(float val){
		this.armor = val;
	}
	

	public void setState(State state) {
		this.currentState = state;
	}
	public State getState() {
		return this.currentState;
	}
	
	/*//Considere inutile
	public int getDenfence(){
		return this.defence;
	}
	/**/
	
	public abstract void interact();
	//public abstract void interact(Object target);
	
	public void speak() {
		if(this instanceof AbleToSpeak) {
			HMI.message(this.dialogue);
		}else {
			HMI.message("It doesn't seem to respond");
		}
	}
	
	public Item[] getLoot(){
		return ((Character)this).getInventory();	
	}
	
	public void onDeath(Quest context, Player p){
		if(this instanceof Lootable) {
			Item[] items = this.getLoot();
			Place location = this.getLocation();
			for (Item i : items) {
			    location.addItem(i);
			}
		}
		
		this.currentState = State.DEAD;
	}


}
