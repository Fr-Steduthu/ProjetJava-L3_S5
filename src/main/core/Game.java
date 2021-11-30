package main.core;

import java.io.File;

import main.core.character.Character;
import main.core.character.Monster;
import main.core.character.Player;
import main.core.character.State;
import main.core.items.Item;
import main.core.places.Exit;
import main.core.places.Place;

import main.hmi.*;

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
		
		ArrayList<Character> deadEnnemies = new ArrayList<>();
				
		boolean hasQuitted = false;
		
		//final String message;
		Boolean victoryState = null;
		
		while(current !=  destination && victoryState == null && hasQuitted == false) {
			HMI.message("------New turn------\nYou are in " + current.getName() +".\n");
		
                        // Player Turn
			boolean hasFinishedTurn = false;
			boolean hasToClear = false;
			while (!hasFinishedTurn) {
				if(hasToClear) {
					HMI.clear();
					hasToClear = false;
				}
				
				Command action = Command.toCommand(HMI.readCommand("Please enter wanted action; type help to see available commands"));
				HMI.message("---------");
				
				switch(action) {
					case ATTACK:
						Character target = selectAttack(q);
						
                        if(target != null){
                            p.attack(target);
                            HMI.message("You've damaged the " + target.getName() + " for " + (int) p.getDamage() + " HP !");
                            hasFinishedTurn = true;
                        }else{
                            HMI.message("Please try again or select another command.");
                        }
                        
						break;
						
					case GO:
						String choosingRoomMessage = "What room do you want to go in?";
						
						for(Exit e : current.getExits()) {
							choosingRoomMessage += "\n" + e.getRoomOmmiting(current).getName();
						}
						
						String chosenRoom = HMI.read(choosingRoomMessage, Regex.regex(current.getExits(), current)+"|"+Regex.regex("back")); //Game.message(current.getExits());
						
						if(!chosenRoom.toLowerCase().equals("back")) {
							
							for(Exit e : current.getExits()) {
								
								Place i_place = e.getRoomOmmiting(current);
								
								if(chosenRoom.toLowerCase().equals(i_place.getName().toLowerCase())){
									
									if(e.canPassThrough(q)) {
										
										Game.charactersActions(q); //Penaliser la fuite contre des monstres
										
										current = e.getRoomOmmiting(current);
										p.setLocation(e.getRoomOmmiting(current));
										
										HMI.message("You reach " + chosenRoom);
										
										hasFinishedTurn = false;
										
									}else {
										HMI.message("Can try all you can but cannot open the door.");
										hasFinishedTurn = true;
									}
								}
							}
						
						}
						break;
						
						
					case HELP:
						Command.help();
						break;
						
						
					case LOOK:
						hasToClear = true;
						HMI.message(current.toString());
						break;
						
						
					case QUIT:
						if (HMI.confirm("Do you want to exit the game ?")) {
							if (HMI.confirm("Do you wish to save your current progression ?")) {
								Game.save(q);
							}
							hasQuitted = true;
							hasFinishedTurn = true;
						}
						break;
						
						
					case TAKE:
						//TODO
						hasFinishedTurn = true;
						break;
						
						
					case USE:
						String messageUse = "What item do you want to use?";
						for(Item e : p.getItems()) {	
							messageUse += "\n" + e.getName();
						}
						
						String inputUse = HMI.read(messageUse, Regex.regex(p.getItems())+"|"+Regex.regex("back")); //Game.message(current.getExits());
						
						if(!Regex.areEquals(inputUse, "back")) {
							for(Item e : p.getItems()) {

								if(Regex.areEquals(inputUse, e.getName())){
									
									if(!e.needsTarget()) {
										assert(p.use(e, null)); //Si false, il y a inconsistance entre la classe et needsTarget -> crash
										hasFinishedTurn = true;
										break;
										
									}else {
										
										String inputUseTarget = HMI.read("What do you want to use it on?", Regex.regex(p.getLocation().getNpcs())+"|"+Regex.regex("BACK")+"|"+Regex.regex("yourself"));
										
										if(Regex.areEquals(inputUseTarget, "yourself")) {
											hasFinishedTurn = p.use(e, p);
											if(!hasFinishedTurn) {
												HMI.message("You cant' use that on yourself");
											} else {
                                                                                            p.removeItem(e);
                                                                                        }
											
											break;
										}else {
											for(Character tar : p.getLocation().getNpcs()) {
	
												if(Regex.areEquals(inputUseTarget, tar.getName())){
													hasFinishedTurn = p.use(e, tar);
													if(!hasFinishedTurn) {
														HMI.message("You can't use that on "+ inputUseTarget.toLowerCase());
													}
													break;
												}
											}
										}
									}
								}
							}
						}
						break;
						
						
					case BACK:
						break;
						
						
					case INTERACT:
						
						String message = "Who or what do you want to talk to?";
						for(Character e : current.getNpcs()) {	
							message += "\n" + e.getName();
						}
						
						String input = HMI.read(message, Regex.regex(current.getNpcs())+"|"+Regex.regex("back")); //Game.message(current.getExits());
						
						if(!input.toLowerCase().equals("back")) {
							for(Character e : current.getNpcs()) {

								if(input.toLowerCase().equals(e.getName().toLowerCase())){
									e.interact(q);
								}
							}
						
						hasFinishedTurn = true;
						}
						break;
						
                    case ME:
                        
                        HMI.message(p.toString());
                        hasFinishedTurn = false;
                        break;
                        
                                            
					default:
						HMI.error("Game.start() -> unknown Command -> no behavior defined -> please try again");
						break;
				}
			}
			
			Game.charactersActions(q);
			
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
    	
        Item[] playerItems = q.getPlayer().getItems();
        
        if (playerItems.length == 0) {
            HMI.message("Your inventory is empty.");
            return null;
        }
        
        HMI.message("Choose and item to use.");
        
        HMI.message(q.getPlayer().getInventoryItemsStr());
        
        Item selectedItem = null; 
        selectedItem = Game.selectItem(playerItems);
        
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
        
        if (roomNpcs.length == 0) { // TODO : La length avec un monstre est de 0. Pourquoi? Prob du a la romm attribuee au player étant donne que le test marche.
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
    
        HMI.message("You've tried to attack " + target + " howether it doesn't exit.");
    	return null;
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
			for( Item i : q.getPlayer().getItems()){
				if(i == objective) {
					return true;
				}
			}
		}
		
		return null;
	}
	
	private static void charactersActions(Quest q) {
		Place current = q.getPlayer().getLocation();
                Player p = q.getPlayer();
		
		for(Character c : current.getNpcs()) {
			if(c.getHP() <= 0 && c.getState() != State.DEAD) {
				c.onDeath(q);
				c.setState(State.DEAD);
			}
			
			if(c instanceof Monster && c.getState() != State.STUNNED) {
				c.attack(p);
			}
		}
                
                if(p.getHP() <= 0 && p.getState() != State.DEAD) {
                    p.onDeath(q);
                    p.setState(State.DEAD);
		}
	}
	private static Boolean checkLoosingConditions(Quest q) {
		Player p = q.getPlayer();
		
		if(p.getState() == State.DEAD) {
			Game.end("You lost all hp!", false);
			return false;
			
		}else if(p.getLocation().getExits().length == 0) {
			HMI.message("Dead end found");
			//Game.end("Dead end found", false);
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
		
		HMI.close();
	}
	
	/*
	 * http://blog.paumard.org/cours/java/chap10-entrees-sorties-serialization.html
	 * Guide pour la serialization
	 */
	
	/**SAVING OF GAME**/
	
	public static void save(Quest q) throws IOException {
                String path = "saves/";
		File saveFile = new File(path);
                
                if (!saveFile.exists()&& !saveFile.mkdir()) { // Checks if the folder exists, if not it will try to create the folder
                    HMI.error("Unable to create folder for the save file. Exiting anyway");
                    return;
                }
                
                String saveName = "savegame_" + q.getClass().getSimpleName() + ".qa_sav";
                saveFile = new File(path + saveName);
                
                if (!saveFile.exists()) {
                    if (!HMI.confirm("A save file already exists, do you want to overwrite it ?")) {
                        return;
                    }
                }
		
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
