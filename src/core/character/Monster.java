package core.character;

import core.items.Item;
import core.places.Place;

public class Monster extends Character implements Lootable {

    public Monster(String name, float maxHP, int inventoryCapacity, float attackDamage) {
        super(name, maxHP, 0.0f, inventoryCapacity);
        this.attackDammage = attackDamage;
    }
    
    public void onDeath() {
        Item[] items = this.getLoot();
        Place location = this.getLocation();
        for (Item i : items) {
            location.addItem(i);
        }
    }
    
    @Override
    public Item[] getLoot() {
        return this.getInventory();
    }
}
