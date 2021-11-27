package core.places;

import core.items.Item;

public interface RandomlyPlacedItem {

	public abstract Item[] getItemList();
	
	public default Item getRandomItem(){
		Item[] list = this.getItemList();
		return (Item) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
