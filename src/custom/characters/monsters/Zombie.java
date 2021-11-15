package custom.characters.monsters;

import core.character.Monster;

public class Zombie extends Monster {

	private static final long serialVersionUID = -9068088720800578892L;

	public Zombie() {
        super("Zombie", 6.0f, 1.5f, 1);
        this.isLootable = true;
        // TODO si la flesh reste : this.inventory.add()
    }
    
    @Override
    public void heal(float amount) {
        this.hurt(amount);
    }
}
