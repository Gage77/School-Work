import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MovieTest 
{
	
	@Test
	public void testParseLine()
	{
		String testCase = "Star Wars: A New Hope (1977)       1977";
		Movie testMovie = new Movie(testCase);
		
		assertEquals("Star Wars: A New Hope (1977)", testMovie.getTitle());
	}
	
	@Test
	public void testPrintInfo()
	{
		Movie testCase = new Movie("Star Wars: A New Hope (1977)       1977");
		String param = "Star Wars";
		Database list = new Database();
		list.insertMovie(testCase);
		int searchType = 1;
		ArrayList<String> m = new ArrayList<String>();
		list.printInfo(param, m, searchType);
		
		//will need to change return type to test, then change back once tested
		
		assertEquals("Star Wars: A New Hope (1977)       1977", m.get(0).toString());
	}


}
