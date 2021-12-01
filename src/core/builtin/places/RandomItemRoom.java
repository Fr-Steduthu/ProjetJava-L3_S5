package core.builtin.places;

import core.builtin.items.Flesh;
import core.builtin.items.SmallPot;
import core.builtin.items.TutoStone;
import core.builtin.items.WoodGloves;
import core.item.Item;
import core.place.Place;
import core.place.RandomlyPlacedItem;

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
