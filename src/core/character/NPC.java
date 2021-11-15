package core.character;


public class NPC extends Character{

	private static final long serialVersionUID = 1134079016999922837L;

    public NPC(String name, String dialogue, int inventoryCapacity, int equipment_capacity) {
        super(name, 20f, 0.0f, inventoryCapacity, equipment_capacity);
        this.dialogue = dialogue;
        this.isLootable = true;
        this.isAbleToSpeak = true;
    }
    
    public NPC(String name, String dialogue, int inventoryCapacity) {
        super(name, 20f, 0.0f, inventoryCapacity, 0);
        this.dialogue = dialogue;
        this.isLootable = true;
        this.isAbleToSpeak = true;
    }
    
    @Override
    public void interact() {
        this.speak();
        this.getLocation().removeNpc(this);
    }   
}
