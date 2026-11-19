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
        return "\n".join(filas)