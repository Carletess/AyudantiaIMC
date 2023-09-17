package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void calcularPromedio() {
        String[][] datos = {
                {"Niño1", "Niño2"},
                {"40.0", "50.0"},
                {"120.0", "130.0"}
        };

        double promedioPeso = Main.calcularPromedio(datos, 1);
        assertEquals(45.0, promedioPeso, 0.01);
    }

    @Test
    void calcularIMC() {
        double imc = Main.calcularIMC("45.0", "120.0");
        double valorEsperado = 31.25;
        double tolerancia = 0.01;

        assertEquals(valorEsperado, imc, tolerancia);
    }

    @Test
    void formatearNumero() {
        String numeroFormateado = Main.formatearNumero(12.3456);
        assertEquals("12,35", numeroFormateado);
    }
}
