from functools import reduce

diccionario = {}

diccionario = { ("ferry",1) : [ 2500 ,350 ], ("mercante",2) : [ 120000, 6500 ], 
 ("mercante",3) : [ 200000, 3200 ], ("ferry",4) : [ 3520 , 420]} 

def obtenerPasajeros(diccionario):
    # Filtro solo los elementos que sean tipo ferry
    ferrys = filter(lambda item: item[0][0] == "ferry", diccionatio.items())

    # Transformo cada ferry filtrado quedandome solo con los pasajeros
    pasajeros = map(lambda item: item[1][1], ferrys)

    return tuple(pasajeros)

def mayorCarga(diccionario):
    mercantes = filter(lambda item: item[0][0] == "mercante", diccionario.items()

    barco_mayor = reduce(lambda it1, it2: it1 if it1[1][0] > it2[1][0] else it2, mercantes)

    return [barco_mayor[0][1]]