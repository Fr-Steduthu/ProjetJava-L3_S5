package custom.characters;

import core.character.Monster;

public class Zombie extends Monster {

    public Zombie() {
        super("Zombie", 6.0f, 1.5f, 1);
        this.isLootable = true;
        // TODO si la flesh reste : this.inventory.add()
    }
    
    @Override
    public void heal(int amount) {
        this.hurt(amount);
    }
}
