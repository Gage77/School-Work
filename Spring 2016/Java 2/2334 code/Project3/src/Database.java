import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * This class will construct Database objects and allow the 
 * user to search through these databases to find specific information
 */
public class Database 
{
	/** ArrayList of all MediaMaker objects */
	private ArrayList<MediaMaker> people;
	/** LinkedHashMap containing references to all MediaMakers */
	private LinkedHashMap<String, MediaMaker> mediaMakerMap;
	/** ArrayList containing all names (keys) for the MediaMakers as Strings */
	private ArrayList<String> names0 = new ArrayList<String>();
	
	/**
	 * Base constructor for this class with no parameters
	 */
	public Database()
	{
		
	}
	
	/**
	 * Main constructor for this class, taking in an int to 
	 * determine which class variables to initialize
	 * 
	 * @param a		int that determines which class variables to initialize
	 * 				a = 0: initialize movies and series
	 * 				a = 1: initialize people
	 */
	public Database(int a)
	{
		if (a == 1)
		{
			people = new ArrayList<MediaMaker>();
			mediaMakerMap = new LinkedHashMap<String, MediaMaker>();
		}
	}
	
	/**
	 * Takes in a file and goes through it, creating MediaMaker objects
	 * based on information found, and adding that MediaMaker to a LinkedHashMap
	 * so that further information can easily be added in the future
	 * 
	 * @param fileName	File containing information about actors
	 * @throws IOException 
	 */
	public void parseActor(String fileName) throws IOException
	{
		FileReader fr = new FileReader(fileName); 
		BufferedReader br = new BufferedReader(fr);
		
		//ArrayList names will contain all acting credits w/ the corresponding MediaMaker's name in front
		ArrayList<String> names = new ArrayList<String>();
		
		String line = br.readLine();
		
		//Puts every line of the file into the ArrayList names
		while (line != null)
		{
			names.add(line);
			line = br.readLine();
		}
		
		//Adds the corresponding MediaMaker name to each corresponding line in the front
		String a = "";
		for (int i = 0; i < names.size(); ++i)
		{
			String[] b = names.get(i).split("\t");
			if (b[0].contains(","))
			{
				a = b[0];
			}
			if (!names.get(i).contains(a))
				names.set(i, a + " " + names.get(i));
		}
		
		ArrayList<String> notPresentNames = new ArrayList<String>();
		//adds just the names to the class variable ArrayList names0 for later cross checking
		for (int i = 0; i < names.size(); ++i)
		{
			String[] c = names.get(i).split("\t");
			if (!names0.contains(c[0]))
			{
				names0.add(c[0]);
				notPresentNames.add(c[0]);
			}
			
		}
		
		HashSet<String> set = new HashSet<String>(notPresentNames);

        //Constructing listWithoutDuplicateElements using set

        ArrayList<String> NotPresentNames= new ArrayList<String>(set);
        
		//Makes the mediamaker objects with the key being the names, and puts them in a separate ArrayList
		for (int i = 0; i < NotPresentNames.size(); ++i)
		{
			MediaMaker mediamaker = new MediaMaker(NotPresentNames.get(i));	
			people.add(mediamaker);
		}
		
		//Puts the MediaMakers into the LinkedHashMap mediaMakerMap
		for (int i = 0; i < names0.size(); ++i)
		{
			mediaMakerMap.put(names0.get(i), people.get(i));
		}
		
		//Adds the acting info to the corresponding MediaMaker
		for (int i = 0; i < names.size(); ++i)
		{
			String[] b = names.get(i).split("\t");
			MediaMaker x = mediaMakerMap.get(b[0]);
			x.addInfo(names.get(i), 0);
		}
		
		//parse all info just passed to each MediaMaker using parseAllInfo method
		for (int i = 0; i < mediaMakerMap.size(); ++i)
		{
			MediaMaker y = mediaMakerMap.get(names0.get(i));
			y.parseAllInfo();
		}
		
		for (int i = 0; i < names.size(); ++i)
			System.out.println(names.get(i));
	
		br.close();
	}
	
	/**
	 * Takes in a file and goes through it, adding information to existing 
	 * MediaMakers through the LinkedHashMap containing their name, or adding
	 * them to the hashmap if they do not already exist
	 * 
	 * @param fileName	File containing information about actors
	 * @throws IOException 
	 */
	public void parseDirector(String fileName) throws IOException
	{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		//names contains all acting credits w/ the corresponding MediaMaker's name in front
		ArrayList<String> names = new ArrayList<String>();
		
		String line = br.readLine();
		
		//Puts every line of the file into the ArrayList names
		while (line != null)
		{
			names.add(line);
			line = br.readLine();
		}
		
		//Adds the corresponding MediaMaker name to each corresponding line in the front
		String a = "";
		for (int i = 0; i < names.size(); ++i)
		{
			String[] b = names.get(i).split("\t");
			if (b[0].contains(","))
			{
				a = b[0];
			}
			if (!names.get(i).contains(a))
				names.set(i, a + " " + names.get(i));
		}
		
		//adds just the names to the class variable ArrayList names0 for later cross checking
		ArrayList<String> notPresentNames = new ArrayList<String>();
		for (int i = 0; i < names.size(); ++i)
		{
			String[] c = names.get(i).split("\t");
			if (!names0.contains(c[0]))
			{
				names0.add(c[0]);
				notPresentNames.add(c[0]);
			}
			
		}
		
		HashSet<String> set = new HashSet<String>(notPresentNames);

        //Constructing listWithoutDuplicateElements using set

        ArrayList<String> NotPresentNames= new ArrayList<String>(set);
        
		//Makes the mediamaker objects with the key being the names, and puts them in a separate ArrayList
		ArrayList<MediaMaker> mapList = new ArrayList<MediaMaker>();
		for (int i = 0; i < NotPresentNames.size(); ++i)
		{
			MediaMaker mediamaker = new MediaMaker(NotPresentNames.get(i));
			mapList.add(mediamaker);
			people.add(mediamaker);
		}
		
		//Puts the MediaMakers into the LinkedHashMap mediaMakerMap
		for (int i = 0; i < notPresentNames.size(); ++i)
		{
			mediaMakerMap.put(notPresentNames.get(i), mapList.get(i));
		}
		
		//Adds the acting info to the corresponding MediaMaker
		for (int i = 0; i < names.size(); ++i)
		{
			String[] b = names.get(i).split("\t");
			MediaMaker x = mediaMakerMap.get(b[0]);
			x.addInfo(names.get(i), 1);
		}
		
		//Parse all of the info we just sent into the MediaMaker using parseAllInfo method
		for (int i = 0; i < mediaMakerMap.size(); ++i)
		{
			MediaMaker y = mediaMakerMap.get(names0.get(i));
			y.parseAllInfo();
		}
		
		for (int i = 0; i < names.size(); ++i)
			System.out.println(names.get(i));
		
		br.close();
	}
	
	/**
	 * Takes in a file and goes through it, adding information to existing
	 * MediaMakers through the LinkedHashMap containing their name, or adding 
	 * them to the hashmap if they do not already exist
	 * 
	 * @param fileName
	 * @throws IOException 
	 */
	public void parseProducer(String fileName) throws IOException
	{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		//names contains all acting credits w/ the corresponding MediaMaker's name in front
		ArrayList<String> names = new ArrayList<String>();
		
		String line = br.readLine();
		
		//Puts every line of the file into the ArrayList names
		while (line != null)
		{
			names.add(line);
			line = br.readLine();
		}
		
		//Adds the corresponding MediaMaker name to each corresponding line in the front
		String a = "";
		for (int i = 0; i < names.size(); ++i)
		{
			String[] b = names.get(i).split("\t");
			if (b[0].contains(","))
			{
				a = b[0];
			}
			if (!names.get(i).contains(a))
				names.set(i, a + " " + names.get(i));
		}
		
		//adds just the names to the class variable ArrayList names0 for later cross checking
		ArrayList<String> notPresentNames = new ArrayList<String>();
		for (int i = 0; i < names.size(); ++i)
		{
			String[] c = names.get(i).split("\t");
			if (!names0.contains(c[0]))
			{
				names0.add(c[0]);
				notPresentNames.add(c[0]);
			}
			
		}
		
		HashSet<String> set = new HashSet<String>(notPresentNames);

        //Constructing listWithoutDuplicateElements using set

        ArrayList<String> NotPresentNames= new ArrayList<String>(set);
        
		//Makes the mediamaker objects with the key being the names, and puts them in a separate ArrayList
		for (int i = 0; i < NotPresentNames.size(); ++i)
		{
			MediaMaker mediamaker = new MediaMaker(NotPresentNames.get(i));
			people.add(mediamaker);
		}
		
		//Puts the MediaMakers into the LinkedHashMap mediaMakerMap
		for (int i = 0; i < notPresentNames.size(); ++i)
		{
			mediaMakerMap.put(notPresentNames.get(i), people.get(i));
		}
		
		//Adds the acting info to the corresponding MediaMaker
		for (int i = 0; i < names.size(); ++i)
		{
			String[] b = names.get(i).split("\t");
			MediaMaker x = mediaMakerMap.get(b[0]);
			x.addInfo(names.get(i), 2);
		}
		
		//parse all info just passed to each MediaMaker using parseAllInfo method
		for (int i = 0; i < mediaMakerMap.size(); ++i)
		{
			MediaMaker y = mediaMakerMap.get(names0.get(i));
			y.parseAllInfo();
		}
		
		for (int i = 0; i < names.size(); ++i)
			System.out.println(names.get(i));
	
		br.close();
	}
	
	public ArrayList<MediaMaker> getMediaMakers()
	{
		return this.people;
	}
	
	/**
	 * Will take the given key and  then
	 * go through and search that pdb Database object's LinkedHashMap for matches,
	 * assigning them to ArrayList display
	 * 
	 * @param key		String to search through LinkedHashMap
	 * @param display	ArrayList of type string that will hold all of
	 * 					the matches found in a format fit for display 
	 * 					to the user
	 * @return 			int determining whether key existed in LinkedHashMap
	 */
	public int searchPeopleExact(String key, ArrayList<String> display, ArrayList<MediaMaker> mediaMakers)
	{
		int num = 0;
		MediaMaker mediaMaker = mediaMakerMap.get(key);
		if (mediaMaker != null)
		{
			display.add(mediaMaker.toString());
			mediaMakers.add(mediaMaker);
		}
		else if (mediaMaker == null)
		{
			System.out.println("That name does not exist in the database.");
			num = 1;
		}
		
		return num;
	}
	
	public int searchPeoplePartial(String name, ArrayList<String> display, ArrayList<MediaMaker> mediaMakers)
	{
		int num = 0;
		for (int i = 0; i < mediaMakerMap.size(); ++i)
		{
			String a = names0.get(i);
			if (a.contains(name))
			{
				display.add(mediaMakerMap.get(names0.get(i)).toString());
				mediaMakers.add(mediaMakerMap.get(names0.get(i)));
			}
			if (i == mediaMakerMap.size() - 1 && display.isEmpty())
			{
				num = 1;
			}
		}
		return num;
	}
	
	public void printInfo()
	{
		for (int i = 0; i < mediaMakerMap.size(); ++i)
		{
			System.out.println(mediaMakerMap.get(names0.get(i)));
		}
		
		for (int i = 0; i < names0.size(); ++i)
		{
			System.out.println(names0.get(i));
		}
	}
}
