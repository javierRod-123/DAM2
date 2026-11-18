class Animal:
    def __init__(self, idAnimal, especie)
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


class Herbivoro:
    def __init__(self, idAnimal, especie, kgDia):
        super().__init__(idAnimal, especie)
        self.__kgDia = kgDia

    @property
    def kgDia(self):
        return self.__kgDia


    def __str__(self):
        return f"{super().__str__()}, Kg de pasto al día: {self.__kg_dia}"

