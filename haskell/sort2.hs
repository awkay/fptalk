data TShirtSize = XSmall | Small | Medium | Large | XLarge | XXLarge deriving (Show,Eq,Ord)
data Color = Red|Green|Blue|Yellow|Orange deriving (Eq, Show)
data TShirt = TShirt TShirtSize Color deriving (Eq, Show)

instance Ord TShirt where
   compare (TShirt sz1 color) (TShirt sz2 color2) = compare sz1 sz2

quicksort :: (Ord a) => [a] -> [a]  
quicksort [] = []  
quicksort (x:xs) =   
    let smallerSorted = quicksort [a | a <- xs, a <= x]  
        biggerSorted = quicksort [a | a <- xs, a > x]  
    in  smallerSorted ++ [x] ++ biggerSorted  
