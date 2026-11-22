<?php
require_once('config.php');

try {
    $conexion = obtenerConexion();

    $cliente = $_POST["cliente"] ?? $_GET["cliente"] ?? "";
    $clienteEscapado = $conexion->real_escape_string($cliente);

    $sql = "SELECT p.idpedido, pl.nombre AS plato, p.unidades, pl.precio
            FROM pedidos AS p
            INNER JOIN platos AS pl ON p.idplato = pl.idplato
            WHERE p.cliente = '$clienteEscapado'
            ORDER BY p.idpedido ASC";

    $resultado = $conexion->query($sql);

    if ($resultado === false) {
        responder(null, false, "Error en la consulta: " . $conexion->error, $conexion);
    }

    $datos = [];
    while ($fila = $resultado->fetch_assoc()) {
        $datos[] = $fila;
    }

    responder($datos, true, "Pedidos recuperados correctamente", $conexion);
} catch (mysqli_sql_exception $e) {
    responder(null, false, "Error en la base de datos: " . $e->getMessage(), $conexion ?? null);
} catch (Exception $e) {
    responder(null, false, "Error general: " . $e->getMessage(), $conexion ?? null);
}