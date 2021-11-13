package core.places;

import java.util.ArrayList;
import java.util.List;

import core.character.Character;
import core.items.Item;

public class Place {
	private final String NAME;
	private List<Exit> exits = new ArrayList<>(); //List car a nombre eventuellement variable
	
	private List<Item> itemList = new ArrayList<>();
	private List<Character> npcList = new ArrayList<>(); //Ne comporte pas le joueur. interet a implemanter ?
	
	private boolean hasBeenFilled = false;
	
	public Place(String name) {
		this.NAME = name;
	}
	
	public void fill(List<Item> itemList, List<Character> npcList) throws Exception {
		if(this.hasBeenFilled) {
			throw new Exception("Room cannot be initialized (filled) twice !");
		}else {
			this.hasBeenFilled = true;

			this.itemList = itemList;
			this.npcList = npcList;
		}
	}
	
	/**NPCs**/
	public Character[] getNpcs() {
		return (Character[]) this.npcList.toArray();
	}
	public void addNpc(Character npc) {
		this.npcList.add(npc);
	}
	public void removeNpc(Character npc) {
		this.npcList.remove(npc);
	}
	/**ITEMS**/
	public Item[] getItems() {
		return (Item[]) this.itemList.toArray();
	}
	public void addItem(Item item) {
		this.itemList.add(item);
	}
	public void removeItem(Item item) {
		assert(this.itemList.contains(item));
		this.itemList.remove(item);
	}

	/**MISC**/
	public String getName() {
		return this.NAME;
	}
	
	public Exit[] getExits() {
		return (Exit[]) this.exits.toArray();
	}
	
	public void setExits(Exit[] exits) {
		this.exits = new ArrayList<>();
		for(Exit e :  exits) {
			this.exits.add(e);
		}
	}

	public static Place[] getRandomDungeon() {
		System.out.println("Place.getRandomDungeon not coded yet");
		assert(false);
		return null;
	}
}
