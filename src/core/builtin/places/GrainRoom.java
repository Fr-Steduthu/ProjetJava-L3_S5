package core.builtin.places;

import core.builtin.characters.npcs.Grain;
import core.place.Place;

public class GrainRoom extends Place {

	private static final long serialVersionUID = 6678858817925190558L;

	public GrainRoom() {
		super("Grain's hideout");
		this.addNpc(new Grain());
	}

}
