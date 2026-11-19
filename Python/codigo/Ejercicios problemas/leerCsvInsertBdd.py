import pymysql

conn = pymysql.connect(
    host="db", user="root", password="test", database="tu_bd"
)
cursor = conn.cursor()

f = open("datos.csv", "rt", encoding="UTF-8")
# Si el CSV tiene cabecera y quieres saltarla:
# next(f)

for linea in f:
  linea = linea.strip()
  if not linea:  # Evita líneas vacías
    continue
  campos = linea.split(",")  # Cambia "," por ";" si el CSV usa punto y coma
  # Ajusta tipos si hace falta, ej.: int(campos[1]), float(campos[2])
  sql = "INSERT INTO tabla (col1, col2, col3) VALUES (%s, %s, %s)"
  cursor.execute(sql, (campos[0], campos[1], campos[2]))

f.close()
conn.commit()  # ¡Muy importante en INSERT / UPDATE / DELETE!
conn.close()