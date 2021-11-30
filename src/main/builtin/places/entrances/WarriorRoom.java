package main.builtin.places.entrances;

import main.builtin.items.WoodGloves;
import main.core.places.Place;

public class WarriorRoom extends Place {

	private static final long serialVersionUID = -8940068725138687084L;

	public WarriorRoom() {
		super("Entrance");
		this.addItem(new WoodGloves());
	}

}
