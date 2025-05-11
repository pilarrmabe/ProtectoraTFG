package com.pilarmabe.base.main;

import com.pilarmabe.base.mvc.Vista;
import com.pilarmabe.base.mvc.Controlador;
import com.pilarmabe.base.mvc.Modelo;


public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
