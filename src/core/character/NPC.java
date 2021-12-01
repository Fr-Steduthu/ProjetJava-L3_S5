package core.character;

import core.game.Quest;

public class NPC extends Character{

    private static final long serialVersionUID = 1134079016999922837L;

    /**
     * The NPC's default constructor
     * 
     * @param name
     * The NPC's name
     * 
     * @param dialogue
     * The NPC's dialogue
     * 
     * @param inventoryCapacity
     * The NPC's inventory capacity
     * 
     * @param equipment_capacity
     * The NPC's equipment capacity
     */
    public NPC(String name, String dialogue, int inventoryCapacity, int equipment_capacity) {
        super(name, 20.0, 0.0, inventoryCapacity, equipment_capacity);
        this.dialogue = dialogue;
        this.isLootable = true;
        this.isAbleToSpeak = true;
    }
    
    
    /**
     * The NPC's reduced constructor, used for NPCs without equipement capacity
     * 
     * @param name
     * The NPC's name
     * 
     * @param dialogue
     * The NPC's dialogue
     * 
     * @param inventoryCapacity
     * The NPC's inventory capacity
     */
    public NPC(String name, String dialogue, int inventoryCapacity) {
        super(name, 20.0, 0.0, inventoryCapacity, 0);
        this.dialogue = dialogue;
        this.isLootable = true;
        this.isAbleToSpeak = true;
    }
    
    /**
     * NPC interaction
     * 
     * See character's interact for more info
     */
    @Override
    public void interact(Quest context) {
        this.speak();
        this.getLocation().removeNpc(this);
    }
}
