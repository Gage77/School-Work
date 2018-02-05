import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TvDataBase 
{
	private String mdbSeriesName;
	private String mdbSeriesStartYear;
	private String mdbSeriesYearRange;
	private ArrayList<TvEpisode> episodeList;
	
	private String mdbEpisodeName;
	private String mdbEpisodeStartYear;
	private String mdbEpisodeInfo;
	private String mdbEpisodeYear;
	
	private ArrayList<TvSeries> seriesList;
	
	/**
	 * This is the main constructor for a TvDataBase object, taking
	 * in a file name and sending it to the parse method to put the 
	 * series into the private instance variable seriesList
	 * 
	 * @param fileName	-	The file containing the TvSeries objects
	 * @throws IOException
	 */
	public TvDataBase(String fileName) throws IOException
	{
		parse(fileName);
	}
	
	/*
	 * This method will take in a file containing the string arguments needed to create the 
	 * TvSeries objects. First it will read the file line by line, parsing the strings based on 
	 * their values and finally store them into TvSeries objects.
	 * 
	 * @param:
	 * fileName - The file containing the TvSeries objects
	 * @throws IOException 
	 * 
	 * @return:
	 * An ArrayList of type TvSeries which will store all of the TvSeriesObjects.
	 */
	public void parse(String fileName) throws IOException
	{
		String nextLine;
		
		FileReader fileReader = new FileReader(fileName);
		
		BufferedReader br = new BufferedReader(fileReader);
		
		String list[];
		
		episodeList = new ArrayList<TvEpisode>();
		seriesList = new ArrayList<TvSeries>();
		
		nextLine = br.readLine();
		
		while(nextLine != null)
		{
			list = nextLine.split("[ |\t]+");
			
			if(list[list.length - 1].length() == 9)
			{
				mdbSeriesYearRange   = list[list.length - 1];
				mdbSeriesStartYear   = list[list.length - 2];
				
				for(int i = 0;i < list.length - 3; i++)
				{
					mdbSeriesName += list[i] + " ";
				}
				
				TvSeries seriesObject = new TvSeries(mdbSeriesName,mdbSeriesStartYear,mdbSeriesYearRange,episodeList);
				seriesList.add(seriesObject);
			}
			
			else
			{
				mdbEpisodeYear      = list[list.length - 1];
				
				for(int i = list.length - 2;!(list[i].contains("{"));i--)
				{
					mdbEpisodeInfo += list[i] + " ";
				}
				mdbEpisodeStartYear = list[list.length - 3];
				
				for(int i = 0;i < list.length - 3; i++)
				{
					mdbEpisodeName += list[i] + " ";
				}
				
				TvEpisode episodeObject = new TvEpisode(mdbEpisodeName,mdbEpisodeStartYear,mdbEpisodeInfo,mdbEpisodeYear);
				
				if(mdbSeriesName.compareTo(mdbEpisodeName) == 1)
				{
					episodeList.add(episodeObject);
				}
				br.readLine();
			}
			br.readLine();
		}
					
			br.close();
		}
	
	/**
	 * @return	-	returns the TvSeries ArrayList
	 */
	public ArrayList<TvSeries> getSeriesList()
	{
		return seriesList;
	}
	
	/*
	 * This method will read in the ArrayList created by the tvDataBAse method. It will then search
	 * the list based on what the user inputs from the Driver class. The results will then be transfered to an
	 * ArrayList of type String and then be shown to the user.
	 * 
	 * @param:
	 * userInput - The input which the user has inputed based on what they want to search for
	 * 
	 * @return:
	 * An ArrayList of type String which will then be sent to be shown via GUI window.
	 */
	public ArrayList<String> tvDataSearcher(String[] a)
	{
		return null;
	}

}
