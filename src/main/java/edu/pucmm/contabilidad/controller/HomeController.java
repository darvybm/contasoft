package edu.pucmm.contabilidad.controller;

import edu.pucmm.contabilidad.modelos.CicloContable;
import edu.pucmm.contabilidad.modelos.Company;
import edu.pucmm.contabilidad.servicios.CatalogoCuentasServices;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeController {
    private final Javalin app;

    public HomeController(Javalin app) {
        this.app = app;
        registerRoutes();
    }

    private void registerRoutes() {
        app.before("/home", this::checkSession);
        app.get("/home", this::renderHome);
        app.post("/home/agregar", this::addCicloContable);
        app.post("/home/modificar/{cicloContable}", this::modifyCicloContable);
        app.get("/home/archivado", this::toggleArchivado);
        app.get("/home/archivar/{cicloContable}", this::toggleCicloArchivado);
    }

    private void checkSession(Context ctx) {
        Company company = ctx.sessionAttribute("company");
        if (company == null) {
            ctx.redirect("/login");
        }
    }

    private void renderHome(Context ctx) {
        Boolean archivado = ctx.sessionAttribute("archivado");
        Company company = ctx.sessionAttribute("company");
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("company", company);
        modelo.put("archivado", archivado);
        ctx.render("/vistas/home.ftl", modelo);
    }

    private void addCicloContable(Context ctx) {
        UUID codigo = UUID.fromString(ctx.formParam("codigo"));
        String titulo = ctx.formParam("titulo");
        String descripcion = ctx.formParam("descripcion");

        CicloContable cicloContable = new CicloContable(UUID.randomUUID());
        cicloContable.setTitulo(titulo);
        cicloContable.setDescripcion(descripcion);

        CompanyServices.getInstancia().agregarCicloContable(cicloContable, codigo);
        CatalogoCuentasServices.getInstance().agregarCuentasByDefault(cicloContable);

        ctx.redirect("/home");
    }

    private void modifyCicloContable(Context ctx) {
        String cicloContableCodigo = ctx.pathParam("cicloContable");
        String companyCodigo = ctx.formParam("company");
        String titulo = ctx.formParam("titulo");
        String descripcion = ctx.formParam("descripcion");

        Company company = CompanyServices.getInstancia().buscarCompanyById(UUID.fromString(companyCodigo));
        CicloContable cicloContable = CompanyServices.getInstancia().buscarCicloContableById(UUID.fromString(cicloContableCodigo), company);

        cicloContable.setTitulo(titulo);
        cicloContable.setDescripcion(descripcion);

        ctx.redirect("/home");
    }

    private void toggleArchivado(Context ctx) {
        Boolean archivado = ctx.sessionAttribute("archivado");
        ctx.sessionAttribute("archivado", archivado == null || !archivado);
        ctx.redirect("/home");
    }

    private void toggleCicloArchivado(Context ctx) {
        String cicloContableCodigo = ctx.pathParam("cicloContable");
        Company company = ctx.sessionAttribute("company");

        CicloContable cicloContable = CompanyServices.getInstancia().buscarCicloContableById(UUID.fromString(cicloContableCodigo), company);
        cicloContable.setArchivado(!cicloContable.isArchivado());

        ctx.redirect("/home");
    }
}
