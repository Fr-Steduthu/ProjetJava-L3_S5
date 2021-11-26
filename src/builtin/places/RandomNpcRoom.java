package builtin.places;

import core.character.NPC;
import core.places.Place;
import core.places.RandomlyPlacedNpcs;

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
