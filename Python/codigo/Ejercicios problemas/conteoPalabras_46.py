f = open("ejemplo46.txt", "rt", encoding="UTF-8")
lineas = f.readLines()
f.close()

lista_numeros = []

for linea in lineas:
    numeros_string = linea.split("\n").split(";")
    numeros_reales = map(lambda num_string: float(num_string), numeros_string)
    lista_numeros.extend(numeros_reales)

print("Máximo:", max(lista_numeros))
print("Minimo:", min(lista_numeros))
print("Suma:", sum(lista_numeros))
print("Media", sum(lista_numeros) / len(lista_numeros))