package builtin.places;

import builtin.characters.npcs.Grain;
import core.places.Place;

public class GrainRoom extends Place {

	private static final long serialVersionUID = 6678858817925190558L;

	public GrainRoom() {
		super("Grain's hideout");
		this.addNpc(new Grain());
	}

}
