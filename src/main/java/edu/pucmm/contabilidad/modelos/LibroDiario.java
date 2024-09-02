package edu.pucmm.contabilidad.modelos;

import java.util.ArrayList;

public class LibroDiario {
    private ArrayList<Asiento> asientos;

    public LibroDiario() {
        this.asientos = new ArrayList<Asiento>();
    }

    public ArrayList<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(ArrayList<Asiento> asientos) {
        this.asientos = asientos;
    }
}