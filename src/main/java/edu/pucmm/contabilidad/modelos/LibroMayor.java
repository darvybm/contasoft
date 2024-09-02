package edu.pucmm.contabilidad.modelos;

import java.util.ArrayList;
//hola

public class LibroMayor {
    ArrayList<CuentaT> cuentasT;

    public LibroMayor() {
        this.cuentasT = new ArrayList<>();
    }

    public ArrayList<CuentaT> getCuentasT() {
        return cuentasT;
    }

    public void setCuentasT(ArrayList<CuentaT> cuentasT) {
        this.cuentasT = cuentasT;
    }
}
