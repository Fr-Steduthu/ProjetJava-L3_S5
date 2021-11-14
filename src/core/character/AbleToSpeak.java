package core.character;


public interface AbleToSpeak extends Interactable {
    public default void interact(){
        this.speak();
    }
    public abstract void speak();
}
