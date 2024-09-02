package edu.pucmm.contabilidad.controller;

import edu.pucmm.contabilidad.modelos.Company;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginController {
    private final Javalin app;

    public LoginController(Javalin app) {
        this.app = app;
        configurarRutas();
    }

    private void configurarRutas() {
        app.get("/login", this::handleGetLogin);
        app.post("/login/signin", this::handlePostSignin);
        app.post("/login/signup", this::handlePostSignup);
    }

    private void handleGetLogin(io.javalin.http.Context ctx) {
        ctx.render("vistas/login.ftl");
    }

    private void handlePostSignin(io.javalin.http.Context ctx) {
        Map<String, String> modelo = new HashMap<>();
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        Company company = CompanyServices.getInstancia().buscarCompanyByEmail(email);

        if (company != null) {
            if (company.getPassword().equals(password)) {
                ctx.sessionAttribute("archivado", false);
                ctx.sessionAttribute("company", company);
                ctx.redirect("/home");
            } else {
                modelo.put("mensaje", "Password Incorrecto");
                ctx.render("vistas/login.ftl", modelo);
            }
        } else {
            modelo.put("mensaje", "Email Incorrecto");
            ctx.render("vistas/login.ftl", modelo);
        }
    }

    private void handlePostSignup(io.javalin.http.Context ctx) {
        Map<String, String> modelo = new HashMap<>();
        String name = ctx.formParam("name");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        if (!CompanyServices.getInstancia().existeCompany(email)) {
            Company company = new Company(UUID.randomUUID(), name, email, password);
            CompanyServices.getInstancia().agregarCompany(company);
            ctx.sessionAttribute("archivado", false);
            ctx.sessionAttribute("company", company);
            ctx.redirect("/home");
        } else {
            modelo.put("mensajeR", "Esta compania ya existe");
            ctx.render("vistas/login.ftl", modelo);
        }
    }
}
