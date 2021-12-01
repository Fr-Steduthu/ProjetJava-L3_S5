package core.builtin.places;

import core.builtin.characters.monsters.Sentinel;
import core.builtin.characters.monsters.WarhammerZombie;
import core.character.Monster;
import core.place.Place;
import core.place.RandomlyPlacedMobs;

public class RandomBossRoom extends Place implements RandomlyPlacedMobs {

	private static final long serialVersionUID = -8316330490139428100L;

	public RandomBossRoom() {
		super("Hell's boiler room");
		this.addNpc(this.getRandomMonster());
	
	}
	public RandomBossRoom(String name){
		super(name);
		this.addNpc(this.getRandomMonster());
	}

	@Override
	public Monster[] getMonsterList() {
		Monster[] out = {new WarhammerZombie(), new Sentinel()};
		return out;
	}

}
