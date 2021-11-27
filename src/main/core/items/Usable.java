package core.items;

public class Usable extends Item {

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
	protected boolean onUse(Object target) {
		// TODO Auto-generated method stub
		return false;
	}

}
