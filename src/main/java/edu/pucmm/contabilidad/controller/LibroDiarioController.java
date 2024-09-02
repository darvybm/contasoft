package edu.pucmm.contabilidad.controller;

import edu.pucmm.contabilidad.modelos.*;
import edu.pucmm.contabilidad.servicios.CatalogoCuentasServices;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import edu.pucmm.contabilidad.servicios.LibroDiarioServices;
import edu.pucmm.contabilidad.servicios.LibroMayorServices;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.*;

public class LibroDiarioController {
    private final Javalin app;
    private final LibroDiarioServices libroDiarioServices;
    private final CompanyServices companyServices;
    private final CatalogoCuentasServices catalogoCuentasServices;
    private final LibroMayorServices libroMayorServices;

    public LibroDiarioController(Javalin app) {
        this.app = app;
        this.libroDiarioServices = LibroDiarioServices.getInstancia();
        this.companyServices = CompanyServices.getInstancia();
        this.catalogoCuentasServices = CatalogoCuentasServices.getInstance();
        this.libroMayorServices = LibroMayorServices.getInstancia();
        configurarRutas();
    }

    private void configurarRutas() {
        app.get("/librodiario", this::handleGetLibroDiario);
        app.post("/librodiario/crear", this::handlePostCrearTransaccion);
        app.post("/librodiario/actualizar", this::handlePostActualizarTransaccion);
        app.get("/librodiario/eliminar/{codigo}", this::handleGetEliminarTransaccion);
        app.post("/librodiario/publicar", this::handlePostPublicarAsiento);
    }

    private void handleGetLibroDiario(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        String cicloContableId = ctx.queryParam("cicloContable");

        if (company == null || cicloContableId == null) {
            ctx.status(400).result("Invalid session or missing cicloContable parameter.");
            return;
        }

        CicloContable cicloContable = companyServices.buscarCicloContableById(UUID.fromString(cicloContableId), company);

        if (cicloContable == null) {
            ctx.status(404).result("Ciclo Contable not found.");
            return;
        }

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("datos", cicloContable.getLibroDiario().getAsientos());
        modelo.put("cuentas", cicloContable.getCatalogoCuentas().getCuentas());

        if (!libroDiarioServices.existenTransaccionesTemporales(ctx, cicloContable)) {
            Map<String, Object> asientosFalsos = new HashMap<>();
            asientosFalsos.put(cicloContable.getUuid() + "", new ArrayList<Transaccion>());
            ctx.sessionAttribute("transaccionesTemporales", asientosFalsos);
        } else {
            Map<String, Object> asientoFalso = ctx.sessionAttribute("transaccionesTemporales");
            if (asientoFalso.get(cicloContable.getUuid() + "") == null) {
                asientoFalso.put(cicloContable.getUuid() + "", new ArrayList<Transaccion>());
            }
            ArrayList<Transaccion> transacciones = (ArrayList<Transaccion>) asientoFalso.get(cicloContable.getUuid() + "");
            Collections.sort(transacciones, this::compareTransacciones);
            modelo.put("datosTemporales", transacciones);
        }

        modelo.put("company", company);
        modelo.put("cicloContable", cicloContable);
        ctx.render("vistas/libroDiario.ftl", modelo);
    }

    private int compareTransacciones(Transaccion o1, Transaccion o2) {
        if (o1.getTipo().equals(o2.getTipo())) {
            return o1.getTipo().compareTo(o2.getTipo());
        } else if (o1.getTipo().equals("D")) {
            return -1;
        } else {
            return 1;
        }
    }

    private void handlePostCrearTransaccion(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        CicloContable cicloContable = companyServices.buscarCicloContableById(
                UUID.fromString(ctx.queryParam("cicloContable")), company
        );

        if (cicloContable == null) {
            ctx.status(404).result("Ciclo Contable not found.");
            return;
        }

        var cuenta = ctx.formParam("cuenta");
        var tipo = ctx.formParam("tipo");
        var monto = ctx.formParam("monto");
        Map<String, Object> asientosFalsos = (Map<String, Object>) ctx.sessionAttribute("transaccionesTemporales");
        ArrayList<Transaccion> transacciones = (ArrayList<Transaccion>) asientosFalsos.get(cicloContable.getUuid() + "");
        transacciones.add(new Transaccion(UUID.randomUUID(), catalogoCuentasServices.getCuentaByCodigo(cuenta, cicloContable), Double.parseDouble(monto), tipo));
        ctx.redirect("/librodiario?cicloContable=" + cicloContable.getUuid());
    }

    private void handlePostActualizarTransaccion(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        CicloContable cicloContable = companyServices.buscarCicloContableById(
                UUID.fromString(ctx.queryParam("cicloContable")), company
        );

        if (cicloContable == null) {
            ctx.status(404).result("Ciclo Contable not found.");
            return;
        }

        var codigo = ctx.formParam("codigo");
        var cuenta = ctx.formParam("cuenta");
        var tipo = ctx.formParam("tipo");
        var monto = ctx.formParam("monto");
        Transaccion transaccion = libroDiarioServices.buscarTransaccionTemporalByCodigo(ctx, codigo, cicloContable);
        transaccion.setCuenta(catalogoCuentasServices.getCuentaByCodigo(cuenta, cicloContable));
        transaccion.setTipo(tipo);
        transaccion.setMonto(Double.parseDouble(monto));
        ctx.redirect("/librodiario?cicloContable=" + cicloContable.getUuid());
    }

    private void handleGetEliminarTransaccion(Context ctx) {
        var codigo = ctx.pathParam("codigo");
        Company company = ctx.sessionAttribute("company");
        CicloContable cicloContable = companyServices.buscarCicloContableById(
                UUID.fromString(ctx.queryParam("cicloContable")), company
        );

        if (cicloContable == null) {
            ctx.status(404).result("Ciclo Contable not found.");
            return;
        }

        libroDiarioServices.eliminarTransaccionTemporal(ctx, codigo, cicloContable);
        ctx.redirect("/librodiario?cicloContable=" + cicloContable.getUuid());
    }

    private void handlePostPublicarAsiento(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        CicloContable cicloContable = companyServices.buscarCicloContableById(
                UUID.fromString(ctx.queryParam("cicloContable")), company
        );

        if (cicloContable == null) {
            ctx.status(404).result("Ciclo Contable not found.");
            return;
        }

        var fecha = ctx.formParam("fecha");
        var descripcion = ctx.formParam("descripcion");
        Asiento asiento = descripcion == null ? new Asiento(UUID.randomUUID(), fecha) : new Asiento(UUID.randomUUID(), fecha, descripcion);
        Map<String, Object> asientosFalsos = ctx.sessionAttribute("transaccionesTemporales");
        ArrayList<Transaccion> transacciones = (ArrayList<Transaccion>) asientosFalsos.get(cicloContable.getUuid() + "");
        asiento.setTransacciones(transacciones);
        libroDiarioServices.agregarAsiento(asiento, cicloContable);

        Map<String, Object> asientosFalsosNuevos = new HashMap<>();
        asientosFalsosNuevos.put(cicloContable.getUuid() + "", new ArrayList<Transaccion>());
        ctx.sessionAttribute("transaccionesTemporales", asientosFalsosNuevos);
        libroMayorServices.generarLibroMayor(libroDiarioServices.getLibroDiario(cicloContable), cicloContable);
        ctx.redirect("/librodiario?cicloContable=" + cicloContable.getUuid());
    }
}
