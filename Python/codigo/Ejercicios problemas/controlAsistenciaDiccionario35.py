# Creo la lista con los nombres de los empleados:

empleados_arco = ['Pedro', 'María', 'Pedro', 'Juan', 'María', 'Marta']

d = {}

for nombre in empleados_arco:
    if nombre in d:
        d[nombre] = not d[nombre]
    else:
        d[nombre] = True

print(d)

personas_dentro = []

for nombre, estado in d.items():
    if estado == True:
        personas_dentro.append(nombre)

print(personas_dentro)