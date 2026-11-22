"use strict";

class Pedido {
  #idpedido;
  #cliente;
  #idplato;
  #unidades;

  constructor(idpedido, cliente, idplato, unidades) {
    this.#idpedido = idpedido;
    this.#cliente = cliente;
    this.#idplato = idplato;
    this.#unidades = unidades;
  }

  get idpedido() {
    return this.#idpedido;
  }

  get cliente() {
    return this.#cliente;
  }

  get idplato() {
    return this.#idplato;
  }

  get unidades() {
    return this.#unidades;
  }

  set idpedido(value) {
    this.#idpedido = value;
  }

  set cliente(value) {
    this.#cliente = value;
  }

  set idplato(value) {
    this.#idplato = value;
  }

  set unidades(value) {
    this.#unidades = value;
  }

  toJSON() {
    return {
      idpedido: this.#idpedido,
      cliente: this.#cliente,
      idplato: this.#idplato,
      unidades: this.#unidades,
    };
  }
}

class Plato {
  #idplato;
  #nombre;
  #descripcion;
  #precio;

  constructor(idplato, nombre, descripcion, precio) {
    this.#idplato = idplato;
    this.#nombre = nombre;
    this.#descripcion = descripcion;
    this.#precio = precio;
  }

  get idplato() {
    return this.#idplato;
  }

  get nombre() {
    return this.#nombre;
  }

  get descripcion() {
    return this.#descripcion;
  }

  get precio() {
    return this.#precio;
  }

  set idplato(value) {
    this.#idplato = value;
  }

  set nombre(value) {
    this.#nombre = value;
  }

  set descripcion(value) {
    this.#descripcion = value;
  }

  set precio(value) {
    this.#precio = value;
  }

  toJSON() {
    return {
      idplato: this.#idplato,
      nombre: this.#nombre,
      descripcion: this.#descripcion,
      precio: this.#precio,
    };
  }
}

class Bar {
    async altaPedido(oPedido) {
        let datos = new FormData();
        datos.append("pedido", JSON.stringify(oPedido));

        let respuesta = await peticionPOST("crear_pedido.php", datos);
        return respuesta;
    }

    async getPlatos() {
        let datos = new FormData();
        let respuesta = await peticionGET("get_platos.php", datos);

        if (respuesta && respuesta.ok && Array.isArray(respuesta.datos)) {
            return respuesta.datos;
        }
        return [];
    }

    async listadoPedidosCliente(nombreCliente) {
        let datos = new FormData();
        datos.append("cliente", nombreCliente);

        let respuesta = await peticionPOST("listado_pedidos_cliente.php", datos);
        let filas = (respuesta && respuesta.ok && Array.isArray(respuesta.datos)) ? respuesta.datos : [];

        if (filas.length === 0) {
            return `<p class="mt-3">No hay pedidos para el cliente <strong>${nombreCliente}</strong>.</p>`;
        }

        let html = `<h3>PEDIDOS DE ${nombreCliente}</h3>`;
        html += `<table class="table table-striped table-bordered mt-3">`;
        html += `<thead><tr><th>IDPEDIDO</th><th>PLATO</th><th>UNIDADES</th><th>PRECIO</th><th>TOTAL</th></tr></thead>`;
        html += `<tbody>`;

        for (let fila of filas) {
            let unidades = Number(fila.unidades);
            let precio = Number(fila.precio);
            let total = unidades * precio;

            html += `<tr>`;
            html += `<td>${fila.idpedido}</td>`;
            html += `<td>${fila.plato}</td>`;
            html += `<td>${unidades}</td>`;
            html += `<td>${precio.toFixed(2)}</td>`;
            html += `<td>${total.toFixed(2)}</td>`;
            html += `</tr>`;
        }

        html += `</tbody></table>`;
        return html;
    }
}