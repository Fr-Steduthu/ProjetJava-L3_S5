package main.builtin.items;

import main.core.items.DamageEnhancer;

public class LightIronSpear extends DamageEnhancer {

	private static final long serialVersionUID = 4372661349974790950L;

	public LightIronSpear() {
		super("Light iron spear", 2.0);
	}

	@Override
	public String look() {
		return "A light iron spear. When equipped, it augments your damage by 2 points.";
	}
}
