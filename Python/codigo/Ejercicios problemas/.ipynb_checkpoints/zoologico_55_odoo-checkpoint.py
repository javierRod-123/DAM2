class Animal:
    def __init__(self, idAnimal, especie):
        self.__idAnimal = idAnimal
        self.__especie = especie

    @property
    def idAnimal(self):
        return self.__idAnimal


    @property
    def especie(self):
        return self.__especie


    def __str__(self):
        return f"ID: {self.__idAnimal}, Especie: {self.__especie}"


class Herbivoro(Animal):
    def __init__(self, idAnimal, especie, kgDia):
        super().__init__(idAnimal, especie)
        self.__kgDia = kgDia

    @property
    def kgDia(self):
        return self.__kgDia


    def __str__(self):
        return f"{super().__str__()}, Kg de pasto al día: {self.__kgDia}"

class Carnivoro(Animal):
    def __init__(self, idAnimal, especie, kgCarneDia):
        super().__init__(idAnimal, especie)
        self.__kgCarneDia = kgCarneDia

    @property
    def kgCarneDia(self):
        return self.__kgCarneDia

    def __str__(self):
        return f"{super().__str__()}, Kg de carne al día: {self.__kgCarneDia}"

class Zoologico:
    def __init__(self):
        self.animales = []

    def altaAnimal(self, o_animal):
        for animal in self.animales:
            if animal.idAnimal == o.animal.idAnimal:
                return "Animal repetido"
        self.animales.append(o_animal)
        return "Alta OK"

    def baja_animal(self, id_buscado):
        for animal in self.animales:
            if animal.idAnimal == id_buscado:
                self.animales.remove(a)
                return "Animal dado de baja"
        return "Animal no localizado"

    def numKilosPasto(self):
        total_kilos = 0
        for animal in self.animales:
            if isistance(animal, Herbivoro):
                total_kilos += animal.kgDia
        return total_kilos

    def listadoDePeligrosos(self):
        filas = []
        for animal in self.animales:
            if isinstance(animal, Carnivoro) and animal.peligroso:
                filas.append(str(animal))
        return "\n".join(filas)

# --- PROGRAMA PRINCIPAL DE DEMOSTRACIÓN ---
zoo = Zoologico()

# 1. Creamos herbívoros y carnívoros de prueba
h1 = Herbivoro(1, "Cebra", 15)
h2 = Herbivoro(2, "Elefante", 80)
c1 = Carnivoro(3, "León", True)
c2 = Carnivoro(4, "Suricata", False)

# 2. Probamos altas y duplicados
print(zoo.altaAnimal(h1))  # "Alta OK"
print(zoo.altaAnimal(h2))  # "Alta OK"
print(zoo.altaAnimal(c1))  # "Alta OK"
print(zoo.altaAnimal(c2))  # "Alta OK"
print(zoo.altaAnimal(h1))  # "Animal repetido"

# 3. Probamos kilos de pasto y listado de peligrosos
print("\n--- Kilos de pasto al día ---")
print(zoo.numKilosPasto())  # Debería sumar 15 + 80 = 95

print("\n--- Animales peligrosos ---")
print(zoo.listadoPeligrosos())  # Debería mostrar solo el león

# 4. Probamos bajas (existente y no existente)
print("\n--- Bajas ---")
print(zoo.baja_animal(2))  # "Animal dado de baja"
print(zoo.baja_animal(99))  # "Animal no localizado"

# Volvemos a comprobar los kilos de pasto tras dar de baja al elefante (id 2)
print("Kilos de pasto tras baja:", zoo.numKilosPasto())  # Debería ser 15