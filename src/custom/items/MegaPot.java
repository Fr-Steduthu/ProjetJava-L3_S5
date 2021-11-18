package custom.items;

import core.items.Item;
import core.character.Character;

public class MegaPot extends Item {

	private static final long serialVersionUID = -1220819768902987370L;

	public MegaPot() {
		super("Mega-pot");
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onUse(Object target) {
		if(target instanceof Character) {
			((Character) target).heal(10.0f);
		}
	}

}
