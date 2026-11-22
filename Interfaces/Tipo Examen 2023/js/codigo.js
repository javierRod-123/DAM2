"use strict";

// MAIN
const oBar = new Bar();

registrarEventos();

function registrarEventos() {
    document.getElementById("mnuAltaPedido").addEventListener("click", mostrarFormulario);
    document.getElementById("mnuListadoPedidosCliente").addEventListener("click", mostrarFormulario);
    document.getElementById("btnAceptarAltaPedido").addEventListener("click", procesarAltaPedido);
    document.getElementById("btnListadoPedidosCliente").addEventListener("click", procesarListadoPedidosCliente);
}

function mostrarFormulario(oEvento) {
    oEvento.preventDefault();
    let opcion = oEvento.target.id;

    ocultarTodo();

    switch (opcion) {
        case "mnuAltaPedido":
            cargarPlatos();
            document.getElementById("frmAltaPedido").style.display = "block";
            break;
        case "mnuListadoPedidosCliente":
            document.getElementById("frmListadoPedidosCliente").style.display = "block";
            break;
    }
}

function ocultarTodo() {
    document.getElementById("frmAltaPedido").style.display = "none";
    document.getElementById("frmListadoPedidosCliente").style.display = "none";
    document.getElementById("listado").style.display = "none";
}

async function cargarPlatos() {
    let platos = await oBar.getPlatos();
    let options = "";

    for (let plato of platos) {
        options += `<option value="${plato.idplato}">${plato.nombre}</option>`;
    }

    document.getElementById("platosSelect").innerHTML = options;
}

async function procesarAltaPedido() {
    let cliente = document.getElementById("txtNombreClienteAltaCliente").value.trim();
    let idplato = Number(document.getElementById("platosSelect").value);
    let radioSeleccionado = document.querySelector('input[name="rdoUnidades"]:checked');
    let unidades = radioSeleccionado ? Number(radioSeleccionado.value) : 1;

    let oPedido = new Pedido(null, cliente, idplato, unidades);
    let respuesta = await oBar.altaPedido(oPedido);

    alert(respuesta.mensaje);

    if (!respuesta.error) {
        document.getElementById("frmAltaPedido").reset();
        document.getElementById("frmAltaPedido").style.display = "none";
    }
}

async function procesarListadoPedidosCliente() {
    let nombreCliente = document.getElementById("txtNombreClienteListado").value.trim();

    let tablaHtml = await oBar.listadoPedidosCliente(nombreCliente);
    let divListado = document.getElementById("listado");

    divListado.innerHTML = tablaHtml;
    divListado.style.display = "block";
}