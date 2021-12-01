package core.item;

public abstract class Usable extends Item {

	private static final long serialVersionUID = 8588907239114065167L;

        /**
         * Default constructor for an usable item
         * 
         * @param name 
         * The item's name
         */
	public Usable(String name) {
		super(name);
		this.isCurrentlyUsable = true;
		this.isUsable = true;
	}

        /**
         * @return a readable string giving info on the item
         */
	@Override
	public abstract String look();

        /**
         * What to do on the item use
         * 
         * @param target
         * The target
         * 
         * @return if the item was used
         */
	@Override
	protected abstract boolean onUse(Object target);

}
