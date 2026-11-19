from functools import reduce

diccionario = { (1, "jarabe") : ("SI",120), (2,"pastilla") : ("SI",20), (3,"jarabe") : ("NO",45), (4,"jarabe") : ("NO",75), (5,"jarabe") : ("SI",35), (6,"jarabe") : ("NO",90), (7,"pastilla") : ("NO",30),(8,"pastilla") : ("SI",15), }

def medicamentosFinanciados(diccionario):

    son_financiados = filter(lambda item: item[1][0] == "SI", diccionario.items())

    id = map(lambda item: item[0][0], son_financiados)

    return tuple(id)

def mayorVolumenNoFinanciado(diccionario):

    jarabes_no_financiados = filter(lambda item: item[1][0] == "jarabe" and item[1][0] == "NO", diccionario.items())

    jarabe_mayor = reduce(lambda it1 it2: it1 if it1[1][1] > it2[1][1] else it2, jarabes_no_financiados)

    return jarabe_mayor[0][0]

print("IDs financiados:", medicamentosFinanciados(diccionario))
print("ID jarabe no financiado de mayor volumen:",mayorVolumenNoFinanciado(diccionario),)