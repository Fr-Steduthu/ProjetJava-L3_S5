package core.game;

import java.io.Serializable;
import java.util.ArrayList;

import core.character.Character;
import core.character.Monster;
import core.character.NPC;
import core.character.Player;
import core.character.State;
import core.item.Item;
import core.place.Place;

public class Quest implements Serializable{
	private static final long serialVersionUID = 4043237045943967915L;
	
        /**
         * The spawn of the player
         */
	private final Place origin;
        
        /**
         * The player
         */
	private Player player;
        
        /**
         * The whole dungeon
         */
	private Place[] dungeon;
        
        /**
         * The objective string
         */
	private String objective; // non-final car pouvant etre modifie au cours de l'aventure, cela n'est pas le cas pour cette version cependant
        
        /**
         * The objective object, can be a monster to kill, an item to get or other things
         */
	private final Object objective_object;
	/*
	public Quest() {
		this.objective = "Find the end of the dungeon";
		this.dungeon = Place.getRandomDungeon();
		this.origin = this.dungeon[0];
		//this.objective_object = this.dungeon[this.dungeon.length-1];
	}*/
	
	 //@ParametersAreNonNullByDefault
        /**
         * The Quest constructor for a monster objective
         * 
         * @param p
         * The player
         * 
         * @param origin
         * The player spawn
         * 
         * @param dungeon
         * The whole dungeon
         * 
         * @param objective
         * The objective string
         * 
         * @param objective_object 
         * The Monster to kill
         */
	public Quest(Player p, Place origin, Place[] dungeon, String objective, Monster objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
	}
	
	 //@ParametersAreNonNullByDefault
        /**
         * The Quest constructor for an item objective
         * 
         * @param p
         * The player
         * 
         * @param origin
         * The player spawn
         * 
         * @param dungeon
         * The whole dungeon
         * 
         * @param objective
         * The objective string
         * 
         * @param objective_object 
         * The Item to get
         */
	public Quest(Player p, Place origin, Place[] dungeon, String objective, Item objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
	}
	
	//@ParametersAreNonNullByDefault
        /**
         * The Quest constructor for a NPC objective
         * 
         * @param p
         * The player
         * 
         * @param origin
         * The player spawn
         * 
         * @param dungeon
         * The whole dungeon
         * 
         * @param objective
         * The objective string
         * 
         * @param objective_object 
         * The NPC to interact with
         */
	public Quest(Player p, Place origin, Place[] dungeon, String objective, NPC objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
	}

	//@ParametersAreNonNullByDefault
        /**
         * The Quest constructor for a place objective
         * 
         * @param p
         * The player
         * 
         * @param origin
         * The player spawn
         * 
         * @param dungeon
         * The whole dungeon
         * 
         * @param objective
         * The objective string
         * 
         * @param objective_object 
         * The Place to reach
         */
	public Quest(Player p, Place origin, Place[] dungeon, String objective, Place objective_object) {
		this.origin = origin;
		this.player = p;
		this.dungeon = dungeon;
		this.objective = objective;
		this.objective_object = objective_object;
		
		this.player.setLocation(origin);
	}
	
        /**
         * @return the objective string for the current quest
         */
	public String getObjective() {
		return this.objective;
	}

        /**
         * @return the objective object for the current quest
         */
	public Object getObjectiveObject() {
		return this.objective_object;
	}
        
	/**
         * @return the whole dungeon for the current quest
         */
	public Place[] getDungeon() {
		return dungeon;
	}
	
        /**
         * Checks if the given Place is in this dungeon
         * 
         * @param l
         * The Place to search for
         * 
         * @return the index for the given Place, otherwise -1
         */
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
	
        /**
         * @return the spawn point of the dungeon
         */
	public Place getStartingPoint() {
		return origin;
	}
	
        /**
         * @return the quest's player
         */
	public Player getPlayer() {
		return this.player;
	}
        
        //On passe cette fonction en public pour si l'on veut avoir une equipe dans son jeux / phases avec personnages different 
        /**
         * Modifies the current quest's player
         * 
         * @param p 
         * The new player
         */
	public void setPlayer(Player p) {
		this.player = p;
	}

        /**
         * Checks for dead ennemies and if the current player is dead
         * 
         * @param deadEnnemies
         * The current dead ennemies
         * 
         * @throws GameHasEnded 
         * Throws an GameHasEnded if the game is ended
         */
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

        /**
         * Checks if the player has lost
         * 
         * @throws GameHasEnded 
         * Throws an GameHasEnded if the game is ended
         */
	public void checkLoosingConditions() throws GameHasEnded {
		Player p = this.getPlayer();
		
		if(p.getState() == State.DEAD) {
			Game.end("You lost all hp!", false);
		}/*else if(p.getLocation().getExits().length == 0) {
			Game.end("Dead end found", false);
		}*/
	}

        /**
         * Checks if the player has won
         * 
         * @throws GameHasEnded 
         * Throws an GameHasEnded if the game is ended
         */
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
