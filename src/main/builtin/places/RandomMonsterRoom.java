package main.builtin.places;

import main.builtin.characters.monsters.Blob;
import main.builtin.characters.monsters.Slime;
import main.builtin.characters.monsters.Zombie;
import main.core.character.Monster;
import main.core.places.Place;
import main.core.places.RandomlyPlacedMobs;

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
