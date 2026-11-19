import pymysql

def altura_media():
    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    activos = list(filter(lambda fila: fila[1] is None, filas))

    alturas = list(map(lambda fila: fila[3], activos))

    if len(alturas) > 0:
        return sum(alturas) / len(alturas)
    return 0


def jugadores_altos(media):
    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    altos = list(filter(lambda fila: fila[1] is None and fila[3] > media, filas
    
    f = open("jugadores.txt", "wt", encoding="UTF-8")
    for jugador in altos:
        f.write(f"{jugador[0]}\n")
    f.close()





