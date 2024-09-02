package edu.pucmm.contabilidad.modelos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Asiento {

    private UUID uuid;
    private String fecha;
    private String descripcion;
    private ArrayList<Transaccion> transacciones;

    public Asiento(UUID uuid, String fecha) {
        this.uuid = uuid;
        this.fecha = fecha;
        this.descripcion = "";
        this.transacciones = new ArrayList<>();
    }

    public Asiento(UUID uuid, String fecha, String descripcion) {
        this.uuid = uuid;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.transacciones = new ArrayList<>();
    }

    public int getSize() {
        return transacciones.size();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransaccions(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
}
