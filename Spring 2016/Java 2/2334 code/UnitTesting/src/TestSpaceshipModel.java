import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * 
 * @author CS 2334 LAB
 * 
 * This class is the class where all of the tests are contained. Some of the 
 * tests are left incomplete intentionally. By the end of the lab assignment, 
 * all of the tests should pass. 
 *
 */
public class TestSpaceshipModel{

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Code executed before the first test method
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Code executed before each test
	}


	/**
	 * Test to make sure the list of speed commands is not null after updating.
	 */
	@Test
	public void testUpdateIsNotNull(){
		SpaceshipModel model = new SpaceshipModel();
		Assert.assertTrue(model.getLastSpeedCommand() == null);   // There should be no last speed command.
		model.updateSpeed(5, SpeedUnits.METERSPERSECOND);
		Assert.assertFalse(model.getLastSpeedCommand() == null);  // There should be a last speed command.		
	}

	
    // TODO  Put other tests here.

	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// Code executed after each test
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Code executed after the last test method
	}
}
