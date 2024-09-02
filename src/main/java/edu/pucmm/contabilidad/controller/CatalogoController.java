package edu.pucmm.contabilidad.controller;

import edu.pucmm.contabilidad.modelos.CicloContable;
import edu.pucmm.contabilidad.modelos.Company;
import edu.pucmm.contabilidad.modelos.Cuenta;
import edu.pucmm.contabilidad.modelos.TipoCuenta;
import edu.pucmm.contabilidad.servicios.CatalogoCuentasServices;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogoController {

    private final CatalogoCuentasServices catalogoCuentasServices;
    private final CompanyServices companyServices;

    public CatalogoController(Javalin app) {
        this.catalogoCuentasServices = CatalogoCuentasServices.getInstance();
        this.companyServices = CompanyServices.getInstancia();
        configurarRutas(app);
    }

    private void configurarRutas(Javalin app) {
        app.get("/catalogo", this::mostrarCatalogo);
        app.post("/catalogo/agregar", this::agregarCuenta);
    }

    private void mostrarCatalogo(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        String cicloContableId = ctx.queryParam("cicloContable");

        if (company == null || cicloContableId == null) {
            ctx.status(400).result("Invalid session or missing cicloContable parameter.");
            return;
        }

        UUID cicloContableUUID = UUID.fromString(cicloContableId);
        CicloContable cicloContable = companyServices.buscarCicloContableById(cicloContableUUID, company);

        if (cicloContable == null) {
            ctx.status(404).result("Ciclo Contable not found.");
            return;
        }

        List<TipoCuenta> tiposCuenta = Arrays.asList(TipoCuenta.ACTIVO, TipoCuenta.PASIVO, TipoCuenta.INGRESO, TipoCuenta.GASTO, TipoCuenta.CAPITAL, TipoCuenta.RETIRO);
        List<List<Cuenta>> cuentas = tiposCuenta.stream()
                .map(tipo -> catalogoCuentasServices.getCuentas(cicloContable).stream()
                        .filter(cuenta -> cuenta.getTipo().equals(tipo))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("datos", cuentas);
        modelo.put("cicloContable", cicloContable);
        modelo.put("company", company);

        ctx.render("vistas/catalogo.ftl", modelo);
    }

    private void agregarCuenta(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        String cicloContableId = ctx.queryParam("cicloContable");

        if (company == null || cicloContableId == null) {
            ctx.status(400).result("Invalid session or missing cicloContable parameter.");
            return;
        }

        UUID cicloContableUUID = UUID.fromString(cicloContableId);
        CicloContable cicloContable = companyServices.buscarCicloContableById(cicloContableUUID, company);

        if (cicloContable == null) {
            ctx.status(404).result("Ciclo Contable not found.");
            return;
        }

        String tipo = ctx.formParam("tipo");
        String codigo = ctx.formParam("codigo");
        String nombre = ctx.formParam("nombre");

        if (tipo == null || codigo == null || nombre == null) {
            ctx.status(400).result("Missing form parameters.");
            return;
        }

        Cuenta cuenta = catalogoCuentasServices.getCuentaByCodigo(codigo, cicloContable);

        if (cuenta != null) {
            int cuentaNum = Integer.parseInt(codigo);
            do {
                cuentaNum++;
                codigo = Integer.toString(cuentaNum);
                cuenta = catalogoCuentasServices.getCuentaByCodigo(codigo, cicloContable);
            } while (cuenta != null);
        }

        cuenta = new Cuenta(codigo, nombre, TipoCuenta.valueOf(tipo));
        catalogoCuentasServices.addCuenta(cuenta, cicloContable);

        ctx.redirect("/catalogo?cicloContable=" + cicloContable.getUuid());
    }
}