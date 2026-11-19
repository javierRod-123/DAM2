import pymysql

def filtrar_peso(peso_min, peso_max):
    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    lista_jugadores = list(filter(lambda fila: fila[2] > peso_min and fila[2] < peso_max, filas))

    dnis = map(lambda fila: fila[0], lista_jugadores)

    return dnis

def lista_to_fichero(lista_dnis):

    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    datos_a_escribir = list(filter(lambda fila: fila[0] in lista_dnis, filas))
    
    f = open("jugadores.txt", "wt", encoding="UTF-8")
    for fila in datos_a_escribir:
        f.write(f"{fila[0]},{fila[1]},{fila[2]},{fila[3]}\n")
    f.close()

# MAIN

dnis_intervalo = filtrar_peso(65, 85)
print("DNIs en el intervalo de peso:", dnis_intervalo)

lista_to_fichero(dnis_intervalo)
print("Fichero jugadores.txt creado correctamente.")