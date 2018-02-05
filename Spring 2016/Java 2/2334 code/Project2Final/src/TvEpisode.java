import java.util.Comparator;

public class TvEpisode 
{
	private String episodeName;
	private String episodeStartYear;
	private String episodeInfo;
	private String episodeYear;
	
	/*
	 * The default constructor of the TvSeries class.
	 */
	public TvEpisode()
	{
		episodeName        = "";
		episodeStartYear   = "";
		episodeInfo        = "";
		episodeYear        = "";
	}
	
	/*
	 * The overloaded TvSeries constructor which will create Movie objects.
	 */
	public TvEpisode(String mdbEpisodeName,String mdbEpisodeStartYear,String mdbEpisodeInfo,String mdbEpisodeYear)
	{
		episodeName          = mdbEpisodeName;
		episodeStartYear     = mdbEpisodeStartYear;
		episodeInfo          = mdbEpisodeInfo;
		episodeYear          = mdbEpisodeYear;
	}
	
	/*
	 * The overloaded compareTo method which had to be implemented due to the abstract nature of Compare.
	 */
	public int compareTo(TvEpisode b)
	{
		return 1;
	}
	
	/*
	 * The overloaded Comparator constructor which will compare to years within two
	 * Movie objects.
	 */
	public static final Comparator<TvEpisode> TvSeriesYearComparator = new Comparator<TvEpisode>()
		{
			public int compare(TvEpisode a,TvEpisode b)
			{
				return 1;
			}
		};

	/**
	 * @return the seriesName
	 */
	public String getEpisodeName() 
	{
		return episodeName;
	}

	/**
	 * @return the seriesStartYear
	 */
	public String getEpisodeStartYear() 
	{
		return episodeStartYear;
	}
	
	
	/**
	 * @return the seriesEpisodeYear
	 */
	public String getEpisodeYear() 
	{
		return episodeYear;
	}

	/**
	 * @return the tvseriesyearcomparator
	 */
	public static Comparator<TvEpisode> getTvEpisodeyearcomparator() 
	{
		return TvSeriesYearComparator;
	}

	/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
	public String toString()
	{
		return "EPISODE episodeName + " " + episodeStartYear + " " + episodeInfo + " " + episodeYear;
	}
}
