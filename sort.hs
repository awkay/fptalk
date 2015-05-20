-- Haskell is statically typed, but inference is very good...(next line is optional)
-- This sort is fully polymorphic across lists of all types that are part of 
-- the Ord typeclass (remember any new OR existing type can be "added" to a typeclass)
quicksort :: (Ord a) => [a] -> [a]  
quicksort [] = []  
quicksort (x:xs) =   
    let smallerSorted = quicksort [a | a <- xs, a <= x]  
        biggerSorted = quicksort [a | a <- xs, a > x]  
    in  smallerSorted ++ [x] ++ biggerSorted  
