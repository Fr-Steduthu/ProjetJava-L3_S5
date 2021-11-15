package custom.characters.monsters;

import core.character.Monster;

public class WarhammerZombie extends Monster {

	private static final long serialVersionUID = 1885438475971672982L;

	public WarhammerZombie() {
        super("Healthy Zombie", 5.0f, 1.5f, 1);
        this.isLootable = true;
        // TODO si la flesh reste : this.inventory.add()
    }
    
    @Override
    public void heal(float amount) {
        this.hurt(amount);
    }
    
    @Override
    public final void attack(core.character.Character target){
	target.hurt((int) this.attackDammage);
    }
}
