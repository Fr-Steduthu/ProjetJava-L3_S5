package builtin;

import builtin.characters.monsters.WarhammerZombie;
import builtin.characters.npcs.Grain;
import builtin.items.SaND;
import builtin.places.EmptyRoom;
import builtin.places.ItemRoom;
import builtin.places.RandomBossRoom;
import builtin.places.RandomItemRoom;
import builtin.places.RandomMonsterRoom;
import builtin.places.RandomNpcRoom;
import builtin.places.SentinelRoom;
import builtin.places.entrances.TutorialRoom;
import core.Quest;
import core.character.Monster;
import core.character.Player;
import core.items.Item;
import core.places.Exit;
import core.places.Place;
import hmi.HMI;

public class TutorialQuest extends Quest {

	private static final long serialVersionUID = -5861628517543096614L;
	
	private static Place origin;
	private static Place[] dungeon;
	private static Exit[] links;
	private static String objective = "Beat the zombie lord";
	private static Monster objective_object = new WarhammerZombie();
	
	static {
		origin = new TutorialRoom();
		dungeon = new Place[10];
		dungeon[0] = origin;
		dungeon[1] = new EmptyRoom();
		dungeon[2] = new Place("Grain's hideout"){

			private static final long serialVersionUID = 5842608014802156452L;

			@Override
			protected void setup() {
				this.addNpc(new Grain() {
					private static final long serialVersionUID = 5067081057941336143L;

					@Override
					public void interact(Quest context) {
						if(this.ask(this.dialogue)) {
							this.getLocation().addItem(new SaND());
							links[7].open();
							HMI.message("Grain drops the sands of life. They fall on the ground.");
							HMI.message("A door has opened somewhere.\n");
						}else {
							this.speak("Aww yOu MEaNie! :(\n");
						}
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
			}
		};
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
		links[7] = new Exit(dungeon[8], dungeon[3]); //RandomBossRoom vers SentinelRoom, ne peut etre ouverte que si le joueur parle a Grain
		links[7].close();
		links[8] = new Exit(dungeon[3], dungeon[4]){
			private static final long serialVersionUID = -7785743492747823627L;

			@Override
			public boolean canPassThrough(Quest q) {
				for(Item e : q.getPlayer().getItems()) {
					if(e instanceof SaND) {
						return true;
					}
				}
				return false;
			}
		};; //SentinelRoom vers ItemRoom, ne peut etre ouvert que si le joueur possede le SaND
	}

	public TutorialQuest() {
		super(new Player("Dummy the dummy"), origin, dungeon, objective, objective_object);
	}
}
