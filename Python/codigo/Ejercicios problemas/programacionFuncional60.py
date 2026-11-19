from functools import reduce

diccionario = { (1, "jarabe") : ("SI",120), (2,"pastilla") : ("SI",20), (3,"jarabe") : ("NO",45), (4,"jarabe") : ("NO",75), (5,"jarabe") : ("SI",35), (6,"jarabe") : ("NO",90), (7,"pastilla") : ("NO",30),(8,"pastilla") : ("SI",15), }


def pastillasNoFinanciadas(diccionario):
    pastillas_no_financiados = filter(lambda item: item[1][0] == "NO" and item[0][1] == "pastilla", diccionario.items())

    id = map(lambda item: item[0][0], pastillas_no_financiados)

    return tuple(id)

def menorEnvase(diccionario):
    pastillas_no_financiados = filter(lambda item: item[0][1] == "pastilla", diccionario.items())

    pastillas_menor = reduce(lambda it1 it2: it1 if it1[1][1] < it2[1][1] else it2, jarabes_no_financiados)

    return pastillas_menor[1][1]

