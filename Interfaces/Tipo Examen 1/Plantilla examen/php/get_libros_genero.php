<?php
require_once('config.php');

try{
    $conexion = obtenerConexion();

    $idgenero = $_GET["idgenero"];

    $sql = "SELECT l.*,g.genero 
            FROM  libro as l inner join genero as g 
            ON l.idgenero = g.idgenero
            WHERE l.idgenero = $idgenero";

    $resultado = $conexion->query($sql);

    $datos = [];

    while ($fila = $resultado->fetch_assoc()){
        $datos[] = $fila;
    }

    responder($datos, true, "Datos recuperados correctamente", $conexion);

} catch (mysqli_sql_exception $e) {
    // Errores específicos de mysqli (p. ej. problemas con la consulta o la conexión)
    // Enviamos un JSON de error. Usamos $conexion ?? null para evitar usar
    // una variable no definida si la conexión falló al crearse.
    responder(null, false, "Error en la base de datos: " . $e->getMessage(), $conexion ?? null);
} catch (Exception $e) {
    // Captura cualquier otra excepción / error inesperado
    responder(null, false, "Error general: " . $e->getMessage(), $conexion ?? null);
}