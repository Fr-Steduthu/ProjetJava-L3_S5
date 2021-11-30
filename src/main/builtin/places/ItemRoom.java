package main.builtin.places;

import main.builtin.items.IronGloves;
import main.core.places.Place;

public class ItemRoom extends Place {

	private static final long serialVersionUID = -2174257839120417601L;

	public ItemRoom() {
		super("Treasure room");
		this.addItem(new IronGloves());
	}

}
