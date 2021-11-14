package core.character;


public interface AbleToSpeak extends Interactable {
	@Override
    public default void interact(){
        this.speak();
    }
    
    public abstract void speak();
}
