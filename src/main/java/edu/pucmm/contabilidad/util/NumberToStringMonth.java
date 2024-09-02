package edu.pucmm.contabilidad.util;

import java.awt.*;
import java.util.Random;

public class NumberToStringMonth {

    public static String numberToStringMonth(String fecha) {
        String[] fechaSplited = fecha.split("-");
        String mes = fechaSplited[1];
        switch (mes) {
            case "01":
                return "Enero";
            case "02":
                return "Febrero";
            case "03":
                return "Marzo";
            case "04":
                return "Abril";
            case "05":
                return "Mayo";
            case "06":
                return "Junio";
            case "07":
                return "Julio";
            case "08":
                return "Agosto"; //best month ever
            case "09":
                return "Septiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
        }
        return "Diciembre";
    }
}