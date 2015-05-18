def fibonacci(n):
    rv = []
    first, second = 0, 1 # python makes it two lines shorter
    for i in range(n):
        rv.append(first)
        first, second = second, first + second 
    return rv
 
print fibonacci(10)
