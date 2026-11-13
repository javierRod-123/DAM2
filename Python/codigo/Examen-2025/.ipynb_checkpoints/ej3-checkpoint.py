import pymysql

def cargar_reservas_desde_csv():

    f = open("reservas.csv", "rt", encoding="UTF-8")

    lista_lineas = f.readlines()

    f.close()

    d = {}

    for linea in lista_lineas[1:]:
        lista_campos = linea.split(",")
        d[int(lista_campos[0])] = {'nombre': lista_campos[1] , 'telefono': lista_campos[2], 'fecha': lista_campos[3], 'personas': int(lista_campos[4]) , 'mesa': int(lista_campos[5].strip())}

    return d

#MAIN
diccionario = cargar_reservas_desde_csv()
print (diccionario)

def insertar_reservas_bbdd(reservas):

    conn = pymysql.connect(host = 'php-db-1', user ='root', password = 'test', database = 'examen')

    cursor = conn.cursor()

    for reserva in reserva.items():

        sql = "INSERT INTO `reserva`(`id`, `nombre_cliente`, `telefono`, `fecha_reserva`, `numero_personas`, `numero_mesa`) VALUES (%s , %s , %s , %s , %s , %s)"

        cursor.execute(sql, ( reserva[0], reserva[1]['nombre'], reserva[1]['telefono'], reserva[1]['fecha'], reserva[1]['personas'], reserva[1]['mesa']))

        conn.commit()
        conn.close()

insertar_reservas_bbdd(diccionario)
                             
def mostrar_reservas_fecha(fecha):
    
    conn = pymysql.connect(host = 'php-db-1', user ='root', password = 'test', database = 'examen')

    cursor = conn.cursor()

    sql = "SELECT * FROM reserva WHERE fecha_reserva = %s"
    cursor.execute(sql, (fecha))

    rows = cursor.fetchall() # Lista de tuplas, cada tupla es una fila de datos

    conn.close()

    listado = f"RESERVAS PARA {fecha}:"
    listado += "=========================="
    personas = 0
    reservas = 0
           
    for row in rows:
        listado += f"Mesa {row[5]}: {row[1]} ({row[4]} personas) - Tel: {row[2]}\n"
        num_reservas += 1
        num_personas += row[4]
    
    listado += f"Total {num_reservas} reservas, {num_personas} personas"
    
    return listados