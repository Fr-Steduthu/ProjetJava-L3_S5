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
                Character[] characters = new Character[this.npcList.size()];
                int cmpt = 0;
                try {
                    for (Character c : this.npcList) {
                        characters[cmpt] = c;
                        cmpt++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                return characters;
	}
	
	public final void addNpc(Character npc) {
		this.npcList.add(npc);
	}
	
	public final void removeNpc(Character npc) {
		this.npcList.remove(npc);
	}
	
	/**ITEMS**/
	
	public final Item[] getItems() {
                Item[] items = new Item[this.itemList.size()];
                int cmpt = 0;
                try {
                    for (Item i : this.itemList) {
                        items[cmpt] = i;
                        cmpt++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                return items;
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
                Exit[] exits = new Exit[this.exits.size()];
                int cmpt = 0;
                try {
                    for (Exit e : this.exits) {
                        exits[cmpt] = e;
                        cmpt++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                return exits;
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
	
	private String deCase_ify(String lowerCase, String upperCase) {
		//TODO exemple : "la muerte", "LA MUERTE" deviens "[l|L][a|A] [m|M][u|U][e|E][r|R][t|T][e|E]"
		return null;
	}
	
	public String getNameRegex() {
		return deCase_ify(this.NAME.toLowerCase(), this.NAME.toUpperCase());
	}
	
	public String getExitsRegex() {
		String exitRegex = null;
		//TODO
		//for each exit : getRegexOmmiting(this)
		return exitRegex;
	}

	/**Static**/
	public static Place[] getRandomDungeon() {
		System.out.println("Place.getRandomDungeon not coded yet");
		assert(false);
		return null;
	}
        
        public static String getRegex() {
            return null;
            // TODO
        }
}
