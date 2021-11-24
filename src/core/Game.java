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
import java.util.List;

public class Game {
	/*public Game(Player p1, Quest quest) {
		
		this.mainloop(p1, quest);
	}*/

	public static void start(Quest q) throws IOException{
		Object destination = q.getObjectiveObject();
		Place current = q.getStartingPoint();
		Player p = q.getPlayer();
				
				boolean hasQuitted = false;
		
		//final String message;
		Boolean victoryState = null;
		
		while(current !=  destination && victoryState == null && hasQuitted == false) {

			HMI.message("\n\nUn nouveau tour commence : choisissez une action à effectuer.");
			
			//Affichage
		
            boolean hasFinishedTurn = false;
            while (hasFinishedTurn) {
            	
                Command action = Command.toCommand(HMI.read());
                //Command action = Command.toCommand(commandLine[0]);
                
                switch(action) {
                    case ATTACK:
                        Character target = selectAttack(q);
                        p.attack(target);
                        hasFinishedTurn = true;
                        break;
                    case GO:
                        Exit exit = selectGo(q);
                        p.setLocation(exit.getRooms()[1]);
                        hasFinishedTurn = false;
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
                        Item takeItem = selectTake(q);
                        if (takeItem != null) {
                            p.give(takeItem);
                            hasFinishedTurn = true;
                        }
                        break;
                    case USE:
                        Item useItem = selectUse(q);
                        if (useItem != null) {
                            p.use(useItem);
                            hasFinishedTurn = true;
                        }
                        break;
                    default:
                    	HMI.error("Game.start() -> unknown Command value -> no behavior defined -> please try again");
                        break;
                }
            }
			
			Game.charactersActions(p, q, current);
			
			victoryState = Game.checkWinningConditions(current, q);
			Game.checkLoosingConditions(q);
		}
		HMI.input.close();
	}
	
	private static boolean checkWinningConditions(Place current, Quest q) {
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
		
		return false;
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
	private static void checkLoosingConditions(Quest q) {
			Player p = q.getPlayer();
		if(p.getState() == State.DEAD) {
			Game.end("You lost all hp!", false);
		}else if(p.getLocation().getExits().length == 0) {
			Game.end("Dead end found", false);
		}
	}

	public static void end(String message, boolean victoryState) {
		if(victoryState) {
			HMI.message("Congratulations, you have won");
		}else {
			HMI.message("Aw, you lost. How sad.");
		}
		HMI.message(message);
		
	}
	
	/*
	 * http://blog.paumard.org/cours/java/chap10-entrees-sorties-serialization.html
	 * Guide pour la s�rialization
	 */
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
        
        public static Item selectItem(Item[] items) {
            HMI.message("Entrez 'Aucun' pour ne pas selectionner d'objet");
            String item = HMI.read();
            boolean isFound = false;
            Item toReturn = null;
            
            while (!isFound) {
                if (item.equals("Aucun")) {
                    isFound = true;
                }
                for (Item it : items) {
                    if (it.getName().equals(item)) {
                        toReturn = it;
                        isFound = true;
                    }
                }
                HMI.message("Object inconnu. Veuillez réessayer, ou entrer 'Aucun'");
                item = HMI.read();
            }
            return toReturn;
        }
        
        public static Item selectUse(Quest q) {
            Item[] playerItems = q.getPlayer().getInventory();
            if (playerItems.length == 0) {
                HMI.message("Votre inventaire est vide !");
                return null;
            }
            
            HMI.message("Choisissez un objet à utiliser parmi ceux de votre inventaire :");
            for (Item item : playerItems) {
                System.out.println(item.toString());
            }
            
            Item selectedItem = selectItem(playerItems);
            while (selectedItem == null) {
                selectedItem = selectItem(playerItems);
            }
            return selectedItem;
        }
        
        public static Item selectTake(Quest q) {
            Item[] roomItems = q.getPlayer().getLocation().getItems();
            if (roomItems.length == 0) {
                HMI.message("Il n'y a rien à ramasser ici !");
                return null;
            }
            
            HMI.message("Choisissez un objet à ramasser parmi ceux de la salle :");
            for (Item item : roomItems) {
                System.out.println(item.toString());
            }
            
            Item selectedItem = selectItem(roomItems);
            while (selectedItem == null) {
                selectedItem = selectItem(roomItems);
            }
            return selectedItem;
            
        }
        
        public static Character selectAttack(Quest q){
            Character toReturn = null;
            Character[] roomNpcs = q.getPlayer().getLocation().getNpcs();
            if (roomNpcs.length == 0) {
                HMI.message("Il n'y a rien à attaquer ici !");
                return toReturn;
            }
            
            HMI.message("Choisissez un ennemi à attaquer parmi ceux de la salle :");
            for (Character charac : roomNpcs) {
                if (charac instanceof Monster) {
                    System.out.println(charac.toString());
                }
            }
            
            String target = HMI.read();
            boolean isFound = false;
            
            while (!isFound) {
                for (Character charac : roomNpcs) {
                    if (charac.getName().equals(target)) {
                        if (charac instanceof Monster) {
                            toReturn = charac;
                            isFound = true;
                        }
                        else {
                            HMI.message("Vous ne pouvez pas attaquer un allié !");
                        }
                    }
                }
                HMI.message("Ennemi inconnu. Veuillez réessayer.");
                target = HMI.read();
            }
            return toReturn;
        }
        
        public static Exit selectGo (Quest q) {
            Exit toReturn = null;
            Exit[] roomDoors = q.getPlayer().getLocation().getExits();
            if (roomDoors.length == 0) {
                HMI.message("Vous etes dans un cul-de-sac !");
                return toReturn;
            }
            
            HMI.message("Choisissez une porte a passer :");
            for (Exit ex : roomDoors) {
                System.out.println(ex.getRooms()[1].getName());
            }
            
            String target = HMI.read();
            boolean isFound = false;
            
            while (!isFound) {
                for (Exit ex : roomDoors) {
                    if (ex.getRooms()[1].getName().equals(target)) {
                        toReturn = ex;
                        isFound = true;
                    }
                }
                HMI.message("Salle inconnue. Veuillez réessayer.");
                target = HMI.read();
            }
            return toReturn;
        }
}
