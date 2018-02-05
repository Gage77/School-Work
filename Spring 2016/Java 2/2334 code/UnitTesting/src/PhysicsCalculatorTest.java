import static org.junit.Assert.*;

import org.junit.Test;

public class PhysicsCalculatorTest {

	@Test
	public void testCalcAccelerationFromSpeedsAndTimes() 
	{
		Speed speed1 = new Speed(0);
		Speed speed2 = new Speed(10);
		Time time1 = new Time(0);
		Time time2 = new Time(1);
		Acceleration calculatedAcceleration =
		 PhysicsCalculator.calcAccelerationFromSpeedsAndTimes (speed1, speed2, time1, time2);
		assertEquals(10.0, calculatedAcceleration.getValue(), 0.0);

	}
	
	@Test
	public void testCanonicalDistance()
	{
		Distance distance = new Distance(1.0, DistanceUnits.FEET);
		
		assertEquals(0.3048, Distance.convertFeetToMeters(distance).getValue(), 0.0);
	}

}
