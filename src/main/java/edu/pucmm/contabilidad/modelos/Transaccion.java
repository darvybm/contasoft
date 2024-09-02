package edu.pucmm.contabilidad.modelos;

import java.util.UUID;

public class Transaccion {

    private UUID uuid;
    private Cuenta cuenta;
    private double monto;
    private String tipo; // "D" para débito, "C" para crédito
    //hola

    public Transaccion(UUID uuid, Cuenta cuenta, double monto, String tipo) {
        this.uuid = uuid;
        this.cuenta = cuenta;
        this.monto = monto;
        this.tipo = tipo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}