package edu.pucmm.contabilidad.modelos;

import java.util.ArrayList;

public class CuentaT {
    private Cuenta cuenta;
    private ArrayList<Double> debito;
    private ArrayList<Double> credito;
    private double saldo;

    public CuentaT(Cuenta cuenta) {
        this.cuenta = cuenta;
        this.debito = new ArrayList<>();
        this.credito = new ArrayList<>();
        this.saldo = 0.0;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public ArrayList<Double> getDebito() {
        return debito;
    }

    public void setDebito(ArrayList<Double> debito) {
        this.debito = debito;
    }

    public ArrayList<Double> getCredito() {
        return credito;
    }

    public void setCredito(ArrayList<Double> credito) {
        this.credito = credito;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSumaD() {
        double saldoTotalDebito = 0;
        for (Double montoDebito : getDebito()) {
            saldoTotalDebito += montoDebito;
        }
        return saldoTotalDebito;
    }

    public double getSumaC() {
        double saldoTotalCredito = 0;
        for (Double montoCredito : getCredito()) {
            saldoTotalCredito += montoCredito;
        }
        return saldoTotalCredito;
    }
}
