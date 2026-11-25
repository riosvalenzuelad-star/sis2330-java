module Lib
    ( suma
    , doble
    , promedio
    ) where

suma :: Double -> Double -> Double
suma a b = a + b

doble :: Double -> Double
doble x = x * 2

promedio :: [Double] -> Double
promedio [] = 0
promedio xs = sum xs / fromIntegral (length xs)
