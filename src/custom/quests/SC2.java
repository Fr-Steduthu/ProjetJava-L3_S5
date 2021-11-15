package custom.quests;

import core.Quest;
import core.character.Monster;
import core.places.Place;
import custom.characters.Infestator;

public class SC2 extends Quest {

	private static final long serialVersionUID = 6466432659359348694L;

	private static Place origin;
	private static Place[] dungeon;
	private static Monster objective_object;
	
	static{
		SC2.origin = new Place("Korhal");
		SC2.dungeon = new Place[6];
		SC2.dungeon[0] = origin;
		SC2.dungeon[1] = new Place("Umoja");
		SC2.dungeon[2] = new Place("Kaldir");
		SC2.dungeon[3] = new Place("Char");
		SC2.dungeon[4] = new Place("Tarsonis");
		SC2.dungeon[5] = new Place("Kelmoria");
		
		SC2.objective_object = new Infestator();
	}
	
	public SC2() {
		super(SC2.origin, SC2.dungeon, "Kill the Infestator", SC2.objective_object);
	}

}
