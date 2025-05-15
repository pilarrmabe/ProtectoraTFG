package com.pilarmabe.base.clases;

public class ValoresCombos {
    public enum TipoUsuario {
        ADMIN("Administrador"),
        EDICION("Edición"),
        CONSULTA("Consulta");

        private String tipo;
        
        TipoUsuario(String tipo) {
            this.tipo = tipo;
        }
        
        @Override
        public String toString() {
            return tipo;
        }
    }

    public enum Especie {
        PERRO("Perro"),
        GATO("Gato"),
        CONEJO("Conejo"),
        HURON("Hurón"),
        ROEDOR("Roedor"),
        AVE("Ave"),
        REPTIL("Reptil"),
        PEZ("Pez");
        
        private String nombre;
        
        Especie(String nombre) {
            this.nombre = nombre;
        }
        
        @Override
        public String toString() {
            return nombre;
        }
    }
}   
