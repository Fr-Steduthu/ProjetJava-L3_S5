package core.items;

public abstract class Equipment extends Item {

	private static final long serialVersionUID = 739031092041156358L;

	public Equipment(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onUse() {
		this.onEquip();
	}
	
	protected abstract void onEquip();
	
	protected abstract void onDeEquip();

}
