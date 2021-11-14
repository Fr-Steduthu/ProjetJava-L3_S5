package core.character;

import core.items.Item;
import core.places.Place;

public class NPC extends Character implements AbleToSpeak, Lootable {

    private String dialog;

    public NPC(String name, int inventoryCapacity) {
        super(name, 20f, 0.0f, inventoryCapacity);
    }
    
    public void interact() {
        this.speak();
        this.getLocation().removeNpc(this);
    }
    
    public void onDeath() {
        Item[] items = this.getLoot();
        Place location = this.getLocation();
        for (Item i : items) {
            location.addItem(i);
        }
    }

    @Override
    public void speak() {
        System.out.println(this.dialog);
    }

    @Override
    public Item[] getLoot() {
        return this.getInventory();
    }
    
}
