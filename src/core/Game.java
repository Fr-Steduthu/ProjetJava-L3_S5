package core;

import core.character.Player;
import core.places.Place;
import core.quests.Quest;
import hmi.HMI;

public class Game {
	/*public Game(Player p1, Quest quest) {
		
		this.mainloop(p1, quest);
	}*/

	public static void start(Player p, Quest q) {
		Place destination = q.getEndPoint();
		Place current = q.getStartingPoint();
		
		//final String message;
		Boolean victoryState = null;
		
		while(current !=  destination && victoryState == null) {
			
			//TODO
			//Affichage
			
			//Gestion input
			
			//Action en consequance
			
			if(p.getHP() == 0) {
				Game.end("You lost all hp!", false);
				break;
			}else if(p.getLocation().getExits().length == 0) {
				Game.end("Dead end found", false);
			}
		}
		Game.end("", true);
		
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
	 * Guide pour la sÚrialization
	 */
	public static void save(Player p, Quest q) {
		//TODO
	}
	
	public static void load() {
		Player p_loadedSave = null;
		Quest q_loadedSave = null;
		
		//TODO
		
		Game.start(p_loadedSave, q_loadedSave);
	}
}
