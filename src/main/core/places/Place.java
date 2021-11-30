package main.core.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.core.character.Character;
import main.core.character.Monster;
import main.core.items.Item;

public class Place implements Serializable{

	private static final long serialVersionUID = 6744297261182547691L;
	
	private final String NAME;
	private List<Exit> exits = new ArrayList<>(); //List car a nombre eventuellement variable
	
	private List<Item> itemList = new ArrayList<>();
	private List<Character> npcList = new ArrayList<>(); //Ne comporte pas le joueur. interet a implemanter ?
	
	private boolean hasBeenFilled = false;
	
	public Place(String name) {
		this.NAME = name;
		this.setup();
	}
	
	/**Used for inheritance in anonymous classes**/
	protected void setup() {
		return;
	}
	
	public final void fill(List<Item> itemList, List<Character> npcList) throws Exception {
		if(this.hasBeenFilled) {
			throw new Exception("Room cannot be initialized (filled) twice !");
		}else {
			this.hasBeenFilled = true;
                        
            itemList.forEach(i -> {
                this.itemList.add(i);
            });
            
            npcList.forEach(c -> {
                this.npcList.add(c);
            });
		}
	}
	
	/**NPCs**/
	public final Character[] getNpcs() {
                Character[] characters = {};
                return this.npcList.toArray(characters);
	}
	
	public final void addNpc(Character npc) {
		this.npcList.add(npc);
		npc.setLocation(this);
	}
	
	public final void removeNpc(Character npc) {
		this.npcList.remove(npc);
		npc.setLocation(null);
	}
	
	/**ITEMS**/
	
	public final Item[] getItems() {
                Item[] items = {};
                return this.itemList.toArray(items);
	}
	public final void addItem(Item item) {
		this.itemList.add(item);
	}
	public final void removeItem(Item item) {
		assert(this.itemList.contains(item));
		this.itemList.remove(item);
	}
	
	/**Exits**/
	
	public final void addExit(Exit c) {
		this.exits.add(c);
	}
	public final void removeExit(Exit e) {
		this.exits.remove(e); // pas de check si n'est pas dedans car verifie dans ArrayList.remove()
	}
	
	public final Exit[] getExits() {
                Exit[] exits = {};
                return this.exits.toArray(exits);
	}
	
	public final void setExits(Exit[] exits) {
		this.exits = new ArrayList<>();
		for(Exit e :  exits) {
			if(e != null) {
				this.exits.add(e);
			}
		}
	}

	/**MISC**/
	
	public final String getName() {
		return this.NAME;
	}
	
	@Override
	public final String toString() {
		String out = "";
		out += "A sign reads " + this.NAME;
		
		//Characters
		if(this.getNpcs().length != 0) {
			out += "\n\n---Creatures---\n";
			for(Character c : this.getNpcs()) {
				if(c.getHP() > 0) {
	                if(c instanceof Monster) {
	                	out += "\t[Monster] ";
	                }else {
	                    out += "\t[NPC]     ";
	                }
	                out += c.getName() + "\n";
				}
			}
		}else {
			out += "\n\nThere is no soul around here.";
		}
		
		//items
		if(this.getItems().length != 0) {
			out += "\n\nYou can see something in the room\n";
			
			for(Item i : this.getItems()) {
				out += "\t" + i.getName() + "\n";
			}
		}else {
			out += "\n\nYou can't see anything valuable around.";
		}
		
		if(this.getExits().length != 0) {
			for(Exit e : this.getExits()) {
				out += "\nAn exit lead to a place called ";
				out += e.getRoomOmmiting(this).getName();
			}
		}else {
			
			out += "\nYou are stuck here.";
		}
		return out;
	}

	/**Static**/
	public static Place[] getRandomDungeon() {
		System.out.println("Place.getRandomDungeon not coded yet");
		assert(false);
		return null;
	}

}
