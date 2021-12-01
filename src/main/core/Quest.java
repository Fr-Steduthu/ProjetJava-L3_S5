package main.core;

import java.io.Serializable;
import java.util.ArrayList;

import main.core.character.Character;
import main.core.character.Monster;
import main.core.character.NPC;
import main.core.character.Player;
import main.core.character.State;
import main.core.items.Item;
import main.core.places.Place;

public class Quest implements Serializable{
	private static final long serialVersionUID = 4043237045943967915L;
	
	private final Place origin;
	private Player player;
	private Place[] dungeon;
	private String objective; // non-final car pouvant etre modifie au cours de l'aventure, cela n'est pas le cas pour cette version cependant
	private final Object objective_object;
	/*
	public Quest() {
		this.objective = "Find the end of the dungeon";
		this.dungeon = Place.getRandomDungeon();
		this.origin = this.dungeon[0];
		//this.objective_object = this.dungeon[this.dungeon.length-1];
	}*/
	
	 //@ParametersAreNonNullByDefault
	public Quest(Player p, Place origin, Place[] dungeon, String objective, Monster objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
	}
	
	 //@ParametersAreNonNullByDefault
	public Quest(Player p, Place origin, Place[] dungeon, String objective, Item objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
	}
	
	//@ParametersAreNonNullByDefault
	public Quest(Player p, Place origin, Place[] dungeon, String objective, NPC objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
	}

	//@ParametersAreNonNullByDefault
	public Quest(Player p, Place origin, Place[] dungeon, String objective, Place objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
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
		for(Place e : this.dungeon) {
			if(e == l) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public Place getStartingPoint() {
		return origin;
	}
	
	public Player getPlayer() {
		return this.player;
	}
        
        //On passe cette fonction en public pour si l'on veut avoir une equipe dans son jeux / phases avec personnages different 
	public void setPlayer(Player p) {
		this.player = p;
	}

	public void charactersActions(ArrayList<Character> deadEnnemies) throws GameHasEnded {
		Place current = this.getPlayer().getLocation();
        Player p = this.getPlayer();
		boolean is_alive = true;
		
		for(Character c : current.getNpcs()) {
			is_alive = true;
			for(Character dead_c : deadEnnemies) {
				if(dead_c == c) {
					is_alive = false;
					break;
				}
			}
			
			if(c.getHP() <= 0 && !is_alive) {
				c.onDeath(this);
				c.setState(State.DEAD);
				deadEnnemies.add(c);
				continue;
			}
			
			if(c instanceof Monster && (c.getState() != State.STUNNED /*|| c.getState() == State.IMMUNE_STUNNED || c.getState() == State.IMMUNE_ALL*/) && is_alive) {
				c.attack(p);
			}
		}
		        
		if(p.getState() == State.DEAD) {
		    p.onDeath(this);
		    p.setState(State.DEAD);
		    Game.end("You lost", false);
		}
	}

	public void checkLoosingConditions() throws GameHasEnded {
		Player p = this.getPlayer();
		
		if(p.getState() == State.DEAD) {
			Game.end("You lost all hp!", false);
		}else if(p.getLocation().getExits().length == 0) {
			Game.end("Dead end found", false);
		}
	}

	public void checkWinningConditions() throws GameHasEnded {
		Object objective = this.getObjectiveObject();
		
		boolean retVal = false;
		
		if(objective instanceof Place) {
			if(this.getPlayer().getLocation() == objective) {
				retVal = true;
			}
			
		}else if(objective instanceof Monster) {
			if(((Monster) objective).getState() == State.DEAD) {
				retVal = true;
			}
			
		}else if(objective instanceof Character) {
			for(Character c : this.getPlayer().getLocation().getNpcs()) {
				if(c == objective) {
					retVal = true;
				}
			}
		}else if(objective instanceof Item) {//Possession et non utilisation d'un item
			for(Item i : this.getPlayer().getItems()){
				if(i == objective) {
					retVal = true;
				}
			}
		}
		
		if(retVal) {
			Game.end("You have won", retVal);
		}
	}
}
