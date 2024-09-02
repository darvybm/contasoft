package edu.pucmm.contabilidad.servicios;

import edu.pucmm.contabilidad.modelos.*;

import java.util.ArrayList;

public class LibroMayorServices {
    private static LibroMayorServices instancia = null;

    private LibroMayorServices() {
    }

    public static LibroMayorServices getInstancia() {
        if (instancia == null) {
            instancia = new LibroMayorServices();
        }
        return instancia;
    }

    public LibroMayor getLibroMayor(CicloContable cicloContable) {
        return cicloContable.getLibroMayor();
    }

    public void setLibroMayor(LibroMayor libroMayor, CicloContable cicloContable) {
        cicloContable.setLibroMayor(libroMayor);
    }

    public void agregarCuentaT(CuentaT cuentaT, CicloContable cicloContable) {
        cicloContable.getLibroMayor().getCuentasT().add(cuentaT);
    }

    public ArrayList<CuentaT> getCuentasT(CicloContable cicloContable) {
        return cicloContable.getLibroMayor().getCuentasT();
    }

    public void generarLibroMayor(LibroDiario libroDiario, CicloContable cicloContable) {
        setLibroMayor(new LibroMayor(), cicloContable);
        for (Asiento asiento: libroDiario.getAsientos()) {
            for (Transaccion transaccion: asiento.getTransacciones()) {
                if(cuentaTExiste(transaccion.getCuenta(), cicloContable)){
                    CuentaT cuentaT = buscarCuentaT(transaccion.getCuenta().getCodigo(), cicloContable);
                    if (transaccion.getTipo().equals("D")) {
                        cuentaT.getDebito().add(transaccion.getMonto());
                    } else if (transaccion.getTipo().equals("C")) {
                        cuentaT.getCredito().add(transaccion.getMonto());
                    }
                }
                else {
                    CuentaT cuentaT = new CuentaT(transaccion.getCuenta());
                    if (transaccion.getTipo().equals("D")) {
                        cuentaT.getDebito().add(transaccion.getMonto());
                    } else if (transaccion.getTipo().equals("C")) {
                        cuentaT.getCredito().add(transaccion.getMonto());
                    }
                    agregarCuentaT(cuentaT, cicloContable);
                }
            }
        }
        for (CuentaT cuentaT: getCuentasT(cicloContable)) {
            double montoDebito = calcularSaldoTotalDebito(cuentaT);
            double montoCredito = calcularSaldoTotalCredito(cuentaT);

            if (cuentaT.getCuenta().getTipo().equals(TipoCuenta.ACTIVO)) {
                cuentaT.setSaldo(montoDebito - montoCredito);
            } else if (cuentaT.getCuenta().getTipo().equals(TipoCuenta.PASIVO)) {
                cuentaT.setSaldo(montoCredito - montoDebito);
            } else if (cuentaT.getCuenta().getTipo().equals(TipoCuenta.INGRESO)) {
                cuentaT.setSaldo(montoCredito - montoDebito);
            } else if (cuentaT.getCuenta().getTipo().equals(TipoCuenta.GASTO)) {
                cuentaT.setSaldo(montoDebito - montoCredito);
            } else if (cuentaT.getCuenta().getTipo().equals(TipoCuenta.CAPITAL)) {
                cuentaT.setSaldo(montoCredito - montoDebito);
            } else {
                cuentaT.setSaldo(montoDebito - montoCredito);
            }
        }
    }

    private CuentaT buscarCuentaT(String codigo, CicloContable cicloContable) {
        return getCuentasT(cicloContable).stream()
                .filter(cuentaT -> cuentaT.getCuenta().getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    private boolean cuentaTExiste(Cuenta cuenta, CicloContable cicloContable) {
        return getCuentasT(cicloContable).stream()
                .anyMatch(cuentaT -> cuentaT.getCuenta().getCodigo().equals(cuenta.getCodigo()));
    }

    public double calcularSaldoTotalDebito(CuentaT cuentaT) {
        double saldoTotalDebito = 0;
        for (Double montoDebito : cuentaT.getDebito()) {
            saldoTotalDebito += montoDebito;
        }
        return saldoTotalDebito;
    }

    public double calcularSaldoTotalCredito(CuentaT cuentaT) {
        double saldoTotalCredito = 0;
        for (Double montoCredito : cuentaT.getCredito()) {
            saldoTotalCredito += montoCredito;
        }
        return saldoTotalCredito;
    }

    public double totalDebitoBalanzaComprobacion(CicloContable cicloContable) {
        double totalDebito = 0;
        for (CuentaT cuentaT: getCuentasT(cicloContable)){
            if (cuentaT.getCuenta().getTipo().equals(TipoCuenta.ACTIVO) ||
                    cuentaT.getCuenta().getTipo().equals(TipoCuenta.RETIRO) ||
                    cuentaT.getCuenta().getTipo().equals(TipoCuenta.GASTO)) {
                totalDebito += cuentaT.getSaldo();
            }
        }
        return totalDebito;
    }

    public double totalCreditoBalanzaComprobacion(CicloContable cicloContable) {
        double totalCredito = 0;
        for (CuentaT cuentaT: getCuentasT(cicloContable)){
            if (!(cuentaT.getCuenta().getTipo().equals(TipoCuenta.ACTIVO) ||
                    cuentaT.getCuenta().getTipo().equals(TipoCuenta.RETIRO) ||
                    cuentaT.getCuenta().getTipo().equals(TipoCuenta.GASTO))) {
                totalCredito += cuentaT.getSaldo();
            }
        }
        return totalCredito;
    }

    public CuentaT getCuentaTByCodigo(String codigo, CicloContable cicloContable) {
        return  getCuentasT(cicloContable).stream()
                .filter(cuenta -> cuenta.getCuenta().getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

}
