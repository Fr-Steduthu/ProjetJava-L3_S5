package core.character;

public class Monster extends Character implements Lootable {

	private static final long serialVersionUID = -5488094578355543197L;

	public Monster(String name, float maxHP, int inventoryCapacity, float attackDamage) {
        super(name, maxHP, 0.0f, inventoryCapacity);
        this.attackDammage = attackDamage;
    }
}
