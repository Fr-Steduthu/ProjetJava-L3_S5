package core;

import java.io.Serializable;

import core.character.Monster;
import core.character.NPC;
import core.character.Player;
import core.items.Item;
import core.places.Place;

public class Quest implements Serializable{
	private static final long serialVersionUID = 4043237045943967915L;
	
	private final Place origin;
	private Player player;
	private Place[] dungeon;
	private String objective; // non-final car pouvant etre modifie au cours de l'aventure
	private final Object objective_object;
	/*
	public Quest() {
		this.objective = "Find the end of the dungeon";
		this.dungeon = Place.getRandomDungeon();
		this.origin = this.dungeon[0];
		//this.objective_object = this.dungeon[this.dungeon.length-1];
	}*/
	
	public Quest(Place origin, Place[] dungeon, String objective, Monster objective_object) {
		this.origin = origin;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
	}
	
	public Quest(Place origin, Place[] dungeon, String objective, Item objective_object) {
		this.origin = origin;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
	}
	
	public Quest(Place origin, Place[] dungeon, String objective, NPC objective_object) {
		this.origin = origin;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
	}

	public Quest(Place origin, Place[] dungeon, String objective, Place objective_object) {
		this.origin = origin;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
	}
	
	public String getObjective() {
		return this.objective;
	}

	public Object getObjectiveObject() {
		return this.objective_object;
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
	
	public Player getPlayer() {
		return this.player;
	}
        
	protected void setPlayer(Player p) {
		this.player = p;
	}
}
