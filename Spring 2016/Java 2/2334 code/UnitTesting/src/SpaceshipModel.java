import java.util.ArrayList;

/**
 * 
 * The SpaceshipModel class crudely models a simple spaceship that moves in a straight line.
 * 
 * We can command the ship what speed to take on each second during its journey.
 * 
 * Based on the commands provided, we can calculate values such as the total distance traveled during the journey
 * and the maximum and minimum acceleration experienced by the spaceship.
 * 
 * Note that commands can be provided in a variety of measurement systems, such as SI and US Customary Units.  SI
 * units are the canonical units for this system, so measurements in other systems are converted to SI for use.
 *
 */
public class SpaceshipModel {

	//Class data
	private ArrayList<Speed> speeds;
	
	private final Time timeStep = new Time (1);  // In this model, the speed value is updated every second.

	/**
	 * Default Constructor for ease of use.
	 */
	public SpaceshipModel(){
		this.speeds = new ArrayList<Speed>();
	}

	/**
	 * Constructor which allows a spaceship to be initialized with any desired speed value and measurement units
	 * (within the limits of the variable types) specified separately.
	 * 
	 * @param speedValue The value of the speed of the new Spaceship.
	 * @param speedUnits The units we would like the Spaceship to initialize with.
	 */
	public SpaceshipModel(double speedValue, SpeedUnits speedUnits){
		this.speeds = new ArrayList<Speed>();
		this.speeds.add(new Speed(speedValue, speedUnits));
	}
	
	/**
	 * Constructor which allows a spaceship to be initialized with any desired speed value and measurement units
	 * (within the limits of the variable types) specified as an object of type Speed.
	 * 
	 * @param speed The speed of the new Spaceship.
	 */
	public SpaceshipModel (Speed speed){
		this.speeds = new ArrayList<Speed>();
		this.speeds.add(speed);
	}
	
	
	/**
	 * 
	 * @return The current Speed Object.
	 */
	public Speed getLastSpeedCommand(){
		if (speeds.size() > 0)
			return this.speeds.get(speeds.size()-1);
		else
			return null;
	}
	

	/**
	 * This method updates the list of speed commands with a new speed command with value and units specified individually.
	 * 
	 * @param speedValue 	Numeric value of the new speed.
	 * @param speedUnits	Units for the corresponding speed.
	 */
	public void updateSpeed(double speedValue, SpeedUnits speedUnits){
		this.speeds.add(new Speed(speedValue, speedUnits));
	}
	
	/**
	 * This method updates the list of speed commands with a new speed command with value and units specified as a Speed object.
	 * 
	 * @param speed 	The new speed to add to the list
	 */
	public void updateSpeed(Speed newSpeed){
		this.speeds.add(newSpeed);
	}
	

	/**
	 * This methods finds the maximum acceleration by looking through the list of speed commands.
	 */
	public Acceleration calculateMaxAcceleration(){
		Acceleration maxAcceleration = new Acceleration (0.0);
		Speed currentSpeed = speeds.get(0);
		
		for (Speed speed: speeds){
			Acceleration currentAcceleration = PhysicsCalculator.calcAccelerationFromSpeedsAndTimes(currentSpeed, speed, new Time(0), timeStep);
			if (currentAcceleration.getValue() > maxAcceleration.getValue())
				maxAcceleration.setValue(currentAcceleration.getValue());
		}
		
		return maxAcceleration;
	}
	
	public Acceleration calculateMinAcceleration() {
		Acceleration minAcceleration = new Acceleration (0.0);
		Speed currentSpeed = speeds.get(0);
		
		for (Speed speed: speeds){
			Acceleration currentAcceleration = PhysicsCalculator.calcAccelerationFromSpeedsAndTimes(currentSpeed, speed, new Time(0), timeStep);
			if (currentAcceleration.getValue() < minAcceleration.getValue())
				minAcceleration.setValue(currentAcceleration.getValue());
		}
		
		return minAcceleration;
	}
	
	/**
	 * This method calculates the distance traveled during the trip based on all of the speed commands.
	 */
	public Distance calculateDistanceTraveled(){
		double distanceValue = 0.0;
		
		for (Speed speed : speeds)
			distanceValue += PhysicsCalculator.calcDistanceFromSpeedAndTime(speed, timeStep).getValue();
		
		return new Distance(distanceValue);
	}


}
