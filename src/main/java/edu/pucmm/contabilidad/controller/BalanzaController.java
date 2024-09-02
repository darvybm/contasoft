package edu.pucmm.contabilidad.controller;

import edu.pucmm.contabilidad.modelos.CicloContable;
import edu.pucmm.contabilidad.modelos.Company;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import edu.pucmm.contabilidad.servicios.LibroMayorServices;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BalanzaController {

    private final Javalin app;
    private final LibroMayorServices libroMayorServices;
    private final CompanyServices companyServices;

    public BalanzaController(Javalin app) {
        this.app = app;
        this.libroMayorServices = LibroMayorServices.getInstancia();
        this.companyServices = CompanyServices.getInstancia();
        configurarRutas();
    }

    private void configurarRutas() {
        app.get("/balanza", this::handleGetBalanza);
    }

    private void handleGetBalanza(Context ctx) {
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

        Map<String, Object> modelo = createModelo(cicloContable, company);

        ctx.render("vistas/balanza.ftl", modelo);
    }

    private Map<String, Object> createModelo(CicloContable cicloContable, Company company) {
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("company", company);
        modelo.put("datos", libroMayorServices.getCuentasT(cicloContable));
        modelo.put("totalDebito", libroMayorServices.totalDebitoBalanzaComprobacion(cicloContable));
        modelo.put("totalCredito", libroMayorServices.totalCreditoBalanzaComprobacion(cicloContable));
        modelo.put("cicloContable", cicloContable);
        return modelo;
    }
}
