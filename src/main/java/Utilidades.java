import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {
    public static String leerCadena(Scanner teclado, String s) {
        // Muestra un mensaje y lee una cadena por teclado
        System.out.println(s);
        return teclado.nextLine();
    }

    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        // Muestra un mensaje y lee un número por teclado (si no es un número, vuelve a solicitar uno)
        System.out.println(mensaje);
        int ResultDeLeerNumero=0;
        try {
            int entrada = teclado.nextInt();
            while (entrada < minimo || entrada > maximo) {
                System.out.println("Número fuera del rango permitido, por favor intente de nuevo: ");
                entrada = teclado.nextInt();
            }
            ResultDeLeerNumero = entrada;
        } catch (NumberFormatException ex) {
            System.out.println("Entrada en fomato inválida");
            leerNumero(teclado, mensaje, minimo, maximo);
        }
        return ResultDeLeerNumero; //este int hace falta porque al intentar usar directamente entrada da un error aunque la variable se inicialice fuera, así que actúa como puente entre el interior del method y el exterior -E
    }

    public static int leerDiaDeLaSemana(Scanner teclado, String mensaje) {
        // Muestra un mensaje, lee un día de la semana por teclado (L, M, X, J, V, S, D) y devuelve su posición
        // dentro de la semana (0-6)
        System.out.println(mensaje);
        int posicion=0;
        try {
            String diaDeLaSemana = teclado.nextLine();
            diaDeLaSemana=diaDeLaSemana.toUpperCase();
            posicion = diaSemanaAPosicion(diaDeLaSemana);
            while (posicion == -1) {
                System.out.println("Error. Introduzca la primera letra del día deseado: ");
                diaDeLaSemana=teclado.nextLine();
                diaDeLaSemana=diaDeLaSemana.toUpperCase();
                posicion = diaSemanaAPosicion(diaDeLaSemana);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Entrada en formato inválido");
            leerDiaDeLaSemana(teclado,mensaje);
        }
        return posicion;
    }

    public static int diaSemanaAPosicion(String dia) {
        // Devuelve la posición de un día de la semana (L, M, X, J, V, S, D) dentro de la semana (0-6)
        dia=dia.toUpperCase();
        int posicion;
        switch (dia) {
            case "L": posicion = 0; break;
            case "M": posicion = 1; break;
            case "X": posicion = 2; break;
            case "J": posicion = 3; break;
            case "V": posicion = 4; break;
            case "S": posicion = 5; break;
            case "D": posicion = 6; break;
            default:
                //creo que este default no se utiliza nunca, pero es mejor tener un caso por defecto en caso de que haya un error que crashear el programa -E
                posicion = -1;
        }
        return posicion;
    }

    public static String posicionADiaSemana(int pos) {
        String salida = "";
        try {
            switch (pos) {
                case 0:
                    salida = "Lunes";
                break;
                case 1:
                    salida = "Martes";
                break;
                case 2:
                    salida = "Miércoles";
                break;
                case 3:
                    salida = "Jueves";
                break;
                case 4:
                    salida = "Viernes";
                break;
                case 5:
                    salida = "Sábado";
                break;
                case 6:
                    salida = "Domingo";
                break;
                default:
                    salida = "Desconocido";
                break;
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Argumento no permitido, inténtelo de nuevo");
            posicionADiaSemana(pos);
        }
        // Devuelve el día de la semana (Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo)
        // correspondiente a una posición dentro de la semana (0-6)
    return salida;
    }
}
