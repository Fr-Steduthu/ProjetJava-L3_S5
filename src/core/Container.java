package core;

import java.util.ArrayList;

import core.items.Item;

public class Container extends ArrayList<Item>{

	public Container(int inventoryCapacity) {
		super(inventoryCapacity);
	}

	private static final long serialVersionUID = -3531698694015794450L;

}
