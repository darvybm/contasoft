package edu.pucmm.contabilidad.controller;

import edu.pucmm.contabilidad.modelos.*;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import edu.pucmm.contabilidad.servicios.LibroMayorServices;
import io.javalin.Javalin;

import java.util.*;
import java.util.stream.Collectors;

public class LibroMayorController {
    private final Javalin app;

    public LibroMayorController(Javalin app) {
        this.app = app;
        configurarRutas();
    }

    private void configurarRutas() {
        app.get("/libromayor", this::handleGetLibroMayor);
    }

    private void handleGetLibroMayor(io.javalin.http.Context ctx) {
        Company company = ctx.sessionAttribute("company");

        if (company == null) {
            ctx.status(401).result("Unauthorized: No company in session");
            return;
        }

        String cicloContableIdStr = ctx.queryParam("cicloContable");
        if (cicloContableIdStr == null || cicloContableIdStr.isEmpty()) {
            ctx.status(400).result("Bad Request: Missing 'cicloContable' query parameter");
            return;
        }

        UUID cicloContableId;
        try {
            cicloContableId = UUID.fromString(cicloContableIdStr);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Bad Request: Invalid 'cicloContable' query parameter format");
            return;
        }

        CicloContable cicloContable = CompanyServices.getInstancia().buscarCicloContableById(cicloContableId, company);
        if (cicloContable == null) {
            ctx.status(404).result("Not Found: CicloContable not found");
            return;
        }

        List<CuentaT> cuentasTList = LibroMayorServices.getInstancia().getCuentasT(cicloContable);
        if (cuentasTList == null) {
            ctx.status(500).result("Internal Server Error: Could not retrieve cuentasT");
            return;
        }

        List<List<CuentaT>> cuentasT = new ArrayList<>();
        cuentasT.add(cuentasTList.stream().filter(cuenta -> cuenta.getCuenta().getTipo().equals(TipoCuenta.ACTIVO)).collect(Collectors.toList()));
        cuentasT.add(cuentasTList.stream().filter(cuenta -> cuenta.getCuenta().getTipo().equals(TipoCuenta.PASIVO)).collect(Collectors.toList()));
        cuentasT.add(cuentasTList.stream().filter(cuenta -> cuenta.getCuenta().getTipo().equals(TipoCuenta.INGRESO)).collect(Collectors.toList()));
        cuentasT.add(cuentasTList.stream().filter(cuenta -> cuenta.getCuenta().getTipo().equals(TipoCuenta.GASTO)).collect(Collectors.toList()));
        cuentasT.add(cuentasTList.stream().filter(cuenta -> cuenta.getCuenta().getTipo().equals(TipoCuenta.CAPITAL)).collect(Collectors.toList()));
        cuentasT.add(cuentasTList.stream().filter(cuenta -> cuenta.getCuenta().getTipo().equals(TipoCuenta.RETIRO)).collect(Collectors.toList()));

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("datos", cuentasT);
        modelo.put("cicloContable", cicloContable);
        modelo.put("company", company);

        ctx.render("vistas/libroMayor.ftl", modelo);
    }
}
