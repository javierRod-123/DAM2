from functools import reduce

diccionario = { ("ferry",1) : [ 2500 ,350 ], ("mercante",2) : [ 120000, 6500 ], 
 ("mercante",3) : [ 200000, 3200 ], ("ferry",4) : [ 3520 , 420]} 

def totalCarga(diccionario):

    cargas = map(lambda item: item[1][0], diccionario.items())

    # Sumar todas las cargas con reduce
    total = reduce(lambda acc, carga: acc + carga, cargas, 0)

    return [total]

def obtenerCarga(diccionario):
    
    mercantes = filter(lambda item: item[0][0] == "mercante", diccionario.items())

    cargas = filter(lambda item: item[1][0], mercantes)

    return tuple(cargas)


# --- PROGRAMA PRINCIPAL DE PRUEBA ---
print("Total carga (todos los barcos):", totalCarga(diccionario))

print("Carga de mercantes:", obtenerCarga(diccionario))
