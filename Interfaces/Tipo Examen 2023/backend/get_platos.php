<?php
require_once('config.php');

try {
    $conexion = obtenerConexion();

    $sql = "SELECT idplato, nombre, descripcion, precio FROM platos";
    $resultado = $conexion->query($sql);

    if ($resultado === false) {
        responder(null, false, "Error en la consulta: " . $conexion->error, $conexion);
    }

    $datos = [];
    while ($fila = $resultado->fetch_assoc()) {
        $datos[] = $fila;
    }

    responder($datos, true, "Platos recuperados correctamente", $conexion);
} catch (mysqli_sql_exception $e) {
    responder(null, false, "Error en la base de datos: " . $e->getMessage(), $conexion ?? null);
} catch (Exception $e) {
    responder(null, false, "Error general: " . $e->getMessage(), $conexion ?? null);
}