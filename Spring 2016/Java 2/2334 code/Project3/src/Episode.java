import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 * Lab #4 CS 2334, Section 0?? February 2?, 2016
 * <P>
 * This class models an episode of a series.
 * </P>
 * @author
 * @version 1.0
 */
public class Episode implements Comparable<Episode>{
	
	/** Name of the Episode */
	private String episodeName;

	/** Name of Series **/
	private String seriesName;
	
	/** Number of the Episode (#series.episode)*/
	private String episodeNumber;

	/** Year of the Episode */
	private String year;

	private boolean suspended = false;
	
	/**
	 * This is the default constructor for Episode.
	 */
	Episode() {
		
	}

	/**
	 * This is the constructor for Episode. It instantiates the class with user
	 * supplied values.
	 * 
	 * @param name
	 * @param seasonNumber
	 * @param episodeNumber
	 * @param year
	 */
	public Episode(String name, String seriesName, String episodeNumber, String year
			, boolean isSuspended) {
		this.episodeName = name;
		this.seriesName = seriesName;
		this.episodeNumber = episodeNumber;
		this.year = year;
		this.suspended = isSuspended;
	}

	public String getEpTitle(){
		return episodeName;
	}
	
	public String getSeries(){
		return seriesName;
	}
	
	public String getEpNum(){
		return episodeNumber;
	}
	
	public String getYear(){
		return year;
	}
	
	public boolean isSuspended(){
		return suspended;
	}
	
	/**
	 * Reads information in file into an Array of Strings as one per line in document
	 * as a solid string using FileReader and BufferedReader
	 * @param TVInfo - an ArrayList of Strings used to store contents of the text file
	 * @return The method has a void return type because the method just reads the text file 
	 * and stores its contents in an ArrayList of Strings, one movie per array element
	 * @exception IOException must be thrown by any method that does input/output,
	 * this method does input using FileReader and BufferedReader
	 * <dd>PRE - There must be an ArrayList of Strings so that the method will have a place to
	 * store input
	 * <dd>POST - The method reads information from a text file and stores the 
	 * information in it into an ArrayList of Strings, one line per element, which is to later be
	 * parsed into separate variables
	 */
	public static void readFile(ArrayList<String> TVInfo) throws IOException {
		String filename = "testTVFile.txt";
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String nextLine = "";
		while(nextLine != null){
			nextLine = br.readLine();
			if(nextLine != null){
				TVInfo.add(nextLine);		
			}
		}
		br.close();
		Collections.sort(TVInfo);
	}
	
	/**
	 * Parses a String ArrayList of TV information one element at a time into different
	 * information to be stored in variables. These variables are then used to construct
	 * Episode objects to be stored in an ArrayList. Parses using substring.
	 * @param TVInfo - A String ArrayList containing the TV information held on a single line
	 * for a movie from the text file 
	 * @param TVList - A TV ArrayList containing the movie information in a episode object 
	 * @return The method has a void return type because the method just parses elements of a
	 * String ArrayList into Strings and assigns them to variables, each a different 
	 * component of the episode
	 * <dd>PRE - There must be an ArrayList of TV information, the elements of this Array are 
	 * set to a String variable, one per line in the text file
	 * <dd>POST - The method parses the elements of a String ArrayList into String variables, one
	 * for each of the components a movie in the text file may have so that they can be 
	 * used to construct a Episode object
	 */
	public static void parseMovie(ArrayList<String> TVInfo, ArrayList<Episode> TVList) {
		String[] tempArray;
		String tempYear;
		String tempTitle;
		String tempEpisodeNum = null;
		String tempSeries;
		boolean suspended = false;
		
		for(int index = 0; index < TVInfo.size(); ++index){
			tempArray = TVInfo.get(index).split("[\\s\\xA0]+");			
			if(!tempArray[tempArray.length - 1].contains("-")){
				tempYear = tempArray[tempArray.length - 1];
				
				//is suspended
				if(Arrays.asList(tempArray).contains("{{SUSPENDED}}")){
					suspended = true;
					tempArray = TVInfo.get(index).substring(0, TVInfo.get(index)
							.indexOf("{{SUSPENDED}}")).split("[\\s\\xA0]+");
					
					for(int oIndex = 0; oIndex < tempArray.length; ++oIndex){
						if(tempArray[oIndex].endsWith("}")){
							tempEpisodeNum = tempArray[oIndex].replace("}", "").replace("{", "")
									.replace(")", "").replace("(", "");
						}
					}
				}
				
				else{
					for(int oIndex = 0; oIndex < tempArray.length; ++oIndex){
						if(tempArray[oIndex].endsWith("}") && tempArray[oIndex].contains("#")){
							tempEpisodeNum = tempArray[oIndex].replace("}", "").replace("{", "")
									.replace(")", "").replace("(", "");
						}
					}
				
				}
				
			
				//getTitle
				int endOfEpTitle = 0;
				for(int iCount = 0; iCount < tempArray.length; ++iCount){
					if(tempArray[iCount].endsWith("}")){
						endOfEpTitle = iCount;
					}
				}
				int startOfTitle = 0;
				for(int iCount = 0; iCount < tempArray.length; ++iCount){
					if(tempArray[iCount].startsWith("{")){
						startOfTitle = iCount;
					}
				}
				tempTitle = tempArray[startOfTitle].replace("{", "");
				for(int count = startOfTitle + 1; count <= endOfEpTitle; ++count){
					tempTitle = tempTitle + " " + tempArray[count];
				}
				
				tempTitle = tempTitle.replace("(" + tempEpisodeNum + ")", "").replace("}", "");
				
				
				tempArray = TVInfo.get(index).substring(0, TVInfo.get(index)
						.indexOf("{")).split("[\\s\\xA0]+");
				//get the name of the series it's apart of
				int endOfSeriesTitle = 0;
				for(int iCount = 0; iCount < tempArray.length; ++iCount){
					if(tempArray[iCount].endsWith("\"")){
						endOfSeriesTitle = iCount;
					}
				}
				int startOfSeriesTitle = 0;
				
				tempSeries = tempArray[startOfSeriesTitle];
				for(int iCount = 0; iCount < endOfSeriesTitle; ++iCount){
					tempSeries = tempSeries + " " + tempArray[iCount];
				}
				
				TVList.add(new Episode(tempTitle, tempSeries, tempEpisodeNum, tempYear, suspended));
				//make an episode
				
				Collections.sort(TVList);
			}
		}		
	}
	
	/**
	 * This method returns the attributes of this class as a single string.
	 * </P>
	 * 
	 * @return String representing the contents of this object.
	 */
	public String toString() {
		String attributesAsString = "";
		if (this.episodeName != null) {
			attributesAsString += this.episodeName + ", ";
		}
		else {
			attributesAsString += "[untitled], ";
		}
		if (this.year != null) {
			attributesAsString += this.year;
		}
		return (attributesAsString);
	}
	
	/**
	 * This method from the comparable interface will compare the Movie object
	 * titles and see if there is a partial or complete match
	 * @param movie - This is a Movie object that this method will use for comparison
	 * @return int - a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object
	 * <dt><b>Conditions:</b>
	 * <dd>PRE - Receives Movie object to compare
	 * <dd>POST - Returns an integer number to say if the titles match
	 */
	@Override
	public int compareTo(Episode episode) {
		return episodeName.compareTo(episode.getEpTitle());
	}
	
	/**
	 * This is a subclass that compares Movie objects' year values and a returns a 
	 * negative integer, zero, or a positive integer as the first argument is less 
	 * than, equal to, or greater than the second. Used to sort movieList by year.
	 */
	public static final Comparator<Episode> EPISODE_YEAR_COMPARATOR = new Comparator<Episode>() {
		public int compare(Episode episode, Episode anotherEpisode) {
			return episode.getYear().compareTo(anotherEpisode.getYear());
		}
	};
}