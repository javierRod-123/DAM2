import pymysql

def filtrar_altura_peso():
    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    lista_jugadores = []

    