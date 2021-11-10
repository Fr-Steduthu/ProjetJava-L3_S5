package core.places;

import java.util.ArrayList;
import java.util.List;

import core.character.Character;
import core.items.Item;

public class Place {
	private final String NAME;
	private List<Exit> exits = new ArrayList<>(); //List car à nombre variable
	
	private List<Item> itemList = new ArrayList<>();
	private List<Character> npcList = new ArrayList<>();
	
	private final PlaceDescription[] FLAGS;
	private boolean hasBeenFilled = false;
	
	public Place(String name, PlaceDescription[] flags, List<Exit> exits) {
		this.NAME = name;
		this.exits = exits;
		this.FLAGS = flags;
	}
	
	public void fillRoom(List<Item> itemList, List<Character> npcList) throws Exception {
		if(this.hasBeenFilled) {
			throw new Exception("Room cannot be initialized (filled) twoce !");
		}else {
			this.hasBeenFilled = true;

			this.itemList = itemList;
			this.npcList = npcList;
		}
	}
	
	public Character[] getNpcs() {
		return (Character[]) this.npcList.toArray();
	}
	public void addNpc(Character npc) {
		this.npcList.add(npc);
	}
	public void removeNpc(Character npc) {
		this.npcList.remove(npc);
	}

	public Item[] getItems() {
		return (Item[]) this.itemList.toArray();
	}
	public void addItem(Item item) {
		this.itemList.add(item);
	}
	public void removeItem(Item item) {
		this.itemList.remove(item);
	}

}
