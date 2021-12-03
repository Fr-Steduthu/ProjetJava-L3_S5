package core;

import java.io.File;
import java.io.IOException;

import core.builtin.TutorialQuest;
import core.game.Game;
import core.game.Quest;
import core.hmi.HMI;
import core.hmi.Regex;
import custom.quests.SC2;

public class Main {
	
	

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		HMI.message("Welcome to our game.");
		
		File[] files;
		File f = new File("./saves/");
		
		files = f.listFiles();
		if(files == null) {
			files = new File[0];
		}
		Quest[] availableQuests = new Quest[files.length + 1];

		availableQuests[0] = new TutorialQuest();
		//availableQuests[1] = new SC2();
		//HMI.message(availableQuests[0].getObjective());
		//HMI.message(availableQuests[1].getObjective());
		
		for (int i = 1; i < availableQuests.length; i++) {
			availableQuests[i] = Game.load (files[i-1]);
			//HMI.message(availableQuests[i].getObjective());			
		}
		
		Quest selectedQuest = null;
		
        HMI.message("Quests available :");
        
        for (Quest q : availableQuests) {
            HMI.message(q.getObjective());
        }
        
        String target = HMI.read("Choose a quest to play.",Regex.regex((Quest[])availableQuests)+"|BACK");

        for (Quest quest : availableQuests) {
            if (quest.getObjective().toLowerCase().equals(target.toLowerCase())) {
                    selectedQuest =  quest;
            }
        }    
		
        //HMI.message("You will experience a DEMOnstration for now of what we've done");
        //HMI.message("Please keep in mind that the rooms will be the same for every attempt you'll do, some items and monsters might not though.");
        //HMI.message("We hope you enjoy, and good luck !");
		Game.start(selectedQuest);
	}
}
