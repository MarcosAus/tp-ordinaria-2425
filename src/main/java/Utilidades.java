import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {
    /**
     * @param teclado el nombre del objeto del Scanner
     * @param s el mensaje que se imprimirá al ejecutar la función
     * @return devuelve la cadena que el usuario ha introducido por el teclado
     */
    public static String leerCadena(Scanner teclado, String s) {
        // Muestra un mensaje y lee una cadena por teclado
        System.out.println(s);
        return teclado.nextLine();
    }

    /**
     * @param teclado el nombre del objeto del Scanner
     * @param mensaje el mensaje que se imprimirá al ejecutar la función
     * @param minimo el número mínimo que el usuario puede introducir
     * @param maximo el número máximo que el usuario puede introducir
     * @return devuelve el número que el usuario ha introducido por el teclado
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        // Muestra un mensaje y lee un número por teclado (si no es un número, vuelve a solicitar uno)
        System.out.println(mensaje);
        int entrada;
        while (true) {
            if (teclado.hasNextInt()) {
                entrada = teclado.nextInt();
                if (entrada >= minimo && entrada <= maximo) {
                    return entrada;
                }
                System.out.print("Número fuera del rango permitido, por favor intente de nuevo: ");
            } else {
                System.out.print("Entrada en formato incorrecto, introdúzaclo de nuevo: ");
                teclado.next();
            }
        }
    }

    /**
     * @param teclado el nombre del objeto del Scanner
     * @param mensaje el mensaje que se imprimirá al ejecutar la función
     * @return devuelve la posición del día de la semana introducido (obtenido gracias a diaSemanaPosicion)
     */

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

    /**
     * @param dia dia de la semana
     * @return devuelve la posición del día de la semana introducido
     */
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

    /**
     * @param pos posición del día de la semana
     * @return un string que es el día de la semana correspondiente
     */
    public static String posicionADiaSemana(int pos) {
        String salida = "";
        try {
            switch (pos) {
                case 0 -> salida = "Lunes";
                case 1 -> salida = "Martes";
                case 2 -> salida = "Miércoles";
                case 3 -> salida = "Jueves";
                case 4 -> salida = "Viernes";
                case 5 -> salida = "Sábado";
                case 6 -> salida = "Domingo";
                default -> salida = "Desconocido";
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
