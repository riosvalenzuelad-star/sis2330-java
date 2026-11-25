module Main (main) where

import Lib (doble, promedio, suma)
import System.IO (hFlush, stdout)
import Text.Read (readMaybe)

leerNumero :: String -> IO Double
leerNumero mensaje = do
  putStr mensaje
  hFlush stdout
  linea <- getLine
  case readMaybe linea of
    Just n -> return n
    Nothing -> do
      putStrLn "Ingrese un numero valido."
      leerNumero mensaje

main :: IO ()
main = do
  putStrLn "Calculadora"
  a <- leerNumero "Primer numero: "
  b <- leerNumero "Segundo numero: "
  putStrLn ("Suma: " ++ show (suma a b))
  putStrLn ("Doble del primero: " ++ show (doble a))
  putStrLn ("Promedio: " ++ show (promedio [a, b]))
