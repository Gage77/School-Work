import static org.junit.Assert.*;

import org.junit.Test;

public class SeriesTest {

	@Test
	public void testSeries() 
	{
		String line = "Doctor Who (1965)	1965-1986";
		Series a = new Series(line);
		
		assertEquals("Doctor Who", a.getTitle());
	}
	
	@Test
	public void testEpisode()
	{
		String line = "Doctor Who (1963) {A Bargain of Necessity (#1.41)} 1964";
		Episode a = new Episode(line);
		
		assertEquals("A Bargain of Necessity", a.getEpisodeTitle());
	}

}
