package core.character;

public class Monster extends Character{

	private static final long serialVersionUID = -5488094578355543197L;

	public Monster(String name, float maxHP, float attackDamage, int inventoryCapacity, int equipment_capacity) {
        super(name, maxHP, 0.0f, inventoryCapacity, equipment_capacity);
        this.attackDammage = attackDamage;
    }
	
	public Monster(String name, float maxHP, float attackDamage, int inventoryCapacity) {
        super(name, maxHP, 0.0f, inventoryCapacity, 0);
        this.attackDammage = attackDamage;
    }

	@Override
	public void interact() {
		this.inspect();
	}
}
