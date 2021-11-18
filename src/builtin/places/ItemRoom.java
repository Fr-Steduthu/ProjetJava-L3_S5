package builtin.places;

import core.places.Place;
import custom.items.IronGloves;

public class ItemRoom extends Place {

	private static final long serialVersionUID = -2174257839120417601L;

	public ItemRoom() {
		super("Treasure room");
		this.addItem(new IronGloves());
	}

}
