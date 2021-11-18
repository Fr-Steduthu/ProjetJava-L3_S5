package builtin.places;

import core.places.Place;
import custom.characters.npcs.Grain;

public class GrainRoom extends Place {

	private static final long serialVersionUID = 6678858817925190558L;

	public GrainRoom() {
		super("Grain's hideout");
		this.addNpc(new Grain());
	}

}
