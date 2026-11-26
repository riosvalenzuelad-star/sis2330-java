module Estadisticas
    ( Estudiante
    , promedio
    , masAlto
    , ordenarPorNota
    , aprobados
    ) where

import Data.List (sortBy)

type Estudiante = (String, Double)

sumaNotas :: [Estudiante] -> Double
sumaNotas [] = 0
sumaNotas ((_, nota) : resto) = nota + sumaNotas resto

promedio :: [Estudiante] -> Double
promedio [] = 0
promedio estudiantes = sumaNotas estudiantes / fromIntegral (length estudiantes)

masAlto :: [Estudiante] -> Estudiante
masAlto [] = ("Sin estudiantes", 0)
masAlto [unico] = unico
masAlto (primero : resto)
    | snd primero >= snd mejorResto = primero
    | otherwise = mejorResto
  where
    mejorResto = masAlto resto

ordenarPorNota :: [Estudiante] -> [Estudiante]
ordenarPorNota = sortBy (\a b -> compare (snd b) (snd a))

aprobados :: [Estudiante] -> [Estudiante]
aprobados estudiantes = [e | e@(_, nota) <- estudiantes, nota >= 51]
