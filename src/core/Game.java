package core;

import java.io.File;

import core.character.Character;
import core.character.Monster;
import core.character.Player;
import core.character.State;
import core.items.Item;
import core.places.Place;
import hmi.*;

public class Game {
	/*public Game(Player p1, Quest quest) {
		
		this.mainloop(p1, quest);
	}*/

	public static void start(Quest q){
		Object destination = q.getObjectiveObject();
		Place current = q.getStartingPoint();
		Player p = q.getPlayer();
		
		//final String message;
		Boolean victoryState = null;
		
		while(current !=  destination && victoryState == null) {
			
			//TODO
			//Affichage
			
			//Gestion input
			Command action = HMI.read();
			//Action en consequance
			switch(action) {
			case ATTACK:
				break;
			case GO:
				break;
			case HELP:
				Command.help();
			case LOOK:
				HMI.message(current.toString());
			case QUIT:
				break;
			case TAKE:
				break;
			case USE:
				break;
			default:
				break;
			}
			
			//Verification morts + tour des monstres
			Game.charactersActions(p, q, current);
			
			victoryState = Game.checkWinningConditions(current, q);
			Game.checkLoosingConditions(p, q);
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
			
			if(c instanceof Monster) {
				c.attack(p);
			}
		}
	}
	private static void checkLoosingConditions(Player p, Quest q) {
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
	 * Guide pour la sérialization
	 */
	public static void save(Player p, Quest q) {
		//TODO
	}
	
	public static void load(File saveFile) {
		Quest q_loadedSave = null;
		
		//TODO
		
		Game.start(q_loadedSave);
	}
}
