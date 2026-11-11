# Apartado a)
def entrada_datos():
    lista_equipos = []

    for i in range(3):
        nombre_equipo = input(("Nombre del equipo: "))
        puntos_a_favor = int(input("Puntos a favor: "))
        puntos_en_contra = int(input("Puntos en contra: "))
        partidos_jugados = int(input("Partidos jugados: "))

        equipo = (nombre_equipo, puntos_a_favor, puntos_en_contra, partidos_jugados)
        lista_equipos.append(equipo)

    return lista_equipos

# Apartado b)
def calculo_estadisticas(lista_datos):
    dicc_datos = {}
    for nombre, p_favor, p_contra, p_jugados in lista_datos:
        diferencia = p_favor - p_contra
        tasa_diferencia = diferencia / p_jugados

        dicc_datos[nombre] = (diferencia, tasa_diferencia)
    return dicc_datos

# Apartado c)

def extremos(dicc_datos):
    equipo_mayor = max(dicc_datos, key=lambda x: dicc_datos[x][0])
    equipo_menor = min(dicc_datos, key=lambda x: dicc_datos[x][0])

    return [equipo_mayor, equipo_menor]

# Apartado d)

def guardar_datos(dicc_datos):

    with open('baloncesto.csv', 'w') as f:
        for equipo, valores in dicc_datos.items():
            f.write(f"{equipo}, {valores[0]}, {valores[1]}\n")

# Apartado e)
def main():
    # 1. Llamamos a entrada_datos() y guardamos la lista
    lista = entrada_datos()
    
    # 2. Llamamos a calculo_estadisticas() pasándole la lista anterior
    dicc = calculo_estadisticas(lista)
    
    # 3. Mostramos por pantalla el resultado de llamar a extremos() pasándole el diccionario
    print("\nEquipos extremo (mayor y menor diferencia):")
    print(extremos(dicc))
    
    # 4. Guardamos los datos en el fichero llamando a guardar_datos() con el diccionario
    guardar_datos(dicc)
    print("\n¡Datos guardados correctamente en 'baloncesto.csv'!")

# Disparador principal para ejecutar el programa
if __name__ == "__main__":
    main()