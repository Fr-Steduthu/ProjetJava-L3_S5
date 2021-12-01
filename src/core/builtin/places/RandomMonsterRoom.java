package core.builtin.places;

import core.builtin.characters.monsters.Blob;
import core.builtin.characters.monsters.Slime;
import core.builtin.characters.monsters.Zombie;
import core.character.Monster;
import core.place.Place;
import core.place.RandomlyPlacedMobs;

public class RandomMonsterRoom extends Place implements RandomlyPlacedMobs{

	private static final long serialVersionUID = 8668775207472225271L;

	public RandomMonsterRoom(String name) {
		super(name);
	}

	@Override
	public Monster[] getMonsterList() {
		Monster retVal[] = {new Zombie(), new Blob(), new Slime()};
		return retVal;
	}

}
