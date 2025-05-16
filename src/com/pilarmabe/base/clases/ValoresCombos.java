package com.pilarmabe.base.clases;

public class ValoresCombos {
    public enum TipoUsuario {
        ADMINISTRADOR("Administrador"),
        EDICIÓN("Edición"),
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

    public enum Raza {
        MEZCLA("Mezcla"),
        BEAGLE("Beagle"),
        BULLDOG("Bulldog"),
        BOXER("Boxer"),
        PASTOR_ALEMAN("Pastor Alemán"),
        PASTOR_BELGA("Pastor Belga"),
        PASTOR_BULTERIER("Pastor Bultier"),
        PASTOR_CANARIO("Pastor Canario"),
        PASTOR_SUIZO("Pastor Suizo"),
        ROTTWEILER("Rottweiler"),
        PITBULL("Pitbull"),
        DOBERMAN("Doberman");
        
        private String raza;
        
        Raza(String raza) {
            this.raza = raza;
        }
        
        @Override
        public String toString() {
            return raza;
        }
    }

    public enum Sexo {
        MACHO("Macho"),
        HEMBRA("Hembra");

        private String sexo;

        Sexo(String sexo) {
            this.sexo = sexo;
        }
        @Override
        public String toString() {
            return sexo;
        }
    }

    public enum Estado {
        EN_ADOPCION("En progreso"),
        ADOPTADO("Adoptado");

        private String estado;

        Estado(String estado) {
            this.estado = estado;
        }
        @Override 
        public String toString() {
            return estado;
        }
        
    }

    public enum Especialidad {
        MEDICINA_GENERAL("Medicina General"),
        CIRUGIA("Cirugía"),
        DENTAL("Dental"),
        OFTALMOLOGIA("Oftalmología"),
        NEUROLOGIA("Neurología"),
        TRAUMATOLOGIA("Traumatología"),
        CARDIOLOGIA("Cardiología"),
        ONCOLOGIA("Oncología");

        private String especialidad;

        Especialidad(String especialidad) {
            this.especialidad = especialidad;
        }
        
        @Override
        public String toString() {
            return especialidad;
        }
    }
    public enum noAdoptante {
        NO_ADOPTANTE("No Adoptante");

        private String noAdoptante;

        noAdoptante(String noAdoptante) {
            this.noAdoptante = noAdoptante;
        }
        
        @Override
        public String toString() {
            return noAdoptante;
        }
    }
}   
