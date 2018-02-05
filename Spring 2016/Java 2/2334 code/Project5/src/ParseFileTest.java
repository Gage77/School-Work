import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ParseFileTest {

	@Test
	public void testMediaMaker() {
		String testCaseMediaMaker = "Test name";
		
		MediaMaker mm = new MediaMaker(testCaseMediaMaker);
		
		Assert.assertTrue(mm.getName().equals(testCaseMediaMaker));
	}
	
	@Test
	public void testMediaModel() {
		Movie movie = new Movie("Star Trek", "1993", "V");
		
		MediaModel model = new MediaModel();
		model.addObject(movie);
		
		ArrayList<Media> getMovies = model.getMovieObjects();
		
		Media testMovie = new Media("False title");
		
		if (!getMovies.isEmpty())
			testMovie = getMovies.get(0);
		
		Assert.assertEquals(movie, testMovie);
	}

}
