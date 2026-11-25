module Main (main) where

import Control.Monad (unless)
import Lib (doble, promedio, suma)
import System.Exit (exitFailure)

main :: IO ()
main = do
  let resultados =
        [ suma 2 3 == 5
        , suma (-1) 1 == 0
        , doble 4 == 8
        , promedio [10, 20] == 15
        , promedio [] == 0
        ]
  unless (and resultados) exitFailure
  putStrLn "Todas las pruebas pasaron"
