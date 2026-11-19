# ==========================================
# CHULETA: OPERACIONES MATEMÁTICAS Y ORDENACIÓN
# ==========================================

valores = [12, 25, 8, 40, 15]

# --- BÁSICOS ---
total = sum(valores)  # Suma
maximo = max(valores)  # Máximo
minimo = min(valores)  # Mínimo
media = total / len(valores) if len(valores) > 0 else 0  # Media segura

# --- PORCENTAJES Y INTERVALOS ---
precio = 1500
con_aumento_20 = precio * 1.20  # +20%
con_descuento_15 = precio * 0.85  # -15%
en_rango = 10 <= 15 <= 20  # True (comprueba intervalo)

# --- DIVISIÓN ENTERA Y RESTO ---
cociente_entero = 125 // 60  # División entera (ej. minutos a horas)
resto = 125 % 60  # Resto / módulo (ej. segundos o múltiplos: n % 7 == 0)

# --- ORDENACIÓN CON SORTED() ---
# Lista de tuplas (nombre, puntuacion)
equipos = [("Boquerones", 8), ("Verdolagas", 16), ("Palanganas", 12)]

# Ordenar de menor a mayor por el segundo elemento (índice 1)
ascendente = sorted(equipos, key=lambda x: x[1])

# Ordenar de mayor a menor (descendente) por puntuación
descendente = sorted(equipos, key=lambda x: x[1], reverse=True)
# -> [("Verdolagas", 16), ("Palanganas", 12), ("Boquerones", 8)]