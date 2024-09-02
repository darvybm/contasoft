package edu.pucmm.contabilidad.controller;

import edu.pucmm.contabilidad.modelos.*;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import edu.pucmm.contabilidad.servicios.LibroDiarioServices;
import edu.pucmm.contabilidad.servicios.LibroMayorServices;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.*;

public class EstadosController {

    public EstadosController(Javalin app) {
        configurarRutas(app);
    }

    private void configurarRutas(Javalin app) {
        app.get("/estados", this::handleGetEstados);
    }

    private void handleGetEstados(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        String cicloContableId = ctx.queryParam("cicloContable");

        if (company == null || cicloContableId == null) {
            ctx.status(400).result("Invalid session or missing cicloContable parameter.");
            return;
        }

        CicloContable cicloContable = CompanyServices.getInstancia().buscarCicloContableById(UUID.fromString(cicloContableId), company);

        // ESTADOS DE RESULTADOS
        double totalIngresos = calculateTotalIngresos(cicloContable);
        double gastosOperativos = calculateGastosOperativos(cicloContable);
        double utilidadNeta = totalIngresos - gastosOperativos;

        // ESTADO DE CAMBIOS EN EL CAPITAL
        double inversion = getSaldoByCodigo(cicloContable, "311");
        double retiros = getSaldoByCodigo(cicloContable, "312");
        double aumentosCapital = inversion - retiros + utilidadNeta;

        // ESTADO DE SITUACION
        double totalActivos = calculateTotalByTipo(cicloContable, TipoCuenta.ACTIVO);
        double totalPasivos = calculateTotalByTipo(cicloContable, TipoCuenta.PASIVO);
        double totalCapital = calculateTotalByTipo(cicloContable, TipoCuenta.CAPITAL);
        double totalPasivosCapital = totalPasivos + aumentosCapital;

        // ESTADO DE FLUJO DE EFECTIVO
        Map<String, Object> modelo = new HashMap<>();
        populateModeloForFlujoDeEfectivo(cicloContable, modelo);

        modelo.put("totalIngreso", totalIngresos);
        modelo.put("totalGasto", gastosOperativos);
        modelo.put("utilidadNeta", utilidadNeta);
        modelo.put("retiros", retiros);
        modelo.put("inversion", inversion);
        modelo.put("aumentoCapital", aumentosCapital);
        modelo.put("totalActivos", totalActivos);
        modelo.put("totalPasivos", totalPasivos);
        modelo.put("totalCapital", totalCapital);
        modelo.put("totalPasivoCapital", totalPasivosCapital);
        modelo.put("cicloContable", cicloContable);
        modelo.put("company", company);

        ctx.render("vistas/estados.ftl", modelo);
    }

    private double calculateTotalIngresos(CicloContable cicloContable) {
        return LibroMayorServices.getInstancia()
                .getCuentasT(cicloContable)
                .stream()
                .filter(c -> c.getCuenta().getTipo().equals(TipoCuenta.INGRESO))
                .mapToDouble(CuentaT::getSaldo)
                .sum();
    }

    private double calculateGastosOperativos(CicloContable cicloContable) {
        return LibroMayorServices.getInstancia()
                .getCuentasT(cicloContable)
                .stream()
                .filter(c -> c.getCuenta().getTipo().equals(TipoCuenta.GASTO))
                .mapToDouble(CuentaT::getSaldo)
                .sum();
    }

    private double getSaldoByCodigo(CicloContable cicloContable, String codigo) {
        return Optional.ofNullable(LibroMayorServices.getInstancia().getCuentaTByCodigo(codigo, cicloContable))
                .map(CuentaT::getSaldo).orElse(0.0);
    }

    private double calculateTotalByTipo(CicloContable cicloContable, TipoCuenta tipo) {
        return LibroMayorServices.getInstancia()
                .getCuentasT(cicloContable)
                .stream()
                .filter(c -> c.getCuenta().getTipo().equals(tipo))
                .mapToDouble(CuentaT::getSaldo)
                .sum();
    }

    private void populateModeloForFlujoDeEfectivo(CicloContable cicloContable, Map<String, Object> modelo) {
        double ingresoEFE = 0;
        double pagosEFE = 0;
        double gastosEFE = 0;
        double inversionEFE = 0;
        double capitalEFE = 0;
        double prestamosEFE = 0;
        double retiroEFE = 0;

        List<Asiento> asientosEFE = getAsientosEFE(cicloContable);

        for (Asiento asiento : asientosEFE) {
            for (Transaccion transaccion : asiento.getTransacciones()) {
                if (!transaccion.getCuenta().getCodigo().equals("111")) {
                    String codigo = transaccion.getCuenta().getCodigo();
                    switch (codigo) {
                        case "411":
                            ingresoEFE += transaccion.getMonto();
                            break;
                        case "612.01":
                            pagosEFE += transaccion.getMonto();
                            break;
                        case "311":
                            capitalEFE += transaccion.getMonto();
                            break;
                        case "211":
                        case "212":
                            prestamosEFE += transaccion.getMonto();
                            break;
                        default:
                            if (transaccion.getCuenta().getTipo() == TipoCuenta.GASTO) {
                                gastosEFE += transaccion.getMonto();
                            } else if (transaccion.getCuenta().getTipo() == TipoCuenta.ACTIVO && !codigo.equals("112")) {
                                inversionEFE += transaccion.getMonto();
                            } else {
                                retiroEFE += transaccion.getMonto();
                            }
                            break;
                    }
                }
            }
        }

        modelo.put("ingresoEFE", ingresoEFE);
        modelo.put("pagosEFE", pagosEFE);
        modelo.put("gastosEFE", gastosEFE);
        modelo.put("inversionEFE", inversionEFE);
        modelo.put("capitalEFE", capitalEFE);
        modelo.put("prestamosEFE", prestamosEFE);
        modelo.put("retiroEFE", retiroEFE);
        modelo.put("cuentasT", LibroMayorServices.getInstancia().getCuentasT(cicloContable));
        modelo.put("asientos", LibroDiarioServices.getInstancia().getLibroDiario(cicloContable).getAsientos());
    }

    private List<Asiento> getAsientosEFE(CicloContable cicloContable) {
        List<Asiento> asientosEFE = new ArrayList<>();
        List<Asiento> asientos = cicloContable.getLibroDiario().getAsientos();
        for (Asiento asiento : asientos) {
            for (Transaccion transaccion : asiento.getTransacciones()) {
                if (transaccion.getCuenta().getNombre().equals("Efectivo")) {
                    asientosEFE.add(asiento);
                }
            }
        }
        return asientosEFE;
    }
}