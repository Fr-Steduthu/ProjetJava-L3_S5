package core.character;


public class NPC extends Character{

	private static final long serialVersionUID = 1134079016999922837L;

    public NPC(String name, int inventoryCapacity, String dialogue) {
        super(name, 20f, 0.0f, inventoryCapacity);
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
