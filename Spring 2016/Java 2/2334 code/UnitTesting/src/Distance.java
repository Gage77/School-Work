
public class Distance {
	private DistanceUnits units;
	private double value;
	
	/**
	 * No-arg constructor defaults to 0 m.
	 */
	public Distance(){
		this(0, DistanceUnits.METERS);
	}
	
	/**
	 * Basic constructor takes only the value of the Distance. Very 
	 * useful as a convenience constructor as long as the units are meters.
	 * 
	 * @param value The value of the Distance.
	 */
	public Distance(double value){
		this(value, DistanceUnits.METERS);
	}
	
	/**
	 * 
	 * @param value The value of the distance.
	 * @param units The type of units the value is represented in.
	 */
	public Distance(double value, DistanceUnits units){
		this.units = units;
		this.value = value;
	}
	
	/**
	 * 
	 * @return Distance value as a double.
	 */
	public double getValue(){
		return this.value;
	}
	
	/**
	 * 
	 * @return Distance units as an enum.
	 */
	public DistanceUnits getUnits(){
		return this.units;
	}
	
	/*
	 * 
	 */
	public String toString(){
		return "" + value + " " + units;
	}

	/**
	 * 
	 * @param 	distance using any units in DistanceUnits
	 * @return  distance using the canonical units (meters)
	 */
	public static Distance canonicalDistance(Distance distance) {
		if (distance.getUnits() == DistanceUnits.METERS)
			return distance;
		else if (distance.getUnits() == DistanceUnits.FEET)
			return convertFeetToMeters(distance);
		return null;
	}

	public static Distance convertFeetToMeters(Distance distance) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
