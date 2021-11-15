package custom.characters;

import core.character.Monster;

public class WarZombie extends Monster {
    
    public WarZombie() {
        super("Healthy Zombie", 5.0f, 1.5f, 1);
        this.isLootable = true;
        // TODO si la flesh reste : this.inventory.add()
    }
    
    @Override
    public void heal(int amount) {
        this.hurt(amount);
    }
    
    @Override
    public final void attack(core.character.Character target){
	target.hurt((int) this.attackDammage);
    }
}
