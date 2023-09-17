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
        String inputNombre;

        do {
            System.out.println("Ingresa el nombre del niño: ");
            inputNombre = scanner.nextLine().trim(); // trim elimina espacios para poder detectar si está vacío :)

            if (inputNombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (inputNombre.isEmpty());

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
                3) Promedio de peso de los niños.
                4) Promedio de altura de los niños.
                5) Ver IMC de los niños.
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
        double promedioPeso = calcularPromedio(datosIMC, 1);
        System.out.println("El peso promedio es: " + formatearNumero(promedioPeso));
    }

    private static void mostrarAlturaPromedio(String[][] datosIMC) {
        double promedioAltura = calcularPromedio(datosIMC, 2);
        System.out.println("La altura promedio es: " + formatearNumero(promedioAltura));
    }

    private static double calcularPromedio(String[][] datosIMC, int fila) {
        double promedio = 0;
        int valoresValidos = 0;

        for (int y = 0; y < datosIMC[0].length; y++) {
            String valorStr = datosIMC[fila][y];

            if (valorStr != null) {
                try {
                    double valor = Double.parseDouble(valorStr);
                    promedio += valor;
                    valoresValidos++;
                } catch (NumberFormatException e) {
                    System.out.println("Error: No se pudo convertir a número en la fila " + fila);
                }
            } else {
                System.out.println("El dato " + (y+1) + " en la fila " +  fila + " es nulo");
            }
        }

        if (valoresValidos > 0) {
            promedio /= valoresValidos;
        } else {
            System.out.println("No se encontraron valores válidos en la fila " + fila);
        }

        return promedio;
    }

    private static void mostrarCategoriaIMC(double imc) {
        if (imc < 18.5) {
            System.out.println("Categoría: Bajo Peso");
        } else if (imc >= 18.5 && imc <= 24.9) {
            System.out.println("Categoría: Normal");
        } else if (imc >= 25.0 && imc <= 29.9) {
            System.out.println("Categoría: Sobrepeso");
        } else {
            System.out.println("Categoría: Obesidad");
        }
    }

    private static double calcularIMC(String pesoStr, String alturaStr) {
        if (pesoStr != null && alturaStr != null) {
            try {
                double peso = Double.parseDouble(pesoStr);
                double altura = Double.parseDouble(alturaStr) / 100.0; // de cm a m
                return peso / (altura * altura);

            } catch (NumberFormatException e) {
                    System.out.println("Error al calcular el IMC");
            }
        } else {
            System.out.println("Advertencia: Los datos son incompletos (null).");
        }
        return 0;
    }

    private static void mostrarIMC(String nombre, double imc) {
        System.out.println("Nombre: " + nombre);
        System.out.println("IMC: " + formatearNumero(imc));
        mostrarCategoriaIMC(imc);
        System.out.println();
    }

    private static void restriccionIMC(String[][] datosIMC) {
        for (int i = 0; i < datosIMC[0].length; i++) {
            String nombre = datosIMC[0][i];
            String pesoStr = datosIMC[1][i];
            String alturaStr = datosIMC[2][i];

            if (nombre == null || pesoStr == null || alturaStr == null) {
                System.out.println("No se han ingresado datos para el niño en la fila " + (i + 1));
                break;
            }

            double imc = calcularIMC(pesoStr, alturaStr);

            if (imc < 12 || imc > 50) {
                System.out.println("El IMC está fuera del rango permitido para un niño. Por favor, reingrese los datos de " + nombre);
                pedirDatosNuevamente(datosIMC, i);
            } else {
                mostrarIMC(nombre, imc);
            }
        }
    }

    private static void pedirDatosNuevamente(String[][] datosIMC, int indice) {
        datosIMC[0][indice] = pedirNombre();
        datosIMC[1][indice] = String.valueOf(pedirPeso());
        datosIMC[2][indice] = String.valueOf(pedirAltura());
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
                case 3 -> mostrarPesoPromedio(datosIMC);
                case 4 -> mostrarAlturaPromedio(datosIMC);
                case 5 -> restriccionIMC(datosIMC);
                case 6 -> System.out.println("Saliste");
            }
        } while (opcionUsuario != 6);
    }
}