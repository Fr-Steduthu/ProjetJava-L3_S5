package core.character;

import core.items.Item;

public interface Lootable {
    public default Item[] getLoot() throws Exception {
    	if(this instanceof Character) {
    		return ((Character)this).getInventory();
    	}else {
    		throw new Exception("Only children of Character can implement Lootable");
    	}
    }
}
