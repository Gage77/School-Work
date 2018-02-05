README

New Milestones to reach for this iteration of MDb:

1. Implement Serializable for the classes necessary to save and load all application data
2. Use object serialization to save and load the application data to and from a binary file
3. Implement a simple graphical display for showing counts of the application data (historgram/pie-charts)
4. Create appropriate classes to store information on media makers
5. Use LinkedHashMaps to save to and retrieve information on media makers


	Essentially, everything from the previous project can be reused, with the exception of saving information
to binary or txt format. With the MediaMakers added, we need to implement ways to PARSE the data for ACTORS, DIRECTORS, 
and PRODUCERS, storing that data in a LINKED HASH MAP, and saving that data to either BINARY/TXT format. Additionally, 
we must, if prompted, display GUI HISTOGRAM/PIE-CHART information for MediaMakers (details of this below). 



HISTOGRAM/PIE-CHART information: Each graphical display should have a title at the top clearly indicating the data 
it represents

PIE-CHART: If the user has chosen “p” for pie chart, MDb will display a pie chart with up to six slices, one slice for
each of movie acting credits, series acting credits, movie directing credits, series directing credits, movie
producing credits, and series producing credits, with the width (subtended angle) of each slice
proportional to the percent of credits for that movie maker for that category. Each slice should be labeled
with the credit type that corresponds to that region. If the movie maker has no credits in some category,
the slice for that category should be omitted. For example, if someone has only movie acting credits and
no others, then the entire chart will be one big 360° slice (a circle) labeled “Movie Acting.”

HISTOGRAM: If the user has chosen “h” for histogram, MDb will display a histogram of the movie maker’s credits
over time, with one bar per year. The first year in the histogram will be the first year for which the
person has a credit. The last year in the histogram will be the last year for which the person has a credit.
The height of each bar will be proportional to the number of credits that person has in that year. The bar
itself will be subdivided into horizontal sections representing the six categories of credits listed above
for pie charts. The height of each subdivision should likewise be proportional to the number of credits
that person has in that category in that year. If a person has no credits in a given year for a given
category, that category should be omitted from the given bar. If a person has no credits at all in a given
year, that bar should be omitted from the histogram. However, the year itself should still be represented.
