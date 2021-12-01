package core.builtin.places;

import core.builtin.items.WoodGloves;
import core.place.Place;

public class WarriorRoom extends Place {

	private static final long serialVersionUID = -8940068725138687084L;

	public WarriorRoom() {
		super("Entrance");
		this.addItem(new WoodGloves());
	}

}
