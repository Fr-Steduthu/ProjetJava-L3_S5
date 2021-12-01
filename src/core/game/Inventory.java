package core.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.character.Character;
import core.hmi.HMI;
import core.item.Equipment;
import core.item.Item;
import core.place.Place;

public class Inventory implements Serializable{

	private static final long serialVersionUID = 8705649583700127039L;
	
        /**
         * The inventory's item capacity
         */
	private final int CAPACITY;
        
        /**
         * The inventory's equipment capacity
         */
	private final int EQUIPMENT_CAPACITY;
	
        /**
         * The inventory's item list
         */
	private List<Item> contents;
        
        /**
         * The inventory's equipment list
         */
	private List<Equipment> equiped;
	
        
        /**
         * The inventory's owner
         */
	private final Character OWNER;

        /**
         * The inventory's default constructor
         * 
         * @param character
         * The caracter that owns this inventory
         * 
         * @param inventoryCapacity
         * The item capacity
         * 
         * @param equipment_size
         * The equipment capacity
         */
	public Inventory(Character character, int inventoryCapacity, int equipment_size) {
		this.CAPACITY =  inventoryCapacity;
		this.EQUIPMENT_CAPACITY = equipment_size;
		this.contents = new ArrayList<>();
		this.equiped = new ArrayList<>();
		this.OWNER = character;
	}

	/**Getters & methods**/
        
        /**
         * @return the owner of this inventory
         */
	public Character getOwner() {
		return this.OWNER;
	}
	
        /**
         * @return an array of items from the inventory's item list
         */
	public Item[] getItems() {
            Item[] items = {};
            return /*items = */this.contents.toArray(items);
            //return items;
	}
	
        /**
         * @return an array of items from the inventory's equipment list
         */
	public Equipment[] getEquipment() {
		Equipment[] items = {};
        return this.equiped.toArray(items);
	}
	
        /**
         * @return if the item inventory is full
         */
	public boolean isFull() {
		return this.CAPACITY <= this.contents.size();
	}
	
        /**
         * @return if the equipment inventory is full
         */
	public boolean isFullyGeared() {
		return this.EQUIPMENT_CAPACITY <= this.equiped.size();
	}
	
	/**
         * @return if the equipment is in the item inventory
         */
	public boolean contains(Item e) {
		return this.findItem(e) != -1;
	}
	
        /**
         * Uses findEquipment to check if the given equipment is in the inventory
         * 
         * @param e
         * The equipment to search for
         * 
         * @return if the equipment is in the inventory
         * 
         * @see findEquipment
         */
	public boolean isEquiped(Equipment e) {
		return this.findEquipment(e) != -1;
	}
	
        /**
         * Searches for an item in the inventory
         * 
         * @param e
         * The item to search for
         * 
         * @return the item index if it's in the inventory, else you get -1
         */
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
	
        /**
         * Searches for an equipment in the inventory
         * 
         * @param e
         * The equipment to search for
         * 
         * @return the equipment index if it's in the inventory, else you get -1
         */
	public int findEquipment(Equipment e) {
		/**Returns -1 if not given item is not found (works by reference); its index in the container otherwise**/
		int o = 0;
		for(Equipment equipment : this.equiped) {
			if(equipment == e) {
				return o;
			}
			o++;
		}
		
		return -1;
	}
	
	/**Manipulation methods**/
        /**
         * Adds a given item to the inventory, if it's not full
         * 
         * @param e
         * The item to put in the inventory
         * 
         * @return if the function succeded
         */
	public boolean addItem(Item e) {
		if(e == null) {
			HMI.error("Inventory.addItem(Item e) -> Tried to put null object in inventory");
			return false;
		}
		
		if(this.isFull()) {
			return false;
		}else {
			if(e.getLocation() != null) {
				if(e.getLocation() instanceof Place) {
					((Place) e.getLocation()).removeItem(e);
					
				}else if(e.getLocation() instanceof Inventory) {
					((Inventory)e.getLocation()).removeItem(e);
				}
			}

			e.setLocation(this);
			this.contents.add(e);
			return true;
		}
	}
	
        /**
         * Removes a given item from the inventory
         * 
         * @param e
         * The item to put in the inventory
         * 
         * @return if the function succeded
         */
	public boolean removeItem(Item e) {
		if(!this.contains(e)) {
			return false;
		}else {
			this.contents.get(this.findItem(e)).setLocation((Place)null);
			this.contents.remove(e);
			return true;
		}
	}
	
        /**
         * Equips a given equipment, if it's not full and in the item inventory
         * 
         * @param i
         * The equipment to equip
         * 
         * @return if the function succeded
         */
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
	
        /**
         * Unequips a given equipment, if it's not full and in the equipment inventory
         * 
         * @param e
         * The equipment to unequip
         * 
         * @return if the function succeded
         */
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
        
        /**
         * @return a readable item string
         */
        public String invToReadableString() {
            String inv = "";
            inv = this.contents.stream().map(item -> item.getName() + "\n").reduce(inv, String::concat);
            return inv;
        }
        
        /**
         * @return a readable equipment string
         */
        public String equipmentToReadableString() {
            String eInv = "";
            eInv = this.equiped.stream().map(equipment -> equipment.getName() + "\n").reduce(eInv, String::concat);
            return eInv;
        }
        
        /**
         * @return a readable inventory string
         */
        @Override
        public String toString() {
            String inv = "Here is your inventory :\nItems :";
            inv += invToReadableString() + "\n";
            
            inv += "Equipped : \n";
            inv += equipmentToReadableString() + "\n";
            
            return inv;
        }
}

