package builtin;

import builtin.places.EmptyRoom;
import builtin.places.GrainRoom;
import builtin.places.ItemRoom;
import builtin.places.RandomBossRoom;
import builtin.places.RandomItemRoom;
import builtin.places.RandomMonsterRoom;
import builtin.places.RandomNpcRoom;
import builtin.places.SentinelRoom;
import builtin.places.entrances.TutorialRoom;
import core.Quest;
import core.character.Monster;
import core.places.Exit;
import core.places.Place;
import custom.characters.monsters.WarhammerZombie;

public class TutorialQuest extends Quest {

	private static final long serialVersionUID = -5861628517543096614L;
	
	private static Place origin;
	private static Place[] dungeon;
	private static Exit[] links;
	private static String objective = "Beat the dragon lord";
	private static Monster objective_object = new WarhammerZombie();
	
	static {
		origin = new TutorialRoom();
		dungeon = new Place[10];
		dungeon[0] = origin;
		dungeon[1] = new EmptyRoom();
		dungeon[2] = new GrainRoom();
		dungeon[3] = new SentinelRoom();
		dungeon[4] = new ItemRoom();
		dungeon[5] = new RandomMonsterRoom();
		dungeon[6] = new RandomItemRoom();
		dungeon[7] = new RandomNpcRoom();
		dungeon[8] = new RandomBossRoom();
		dungeon[9] = new Place("Abheration's lair");
		
		dungeon[9].addNpc(objective_object);
	}

	public TutorialQuest() {
		super(origin, dungeon, objective, objective_object);
	}

}
