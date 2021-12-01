package core.place;

import core.item.Item;

public interface RandomlyPlacedItem {

        /**
         * @return an item array
         */
	public abstract Item[] getItemList();
	
        /**
         * @return a random item based on the item array
         */
	public default Item getRandomItem(){
		Item[] list = this.getItemList();
		return (Item) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
