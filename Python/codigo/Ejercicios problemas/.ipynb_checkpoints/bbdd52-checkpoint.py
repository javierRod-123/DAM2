import pymysql

def filtrar_altura_peso(altura_min, peso_max):
    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    lista_jugadores = list(filter(lambda fila: fila[3] > altura_min and fila[2] < peso_max, filas))

    dnis = map(lambda fila: fila[0], lista_jugadores)
    return tuple(dnis)

def lista_to_fichero(tupla_dni):
    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    seleccionados = filter(lambda fila: fila[0] in tupla_dni, filas)

    f = open("jugadores.txt", "wt", encoding="UTF-8")

    for fila in seleccionados:
        f.write(f"{fila[0]}-{fila[1]}-{fila[2]}-{fila[3]}\n")

dnis_filtrados = filtrar_altura_peso(1.75, 85)
print("DNIs filtrados:", dnis_filtrados)

lista_to_fichero(dnis_filtrados)  #[cite: 15]
print("Fichero jugadores.txt generado correctamente.")