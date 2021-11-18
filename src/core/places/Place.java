package core.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.character.Character;
import core.character.Monster;
import core.items.Item;

public class Place implements Serializable{

	private static final long serialVersionUID = 6744297261182547691L;
	
	private final String NAME;
	private List<Exit> exits = new ArrayList<>(); //List car a nombre eventuellement variable
	
	private List<Item> itemList = new ArrayList<>();
	private List<Character> npcList = new ArrayList<>(); //Ne comporte pas le joueur. interet a implemanter ?
	
	private boolean hasBeenFilled = false;
	
	public Place(String name) {
		this.NAME = name;
	}
	
	public final void fill(List<Item> itemList, List<Character> npcList) throws Exception {
		if(this.hasBeenFilled) {
			throw new Exception("Room cannot be initialized (filled) twice !");
		}else {
			this.hasBeenFilled = true;

			this.itemList = itemList;
			this.npcList = npcList;
		}
	}
	
	/**NPCs**/
	public final Character[] getNpcs() {
		return (Character[]) this.npcList.toArray();
	}
	public final void addNpc(Character npc) {
		this.npcList.add(npc);
	}
	public final void removeNpc(Character npc) {
		this.npcList.remove(npc);
	}
	/**ITEMS**/
	public final Item[] getItems() {
		return (Item[]) this.itemList.toArray();
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
		this.exits.remove(e); // pas de check si null car verifie dans ArrayList.remove()
	}
	
	public final Exit[] getExits() {
		return (Exit[]) this.exits.toArray();
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
		out += "A sign reads " + this.NAME + "\n";
		
		//Characters
		if(this.getNpcs().length != 0) {
			out += "\nYou can see creatures :\n";
			for(Character c : this.getNpcs()) {
                            if(c instanceof Monster) {
                                        out += "\t[Monster] ";
                            }else {
                                        out += "\t[NPC]     ";
                            }
                            out += c.getName() + "\n";
                            }
		}
		//items
		if(this.getItems().length != 0) {
			out += "\nYou can see something on the ground\n";
			
			for(Item i : this.getItems()) {
				out += "\t" + i.getName() + "\n";
			}
		}
		
		if(this.getExits().length != 0) {
			
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
