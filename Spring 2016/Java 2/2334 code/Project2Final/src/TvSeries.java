import java.util.ArrayList;
import java.util.Comparator;

public class TvSeries 
{
	private String seriesName;
	private String seriesStartYear;
	private String seriesYearRange;
	private ArrayList<TvEpisode> episodeList;
	
	/*
	 * The default constructor of the TvSeries class.
	 */
	public TvSeries()
	{
		seriesName        = "";
		seriesStartYear   = "";
		seriesYearRange   = "";
		episodeList       = null;
	}
	
	/*
	 * The overloaded TvSeries constructor which will create Movie objects.
	 */
	public TvSeries(String mdbSeriesName,String mdbSeriesStartYear,String mdbSeriesEpisodeYear,ArrayList<TvEpisode> mdbEpisodeList)
	{
		seriesName          = mdbSeriesName;
		seriesStartYear     = mdbSeriesStartYear;
		seriesYearRange     = mdbSeriesEpisodeYear;
		episodeList         = mdbEpisodeList;
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
	public String getSeriesName() 
	{
		return seriesName;
	}

	/**
	 * @return the seriesStartYear
	 */
	public String getSeriesStartYear() 
	{
		return seriesStartYear;
	}

	/**
	 * @return the seriesEpisodeYear
	 */
	public String getSeriesEpisodeYear() 
	{
		return seriesYearRange;
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
		return seriesName + " " + seriesStartYear + "\n" + this.episodeList.toString();
	}
}
