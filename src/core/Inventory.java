package core;

import java.io.Serializable;
import core.character.Character;
import core.items.Equipment;
import core.items.Item;
import core.places.Place;
import hmi.HMI;

public class Inventory implements Serializable{

	private static final long serialVersionUID = 8705649583700127039L;
	
	private final int CAPACITY;
	private Item[] contents;
	private Equipment[] equiped;
	
	private int currentIndex = 0;
	private int equipedIndex = 0;
	private final Character OWNER;

	public Inventory(Character character, int inventoryCapacity, int equipment_size) {
		this.CAPACITY =  inventoryCapacity;
		this.contents = new Item[inventoryCapacity];
		this.equiped = new Equipment[equipment_size];
		this.OWNER = character;
	}

	public boolean isFull() {
		return this.CAPACITY == this.currentIndex +1;
	}
	
	public boolean add(Item e) {
		if(this.isFull()) {
			return false;
		}else {
			e.setLocation(this);
			this.contents[this.currentIndex] = e;
			this.currentIndex++;
			return true;
		}
	}
	
	public boolean remove(Item e) {
		if(!this.contains(e)) {
			return false;
		}else {
			this.contents[this.find(e)].setLocation((Place)null);
			this.contents[this.find(e)] = null;
			
			Item[] temp = this.contents.clone();
			this.contents = new Item[this.contents.length-2];
			this.currentIndex = 0;
			for(int i = 0; i < this.contents.length; i++) {
				if(!(temp[i] == null)) {
					this.add(temp[i]);
				}
			}
			return true;
		}
	}
	
	public boolean contains(Item e) {
		return this.find(e) != -1;
	}
	
	public int find(Item e) {
		/**Returns -1 if not given item is not found (works by reference); its index in the container otherwise**/
		int o = 0;
		for(Item i : this.contents) {
			if(i == e) {
				return o;
			}
			o++;
		}
		
		return -1;
	}
	
	public Item[] toArray() {
		return this.contents.clone();
	}
	
	public Character getOwner() {
		return this.OWNER;
	}
	
	public int findEquipment(Equipment e) {
		/**Returns -1 if not given item is not found (works by reference); its index in the container otherwise**/
		int o = 0;
		for(Item i : this.equiped) {
			if(i == e) {
				return o;
			}
			o++;
		}
		
		return -1;
	}
	
	public void equip(Equipment i) {
		if(this.find(i) != -1) {
			this.remove(i);
			i.setLocation(this);
			
			this.equiped[this.equipedIndex] = i;
			this.equipedIndex++;
			
			i.onEquip(this.OWNER);
		}else {
			HMI.message("An error has occured, you cannot equip that");
		}
	}
	
	public void unequip(Equipment e) {
		if(this.findEquipment(e) != -1) {
			//this.equiped[this.findEquipment(e)].setLocation(this);
			this.equiped[this.findEquipment(e)] = null;
			
			Equipment[] temp = this.equiped.clone();
			this.equiped = new Equipment[this.equiped.length-2];
			this.equipedIndex = 0;
			for(int i = 0; i < this.equiped.length; i++) {
				if(!(temp[i] == null)) {
					this.add(temp[i]);
				}
			}

			e.onUnequip(this.OWNER);
		}
	}
}

