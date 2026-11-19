from functools import reduce

# ==========================================
# CHULeTA DE OPERACIONES CON REDUCE()
# ==========================================
# Estructura: reduce(lambda acumulado, elemento: ..., iterable, valor_inicial)

# 1. SUMAR valores (útil para totales, cargas, pesos, importes)
numeros = [2500, 120000, 200000, 3520]
suma = reduce(lambda acc, x: acc + x, numeros, 0)  # -> 326020

# 2. MULTIPLICAR valores
producto = reduce(lambda acc, x: acc * x, [2, 3, 4], 1)  # -> 24

# 3. MÁXIMO de una lista de números
maximo = reduce(lambda acc, x: x if x > acc else acc, numeros)  # -> 200000

# 4. MÍNIMO de una lista de números
minimo = reduce(lambda acc, x: x if x < acc else acc, numeros)  # -> 2500

# 5. ELEMENTO CON MAYOR CAMPO (ej. clave/id con mayor valor asociado)
# Imagina items = [("mercante", 2, 120000), ("mercante", 3, 200000)]
items = [("mercante", 2, 120000), ("mercante", 3, 200000)]
mayor_item = reduce(lambda acc, x: x if x[2] > acc[2] else acc, items)
# -> ("mercante", 3, 200000)  (luego podrías sacar su id con mayor_item[1])

# 6. CONCATENAR cadenas o listas
palabras = ["Hola", " ", "mundo"]
frase = reduce(lambda acc, p: acc + p, palabras, "")  # -> "Hola mundo"

listas = [[1, 2], [3], [4, 5]]
unida = reduce(lambda acc, l: acc + l, listas, [])  # -> [1, 2, 3, 4, 5]

# 7. CONTAR elementos que cumplen una condición
# Contar cuántos números son mayores que 5000
mayores_5000 = reduce(
    lambda acc, x: acc + 1 if x > 5000 else acc, numeros, 0
)  # -> 2

# 8. CONSTRUIR un diccionario acumulando datos
# Ej: sumar valores por categoría en tuplas (categoria, cantidad)
movimientos = [("A", 10), ("B", 5), ("A", 20)]


def acumular_dict(acc, mov):
  cat, cant = mov
  acc[cat] = acc.get(cat, 0) + cant
  return acc


por_categoria = reduce(acumular_dict, movimientos, {})  # -> {"A": 30, "B": 5}

