package core.places;

import java.io.Serializable;

//import javax.annotation.*;

public final class Exit implements Serializable{

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
	
	public final Place[] getRooms() {
		Place[] r = new Place[2];
		r[0] = this.connectedRooms[0];
		r[1] = this.connectedRooms[1];
		return r;
	}
	
	public final void addTo(Place p) {
		p.addExit(this);
	}
	
	public final void removeFrom(Place p) {
		p.removeExit(this);
	}
	
	public String getRegexOmmiting(Place p) {
		String regex = null;
		//TODO renvoie le regex de la salle autre que p passée en argument (exemple :
		//Salle : la muerte : "[l|L][a|A] [m|M][u|U][e|E][r|R][t|T][e|E]"
		return regex;
	}

}
