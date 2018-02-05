
public class Time {
	
	private TimeUnits units;
	private double value;
	
	/**
	 * Basic constructor takes only the value of the Time. 
	 * useful as a convenience constructor as long as the units are seconds.
	 * 
	 * @param value The value of the Time.
	 */
	public Time(double value){
		this(value, TimeUnits.SECONDS);
	}
	
	/**
	 * 
	 * @param value The value of the Time.
	 * @param units The type of units the value is represented in.
	 */
	public Time(double value, TimeUnits units){
		this.units = units;
		this.value = value;
	}
	
	/**
	 * 
	 * @return Time value as a double.
	 */
	public double getValue(){
		return this.value;
	}

	/**
	 * 
	 * @return Time units as an enum.
	 */
	public TimeUnits getUnits(){
		return this.units;
	}
	
	public String toString(){
		return "" + value + " " + units;
	}

	/**
	 * @return Time using canonical units of seconds.
	 */
	public static Time canonicalTime(Time time){
		if (time.units == TimeUnits.SECONDS)
			return time;
		else if (time.units == TimeUnits.HOURS)
			return new Time(time.value/3600);
		else return null;
	}
}
