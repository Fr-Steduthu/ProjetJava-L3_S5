package core.game;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import core.character.Character;
import core.character.Monster;
import core.character.Player;
import core.hmi.Command;
import core.hmi.HMI;
import core.hmi.Regex;
import core.item.Item;
import core.place.Exit;
import core.place.Place;

public class Game {
	
        /**
         * Start's the game
         * 
         * @param q
         * The quest to use to start the game
         * 
         * @throws IOException 
         * Throws an IOException on read error
         */
	public static void start(Quest q) throws IOException{
		Object destination = q.getObjectiveObject();
		Place current = q.getStartingPoint();
		Player p = q.getPlayer();
		
		ArrayList<Character> deadEnnemies = new ArrayList<>();
				
		boolean hasQuitted = false;
		
		//final String message;
		Boolean victoryState = null;
		
		try {
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
							boolean is_alive = true;
							
							for(Character dead_c : deadEnnemies) {
								if(dead_c.equals(target)) {
									is_alive = false;
									break;
								}
							}
							
	                        if(target != null && is_alive){
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
											
											q.charactersActions(deadEnnemies); //Penaliser la fuite contre des monstres
											
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
							HMI.clear();
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
							String messageTake = "What item do you want to pick up ?";
							for(Item e : p.getLocation().getItems()) {	
								messageTake += "\n" + e.getName();
							}
							
							String inputTake = HMI.read(messageTake, Regex.regex(p.getLocation().getItems())+"|"+Regex.regex("back")); //Game.message(current.getExits());
							
							if(!Regex.areEquals(inputTake, "back")) {
								for(Item e : p.getLocation().getItems()) {
										
									if(Regex.areEquals(inputTake, e.getName())){
										if (e.giveTo(p)) {
											hasFinishedTurn = true;
										}
										
									}
								}
							}
							
							hasToClear = true;
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
											//assert(p.use(e, null)); //Si false, il y a inconsistance entre la classe et needsTarget -> crash
											p.use(e, null);
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
				
				q.charactersActions(deadEnnemies);
				
				q.checkWinningConditions();
				q.checkLoosingConditions();
			}
		}catch(GameHasEnded e) {
			e.printStackTrace(System.out);
			HMI.close();
		}
	}
	
	/**SELECTORS for commands**/
    
        /**
         * Used to select the player's target
         * 
         * @param q
         * The current quest
         * 
         * @return the player's target
         */
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

            HMI.message("You've tried to attack " + target + " however it doesn't exist.");
            return null;
        }

	/**GAMEPLAY loop fundamental elements**/

        /**
         * Used to send some messages according to the current ending. Can be used for either victory or death of the player
         * 
         * @param message
         * The message to send to the player
         * 
         * @param victoryState
         * If the player has won or not
         * 
         * @throws GameHasEnded 
         * Throws if the game has ended or not
         */
	public static void end(String message, boolean victoryState) throws GameHasEnded {
		
		HMI.message(message);

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			//Le sleep n'est pas important mais agreable pour pouvoir lire le message
		}
		
		throw new GameHasEnded();
	}
	

	/**SAVING OF GAME**/
	
        /**
         * Saves the game
         * 
         * @param q
         * The current quest
         * 
         * @throws IOException 
         * Throws an IOException on read error
         */
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
	
        /**
         * Loads the game
         * 
         * @param saveFile
         * The file to load
         *
         * @return the loaded quest
         * 
         * @throws FileNotFoundException
         * Throws an FileNotFoundException if the file isn't found
         * 
         * @throws IOException
         * Throws an IOException on read error
         * 
         * @throws ClassNotFoundException 
         * Throws an ClassNotFoundException if the object doesn't exist's
         */
	public static Quest load(File saveFile) throws FileNotFoundException, IOException, ClassNotFoundException {
		Quest q_loadedSave;
		
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
				q_loadedSave = (Quest)ois.readObject();
			}
		return q_loadedSave;
	}
}
