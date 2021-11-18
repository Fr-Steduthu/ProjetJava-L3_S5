package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.character.Character;
import core.items.Equipment;
import core.items.Item;
import core.places.Place;

public class Inventory implements Serializable{

	private static final long serialVersionUID = 8705649583700127039L;
	
	private final int CAPACITY;
	private final int EQUIPMENT_CAPACITY;
	
	private List<Item> contents;
	private List<Equipment> equiped;
	
	private final Character OWNER;

	public Inventory(Character character, int inventoryCapacity, int equipment_size) {
		this.CAPACITY =  inventoryCapacity;
		this.EQUIPMENT_CAPACITY = equipment_size;
		this.contents = new ArrayList<>();
		this.equiped = new ArrayList<>();
		this.OWNER = character;
	}

	/**Getters & methods**/
	public Character getOwner() {
		return this.OWNER;
	}
	
	public Item[] getItems() {
                Item[] items = new Item[]{};
                int cmpt = 0;
		for (Item i : this.contents) {
                    items[cmpt] = i;
                    cmpt++;
                }
                return items;
	}
	
	public Equipment[] getEquipment() {
                Equipment[] equipments = new Equipment[]{};
                int cmpt = 0;
		for (Equipment e : this.equiped) {
                    equipments[cmpt] = e;
                    cmpt++;
                }
                return equipments;
	}
	
	public boolean isFull() {
		return this.CAPACITY <= this.contents.size();
	}
	public boolean isFullyGeared() {
		return this.EQUIPMENT_CAPACITY <= this.equiped.size();
	}
	
	
	public boolean contains(Item e) {
		return this.findItem(e) != -1;
	}
	
	public boolean isEquiped(Equipment e) {
		return this.findItem(e) == -1;
	}
	
	public int findItem(Item e) {
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
	
	/**Manipulation methods**/
	public boolean addItem(Item e) {
		if(this.isFull()) {
			return false;
		}else {
			e.setLocation(this);
			this.contents.add(e);
			return true;
		}
	}
	
	public boolean removeItem(Item e) {
		if(!this.contains(e)) {
			return false;
		}else {
			this.contents.get(this.findItem(e)).setLocation((Place)null);
			this.contents.remove(e);
			return true;
		}
	}
	
	public boolean equip(Equipment i) {
		if(this.contains(i) && !this.isFullyGeared()) {
			this.removeItem(i);
			i.setLocation(this);
			
			this.equiped.add(i);
			
			i.onEquip(this.OWNER);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean unequip(Equipment e) {
		if(this.isEquiped(e) && !(this.isFull())) {
			
			this.equiped.remove(e);
			e.setLocation((Place)null);
			
			e.onUnequip(this.OWNER);
			return true;
		}else {
			return false;
		}
	}
}

