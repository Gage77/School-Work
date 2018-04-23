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

# Iterator/yield function to give all prime numbers in a range
# NOTE: This funciton was developed in class by Dr. Cheng
def Primes():
    yield 2
    p = 3
    while True:
        if is_prime(p):
            yield p
        p += 2

# Iterator/yield function to give all twin primes in a range
# NOTE: This function was developed in class by Dr. Cheng
def twin_primes():
    i = 3
    while True:
        if is_prime(i) and is_prime(i + 2):
            yield i
        i += 2

# Tells whether a number is the sum of a prime and a square
def is_nice(n):
    for i in squares():
        if i > n:
            return False
        if is_prime(n - i):
            return True

##################################################
# First define a function to find all powers of 3
##################################################

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

for i in pow_three():
    print(i)
    if i > 200:
        break
