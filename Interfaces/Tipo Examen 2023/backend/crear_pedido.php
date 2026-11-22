<?php
require_once('config.php');

$pedido = json_decode($_POST["pedido"]);

try {
    // Obtener conexión a la BBDD (usa la configuración centralizada)
    $conexion = obtenerConexion();

    $sql = "INSERT INTO pedidos(idpedido, cliente, idplato, unidades) VALUES (null, '$pedido->cliente', $pedido->idplato, $pedido->unidades)";

    $resultado = $conexion->query($sql);

    responder(null, true, "Pedido guardado en la base de datos", $conexion);

} catch (mysqli_sql_exception $e) {
    responder(null, false, "Error en la base de datos: " . $e->getMessage(), $conexion ?? null);

} catch (Exception $e) {
    // Captura cualquier otra excepción / error inesperado
    responder(null, false, "Error general: " . $e->getMessage(), $conexion ?? null);
}