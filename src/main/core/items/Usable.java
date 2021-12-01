package main.core.items;

public abstract class Usable extends Item {

	private static final long serialVersionUID = 8588907239114065167L;

	public Usable(String name) {
		super(name);
		this.isCurrentlyUsable = true;
		this.isUsable = true;
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected abstract boolean onUse(Object target);

}
