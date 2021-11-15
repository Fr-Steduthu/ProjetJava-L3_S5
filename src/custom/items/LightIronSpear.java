package custom.items;

import core.character.Character;
import core.items.Equipment;

public class LightIronSpear extends Equipment {

	private static final long serialVersionUID = 4372661349974790950L;

	public LightIronSpear() {
		super("Light iron spear");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onEquip(Character target) {
		target.setDammage(target.getDammage() + 2.0f);
	}

	@Override
	public void onUnequip(Character target) {
		target.setDammage(target.getDammage() - 2.0f);

	}

}
