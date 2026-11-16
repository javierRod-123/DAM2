"use strict";

// Codificar aquí las clases

// 1º Creo la clase Libro con su constructor
class Libro{

    #idlibro;
    #idgenero;
    #titulo;
    #autor;
    #descripcion;
    #imagen;

    constructor(idlibro, idgenero, titulo, autor, descripcion, imagen){
        this.#idlibro = idlibro;
        this.#idgenero = idgenero;
        this.#titulo = titulo;
        this.#autor = autor;
        this.#descripcion = descripcion;
        this.#imagen = imagen;
    }

// 2º Creo los get y set por si necesito leer o modificar fuera de la clase
    get idlibro() {
    return this.#idlibro;
    }

    get idgenero(){
        return this.#idgenero;
    }

    get titulo(){
        return this.#titulo;
    }

    get autor(){
        return this.#autor;
    }

    get descripcion(){
        return this.#descripcion;
    }

    get imagen(){
        return this.#imagen;
    }

    set idlibro(value){
        this.#idlibro = value;
    }

    set idgenero(value){
        this.#idgenero = value;
    }

    set titulo(value){
        this.#titulo = value;
    }

    set autor(value){
        this.#autor = value;
    }

    set descripcion(value){
        this.#descripcion = value;
    }

    set imagen(value){
        this.#imagen = value;
    }

// 3º Ahora me tocaría crear el toJSON() que sirve para enviar los datos de JS a un script PHP

    toJSON(){
        return{
            idlibro: this.#idlibro,
            idgenero: this.#idgenero,
            titulo: this.#titulo,
            autor: this.#autor,
            descripcion: this.#descripcion,
            imagen: this.#imagen
        }
    }
}

class Genero{

    #idgenero;
    #genero;

    constructor(idgenero, genero){
        this.#idgenero = idgenero;
        this.#genero = genero;
    }

    get idgenero(){
        return this.#idgenero;
    }

    get genero(){
        return this.#genero;
    }

    set idgenero(value){
        this.#idgenero = value;
    }

    set genero(value){
        this.#genero = value;
    }

    toJSON(){
        return{
            idgenero: this.#idgenero,
            genero: this.#genero
        }
    }
}

class Libreria{

    // Metodo para crear un Libro
    async crearLibro(oLibro){
        let datos = new FormData();

        datos.append("libro", JSON.stringify(oLibro));

        let respuesta = await peticionPOST("crear_libro.php", datos);

        return respuesta;
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