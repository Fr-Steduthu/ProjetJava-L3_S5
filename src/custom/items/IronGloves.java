package custom.items;

import core.character.Character;
import core.items.Equipment;

public class IronGloves extends Equipment {
	private static final long serialVersionUID = 4278020827991155988L;

	public IronGloves() {
		super("Iron gloves");
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onEquip(Character target) {
		target.setDammage(target.getDammage() + 3.0f);
	}

	@Override
	public void onUnequip(Character target) {
		target.setDammage(target.getDammage() - 3.0f);
	}

}
