import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DatabaseTest 
{

	@Test
	public void testDatabaseInt() 
	{
		
	}

	//@Test
	//public void testParseActor() 
	//{
		//String actor = "Anniston, Jennifer (I)	\"Friends\" (1990) {(1990-01-01)} [Rachel]";
		//Database a = new Database();
		//a.parseActor(actor);
	//}

	@Test
	public void testSearchMedia() 
	{
		ArrayList<String> display = new ArrayList<String>(1);
		String[] questions = new String[7];
		questions[0] = "m";
		questions[1] = "t";
		questions[2] = "e";
		questions[3] = "";
		questions[4] = "Star Trek";
		questions[5] = "";
		questions[6] = "t";
		Database a = new Database(0);
		Movie m = new Movie();
		
		String show = display.get(0);
		
		assertEquals("MOVIE: Star Trek (1985)	1985", show);
		
	}

}
