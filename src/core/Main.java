package core;

import java.io.IOException;

import core.builtin.TutorialQuest;
import core.game.Game;
import core.hmi.HMI;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		
		HMI.message("Welcome to our game.");
		//TODO Choix de la quete / sauvegarde
        //HMI.message("You will experience a DEMOnstration for now of what we've done");
        //HMI.message("Please keep in mind that the rooms will be the same for every attempt you'll do, some items and monsters might not though.");
        //HMI.message("We hope you enjoy, and good luck !");
		Game.start(new TutorialQuest());
	}
}
