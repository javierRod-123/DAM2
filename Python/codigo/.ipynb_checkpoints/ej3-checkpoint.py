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