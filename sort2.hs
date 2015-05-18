data TShirtSize = XSmall | Small | Medium | Large | XLarge | XXLarge deriving (Show,Eq,Ord)
data Color = Red|Green|Blue|Yellow|Orange deriving (Eq, Show)
data TShirt = TShirt TShirtSize Color deriving (Eq, Show)

-- Ord is defined in library. No need to recompile it. 
-- Adding "shirt" support for the "Ord" typeclass is trivial, and NOT tied to inheritance
instance Ord TShirt where
   compare (TShirt sz1 color) (TShirt sz2 color2) = compare sz1 sz2

-- Could have been defined in a library ages ago...has no knowledge of shirts at all
quicksort :: (Ord a) => [a] -> [a]  
quicksort [] = []  
quicksort (x:xs) =   
    let smallerSorted = quicksort [a | a <- xs, a <= x]  
        biggerSorted = quicksort [a | a <- xs, a > x]  
    in  smallerSorted ++ [x] ++ biggerSorted  
