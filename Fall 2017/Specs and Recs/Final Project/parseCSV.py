import csv
def main():
    #+---------------------------------------+
    #| The file names should go in this list |
    #|			|			|
    #|			V			|
    #+---------------------------------------+
    fileNames = ['Slack_Web_CSV.csv', 'Slack_Desktop_CSV.csv', 'Slack_Mobile_CSV.csv', 'Yammer_Desktop_CSV.csv', 'Yammer_Mobile_CSV.csv', 'Yammer_Web_CSV.csv', 'Pinterest.csv', 'Yo.csv']
    # loop through each filename
    for fName in fileNames:
        # Display the filename so we know which file this is
        print(fName, end='\t')
        # Open each file
        with open(fName) as csvfile:
            # Sets are unable to have duplicate entries
            numThings = Set()
            # Get the lines of the file as rows in an array
            # Split each line into separate strings delimited by a comma
            readCSV = csv.reader(csvfile, delimiter=',')
            # Loop though all of the items in this file
            for row in readCSV:
                for item in row:
                    # If this item is actually something, display it and add it to the Set
                    if (item != ""):
                        print(item, end = ' ')
                        numThings.add(item)
                print('')
            # Display the total size of this file
            print("The size of this application is %d items."%len(numThings))

if __name__ == '__main__':
    # Run the program
    main()
