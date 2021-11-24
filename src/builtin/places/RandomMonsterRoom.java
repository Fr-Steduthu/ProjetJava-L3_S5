package builtin.places;

import core.character.Monster;
import core.places.Place;
import core.places.RandomlyPlacedMobs;

public class RandomMonsterRoom extends Place implements RandomlyPlacedMobs{

	private static final long serialVersionUID = 8668775207472225271L;

	public RandomMonsterRoom(String name) {
		super(name);
	}

	@Override
	public Monster[] getMonsterList() {
		// TODO Auto-generated method stub
		return null;
	}

}
