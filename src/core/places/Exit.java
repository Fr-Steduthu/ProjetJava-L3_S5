package core.places;

import java.io.Serializable;

//import javax.annotation.*;

public class Exit implements Serializable{

	private static final long serialVersionUID = 5674585445142853986L;
	
	private Place[] connectedRooms = new Place[2];

	//@ParametersAreNonnullByDefault
	public Exit(Place roomA, Place roomB) throws IllegalArgumentException{
		if(roomA == roomB) {
			throw new IllegalArgumentException("Rooms cannot be the same");
		}else {
			this.connectedRooms[0] = roomA;
			this.connectedRooms[1] = roomB;
		}
	}
	
	public Place[] getRooms() {
		Place[] r = new Place[2];
		r[0] = this.connectedRooms[0];
		r[1] = this.connectedRooms[1];
		return r;
	}

}
