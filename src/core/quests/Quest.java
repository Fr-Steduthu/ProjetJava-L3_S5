package core.quests;

import java.io.Serializable;

import core.places.Place;

public class Quest implements Serializable{
	private static final long serialVersionUID = 4043237045943967915L;
	
	private final Place origin;
	private Place[] dungeon;
	private String objective; // non-final car pouvant etre modifie au cours de l'aventure
	private final Place endPoint;
	
	public Quest() {
		this.objective = "Find the end of the dungeon";
		this.dungeon = Place.getRandomDungeon();
		this.origin = this.dungeon[0];
		this.endPoint = this.dungeon[this.dungeon.length-1];
	}
	
	public Quest(Place origin, Place[] dungeon, String objective, Place destination) {
		this.origin = origin;
		this.dungeon = dungeon;
		this.objective = objective;
		this.endPoint = destination;
	}
	
	public String getObjective() {
		return this.objective;
	}

	public Place[] getDungeon() {
		return dungeon;
	}
	
	public int find(Place l) {
		int i = 0;
		for( Place e : this.dungeon) {
			if(e == l) {
				return i;
			}
			i++;
		}
		return i;
	}
	
	public Place getStartingPoint() {
		return origin;
	}

	public Place getEndPoint() {
		return this.endPoint;
	}
}
