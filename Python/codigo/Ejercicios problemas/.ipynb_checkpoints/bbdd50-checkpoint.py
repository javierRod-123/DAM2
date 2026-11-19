import pymysql

def bd_to_diccionario():
    conn = pymysql.connect(
        host="db", user = "root", password = "test", database = "equipo"
    )

    cursor = conn.cursor()

    sql = "SELECT dni, fecha_baja, peso, altura FROM jugadores"
    cursor.execute(sql)
    filas = cursor.fetchall() # Lista de tuplas
    conn.close()

    d_jugadores = {}

    for fila in filas:
        dni = fila[0]
        d_jugadores[dni] = (
            fila[1],
            fila[2],
            fila[3],
        )
    return d_jugadores

def diccionario_to_fichero(diccionario):
    f = open("jugadores.txt", "wt", encoding="UTF-8")

    for dni, fecha_baja, peso, altura in diccionario.items():
        f.write(f"{dni}, {fecha_baja}, {peso}, {altura}\n")

    f.close()

jugadores_dict = bd_to_diccionario()
print(jugadores_dict) 

diccionario_to_fichero(jugadores_dict)
print("Fichero jugadores.txt generado correctamente.")