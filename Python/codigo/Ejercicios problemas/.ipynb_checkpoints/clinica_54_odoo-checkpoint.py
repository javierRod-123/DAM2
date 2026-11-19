class Mascota:
    def __init__(self, idMascota, peso):
        self.__idMascota = idMascota
        self.__peso = peso

    @property
    def idMascota(self):
        return self.__idMascota

    @property
    def peso(self):
        return self.__peso

    def __str__(self):
        return f"ID: {self.__idMascota}, Peso: {self.__peso}"

class Perro(Mascota):
    def __init__(self, idMascota, peso, altura):
        super().__init__(idMascota, peso)
        self.__altura = altura

    @property
    def altura(self):
        return self.__altura

    def __str__(self):
        return f"{super().__str__()}, Altura: {self.__altura}"


class Gato(Mascota):
    def __init__(self, idMascota, peso, raza):
        super().__init__(idMascota, peso)
        self.__raza = raza


    @property
    def raza(self):
        return self.__raza


    def __str__(self):
        return f"{super().__str__()}, Raza: {self.__raza}"



class Clinica:
    def __init__(self):
        self.mascotas = []

    def altaMascota(self, oMascota):
        for mascota in self.mascotas:
            if mascota.idMascota == oMascota.idMascota:
                return "Mascota repetida"
        self.mascotas.append(oMascota)
        return "Alta OK"


    def bajaMascota(self, idMascota):
        for mascota in self.mascotas:
            if mascota.idMascota == idMascota:
                self.mascotas.remove(mascota)
                return "Mascota dada de baja"

        return "Mascota no localizada"

    def pesoMaximoGato(self):
        paso_maximo = 0
        for mascota in self.mascotas:
            if isinstance(mascota, Gato):
                if mascota.peso > peso_maximo:
                    peso_maximo = mascota.peso

        return peso_maximo

    def listadoPerros(self):
        filas = []
        for mascota in self.mascotas:
            if isinstance(mascota, Perro):
                filas.append(str(mascota))
        return "\n".join(filas)

# --- PROGRAMA PRINCIPAL DE DEMOSTRACIÓN ---
clinica = Clinica()  #

# 1. Creamos objetos de prueba (idMascota, peso, y atributo propio)
perro1 = Perro(1, 18000, 55)  
perro2 = Perro(2, 12000, 40)
gato1 = Gato(3, 4200, "Siamés") 
gato2 = Gato(4, 5100, "Común")

# 2. Probamos altas y duplicados
print(clinica.altaMascota(perro1))  # Debería mostrar "Alta OK"
print(clinica.altaMascota(perro2))  # Debería mostrar "Alta OK"
print(clinica.altaMascota(gato1))  # Debería mostrar "Alta OK"
print(clinica.altaMascota(gato2))  # Debería mostrar "Alta OK"
print(
    clinica.altaMascota(perro1)
)  # Debería mostrar "Mascota repetida"

# 3. Probamos listado de perros y peso máximo de gato
print("\n--- Listado de perros ---")
print(clinica.listadoPerros())  #

print("\n--- Peso máximo de gato ---")
print(clinica.pesoMaximoGato())  #

# 4. Probamos bajas (existente y no existente)
print("\n--- Bajas ---")
print(clinica.bajaMascota(2))  # Debería mostrar "Mascota dada de baja"
print(clinica.bajaMascota(99))  # Debería mostrar "Mascota no localizada"