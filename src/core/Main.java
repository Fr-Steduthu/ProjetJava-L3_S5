package core;

import core.places.*;
import custom.quests.SC2;

public class Main {
	
	
	public static void main(String[] args) {
		Place origin = new Place("Entrance hall");
		Place outside = new Place("The outside");
	
		Exit[] doors = {new Exit(origin, outside)};
		
		origin.setExits(doors);
		outside.setExits(doors);
		
		Place[] dungeon = {origin, outside};
		
		@SuppressWarnings("unused")
		Quest<Place> game = new Quest<Place>(origin, dungeon, "Corridor", outside);
		Quest<?> sc2 = new SC2();
		
		Game.start(sc2);
	}
}
