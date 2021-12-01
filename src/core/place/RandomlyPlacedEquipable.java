package core.place;

import core.item.Equipment;

public interface RandomlyPlacedEquipable {

        /**
         * @return an equipment array
         */
	public abstract Equipment[] getEquipableList();
	
        /**
         * @return a random equipment based on the equipment array
         */
	public default Equipment getRandomEquipable(){
		Equipment[] list = this.getEquipableList();
		return (Equipment) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
