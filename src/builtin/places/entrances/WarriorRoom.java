package builtin.places.entrances;

import core.places.Place;
import custom.items.WoodGloves;

public class WarriorRoom extends Place {

	private static final long serialVersionUID = -8940068725138687084L;

	public WarriorRoom() {
		super("Entrance");
		this.addItem(new WoodGloves());
	}

}
