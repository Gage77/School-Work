
public class Acceleration {

	private AccelerationUnits units;
	private double value;
	
	/**
	 * Basic constructor takes only the value of the acceleration. Very 
	 * useful as a convenience constructor as long as the units are m/s^2.
	 * 
	 * @param value The value of the Acceleration.
	 */
	public Acceleration(double value){
		this(value, AccelerationUnits.METERSPERSECONDPERSECOND);
	}
	
	/**
	 * 
	 * @param value The value of the Acceleration.
	 * @param units The type of units the value is represented in.
	 */
	public Acceleration(double value, AccelerationUnits units){
		this.units = units;
		this.value = value;
	}
	
	/**
	 * 
	 * @return Acceleration value as a double.
	 */
	public double getValue(){
		return this.value;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	/**
	 * 
	 * @return Acceleration units as an enum.
	 */
	public AccelerationUnits getUnits(){
		return this.units;
	}
	
	/*
	 * 
	 */
	public String toString(){
		return "" + value + " " + units;
	}
	
}
