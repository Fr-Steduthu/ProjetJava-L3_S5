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
	protected void onUse() {
		this.onEquip();
	}
	
	protected abstract void onEquip();
	
	protected abstract void onDeEquip();

}
