# 1º Creo la lista
equipos = []

# Hago un bucle para guardar los datos en la lista no sin antes hacer una tupla y luego meterlo en la lista
for i in range(3):
    nombre_equipo = input("Introduce el nombre del equipo: ")
    ganados = int(input("¿Cuantos equipos ha ganado?: "))
    perdidos = int(input("¿Cuantos ha perdido?"))
    empatados = int(input("¿Cuantos han empatado?"))

    tupla = (nombre_equipo, ganados, perdidos, empatados)
    equipos.append(tupla)

print(equipos)

# Aqui calculo la gestion de puntos y luego lo ordeno de mayor a menor
resultado = []
for i in equipos:
    puntos = i[1] * 3 + i[2] * 0 + i[3] * 1
    resultado.append((i[0], puntos))

resultado.sort(key=lambda equipo: equipo[1], reverse=True) 
print(resultado)