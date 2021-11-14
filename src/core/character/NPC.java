package core.character;


public class NPC extends Character implements AbleToSpeak, Lootable {

	private static final long serialVersionUID = 1134079016999922837L;

    public NPC(String name, int inventoryCapacity, String dialogue) {
        super(name, 20f, 0.0f, inventoryCapacity);
        this.dialogue = dialogue;
    }
    
    @Override
    public void interact() {
        this.speak();
        this.getLocation().removeNpc(this);
    }   
}
