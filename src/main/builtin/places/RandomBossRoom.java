package main.builtin.places;

import main.builtin.characters.monsters.Sentinel;
import main.builtin.characters.monsters.WarhammerZombie;
import main.core.character.Monster;
import main.core.places.Place;
import main.core.places.RandomlyPlacedMobs;

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
