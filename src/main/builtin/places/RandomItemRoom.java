package main.builtin.places;

import main.builtin.items.Flesh;
import main.builtin.items.SmallPot;
import main.builtin.items.TutoStone;
import main.builtin.items.WoodGloves;
import main.core.items.Item;
import main.core.places.Place;
import main.core.places.RandomlyPlacedItem;

public class RandomItemRoom extends Place implements RandomlyPlacedItem{

	private static final long serialVersionUID = 5901191614150277973L;

	public RandomItemRoom(String name) {
		super(name);
	}

	@Override
	public Item[] getItemList() {
		Item[] retVal = {new Flesh(), new SmallPot(), new TutoStone(), new WoodGloves()};
		return retVal;
	}

}
