package builtin.items;

import core.items.DamageEnhancer;

public class IronGloves extends DamageEnhancer {
	private static final long serialVersionUID = 4278020827991155988L;

	public IronGloves() {
		super("Iron gloves", 3.0);
	}

	@Override
	public String look() {
		return "Iron gloves. When equipped, it augments your damage by 3 points.";
	}
}
