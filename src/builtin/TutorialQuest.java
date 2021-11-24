package builtin;

import builtin.characters.monsters.WarhammerZombie;
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

public class TutorialQuest extends Quest {

	private static final long serialVersionUID = -5861628517543096614L;
	
	private static Place origin;
	private static Place[] dungeon;
	private static Exit[] links; //Pas utile
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
		dungeon[5] = new RandomMonsterRoom("Hell's kitchen");
		dungeon[6] = new RandomItemRoom("Item room");
		dungeon[7] = new RandomNpcRoom("Tea time room");
		dungeon[8] = new RandomBossRoom();
		dungeon[9] = new Place("Aberation's lair");
		
		dungeon[9].addNpc(objective_object);
		
		links = new Exit[10];
		
		links[0] = new Exit(origin, dungeon[1]); // Origine vers Empty room
		links[1] = new Exit(dungeon[1], dungeon[2]); // Empty room vers Grian's room
		links[2] = new Exit(dungeon[0], dungeon[7]); // Origin vers RandomNPCRomm
		links[3] = new Exit(dungeon[7], dungeon[6]); // RandomNPCRoom vers RandomItemRoom
		links[4] = new Exit(dungeon[7], dungeon[5]); // RandomNPCRoom vers RandomMonsterRoom
		links[5] = new Exit(dungeon[5], dungeon[8]); // RandomMonsterRoom vers RandomBossRoom
		links[6] = new Exit(dungeon[8], dungeon[9]); // RandomBossRoom vers BossRoom
		links[6].removeFrom(dungeon[9]); // One way
		links[7] = new Exit(dungeon[8], dungeon[3]); //RandomBossRoom vers SentinelRoom
		links[8] = new Exit(dungeon[3], dungeon[4]); //SentinelRoom vers ItemRoom
	}

	public TutorialQuest() {
		super(origin, dungeon, objective, objective_object);
	}
}
