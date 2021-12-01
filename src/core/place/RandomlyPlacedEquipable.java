package core.place;

import core.item.Equipment;

public interface RandomlyPlacedEquipable {

	public abstract Equipment[] getEquipableList();
	
	public default Equipment getRandomEquipable(){
		Equipment[] list = this.getEquipableList();
		return (Equipment) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
