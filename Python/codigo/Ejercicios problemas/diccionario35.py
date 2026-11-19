entradas =  [‘Pedro’, ‘María’, ‘Pedro’, ‘Juan’, ‘María’,‘Marta’ ]

registro = {}

entrado = False

for nombre in entradas:
    if nombre not in asistencia:
        registro[nombre] = True
    else:
        registro[nombre] = (not asistencia[nombre])

en_edificio = [nombre for nombre, dentro in asistencia.items() if dentro]

print(asistencia)
print(en_edificio)