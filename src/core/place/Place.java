package core.place;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.character.Character;
import core.character.Monster;
import core.item.Item;

public class Place implements Serializable{

	private static final long serialVersionUID = 6744297261182547691L;
	
        /**
         * The room's name
         */
	private final String NAME;
        
        /**
         * The room's exits
         */
	private List<Exit> exits = new ArrayList<>(); //List car a nombre eventuellement variable
	
        /**
         * The room's item list
         */
	private List<Item> itemList = new ArrayList<>();
        
        /**
         * The room's character list, can contain NPC's or monsters
         */
	private List<Character> npcList = new ArrayList<>(); //Ne comporte pas le joueur. interet a implemanter ?
	
        /**
         * Used to know if the room has been filled or not
         */
	private boolean hasBeenFilled = false;
	
        /**
         * Default place constructor
         * 
         * @param name 
         * The place's name
         */
	public Place(String name) {
		this.NAME = name;
		this.setup();
	}
	
        /**
         * Setups the place, used for inheritance for now
         */
	protected void setup() {
		return;
	}
	
        /**
         * Fills the room
         * 
         * @param itemList
         * A list of items to place
         * 
         * @param npcList
         * A list of NPC or monsters to place
         * 
         * @throws Exception 
         * Throws an Exception if the room is filled a second time
         */
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
        /**
         * @return a character array
         */
	public final Character[] getNpcs() {
                Character[] characters = {};
                return this.npcList.toArray(characters);
	}
	
        /**
         * Adds a character to the npcList
         * 
         * @param npc 
         * The character to add
         */
	public final void addNpc(Character npc) {
		this.npcList.add(npc);
		npc.setLocation(this);
	}
        
	/**
         * Removes a character from the npcList
         * 
         * @param npc 
         * The character to remove
         */
	public final void removeNpc(Character npc) {
		this.npcList.remove(npc);
		npc.setLocation(null);
	}
	
	/**ITEMS**/
	
        /**
         * @return an item array
         */
	public final Item[] getItems() {
                Item[] items = {};
                return this.itemList.toArray(items);
	}
        
        /**
         * Adds an item to the itemList
         * 
         * @param item 
         * The item to add
         */
	public final void addItem(Item item) {
		this.itemList.add(item);
	}
        
        /**
         * Removes an item from the itemList
         * 
         * @param item 
         * The item to remove
         */
	public final void removeItem(Item item) {
		assert(this.itemList.contains(item));
		this.itemList.remove(item);
	}
	
	/**Exits**/
	
        /**
         * Adds an exit to the place
         * 
         * @param c 
         * The exit to add
         */
	public final void addExit(Exit c) {
		this.exits.add(c);
	}
        
        /**
         * Removes an exit from the place
         * 
         * @param e
         * The exit to remove
         */
	public final void removeExit(Exit e) {
		this.exits.remove(e); // pas de check si n'est pas dedans car verifie dans ArrayList.remove()
	}
	
        /**
         * @return an exit array
         */
	public final Exit[] getExits() {
                Exit[] exits = {};
                return this.exits.toArray(exits);
	}
	
        /**
         * Set's the place's exits
         * 
         * @param exits 
         * An exit array
         */
	public final void setExits(Exit[] exits) {
		this.exits = new ArrayList<>();
		for(Exit e :  exits) {
			if(e != null) {
				this.exits.add(e);
			}
		}
	}

	/**MISC**/
	
        /**
         * @return the place's name
         */
	public final String getName() {
		return this.NAME;
	}
	
        /**
         * @return a readable place's information string
         */
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
        /**
         * @return a randomized dungeon, not done yet
         */
	public static Place[] getRandomDungeon() {
		System.out.println("Place.getRandomDungeon not coded yet");
		assert(false);
		return null;
	}

}
