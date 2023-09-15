package org.example;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] datosIMC = crearMatrizIMC();
        menu(datosIMC);
    }

    public static String[][] crearMatrizIMC(){
        return new String [3][3]; // peso x altura
    }

    public static void mostrarMatrizIMC(String[][] datosIMC) {
        for (int i = 0; i < datosIMC.length; i++) {
            for (int j = 0; j < datosIMC[0].length; j++) {
                System.out.print(datosIMC[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String pedirNombre() {
        Scanner scanner = new Scanner(System.in);
        String inputNombre = scanner.nextLine();
        System.out.println("Ingresa el nombre del niño: ");

        try {
            int numero = Integer.parseInt(inputNombre);
            System.out.println("Ingresaste un número, debes ingresar un nombre válido: ");

            inputNombre = pedirNombre();
        } catch (NumberFormatException e) {
            System.out.println("Ingresaste una cadena: " + inputNombre);
        }

        return inputNombre;
    }


    public static double pedirPeso() {
        Scanner scanner = new Scanner(System.in);
        double inputPeso = 0.0;
        System.out.println("Ingresa el peso del niño en kilogramos: ");

        try {
            inputPeso = Double.parseDouble(scanner.next());

            if (inputPeso < 10 || inputPeso > 150) {
                throw new NumberFormatException("El peso debe estar entre 10 y 150.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido entre 10 y 150.");
            inputPeso = pedirPeso();
        }

        return inputPeso;
    }

    public static double pedirAltura() {
        Scanner scanner = new Scanner(System.in);
        double inputAltura = 0.0;
        System.out.println("Ingresa la altura del niño en centímetros: ");

        try {
            inputAltura = Double.parseDouble(scanner.next());

            if (inputAltura < 80 || inputAltura > 240) {
                throw new NumberFormatException("La altura debe estar entre 80 y 240.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido entre 80 y 240.");
            inputAltura = pedirAltura();
        }

        return inputAltura;
    }


    public static void pedirDatosMatriz(String[][] datosIMC) {
        for (int y = 0; y < datosIMC[0].length; y++) {
            datosIMC[0][y] = pedirNombre();
            datosIMC[1][y] = String.valueOf(pedirPeso());
            datosIMC[2][y] = String.valueOf(pedirAltura());
        }
    }

    public static void mostrarMenu() {
        System.out.println("""
                Menú
                1) Ingresar datos.
                2) Ver datos ingresados.
                3) Categoria de lo niños.
                4) Promedio de peso de los niños.
                5) Promedio de altura de los niños.
                6) Salir.
                """);
    }

    public static int pedirInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngresa una opción: ");

        try {
            int inputUsuario = scanner.nextInt();

            if (inputUsuario < 1 || inputUsuario > 6) {
                System.out.println("Opción inválida.");
                return pedirInput();
            }

            return inputUsuario;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            scanner.nextLine();
            return pedirInput();
        }
    }

    private static void mostrarPesoPromedio(String[][] datosIMC) {
        double promedioPeso = calcularPromedio(datosIMC, 1); // Usamos la fila 1 para los pesos.
        System.out.println("El peso promedio es: " + formatearNumero(promedioPeso));
    }

    private static void mostrarAlturaPromedio(String[][] datosIMC) {
        double promedioAltura = calcularPromedio(datosIMC, 2); // Usamos la fila 2 para las alturas.
        System.out.println("La altura promedio es: " + formatearNumero(promedioAltura));
    }

    private static double calcularPromedio(String[][] datosIMC, int fila) {
        double promedio = 0;

        for(int y = 0; y < datosIMC[0].length; y++) {
            try {
                double valor = Double.parseDouble(datosIMC[fila][y]);
                promedio += (valor / datosIMC[0].length);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir valor a número en la fila " + fila);
            }
        }

        return promedio;
    }

    public static String formatearNumero(double numero) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(numero);
    }

    public static void menu(String[][] datosIMC){
        int opcionUsuario;

        do {
            mostrarMenu();
            opcionUsuario = pedirInput();

            switch (opcionUsuario) {
                case 1 -> pedirDatosMatriz(datosIMC);
                case 2 -> mostrarMatrizIMC(datosIMC);
                case 3 -> System.out.println("Categoria");
                case 4 -> mostrarPesoPromedio(datosIMC);
                case 5 -> mostrarAlturaPromedio(datosIMC);
                case 6 -> System.out.println("Saliste");
            }
        } while (opcionUsuario != 6);
    }
}