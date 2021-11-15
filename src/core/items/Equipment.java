package core.items;

public abstract class Equipment extends Item {

	private static final long serialVersionUID = 739031092041156358L;

	public Equipment(String name) {
		super(name);
		this.takable = true;
		this.isPickupable = true;
	}

	@Override
	public abstract String look();

	@Override
	protected void onUse(Object target) {
		this.onEquip(target);
	}
	
	protected abstract void onEquip(Object target);
	
	protected abstract void onDeEquip();

}
