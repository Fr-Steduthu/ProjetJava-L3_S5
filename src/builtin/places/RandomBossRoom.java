package builtin.places;

import builtin.characters.monsters.Sentinel;
import builtin.characters.monsters.WarhammerZombie;
import core.character.Monster;
import core.places.Place;
import core.places.RandomlyPlacedMobs;

public class RandomBossRoom extends Place implements RandomlyPlacedMobs {

	private static final long serialVersionUID = -8316330490139428100L;

	public RandomBossRoom(){
		super("Hell's boiler room");
		this.addNpc(this.getRandomMonster());
	}

	@Override
	public Monster[] getMonsterList() {
		Monster[] out = {new WarhammerZombie(), new Sentinel()};
		return out;
	}

}
