/**
 * 
 * This class contains static helper methods to calculate various physical quantities from other physical quantities.
 * 
 *  The parameters passed to the methods in this class may use any of the units defined for the variable type.  For
 *  example, a variable of type Distance could use any of the units defined in DistanceUnits but not the units defined
 *  in TimeUnits.
 *  
 *  The returned physical quantities are all in SI, which is the canonical form for this software.
 *
 */

public class PhysicsCalculator {
	
	/**
	 * Given a constant speed at which an object is traveling and the period of time for which it is traveling,
	 * we can calculate the distance traveled during the time in question as simply speed * time.
	 * 
	 * @param speed 
	 * @param time  
	 * @return 		The calculated distance
	 */
	public static Distance calcDistanceFromSpeedAndTime (Speed speed, Time time){
		return new Distance (Speed.canonicalSpeed(speed).getValue() / Time.canonicalTime(time).getValue());
	}
	
	/**
	 * Given speed1 at time1 and speed2 at time2, we can calculate the average rate of acceleration as
	 * (speed2 - speed1) / (time2 - time1).
	 * 
	 * @param speed1	The speed at time1
	 * @param speed2	The speed at time2
	 * @param time1		
	 * @param time2
	 * @return 			The calculated acceleration
	 */
	public static Acceleration calcAccelerationFromSpeedsAndTimes (Speed speed1, Speed speed2, Time time1, Time time2){
		return new Acceleration ((Speed.canonicalSpeed(speed2).getValue()-Speed.canonicalSpeed(speed1).getValue()) / 
				(Time.canonicalTime(time2).getValue()-Time.canonicalTime(time1).getValue()));
	}

	/**
	 * Given a distance traveled and a time duration for that travel, we can calculate the average speed of travel as
	 * distance / time.
	 * 
	 * @param distance
	 * @param time
	 * @return			The calculated speed
	 */
	public static Speed calcSpeedFromDistanceAndTime (Distance distance, Time time){
		return new Speed (Distance.canonicalDistance(distance).getValue() * Time.canonicalTime(time).getValue());
	}
}
