package edu.pucmm.contabilidad.util;
import java.awt.Color;
import java.util.Random;

public class GeneradorColoresPastel {

    public static Color generarColorPastel() {
        Random random = new Random();
        int red = random.nextInt(156) + 100;
        int green = random.nextInt(156) + 100;
        int blue = random.nextInt(156) + 100;
        return new Color(red, green, blue);
    }
}