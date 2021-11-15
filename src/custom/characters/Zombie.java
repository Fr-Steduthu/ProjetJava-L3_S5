package custom.characters;

import core.character.Monster;
import core.character.Character;

public class Zombie extends Monster {

    public Zombie() {
        super("Zombie", 5.0f, 1.5f, 1);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
    
    @Override
    public final void attack(Character target){
	target.hurt((int) this.attackDammage);
    }
}
