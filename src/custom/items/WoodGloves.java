package custom.items;

import core.items.DamageEnhancer;

public class WoodGloves extends DamageEnhancer {

	private static final long serialVersionUID = -580192015404933315L;

	public WoodGloves() {
        super("Wood Gloves", 1.5);
    }

    @Override
    public String look() {
        return "Some wood gloves. Using them gives you 1.5 additional damage points.";
    }
}
