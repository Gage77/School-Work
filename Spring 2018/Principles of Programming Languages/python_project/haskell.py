# Class to hold student information
# All students will have a first name, last name, grade,
# type (L or E), and an optional address
class Student:

    def __init__(self, id, first, last, grade, type):
        self.id = id
        self.first = first
        self.last = last
        self.grade = grade
        self.type = type
        self.letterGrade = 'F'

    def __repr__(self):
        return '{}: {} {} {} {} {} {}'.format(self.__class__.__name__, self.id, self.first, self.last, self.grade, self.type, self.letterGrade)

splitLine = []      # Will hold all info read in from file, split by white space
students = []       # Will hold all students that get created
sortedStudents = [] # List of sorted students for html printing

ids = []            # Hold ids
fname = []          # Hold first names
lname = []          # Hold last names
sgrade = []         # Hold grades
stype = []          # Hold types

# Booleans for reading in lines from txt file
idRead = False
gradeRead = False
fNameRead = False

# Open the file using a context manager
with open('input.txt', 'r') as f:
    # Iterate through all lines in the file
    for line in f:
        # Add new line to big list
        splitLine = splitLine + line.split()

# Prints big list to make sure all info made it in
print(splitLine)

# Returns whether or not the passed in word can be parsed
# as an integer
def isInt(word):
    try:
        int(word)
        return True
    except:
        return False

def getKeyForSort(student):
    return student.last

# Create student objects
for w in splitLine:
    # ID or grade
    if isInt(w):
        #ID
        if int(w) > 1000000:
            ids.append(w);
            idRead = True
            gradeRead = False
            fNameRead = False
        # Grade
        else:
            sgrade.append(w);
            gradeRead = True
    else:
        # Get type (E or L)
        if len(w) == 1:
            stype.append(w)
        elif idRead and not gradeRead:
            if fNameRead:
                lname.append(w)
                idRead = False
            else:
                fname.append(w)
                fNameRead = True
        else:
            pass

# Create student class objects and add them to global list
for i, val in enumerate(ids, 0):
    newStudent = Student(ids[i], fname[i], lname[i], sgrade[i], stype[i])
    students.append(newStudent)

for s in students:
    print(s)

# Sort students by last name
sortedStudents = sorted(students, key = lambda student: student.first)
sortedStudents = sorted(sortedStudents, key = lambda student: student.last)

print("----------------------")

for s in sortedStudents:
    print(s)
