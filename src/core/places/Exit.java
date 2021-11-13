package core.places;

//import javax.annotation.*;

public class Exit{
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
	
	public String[] getRooms() {
		String[] r = new String[2];
		r[0] = this.connectedRooms[0].getName();
		r[1] = this.connectedRooms[1].getName();
		return r;
	}

}
