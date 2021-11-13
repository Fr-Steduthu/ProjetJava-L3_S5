package core;

import core.places.*;
import core.quests.Quest;
import core.character.*;

public class Main {
	
	
	public static void main(String[] args) {
		Player p = new Player("Dehaka");
		Place origin = new Place("Entrance hall");
		Place outside = new Place("The outside");
	
		Exit[] doors = {new Exit(origin, outside)};
		
		origin.setExits(doors);
		outside.setExits(doors);
		
		Place[] dungeon = {origin, outside};
		
		Quest game = new Quest(origin, dungeon, "Corridor", outside);
		
		Game.start(p, game);
	}
}
