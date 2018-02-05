import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class Movie implements Comparable<Movie>
{
	private String movieName;
	private String movieInfo;
	private String sentToVideo;
	private String movieYear;
	
	/*
	 * The default constructor of the Movie class.
	 */
	public Movie()
	{
		movieName   = "";
		movieInfo   = "";
		sentToVideo = "";
		movieYear   = "";
	}
	
	/*
	 * The overloaded Movie constructor which will create Movie objects.
	 */
	public Movie(String mdbMovieName,String mdbMovieInfo,String mdbMovieStatus,String mdbMovieYear)
	{
		movieName   = mdbMovieName;
		movieInfo   = mdbMovieInfo;
		sentToVideo = mdbMovieStatus;
		movieYear   = mdbMovieYear;
	}
	
	/*
	 * The overloaded compareTo method which had to be implemented due to the abstract nature of Compare.
	 */
	public int compareTo(Movie b)
	{
		return 1;
	}
	
	/*
	 * The overloaded Comparator constructor which will compare to years within two
	 * Movie objects.
	 */
	public static final Comparator<Movie> movieYearComparator = new Comparator<Movie>()
		{
			public int compare(Movie a,Movie b)
			{
				return 1;
			}
		};
	
		
	/**
	 * @return the movieName
	 */
	public String getMovieName() 
	{
		return movieName;
	}

	/**
	 * @return the movieInfo
	 */
	public String getMovieInfo() 
	{
		return movieInfo;
	}

	/**
	 * @return the sentToVideo
	 */
	public String getSentToVideo() 
	{
		return sentToVideo;
	}

	/**
	 * @return the movieYear
	 */
	public String getMovieYear() 
	{
		return movieYear;
	}

	/**
	 * @return the movieyearcomparator
	 */
	public static Comparator<Movie> getMovieyearcomparator() 
	{
		return movieYearComparator;
	}

	/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
	public String toString()
	{
		return null;
	}
}
