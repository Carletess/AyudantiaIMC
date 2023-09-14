package org.example;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String [][] datosIMC = crearMatrizIMC();
        llenarMatrizIMC(datosIMC);
        mostrarMatrizIMC(datosIMC);
    }

    public static void menu(double[][] datosIMC){
    }

    public static String[][] crearMatrizIMC(){
        return new String [3][3]; // peso x altura
    }

    public static void llenarMatrizIMC(String[][] datosIMC) {
        for (int x = 0; x < datosIMC.length; x++) {
            for (int y = 0; y < datosIMC[0].length; y++) {
                if (x == 0) {
                    System.out.print("Ingresa los nombres de los niños: ");
                    datosIMC[x][y] = pedirNombre();
                } else if (x == 1) {
                    System.out.print("Ingresa el peso de " + datosIMC[0][y] + ": ");
                    datosIMC[x][y] = String.valueOf(pedirPeso());
                } else if (x == 2) {
                    System.out.print("Ingresa la altura de " + datosIMC[0][y] + ": ");
                    datosIMC[x][y] = String.valueOf(pedirAltura());
                }
            }
        }
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

        try {
            int numero = Integer.parseInt(inputNombre);
            System.out.println("Ingresaste un número: " + numero);

            inputNombre = pedirNombre();
        } catch (NumberFormatException e) {
            System.out.println("Ingresaste una cadena: " + inputNombre);
        }

        return inputNombre;
    }


    public static double pedirPeso() {
        Scanner scanner = new Scanner(System.in);
        double inputPeso = 0.0;

        while (true) {
            try {
                inputPeso = Double.parseDouble(scanner.next());

                if (inputPeso >= 10 && inputPeso <= 150) {
                    break;
                } else {
                    System.out.println("Error: El peso debe estar entre 10 y 150. Inténtalo nuevamente.");
                    pedirPeso();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                pedirPeso();
            }

            return inputPeso;
        }

        return inputPeso;
    }


    public static double pedirAltura() {
        Scanner scanner = new Scanner(System.in);
        double inputAltura = 0.0;

        while (true) {
            try {
                inputAltura = Double.parseDouble(scanner.next());

                if (inputAltura >= 10 && inputAltura <= 150) {
                    break;
                } else {
                    System.out.println("Error: El peso debe estar entre 10 y 150. Inténtalo nuevamente.");
                    pedirPeso();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                pedirPeso();
            }

            return inputAltura;
        }

        return inputAltura;
    }

    public static void mostrarMenu() {
        System.out.println("""
                Menú
                1) Ingresar datos.
                2) Ver datos ingresados.
                3) Categoria de lo niños.
                4) Promedio de peso y estatura de los niños.
                5) Salir.
                """);
    }


}