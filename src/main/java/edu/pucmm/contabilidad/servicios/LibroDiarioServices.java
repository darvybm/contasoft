package edu.pucmm.contabilidad.servicios;

import edu.pucmm.contabilidad.modelos.*;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Map;

public class LibroDiarioServices {
    private static LibroDiarioServices instancia = null;

    private LibroDiarioServices() {
    }

    public static LibroDiarioServices getInstancia() {
        if (instancia == null) {
            instancia = new LibroDiarioServices();
        }
        return instancia;
    }

    public LibroDiario getLibroDiario(CicloContable cicloContable) {
        return cicloContable.getLibroDiario();
    }

    public void setLibroDiario(LibroDiario libroDiario, CicloContable cicloContable) {
        cicloContable.setLibroDiario(libroDiario);
    }

    public void agregarAsiento(Asiento asiento, CicloContable cicloContable) {
        cicloContable.getLibroDiario().getAsientos().add(asiento);
    }

    public ArrayList<Asiento> getAsientos(CicloContable cicloContable) {
        return cicloContable.getLibroDiario().getAsientos();
    }

    public Boolean existenTransaccionesTemporales(Context ctx, CicloContable cicloContable) {
        HttpSession session = ctx.req().getSession();
        Map<String, Object> asientosFalsos = (Map<String, Object>) session.getAttribute("transaccionesTemporales");
        if (asientosFalsos == null) {
            return false;
        } else {
            return true;
        }
    }

    public void eliminarTransaccionTemporal(Context ctx, String codigo, CicloContable cicloContable) {
        HttpSession session = ctx.req().getSession();
        Transaccion transaccion = buscarTransaccionTemporalByCodigo(ctx, codigo, cicloContable);
        Map<String, Object> asientosFalsos = (Map<String, Object>) session.getAttribute("transaccionesTemporales");
        ArrayList<Transaccion> transacciones = (ArrayList<Transaccion>) asientosFalsos.get(cicloContable.getUuid() + "");
        transacciones.remove(transaccion);
    }

    public Transaccion buscarTransaccionTemporalByCodigo(Context ctx, String codigo, CicloContable cicloContable) {
        HttpSession session = ctx.req().getSession();
        Map<String, Object> asientosFalsos = (Map<String, Object>) session.getAttribute("transaccionesTemporales");
        ArrayList<Transaccion> transacciones = (ArrayList<Transaccion>) asientosFalsos.get(cicloContable.getUuid() + "");
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getUuid().toString().equals(codigo)) {
                return transaccion;
            }
        }
        return null;
    }
}
