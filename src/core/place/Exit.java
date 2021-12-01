package core.place;

import java.io.Serializable;

import core.game.Quest;

//import javax.annotation.*;

public class Exit implements Serializable{

	private static final long serialVersionUID = 5674585445142853986L;
	
	protected Place[] connectedRooms = new Place[2];

	protected boolean isOpen = true;

	//@ParametersAreNonnullByDefault
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
	
	public final Place[] getRooms() {
		Place[] r = new Place[2];
		r[0] = this.connectedRooms[0];
		r[1] = this.connectedRooms[1];
		return r;
	}
	
	public final Place getRoomOmmiting(Place p) {
		if(p == this.connectedRooms[0]) {
			return this.connectedRooms[1];
		}else {
			return this.connectedRooms[0];
		}
	}
	
	public final void addTo(Place p) {
		p.addExit(this);
	}
	
	public final void removeFrom(Place p) {
		p.removeExit(this);
	}
	
	public boolean canPassThrough(Quest q) {
		return this.isOpen;
	}
	
	public void open() {
		this.isOpen= true;
	}
	
	public void close() {
		this.isOpen = false;
	}
}
