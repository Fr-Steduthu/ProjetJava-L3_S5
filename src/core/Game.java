package core;

import java.io.File;

import core.character.Character;
import core.character.Monster;
import core.character.Player;
import core.character.State;
import core.items.Item;
import core.places.Exit;
import core.places.Place;

import hmi.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Game {
	
	public static void start(Quest q) throws IOException{
		Object destination = q.getObjectiveObject();
		Place current = q.getStartingPoint();
		Player p = q.getPlayer();
				
		boolean hasQuitted = false;
		
		//final String message;
		Boolean victoryState = null;
		
		while(current !=  destination && victoryState == null && hasQuitted == false) {

			HMI.clear();
			HMI.message("It's your turn; what do you do?");
			
			//Affichage
		

			boolean hasFinishedTurn = false;
			while (hasFinishedTurn == false) {
				
				Command action = Command.toCommand(HMI.read("Please enter wanted action"));
				//Command action = Command.toCommand(commandLine[0]);
				
				switch(action) {
					case ATTACK:
						HMI.read("Choisissez une cible a attaquer :\n");
						hasFinishedTurn = true;
						break;
					case GO:
						HMI.message("Choose where to go:");
						for(Exit e : current.getExits()) {
							for(Place p1 : e.getRooms()) {
								HMI.message(p1.getName());
							}
						}
						Game.selectGo(current.getExits());
						break;
					case HELP:
						Command.help();
						break;
					case LOOK:
						HMI.message(current.toString());
						break;
					case QUIT:
						if (HMI.confirm("Voulez vous vraiment quitter le jeu ?")) {
							if (HMI.confirm("Voulez-vous sauvegarder la partie ?")) {
								Game.save(q);
							}
							hasQuitted = true;
							hasFinishedTurn = true;
						}
						break;
					case TAKE:
						hasFinishedTurn = true;
						//TODO
						break;
					case USE:
						hasFinishedTurn = true;
						//TODO
						break;
					default:
						HMI.error("Game.start() -> unknown Command -> no behavior defined -> please try again");
						break;
				}
			}
			
			Game.charactersActions(p, q, current);
			
			victoryState = Game.checkWinningConditions(current, q);
			victoryState = Game.checkLoosingConditions(q);
		}
	}
	
	/**SELECTORS for commands**/
	
	private static Item selectItem(Item[] items) {
        String item =  HMI.read("Select an item. Enter BACK to cancel.",Regex.regex(items)+"|BACK");
        
        if(!item.equals("BACK")) {
        	
        	for(Item i : items) {
        		
        		if(i.getName().equals(item)) {
        			return i;
        			
        		}
        	}
        }
		return null;
    }
    
    private static Item selectUse(Quest q) {
    	
        Item[] playerItems = q.getPlayer().getInventory();
        
        if (playerItems.length == 0) {
            HMI.message("Your inventory is empty.");
            return null;
        }
        
        HMI.message("Choose and item to use.");
        
        for (Item item : playerItems) {
            HMI.message(item.toString());
        }
        
        Item selectedItem = null;
        while (selectedItem == null) {
            selectedItem = Game.selectItem(playerItems);
        }
        
        return selectedItem;
    }
    
    private static Item selectTake(Quest q) {
        Item[] roomItems = q.getPlayer().getLocation().getItems();
        if (roomItems.length == 0) {
            HMI.message("There is nothing you can pickup around here.");
            return null;
        }
        
        HMI.message("Choose and item to pick u.p");
        for (Item item : roomItems) {
            HMI.message(item.toString());
        }
        
        Item selectedItem = null;
        while (selectedItem == null) {
            selectedItem = Game.selectItem(roomItems);
        }
        return selectedItem;
        
    }
    
    private static Character selectAttack(Quest q){
        ArrayList<Character> monsterList = new ArrayList<>();
        Character[] roomNpcs = q.getPlayer().getLocation().getNpcs();
        
        if (roomNpcs.length == 0) {
            HMI.message("There is nothing to satisfy your bloodlust here.");
            return null;
        }
        
        HMI.message("Monsters in room:");
        
        for (Character charac : roomNpcs) {
            if (charac instanceof Monster) {
                HMI.message(charac.toString());
                monsterList.add(charac);
            }
        }
        
        String target = HMI.read("Choose an ennemy to attack.",Regex.regex((Character[])monsterList.toArray())+"BACK");

        for (Character charac : monsterList) {
            if (charac.getName().equals(target)) {
                    return charac;
            }
        }
    
    	return null;
    }
    
    private static Exit selectGo(Quest q) {
        Exit toReturn = null;
        Exit[] roomDoors = q.getPlayer().getLocation().getExits();
        if (roomDoors.length == 0) {
            HMI.message("You have found a dead-end.");
            return toReturn;
        }
        
        HMI.message("Choisissez une porte a passer :");
        for (Exit ex : roomDoors) {
            HMI.message(ex.getRooms()[1].getName());
        }
        
        String target = HMI.read("Please choose a door to go through");
        boolean isFound = false;
        
        while (!isFound) {
            for (Exit ex : roomDoors) {
                if (ex.getRooms()[1].getName().equals(target)) {
                    toReturn = ex;
                    isFound = true;
                }
            }
            target = HMI.read("Unknown room. Please retry");
        }
        return toReturn;
    }

	/**GAMEPLAY loop fundamental elements**/
    
	private static Boolean checkWinningConditions(Place current, Quest q) {
		Object objective = q.getObjectiveObject();
		if(objective instanceof Place) {
			if(current == objective) {
				return true;
			}
		}else if(objective instanceof Monster) {
			if(((Monster) objective).getState() == State.DEAD) {
				return true;
			}
		}else if(objective instanceof Character) {
			for(Character c : current.getNpcs()) {
				if(c == objective) {
					return true;
				}
			}
		}else if(objective instanceof Item) {//Possession et non utilisation d'un item
			for( Item i : q.getPlayer().getInventory()){
				if(i == objective) {
					return true;
				}
			}
		}
		
		return null;
	}
	
	private static void charactersActions(Player p, Quest q, Place current) {
		for(Character c : current.getNpcs()) {
			if(c.getHP() <= 0 && c.getState() != State.DEAD) {
				c.onDeath(q, p);
				c.setState(State.DEAD);
			}
			
			if(c instanceof Monster && c.getState() != State.STUNNED) {
				c.attack(p);
			}
		}
	}
	private static Boolean checkLoosingConditions(Quest q) {
		Player p = q.getPlayer();
		
		if(p.getState() == State.DEAD) {
			Game.end("You lost all hp!", false);
			return false;
			
		}else if(p.getLocation().getExits().length == 0) {
			Game.end("Dead end found", false);
			return false;
		}
		
		return null;
	}

	public static void end(String message, boolean victoryState) {
		
		if(victoryState) {
			HMI.message("Congratulations, you have won");
			
		}else {
			
			HMI.message("Aw, you lost. How sad.");
		}
		
		HMI.message(message);

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			//Le sleep n'est pas important mais agreable pour pouvoir lire le message
		}
		
		HMI.input.close();
		//HMI.output.close(); //On laisse a l'utilisateur le soin de fermer le jeu (System.out en ce moment)
		HMI.errorOutput.close();
	}
	
	/*
	 * http://blog.paumard.org/cours/java/chap10-entrees-sorties-serialization.html
	 * Guide pour la serialization
	 */
	
	/**SAVING OF GAME**/
	
	public static void save(Quest q) throws IOException {
		String path = "saves/";
		String saveName = "savegame_" + q.getClass().getSimpleName() + ".qa_sav";
		File saveFile = new File(path + saveName);
		
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
				oos.writeObject(q);
			}
	}
	
	public static void load(File saveFile) throws FileNotFoundException, IOException, ClassNotFoundException {
		Quest q_loadedSave;
		
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
				q_loadedSave = (Quest)ois.readObject();
			}
		Game.start(q_loadedSave);
	}
}
