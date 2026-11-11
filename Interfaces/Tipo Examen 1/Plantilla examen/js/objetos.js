"use strict";

// Codificar aquí las clases

// 1º Creo la clase Libro con su constructor
class Libro{
    constructor(idlibro, idgenero, titulo, autor, descripcion, imagen){
        this._idlibro = idlibro;
        this._idgenero = idgenero;
        this._titulo = titulo;
        this._autor = autor;
        this._descripcion = descripcion;
        this._imagen = imagen;
    }

// 2º Creo los get y set por si necesito leer o modificar fuera de la clase
    get idlibro() {
    return this._idlibro;
    }

    get idgenero(){
        return this._idgenero;
    }

    get titulo(){
        return this._titulo;
    }

    get autor(){
        return this.autor;
    }

    get descripcion(){
        return this._descripcion;
    }

    get imagen(){
        return this._imagen;
    }

    set idlibro(value){
        this._idlibro = value;
    }

    set idgenero(value){
        this._idgenero = value;
    }

    set titulo(value){
        this._titulo = value;
    }

    set autor(value){
        this._autor = value;
    }

    set descripcion(value){
        this._descripcion = value;
    }

    set imagen(value){
        this._imagen = value;
    }

// 3º Ahora me tocaría crear el toJSON() que sirve para enviar los datos de JS a un script PHP

    toJSON(){
        return{
            idlibro: this.idlibro,
            idgenero: this.idgenero,
            titulo: this.titulo,
            autor: this.autor,
            descripcion: this.descripcion,
            imagen: this.imagen
        }
    }
}

class Genero{
    constructor(idgenero, genero){
        this._idgenero = idgenero;
        this._genero = genero;
    }

    get idgenero(){
        return this._idgenero;
    }

    get genero(){
        return this._genero;
    }

    set idgenero(value){
        this._idgenero = value;
    }

    set genero(value){
        this._genero = value;
    }

    toJSON(){
        return{
            idgenero: this.idgenero,
            genero: this.genero
        }
    }
}

class GestorNotas{

    // Metodo para crear un Libro
    async crearLibro(oLibro){
        let respuesta;

        try {
            
            // Convierto el objeto libro a un FormDara o envio su toJSON()
            let formData = new FormData();
            FormData.append("idlibro", oLibro.idlibro);
            FormData.append("idgenero", oLibro.idgenero);
            FormData.append("titulo", oLibro.titulo);
            FormData.append("autor", oLibro.autor);
            FormData.append("descripcion", oLibro.descripcion);
            FormData.append("imagen", oLibro.imagen);

            let respuestaServidor = await fetch("php/crear_libro.php", {
                method: "POST",
                body: FormData
            });

            if (!respuestaServidor.ok){
                respuesta = {
                    ok: false,
                    mensaje: "Error al crear el libro en el servidor",
                    status: respuestaServidor.status,
                    datos: null
                };
            } else {
                let datosServidor = await respuestaServidor.json();
                respuesta = {
                    ok: datosServidor.ok,
                    mensaje: datosServidor.mensaje || "OK",
                    status: respuestaServidor.status,
                    datos: null
                };
            }

        } catch (error) {
            response = {
                ok: false,
                mensaje: "Error de red: " + error.message,
                status: 0,
                datos: null
            };
        }
    }


    // Metodo que recupera los libros de un género específico
    async getLibrosGenero(idgenero){

        let respuesta;

        try {
        let formData = new FormData();
        formData.append("idgenero", idgenero);

        let respuestaServidor = await fetch("php/get_libro_genero.php", {
            method: "POST",
            body: FormData
        });

        if (!respuestaServidor.ok){
            respuesta = {
                ok: false,
                mensaje: "Error al acceder al servidor",
                status: respuestaServidor.status,
                datos: null
            };
        } else {
                respuesta = {
                    ok: true,
                    mensaje: "OK",
                    status: respuestaServidor.status,
                    datos: await respuestaServidor.json()
                };
            }
        } catch (error){
            respuesta = {
                ok: false,
                mensaje: "Error de red: " + error.message,
                status: 0,
                datos: null
            };
        }

        return respuesta;
    }

    // Metodo que recupera todos los géneros de la base de datos
    async getGeneros(){
        let respuesta;

        try {
            let respuestaServidor = await fetch("php/get_genero.php", {method: "GET"});
            
            if (!respuestaServidor.ok){
                respuesta = {
                    ok: false,
                    mensaje: "Error al acceder al servidor",
                    status: respuestaServidor.status,
                    datos: null
                };
            } else{
                respuesta = {
                    ok: true,
                    mensaje: "OK",
                    status: respuestaServidor.status,
                    datos: await respuestaServidor.json()
                };
            }

        } catch (error) {
            respuesta = {
                ok: false,
                mensaje: "Error de red: " + error.message
            }
        }

        return respuesta;
    }
}