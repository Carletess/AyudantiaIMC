package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void calcularPromedio() {
        // Caso de prueba con valores válidos
        String[][] datos = {
                {"Niño1", "Niño2"},
                {"40.0", "50.0"},
                {"120.0", "130.0"}
        };

        double promedioPeso = Main.calcularPromedio(datos, 1);
        assertEquals(45.0, promedioPeso, 0.01);

        String[][] datosNulos = {
                {"Niño1", "Niño2"},
                {"40.0", "50.0"},
                {"120.0", null}
        };

        promedioPeso = Main.calcularPromedio(datosNulos, 1);
        assertEquals(45.0, promedioPeso, 0.01);

        String[][] datosSinValores = {
                {"Niño1", "Niño2"},
                {null, null},
                {null, null}
        };

        promedioPeso = Main.calcularPromedio(datosSinValores, 1);
        assertEquals(0.0, promedioPeso, 0.01);
    }

    @Test
    void calcularIMC() {
        double imc = Main.calcularIMC("45.0", "120.0");
        double valorEsperado = 31.25;
        double tolerancia = 0.01;

        assertEquals(valorEsperado, imc, tolerancia);

        imc = Main.calcularIMC(null, "120.0");
        assertEquals(0.0, imc, tolerancia);

        imc = Main.calcularIMC("texto", "120.0");
        assertEquals(0.0, imc, tolerancia);
    }

    @Test
    void formatearNumero() {
        String numeroFormateado = Main.formatearNumero(12.3456);
        assertEquals("12,35", numeroFormateado);

        numeroFormateado = Main.formatearNumero(-12.3456);
        assertEquals("-12,35", numeroFormateado);

        numeroFormateado = Main.formatearNumero(12345);
        assertEquals("12345,00", numeroFormateado);

        numeroFormateado = Main.formatearNumero(0.123456);
        assertEquals(",12", numeroFormateado);

        numeroFormateado = Main.formatearNumero(5.0);
        assertEquals("5,00", numeroFormateado);
    }
}
