package builtin.places;

import core.character.Monster;
import core.places.Place;
import core.places.RandomlyPlacedMobs;

public class RandomNpcRoom extends Place implements RandomlyPlacedMobs {

	private static final long serialVersionUID = -6304241378807195612L;

	public RandomNpcRoom(String name) {
		super(name);
	}

	@Override
	public Monster[] getMonsterList() {
		// TODO Auto-generated method stub
		return null;
	}

}
