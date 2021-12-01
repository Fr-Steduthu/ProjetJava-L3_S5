package core.builtin.characters.monsters;

import core.builtin.items.Flesh;
import core.character.Character;
import core.character.Monster;

public class WarhammerZombie extends Monster {

	private static final long serialVersionUID = 1885438475971672982L;

	public WarhammerZombie() {
        super("Healthy Zombie", 5.0, 1.5, 1);
        this.isLootable = true;
        this.inventory.addItem(new Flesh());
    }
    
    @Override
    public void heal(double amount) {
        this.hurt(amount);
    }
    
    @Override
    public final void attack(Character target){
	target.hurt((int) this.attackDamage);
    }
}
