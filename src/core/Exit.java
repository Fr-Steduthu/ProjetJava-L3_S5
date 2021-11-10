package core;

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

}
