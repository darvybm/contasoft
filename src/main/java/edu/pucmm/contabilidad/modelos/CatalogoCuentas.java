package edu.pucmm.contabilidad.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class CatalogoCuentas {
    private ArrayList<Cuenta> cuentas;

    public CatalogoCuentas() {
        cuentas = new ArrayList<>();
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

}