package builtin.places.entrances;

import builtin.items.SmallPot;
import core.places.Place;

public class HealerRoom extends Place {

	private static final long serialVersionUID = 6299385520579155085L;

	public HealerRoom() {
		super("Entrance");
		this.addItem(new SmallPot());
	}

}
