-- Direct declarative approach to get the nth fibonacci number (performs very poorly, as it recalculates a LOT)
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib (n-2)

-- Try getting the first 25 numbers using fib...now try getting JUST the 30th...

-- Simple "Lazy" approach to get an infinite sequence of all of the fibonacci numbers using HO functions
fib2 = 0:1:zipWith (+) fib2 (tail fib2)

-- Try getting the first 25 numbers using fib2...now try getting the 1000th one
