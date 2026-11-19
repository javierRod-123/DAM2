# ==========================================
# CHULETA: DICCIONARIOS Y LISTAS
# ==========================================

# --- COMPROBAR SI EXISTE CLAVE Y USAR .get() ---
d = {"ventas": 100, "compras": -40}

if "ventas" in d:
  pass  # La clave existe

if "stock" not in d:
  d["stock"] = 0  # Crear clave si no existe

valor_seguro = d.get(
    "no_existe", 0
)  # Devuelve 0 si no está, sin dar KeyError

# --- CONTADOR DE FRECUENCIAS (palabras, elementos, etc.) ---
palabras = ["sol", "luna", "sol", "mar", "sol", "luna"]
frecuencias = {}

for p in palabras:
  frecuencias[p] = frecuencias.get(p, 0) + 1
# -> {"sol": 3, "luna": 2, "mar": 1}

# --- ACUMULAR VALORES POR CLAVE (ej. sumar movimientos por cuenta) ---
movimientos = [("Ventas", 100), ("Compras", -30), ("Ventas", 50)]
saldos = {}

for cuenta, importe in movimientos:
  saldos[cuenta] = saldos.get(cuenta, 0) + importe
# -> {"Ventas": 150, "Compras": -30}

# --- AGREGAR ELEMENTOS A UNA LISTA DENTRO DE UN DICCIONARIO ---
grupos = {}
datos = [("par", 2), ("impar", 3), ("par", 4)]

for tipo, num in datos:
  if tipo not in grupos:
    grupos[tipo] = []
  grupos[tipo].append(num)
# -> {"par": [2, 4], "impar": [3]}

# --- RECORRER DICCIONARIO Y FILTRAR A LISTA ---
claves_altas = [clave for clave, val in saldos.items() if val > 50]

# --- OPERACIONES ÚTILES EN LISTAS ---
lista = [1, 2, 3, 2]
lista.append(4)  # Añade al final
lista.remove(2)  # Elimina la primera aparición del valor 2
veces_2 = lista.count(2)  # Cuenta cuántas veces aparece 2