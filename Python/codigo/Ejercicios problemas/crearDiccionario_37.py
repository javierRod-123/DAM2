#1º Creo el diccionario

cuenta = input("Introduzca una cuenta:")
cuentas = {}

# Hago un while para que el usuario pide un tipo de cuenta contable hasta que ponga x
while cuenta != 'x':
    importe = float(input("Importe:"))

    if cuenta in cuentas:
        cuentas[cuenta] = cuentas[cuenta] + (importe, )
    else:
        cuentas[cuenta] = (importe, )

    cuenta = input("Introduzca una cuenta:")

    lista_cuentas = []

    for (k,v) in cuentas.items():
        lista_cuentas.append( (k, sum(v) ) )

lista_cuentas.sort(key= lambda elemento : elemento[1])
print("Cuenta con menor saldo:", lista_cuentas[0])
print("Cuenta con mayor saldo:", lista_cuentas[-1])
