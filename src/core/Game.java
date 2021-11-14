package core;

import java.io.File;

import core.character.Character;
import core.character.Player;
import core.character.State;
import core.character.monsters.Monster;
import core.places.Place;
import core.quests.Quest;
import hmi.*;

public class Game {
	/*public Game(Player p1, Quest quest) {
		
		this.mainloop(p1, quest);
	}*/

	public static void start(Player p, Quest q){
		Place destination = q.getEndPoint();
		Place current = q.getStartingPoint();
		
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
			
			Game.checkLoosingConditions(p, q);
		}
		Game.end("", true);
		
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
		Player p_loadedSave = null;
		Quest q_loadedSave = null;
		
		//TODO
		
		Game.start(p_loadedSave, q_loadedSave);
	}
}
