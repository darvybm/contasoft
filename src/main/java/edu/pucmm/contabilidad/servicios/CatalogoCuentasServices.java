package edu.pucmm.contabilidad.servicios;

import java.util.ArrayList;

import edu.pucmm.contabilidad.modelos.*;

public class CatalogoCuentasServices {
    private static CatalogoCuentasServices instance;

    private CatalogoCuentasServices() {
    }

    public static CatalogoCuentasServices getInstance() {
        if (instance == null) {
            instance = new CatalogoCuentasServices();
        }
        return instance;
    }

    public void addCuenta(Cuenta cuenta, CicloContable cicloContable) {
        if (!existeCuenta(cuenta, cicloContable)) {
            cicloContable.getCatalogoCuentas().getCuentas().add(cuenta);
        }
    }

    public Cuenta getCuentaByCodigo(String codigo, CicloContable cicloContable) {
        return cicloContable.getCatalogoCuentas().getCuentas().stream()
                .filter(cuenta -> cuenta.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public boolean existeCuenta(Cuenta cuenta, CicloContable cicloContable) {
        return cicloContable.getCatalogoCuentas().getCuentas().stream()
                .anyMatch(c -> c.getCodigo().equals(cuenta.getCodigo()));
    }

    public ArrayList<Cuenta> getCuentas(CicloContable cicloContable) {
        return cicloContable.getCatalogoCuentas().getCuentas();
    }

    public void agregarCuentasByDefault(CicloContable cicloContable) {
        //Activos
        Cuenta efectivo = new Cuenta("111", "Efectivo", TipoCuenta.ACTIVO);
        Cuenta cuentasPorCobrar = new Cuenta("112", "Cuentas por Cobrar", TipoCuenta.ACTIVO);
        Cuenta insumos = new Cuenta("114", "Insumos", TipoCuenta.ACTIVO);
        Cuenta materialesDeOficina = new Cuenta("115", "Materiales de Oficina", TipoCuenta.ACTIVO);
        Cuenta terreno = new Cuenta("121", "Terreno", TipoCuenta.ACTIVO);
        Cuenta mobiliariosYEquipos = new Cuenta("123", "Mobiliarios y Equipos", TipoCuenta.ACTIVO);

        //Pasivos
        Cuenta documentosPorPagar = new Cuenta("211", "Documentos por pagar", TipoCuenta.PASIVO);
        Cuenta cuentasPorPagar = new Cuenta("212", "Cuentas por pagar", TipoCuenta.PASIVO);

        //Capital
        Cuenta capital = new Cuenta("311", "Capital", TipoCuenta.CAPITAL);

        //Retiros
        Cuenta retiros = new Cuenta("312", "Retiros", TipoCuenta.RETIRO);

        //Ingresos
        Cuenta ingresosPorServicio = new Cuenta("411", "Ingresos por Servicios", TipoCuenta.INGRESO);

        //Gastos
        Cuenta gastosPorSueldo = new Cuenta("612.01", "Gastos por Sueldos", TipoCuenta.GASTO);
        Cuenta gastosPorRenta = new Cuenta("612.05", "Gastos por Renta", TipoCuenta.GASTO);
        Cuenta gastosPorTelefonos = new Cuenta("612.07", "Gastos por Telefonos", TipoCuenta.GASTO);
        Cuenta gastosPorEnergiaElectrica = new Cuenta("612.1", "Gastos por Energia Electrica", TipoCuenta.GASTO);

        //Agregando Cuentas
        addCuenta(efectivo, cicloContable);
        addCuenta(cuentasPorCobrar, cicloContable);
        addCuenta(insumos, cicloContable);
        addCuenta(materialesDeOficina, cicloContable);
        addCuenta(terreno, cicloContable);
        addCuenta(mobiliariosYEquipos, cicloContable);
        addCuenta(documentosPorPagar, cicloContable);
        addCuenta(cuentasPorPagar, cicloContable);
        addCuenta(capital, cicloContable);
        addCuenta(retiros, cicloContable);
        addCuenta(ingresosPorServicio, cicloContable);
        addCuenta(gastosPorSueldo, cicloContable);
        addCuenta(gastosPorRenta, cicloContable);
        addCuenta(gastosPorTelefonos, cicloContable);
        addCuenta(gastosPorEnergiaElectrica, cicloContable);
    }
}
