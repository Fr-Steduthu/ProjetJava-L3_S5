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
		
		final String message;
		Boolean victoryState = null;
		
		while(current !=  destination && victoryState == null) {
			
			//TODO
			//Affichage
			
			//Gestion input
			
			//Action en consequance
			
			if(p.getHP() == 0) {
				victoryState = false;
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
}
