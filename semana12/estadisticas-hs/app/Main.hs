module Main (main) where

import Estadisticas (Estudiante, aprobados, masAlto, ordenarPorNota, promedio)
import System.IO (hFlush, stdout)
import Text.Read (readMaybe)

pedir :: String -> IO String
pedir mensaje = do
  putStr mensaje
  hFlush stdout
  getLine

leerEntero :: String -> IO Int
leerEntero mensaje = do
  linea <- pedir mensaje
  case readMaybe linea of
    Just n | n >= 0 -> return n
    _ -> do
      putStrLn "Ingrese un entero mayor o igual a 0."
      leerEntero mensaje

leerNota :: String -> IO Double
leerNota mensaje = do
  linea <- pedir mensaje
  case readMaybe linea of
    Just n | n >= 0 && n <= 100 -> return n
    _ -> do
      putStrLn "Ingrese una nota entre 0 y 100."
      leerNota mensaje

leerEstudiante :: Int -> IO Estudiante
leerEstudiante indice = do
  nombre <- pedir ("Nombre del estudiante " ++ show indice ++ ": ")
  nota <- leerNota ("Nota de " ++ nombre ++ ": ")
  return (nombre, nota)

leerEstudiantes :: Int -> IO [Estudiante]
leerEstudiantes cantidad = mapM leerEstudiante [1 .. cantidad]

formatear :: Estudiante -> String
formatear (nombre, nota) = nombre ++ " (" ++ show nota ++ ")"

mostrarLista :: [Estudiante] -> IO ()
mostrarLista [] = putStrLn "  (ninguno)"
mostrarLista estudiantes = mapM_ (putStrLn . ("  " ++) . formatear) estudiantes

main :: IO ()
main = do
  cantidad <- leerEntero "Cuantos estudiantes? "
  estudiantes <- leerEstudiantes cantidad
  putStrLn ""
  putStrLn "===== REPORTE ====="
  putStrLn ("Promedio: " ++ show (promedio estudiantes))
  case estudiantes of
    [] -> putStrLn "No hay estudiantes."
    _ -> do
      putStrLn ("Mas alto: " ++ formatear (masAlto estudiantes))
      putStrLn "Ordenados por nota:"
      mostrarLista (ordenarPorNota estudiantes)
      putStrLn "Aprobados:"
      mostrarLista (aprobados estudiantes)
