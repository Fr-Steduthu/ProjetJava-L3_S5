package core.character;


public abstract class NPC extends Character implements AbleToSpeak, Lootable {

	private static final long serialVersionUID = 1134079016999922837L;
	
	private String dialog;

    public NPC(String name, int inventoryCapacity) {
        super(name, 20f, 0.0f, inventoryCapacity);
    }
    
    @Override
    public void interact() {
        this.speak();
        this.getLocation().removeNpc(this);
    }

    @Override
    public void speak() {
        System.out.println(this.dialog);
    }
    
}
