##################################################
# Functions from class
##################################################

# Function to give all squares in a range
# NOTE: This function was developed in class by Dr. Cheng
def squares():
    i = 0
    while True:
        yield i*i
        i += 1

# Iterator/yield function to give all twin primes in a range
# NOTE: This function was developed in class by Dr. Cheng
def twin_primes():
    i = 3
    while True:
        if is_prime(i) and is_prime(i + 2):
            yield i
        i += 2

# # Tells whether a number is the sum of a prime and a square
# def is_nice(n):
#     for i in squares():
#         if i > n:
#             return False
#         if is_prime(n - i):
#             return True

##################################################
# Functions for this project
##################################################

# Iterator/yield function to give all prime numbers in a range
# NOTE: This funciton was developed in class by Dr. Cheng
def Primes():
    yield 2
    p = 3
    while True:
        if is_prime(p):
            yield p
        p += 2

# Yields all powers of three
def pow_three():
    i = 1
    while True:
        yield pow(3, i)
        i += 1

# Function to determine if a passed in number is a prime number
# NOTE: This function was developed in class by Dr. Cheng
def is_prime(n):
    if n < 2:
        return False
    i = 2
    while i*i <= n:
        if n % i == 0:
            return False
        i += 1
    return True

# Finds all interesting numbers. That is:
# All numbers that are a sum of a prime and a square of 3
def is_nice(i, j):
    for n in Primes():
        if n + j == i:
            return True
    return False

# Function to print all nice numbers
def all_nice(n):
    i = n
    while True:
        for j in pow_three:
            if is_nice(i, j):
                yield i
            i += 1


##################################################
# Output
##################################################

# Powers of three
print("Enter a number n to yield all powers of three up to 3^n:")
n = int(input())
iter = int(0)
print("--------------")
for i in pow_three():
    iter += 1
    if iter > n:
        break
    print(i)

# Interesting numbers
print("Enter a number n to yield all intersting numbers (that is, all numbers that can be written as a sum of a prime and a positive power of three) that are greater than n")
n = int(input())
for i in all_nice(n):
    print(i)
    if i > 100:
        break

# Student ID number interesting numbers
n = 113229605
