package custom.items;

import core.items.Equipment;
import core.character.Character;

public class WoodGloves extends Equipment {

	private static final long serialVersionUID = -580192015404933315L;

	public WoodGloves() {
        super("Wood Gloves");
    }

    @Override
    public String look() {
        return "Some wood gloves. Using them gives you 1.5 additional damage points.";
    }

    @Override
    public void onEquip(Character target) {
    	target.setDammage(target.getDammage() + 1.5f);
    }

    @Override
    public void onUnequip(Character target) {
    	target.setDammage(target.getDammage() - 1.5f);
    }
}
