{- ******************************************************************************** 
 - IMPORTANT NOTE: Each whitespace separated section of this example could be in a separate file/compile.  -}

{- This is a bit contrived...type classes often try to encompass more than 
 - a single function, but for this example, lets assume that there was a need
 - to deal a lot with the concept of Area -}
class Area s where
  area :: s -> Double

{- I can base a WHOLE library of functions on the concept of things that have area: -}
totalArea :: (Area a) => [a] -> Double 
totalArea = foldl (+) 0 . map area 
areaDiff :: (Area a, Area b) => a -> b -> Double
areaDiff a b = (area a) - (area b)

{- and create any new arbitrary data type at any time -}
data Shape = Rectangle Double Double | Circle Double

{- AND "hook it" into the existing type class (and library) without needing to edit/compile (or even SEE) the prior 
 - source: -}
instance Area Shape where
  area (Rectangle w h) = w * h
  area (Circle r) = 3.14 * r * r

{- Then perhaps add another data type... -}
data ExtendedShape = EquilateralTriangle Double | Shape

{- and add support for "taking the area of" -}
instance Area ExtendedShape where
  area (EquilateralTriangle s) = 0.433 * s * s

{- I can also add new type classes at any time in the evolution (file/compile/library) -}
class Perimeter s where
  perimeter :: s -> Double

{- and add support for that functionality to pre-existing data types -}
instance Perimeter Shape where
  perimeter (Rectangle w h) = 2*(w + h)
  perimeter (Circle r) = 2 * 3.14 * r

instance Perimeter ExtendedShape where
  perimeter (EquilateralTriangle a) = 3*a

{- As additional "proof"...add the concept of Perimeter to lists (which includes strings) -}
instance Perimeter [a] where
  perimeter s = fromIntegral $ length s

instance Area [a] where
  area s = (fromIntegral $ length s) - 2
