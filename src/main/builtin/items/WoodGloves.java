package main.builtin.items;

import main.core.items.DamageEnhancer;

public class WoodGloves extends DamageEnhancer {

	private static final long serialVersionUID = -580192015404933315L;

	public WoodGloves() {
            super("Wood Gloves", 1.5);
        }

    @Override
    public String look() {
        return "Wood gloves. When equipped, it augments your damage by 1.5 points.";
    }
}
