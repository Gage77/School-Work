
public class Speed {

	private SpeedUnits units;
	private double value;
	
	/**
	 * Basic constructor takes only the value of the Speed. Very 
	 * useful as a convenience constructor as long as the units are m/s.
	 * 
	 * @param value The value of the Speed.
	 */
	public Speed(double value){
		this(value, SpeedUnits.METERSPERSECOND);
	}
	
	/**
	 * 
	 * @param value The value of the speed.
	 * @param units The type of units the value is represented in.
	 */
	public Speed(double value, SpeedUnits units){
		this.units = units;
		this.value = value;
	}
	
	/**
	 * 
	 * @return Speed value as a double.
	 */
	public double getValue(){
		return this.value;
	}
	
	/**
	 * 
	 * @return Speed units as an enum.
	 */
	public SpeedUnits getUnits(){
		return this.units;
	}
	
	public static Speed canonicalSpeed(Speed speed){
		if (speed.getUnits() == SpeedUnits.METERSPERSECOND)
			return speed;
		else if (speed.getUnits() == SpeedUnits.MILESPERHOUR)
			return new Speed (convertMilesPerHourToMetersPerSecond(speed.getValue()));
		return null;
	}
	
	/**Covert from mph to m/s.
	 * 
	 * @param mph The speed value represented in miles per hour.
	 * @return Speed value as a double represented in meters per second.
	 */
	private static double convertMilesPerHourToMetersPerSecond(double mph){
		//divide out the hours 
		double result = mph / 3600;
		//divide out the miles and leave the units as meters per second
		result = result * 1609.34;
		return result;
	}

}
