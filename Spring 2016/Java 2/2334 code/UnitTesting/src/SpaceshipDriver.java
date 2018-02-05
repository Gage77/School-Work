/**
 * 
 */

public class SpaceshipDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Start with a new stationary spaceship.
		SpaceshipModel ship = new SpaceshipModel(0, SpeedUnits.METERSPERSECOND);
		
		// Give it a series of new speed commands, one for each second.
		ship.updateSpeed(10, SpeedUnits.METERSPERSECOND);
		ship.updateSpeed(20, SpeedUnits.MILESPERHOUR);
		ship.updateSpeed(30, SpeedUnits.METERSPERSECOND);
		ship.updateSpeed(40, SpeedUnits.MILESPERHOUR);
		ship.updateSpeed(50, SpeedUnits.METERSPERSECOND);
		ship.updateSpeed(60, SpeedUnits.MILESPERHOUR);
		ship.updateSpeed(70, SpeedUnits.METERSPERSECOND);
		ship.updateSpeed(80, SpeedUnits.MILESPERHOUR);
		ship.updateSpeed(90, SpeedUnits.METERSPERSECOND);
		ship.updateSpeed(100, SpeedUnits.MILESPERHOUR);
		ship.updateSpeed(110, SpeedUnits.METERSPERSECOND);
		ship.updateSpeed(120, SpeedUnits.MILESPERHOUR);
		ship.updateSpeed(130, SpeedUnits.METERSPERSECOND);

		
		// Calculate and print the distance the spaceship will have traveled by the end of the commanded flight.
		System.out.println("Distance traveled is calculated to be " + ship.calculateDistanceTraveled());
		
		// Calculate and print the maximum acceleration the spaceship will encounter during its commanded flight.
		System.out.println("Maximum acceleration encountered will be " + ship.calculateMaxAcceleration());
		
		// Calculate and print the minimum acceleration the spaceship will encounter during its commanded flight.
		System.out.println("Minimum acceleration encountered will be " + ship.calculateMinAcceleration());
	}

}
