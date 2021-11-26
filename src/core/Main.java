package core;

import core.character.Player;
import core.places.*;
import custom.quests.SC2;
import java.io.IOException;

import builtin.TutorialQuest;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		Place origin = new Place("Entrance hall");
		Place outside = new Place("The outside");
	
		Exit[] doors = {new Exit(origin, outside)};
		
		origin.setExits(doors);
		outside.setExits(doors);
		
		Place[] dungeon = {origin, outside};
		
		@SuppressWarnings("unused")
		Quest game = new Quest(new Player("You"), origin, dungeon, "Corridor", outside);
		@SuppressWarnings("unused")
		Quest sc2 = new SC2();
		
		//Game.start(new TutorialQuest());
	}
}
