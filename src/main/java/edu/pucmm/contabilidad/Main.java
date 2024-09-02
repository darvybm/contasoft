package edu.pucmm.contabilidad;

import edu.pucmm.contabilidad.controller.*;
import edu.pucmm.contabilidad.modelos.Asiento;
import edu.pucmm.contabilidad.modelos.CicloContable;
import edu.pucmm.contabilidad.modelos.Company;
import edu.pucmm.contabilidad.modelos.Transaccion;
import edu.pucmm.contabilidad.servicios.CatalogoCuentasServices;
import edu.pucmm.contabilidad.servicios.CompanyServices;
import edu.pucmm.contabilidad.servicios.LibroDiarioServices;
import edu.pucmm.contabilidad.servicios.LibroMayorServices;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinFreemarker;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Javalin app =  Javalin.create(config -> {
            config.fileRenderer(new JavalinFreemarker());
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
            });
        });

        app.start(7071);

        new BalanzaController(app);
        new LoginController(app);
        new CatalogoController(app);
        new EstadosController(app);
        new HomeController(app);
        new LibroDiarioController(app);
        new LibroMayorController(app);

        // Agregando cuentas al catálogo
        Company kittyKouture = new Company(UUID.randomUUID(), "KittyKouture", "kittykouture@gmail.com", "1234");
        CompanyServices.getInstancia().agregarCompany(kittyKouture);
        CicloContable kittyKoutureCicloContable = new CicloContable(UUID.randomUUID());
        kittyKoutureCicloContable.setCompanyName(kittyKouture.getName());
        kittyKoutureCicloContable.setTitulo("Ciclo Contable 1");
        kittyKoutureCicloContable.setDescripcion("Primer Ciclo Contable de Kitty Kouture!");
        CompanyServices.getInstancia().agregarCicloContable(kittyKoutureCicloContable, kittyKouture.getUuid());
        CatalogoCuentasServices.getInstance().agregarCuentasByDefault(kittyKoutureCicloContable);

        Asiento asiento01 = new Asiento(UUID.randomUUID(), "19/08/2023", "Para registrar el aporte al capital");
        asiento01.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 40000, "D"));
        asiento01.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("311", kittyKoutureCicloContable), 40000, "C"));

        Asiento asiento02 = new Asiento(UUID.randomUUID(), "19/08/2023", "Para registrar un prestamo");
        asiento02.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 5000, "D"));
        asiento02.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("211", kittyKoutureCicloContable), 5000, "C"));

        Asiento asiento03 = new Asiento(UUID.randomUUID(), "19/08/2023", "Para registrar la compra de un terreno al contado");
        asiento03.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("121", kittyKoutureCicloContable), 30000, "D"));
        asiento03.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 30000, "C"));

        Asiento asiento04 = new Asiento(UUID.randomUUID(), "20/08/2023", "Para registrar un prestamo");
        asiento04.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 5000, "D"));
        asiento04.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("212", kittyKoutureCicloContable), 5000, "C"));

        Asiento asiento05 = new Asiento(UUID.randomUUID(), "20/08/2023", "Para registrar un retiro para uso personal");
        asiento05.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 10000, "D"));
        asiento05.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("312", kittyKoutureCicloContable), 10000, "C"));

        Asiento asiento06 = new Asiento(UUID.randomUUID(), "24/08/2023", "Para registrar la venta de un servicio al contado");
        asiento06.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 20000, "D"));
        asiento06.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("411", kittyKoutureCicloContable), 20000, "C"));

        Asiento asiento07 = new Asiento(UUID.randomUUID(), "24/08/2023", "Para registrar el pago de sueldos, renta y telefono al contado");
        asiento07.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("612.01", kittyKoutureCicloContable), 15000, "D"));
        asiento07.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("612.05", kittyKoutureCicloContable), 5000, "D"));
        asiento07.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("612.07", kittyKoutureCicloContable), 5000, "D"));
        asiento07.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 25000, "C"));

        Asiento asiento08 = new Asiento(UUID.randomUUID(), "26/08/2023", "Para registrar la venta de un servicio a credito");
        asiento08.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("112", kittyKoutureCicloContable), 20000, "D"));
        asiento08.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("411", kittyKoutureCicloContable), 20000, "C"));

        Asiento asiento09 = new Asiento(UUID.randomUUID(), "31/08/2023", "Para registrar el cobro de la venta del dia 26");
        asiento09.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", kittyKoutureCicloContable), 20000, "D"));
        asiento09.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("112", kittyKoutureCicloContable), 20000, "C"));

        LibroDiarioServices.getInstancia().agregarAsiento(asiento01, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento02, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento03, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento04, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento05, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento06, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento07, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento08, kittyKoutureCicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(asiento09, kittyKoutureCicloContable);

        LibroMayorServices.getInstancia().generarLibroMayor(LibroDiarioServices.getInstancia().getLibroDiario(kittyKoutureCicloContable), kittyKoutureCicloContable);


        Company company = new Company(UUID.randomUUID(), "Hipoteca Surprise SRL", "darvybetance@gmail.com", "123456789");
        CompanyServices.getInstancia().agregarCompany(company);
        CicloContable cicloContable = new CicloContable(UUID.randomUUID());
        cicloContable.setCompanyName(company.getName());
        cicloContable.setTitulo("Ciclo Contable 1");
        cicloContable.setDescripcion("asfjg hrg eifgh erfgyq rfygweurfy we rfw eryfgwueoryfg qwe rfyq rfgyq erfywrf we rfowe rfwoe r");
        CompanyServices.getInstancia().agregarCicloContable(cicloContable, company.getUuid());
        CatalogoCuentasServices.getInstance().agregarCuentasByDefault(cicloContable);

        Asiento a1 = new Asiento(UUID.randomUUID(), "01/03/2020");
        a1.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 750000, "D"));
        a1.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("311", cicloContable), 750000, "C"));

        Asiento a2 = new Asiento(UUID.randomUUID(), "04/03/2020");
        a2.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("115", cicloContable), 31500, "D"));
        a2.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("212", cicloContable), 31500, "C"));

        Asiento a3 = new Asiento(UUID.randomUUID(), "08/03/2020");
        a3.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("121", cicloContable), 600000, "D"));
        a3.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 600000, "C"));

        Asiento a4 = new Asiento(UUID.randomUUID(), "12/03/2020");
        a4.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 180500, "D"));
        a4.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("411", cicloContable), 180500, "C"));

        Asiento a5 = new Asiento(UUID.randomUUID(),"15/03/2020");
        a5.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 300000, "D"));
        a5.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("211", cicloContable), 300000, "C"));

        Asiento a6 = new Asiento(UUID.randomUUID(), "18/03/2020");
        a6.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("212", cicloContable), 15500, "D"));
        a6.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 15500, "C"));

        Asiento a7 = new Asiento(UUID.randomUUID(), "25/03/2020");
        a7.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 52500.00, "D"));
        a7.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("112", cicloContable), 122500.00, "D"));
        a7.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("411", cicloContable), 175000.00, "C"));

        Asiento a8 = new Asiento(UUID.randomUUID(), "31/03/2020");
        a8.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("612.01", cicloContable), 15000.00, "D"));
        a8.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 15000.00, "C"));

        Asiento a9 = new Asiento(UUID.randomUUID(), "31/03/2020");
        a9.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("612.1", cicloContable), 20800, "D"));
        a9.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 20800, "C"));

        Asiento a10 = new Asiento(UUID.randomUUID(), "31/03/2020");
        a10.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("612.05", cicloContable), 35000, "D"));
        a10.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 35000, "C"));

        Asiento a11 = new Asiento(UUID.randomUUID(), "31/03/2020");
        a11.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("312", cicloContable), 30000.00, "D"));
        a11.getTransacciones().add(new Transaccion(UUID.randomUUID(), CatalogoCuentasServices.getInstance().getCuentaByCodigo("111", cicloContable), 30000.00, "C"));

        LibroDiarioServices.getInstancia().agregarAsiento(a1, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a2, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a3, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a4, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a5, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a6, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a7, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a8, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a9, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a10, cicloContable);
        LibroDiarioServices.getInstancia().agregarAsiento(a11, cicloContable);

        LibroMayorServices.getInstancia().generarLibroMayor(LibroDiarioServices.getInstancia().getLibroDiario(cicloContable), cicloContable);

        app.get("/", ctx -> {
            ctx.sessionAttribute("company", company);
            ctx.redirect("login");
        });
    }
}