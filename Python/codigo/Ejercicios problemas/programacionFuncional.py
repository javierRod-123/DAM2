from functools import reduce

# ==========================================
# CHULETA: PROGRAMACIÓN FUNCIONAL
# ==========================================

# --- FILTER (filtrar elementos que cumplen una condición) ---
numeros = [10, 25, 40, 5, 60]
mayores_20 = list(filter(lambda x: x > 20, numeros))  # [25, 40, 60]

# Con diccionario: items es una tupla (clave, valor)
dic = {("a", 1): 100, ("b", 2): 250, ("a", 3): 50}
solo_a = list(filter(lambda item: item[0][0] == "a", dic.items()))

# --- MAP (transformar o extraer un dato de cada elemento) ---
dobles = list(map(lambda x: x * 2, numeros))  # [20, 50, 80, 10, 120]
valores_a = tuple(map(lambda item: item[1], solo_a))  # (100, 50) como tupla

# --- REDUCE (acumular en un único valor) ---
suma_total = reduce(lambda acc, x: acc + x, numeros, 0)  # 140
max_num = reduce(lambda acc, x: x if x > acc else acc, numeros)  # 60
min_num = reduce(lambda acc, x: x if x < acc else acc, numeros)  # 5

# Obtener el elemento completo con mayor campo (ej. índice 1)
pares = [("x", 15), ("y", 40), ("z", 22)]
par_mayor = reduce(lambda acc, x: x if x[1] > acc[1] else acc, pares)  # ("y", 40)

# --- COMPRENSIÓN DE LISTAS (alternativa rápida sin bucles) ---
pares_lista = [x for x in numeros if x % 2 == 0]  # [10, 40, 60]