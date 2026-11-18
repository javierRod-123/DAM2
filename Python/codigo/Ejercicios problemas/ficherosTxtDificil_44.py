# 1. Pido los nombres de los ficheros por teclado
fich_entrada = input("Introduce el nombre del fichero de entrada: ")
fich_salida = input("Introduce el nombre del fichero de salida: ")

# Abro el fichero de entrada en modo lectura
f = open(fich_entrada, "r", encoding="UTF-8")
contenido = f.read()
f.close()

print("Texto leído correctamente:")
print(contenido)

# Paso todo el texto a minúsculas para que no distinga entre mayúsculas
texto_minusculas = contenido.lower()

# Quito los signos de puntuación

for signo in [".", ",", ";", ":", "!", "?", "¿", "¡"]:
    texto_minusculas = texto_minusculas.replace(signo, "")

# Ahora separo los espacios para obtener una lista con todas las palabras
palabras = texto_minusculas.split()

print("Lista de palabras limpias:", palabras)

conteo_palabras = {}

for palabra in palabras:
    if palabra in conteo_palabras:
        conteo_palabras[palabra] += 1
    else:
        conteo_palabras[palabra] = 1

# Abro el fichero de salida en modo escritura
print("Diccionario con el conteo:", conteo_palabras)

f_out = open(fich_salida, "w", encoding="UTF-8")

for palabra, frecuencia in conteo_palabras.items():
    f_out.write(f"{palabra.upper()}, {frecuencia}\n")

f_out.close()

print(f"¡Fichero '{fich_salida}' generado con éxito!")