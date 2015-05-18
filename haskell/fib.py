def fibonacci(n):
    first, second = 0, 1
    for i in range(n):
        print first  # Print current iteration
        first, second = second, first + second #Calculate next values
 
fibonacci(50)
