module Main (main) where

import Control.Monad (unless)
import Estadisticas (Estudiante, aprobados, masAlto, ordenarPorNota, promedio)
import System.Exit (exitFailure)

ejemplo :: [Estudiante]
ejemplo = [("Ana", 80), ("Luis", 45), ("Marta", 95)]

main :: IO ()
main = do
  let resultados =
        [ promedio [("Ana", 80), ("Luis", 60)] == 70
        , promedio [] == 0
        , masAlto ejemplo == ("Marta", 95)
        , map fst (ordenarPorNota ejemplo) == ["Marta", "Ana", "Luis"]
        , aprobados ejemplo == [("Ana", 80), ("Marta", 95)]
        , aprobados [] == []
        ]
  unless (and resultados) exitFailure
  putStrLn "Todas las pruebas pasaron"
