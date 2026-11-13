class Usuario:
    def __init__(self, nombre, direccion):
        self._nombre = nombre
        self._direccion = direccion

    @property
    def nombre(self):
        return self._nombre

    @property
    def direccion(self):
        return self._direccion

    @nombre.setter
    def nombre(self, nuevo_nombre):
        self._nombre = nuevo_nombre

    @direccion.setter
    def direccion(self, nueva_direccion):
        self._direccion = nueva_direccion

    def __str__(self):
        return f"Nombre: {self._nombre} - Direccion: {self._direccion}"

class Monitor(Usuario):
    def __init__(self, nombre, direccion, identificador):
        super().__init__(nombre, direccion)
        self._identificador = identificador

    @property
    def identificador(self):
        return self._identificador

    @identificador.setter
    def identificador(self, nuevo_identificador):
        self._identificador = nuevo_identificador

    def __str__(self):
        return f"Nombre: {self._nombre} - Direccion: {self._direccion} - Identificador: {self._identificador}"

class Cliente(Usuario):
    def __init__(self, nombre, direccion, dni):
        super().__init__(nombre, direccion)
        self._dni = dni

    @property
    def dni(self):
        return self._dni

    @dni.setter
    def dni(self, nuevo_dni):
        self._dni = nuevo_dni

    def __str__(self):
        return f"Nombre: {self._nombre} - Direccion: {self._direccion} - DNI: {self._dni}"

usuario = Usuario("Pedro Picapiedra", "Calle Cantera 1")
monitor = Monitor("Marta Mármol", "Avenida Piedra 2", 50)
cliente = Cliente("Bambam", "Calle Dinosaurio 3", "99999999Z")

print("INFORMACIÓN INICIAL")
print(usuario)
print(monitor)
print(cliente)

usuario.direccion = "Calle Pedregal 4"
monitor.identificador = 75
cliente.dni = "11111111A"

print("\nINFORMACIÓN ACTUALIZADA")
print(usuario)
print(monitor)
print(cliente)

