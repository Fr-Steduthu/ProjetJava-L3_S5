package core;

import java.io.File;

import core.character.Character;
import core.character.Monster;
import core.character.Player;
import core.character.State;
import core.items.Item;
import core.places.Place;

import hmi.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

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
                        while (hasFinishedTurn == false) {

                            //Gestion input
                            Command action = HMI.read();
                            //Action en consequence
                            switch(action) {
                            case ATTACK:
                                    HMI.message("Choisissez une cible a attaquer :\n");
                                    hasFinishedTurn = true;
                                    break;
                            case GO:
                                    HMI.message("Choisissez une porte ouverte a passer");
                                    hasFinishedTurn = true;
                                    break;
                            case HELP:
                                    Command.help();
                                    break;
                            case LOOK:
                                    HMI.message(current.toString());
                                    break;
                            case QUIT:
                                    String s1 = ("Voulez vous vraiment quitter le jeu ?");
                                    if (askYes(s1)) {
                                        String s2 = ("Voulez-vous sauvegarder la partie ?");
                                        if (askYes(s2)) {
                                            save (q);
                                        }
                                        hasQuitted = true;
                                        hasFinishedTurn = true;
                                    }
                                    break;
                            case TAKE:
                                    hasFinishedTurn = true;
                                    break;
                            case USE:
                                    hasFinishedTurn = true;
                                    break;
                            default:
                                    break;
                            }
                        }
			
			//Verification morts + tour des monstres
			Game.charactersActions(p, q, current);
			
			victoryState = Game.checkWinningConditions(current, q);
			Game.checkLoosingConditions(q);
		}
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
			if(c.getHP() <= 0) {
				c.onDeath(q, p);
				c.setState(State.DEAD); //Redondant
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
        
        public static boolean askYes(String s) {
            HMI.message(s);
            boolean returnedBool = false;
            Scanner sc = new Scanner(System.in);
            String answer = sc.next();
            if (answer.equals ("Oui") |
                    answer.equals("O") | 
                    answer.equals("o") |
                    answer.equals("OUI") |
                    answer.equals("oui")) {
                returnedBool = true;
            }
            return returnedBool;
        }
}
