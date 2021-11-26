package builtin.items;

import core.items.DamageEnhancer;

public class ReinforcedGloves extends DamageEnhancer {

	private static final long serialVersionUID = 6304460385174262050L;

	public ReinforcedGloves() {
		super("Reinforced gloves", 5.0);
	}

	@Override
	public String look() {
		return "Reinforced gloves. When equipped, it augments your damage by 5 points.";
	}

}
