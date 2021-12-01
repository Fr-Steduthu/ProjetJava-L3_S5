package core.place;

import java.io.Serializable;

import core.game.Quest;

//import javax.annotation.*;

public class Exit implements Serializable{

	private static final long serialVersionUID = 5674585445142853986L;
	
        /**
         * The connected rooms to the current exit
         */
	protected Place[] connectedRooms = new Place[2];

        /**
         * If the exit is open or not
         */
	protected boolean isOpen = true;

	//@ParametersAreNonnullByDefault
        /**
         * Default exit constructor
         * 
         * @param roomA
         * A room
         * 
         * @param roomB
         * Another room
         * 
         * @throws IllegalArgumentException 
         * Throws an IllegalArgumentException if the rooms are the same
         */
	public Exit(Place roomA, Place roomB) throws IllegalArgumentException{
		if(roomA == roomB) {
			throw new IllegalArgumentException("Rooms cannot be the same");
		}else {
			this.connectedRooms[0] = roomA;
			this.connectedRooms[1] = roomB;
			this.addTo(roomA);
			this.addTo(roomB);
		}
	}
	
        /**
         * @return an array of connected rooms
         */
	public final Place[] getRooms() {
		Place[] r = new Place[2];
		r[0] = this.connectedRooms[0];
		r[1] = this.connectedRooms[1];
		return r;
	}
	
        /**
         * Returns the other room connected to the exit depending on the given place
         * 
         * @param p
         * The place we do not want
         * 
         * @return the opposite room to the given one
         */
	public final Place getRoomOmmiting(Place p) {
		if(p == this.connectedRooms[0]) {
			return this.connectedRooms[1];
		}else {
			return this.connectedRooms[0];
		}
	}
	
        /**
         * Adds a place to the exit
         * 
         * @param p 
         * The place to add
         */
	public final void addTo(Place p) {
		p.addExit(this);
	}
	
        /**
         * Removes a place from the exit
         * 
         * @param p 
         * The place to remove
         */
	public final void removeFrom(Place p) {
		p.removeExit(this);
	}
	
        /**
         * Returns if the exit is open or not
         * 
         * @param q
         * The current quest 
         * 
         * @return if the exit is open or not
         */
	public boolean canPassThrough(Quest q) {
		return this.isOpen;
	}
	
        /**
         * Opens the exit
         */
	public void open() {
		this.isOpen= true;
	}
	
        /**
         * Closes the exit
         */
	public void close() {
		this.isOpen = false;
	}
}
