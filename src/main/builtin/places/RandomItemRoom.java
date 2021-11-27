package builtin.places;

import builtin.items.Flesh;
import builtin.items.SmallPot;
import builtin.items.TutoStone;
import builtin.items.WoodGloves;
import core.items.Item;
import core.places.Place;
import core.places.RandomlyPlacedItem;

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
