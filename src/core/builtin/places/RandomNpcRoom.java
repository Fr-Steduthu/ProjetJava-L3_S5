package core.builtin.places;

import core.character.NPC;
import core.place.Place;
import core.place.RandomlyPlacedNpcs;

public class RandomNpcRoom extends Place implements RandomlyPlacedNpcs {

	private static final long serialVersionUID = -6304241378807195612L;

	public RandomNpcRoom(String name) {
		super(name);
	}

	@Override
	public NPC[] getNpcList() {
		// TODO Auto-generated method stub
		return null;
	}

}
