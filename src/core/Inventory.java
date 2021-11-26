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
        /*Item[] items = new Item[this.contents.size()];
        int cmpt = 0;
        try {
            for (Item i : this.contents) {
                items[cmpt] = i;
                cmpt++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}*/
        return (Item[]) this.contents.toArray();
	}
	
	public Equipment[] getEquipment() {
        /*Equipment[] equipments = new Equipment[this.equiped.size()];
        int cmpt = 0;
        try {
            for (Equipment e : this.equiped) {
                equipments[cmpt] = e;
                cmpt++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        	HMI.error(null);
        }*/
        return (Equipment[]) this.equiped.toArray();
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
		return this.findEquipment(e) != -1;
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
		for(Equipment equipment : this.equiped) {
			if(equipment == e) {
				return o;
			}
			o++;
		}
		
		return -1;
	}
	
	/**Manipulation methods**/
	public boolean addItem(Item e) {
		assert(e != null);
		
		if(this.isFull()) {
			return false;
		}else {
			if(e.getLocation() != this && e.getLocation() != null) {
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
        
        public String invToReadableString() {
            String inv = "";
            inv = this.contents.stream().map(item -> item.getName() + "\n").reduce(inv, String::concat);
            return inv;
        }
        
        public String equipmentToReadableString() {
            String eInv = "";
            eInv = this.equiped.stream().map(equipment -> equipment.getName() + "\n").reduce(eInv, String::concat);
            return eInv;
        }
        
        @Override
        public String toString() {
            String inv = "Here is your inventory :\nItems :";
            inv += invToReadableString();
            
            inv += "Equipped : \n";
            inv += equipmentToReadableString();
            
            return inv;
        }
}

