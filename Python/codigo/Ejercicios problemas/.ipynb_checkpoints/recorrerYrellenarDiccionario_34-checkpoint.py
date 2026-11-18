d = {}

while len(d) < 5:
    trabajador = input("Nombre del trabajador:")
    dia = input("Día de la semana que trabaja:")

    if trabajador in d.keys():
        print("El trabajador ya tiene asignada una guardia")
    elif dia not in ['lunes','martes','miercoles','jueves','viernes']:
        print("El día no es válido")
    elif dia in d.values():
        print("El día ya se ha asignado antes")
    else:
        d[trabajador] = dia

print(d)
        