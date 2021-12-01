package core.builtin.places;

import core.builtin.items.IronGloves;
import core.place.Place;

public class ItemRoom extends Place {

	private static final long serialVersionUID = -2174257839120417601L;

	public ItemRoom() {
		super("Treasure room");
		this.addItem(new IronGloves());
	}

}
