package core.character;

import core.game.Quest;

public class NPC extends Character{

    private static final long serialVersionUID = 1134079016999922837L;

    public NPC(String name, String dialogue, int inventoryCapacity, int equipment_capacity) {
        super(name, 20.0, 0.0, inventoryCapacity, equipment_capacity);
        this.dialogue = dialogue;
        this.isLootable = true;
        this.isAbleToSpeak = true;
    }
    
    public NPC(String name, String dialogue, int inventoryCapacity) {
        super(name, 20.0, 0.0, inventoryCapacity, 0);
        this.dialogue = dialogue;
        this.isLootable = true;
        this.isAbleToSpeak = true;
    }
    
    @Override
    public void interact(Quest context) {
        this.speak();
        this.getLocation().removeNpc(this);
    }
}
