from functools import reduce

def leer_ventas_tapas():
    lista = []

    for _ in range(4):
        nombre_tapa = input("Nombre de la tapa: ")
        precio = float(input("Precio: "))
        unidades int(input("Unidades vendidas: "))

        tupla = (nombre, precio, unidades)

        lista.append(tupla)
    return lista

#MAIN
datos = leer_ventas_tapas

print(datos)


def calcular_estadisticas_tapas(lista_tapas):
    d = {}

    for tapa in lista_tapas:
        if tapa[2] > 25:
            popularidas = 'Alta'
        elif tapa[2] > 15:
            popularidad = 'Media':
        else:
            popularidad = 'Baja'
        
        d[tapa[0]] = (tapa[1] * tapa[2] , tapa[1] * tapa[2] * 0.1, popularidad)
    return d

#MAIN

resultado = calcular_estadisticas_tapas(datos)

print(resultado)


def analizar_rendimiento_tapas(dicc_tapas):
    
    tapas_popularidad_alta = filter(lambda tapa: tapa[1][2] == 'Alta' , dicc_tapas.item())

    promedio_iva = sum(map(lambda tapa : tapa[1][1], dicc_tapas.items()) / len(dicc_tapas)
                       
    tapa_mayor_importe = reduce(lambda t1, t2 : t1 if t1[1][0] > t2[1][0] else t2, dicc_tapas.items())

    return (list(tapas_popularidad_alta), promedio_iva, tapa_mayor_importe[0])




