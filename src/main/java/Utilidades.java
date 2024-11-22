import java.util.Scanner;
/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {
    public static String leerCadena(Scanner teclado, String s) {
        // Muestra un mensaje y lee una cadena por teclado
        System.out.println(s);
        teclado=new Scanner(System.in);
        return teclado.nextLine();
    }

    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        // Muestra un mensaje y lee un número por teclado (si no es un número, vuelve a solicitar uno)
        System.out.println(mensaje);
        teclado = new Scanner(System.in);
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
        return ResultDeLeerNumero;
    }

    public static int leerDiaDeLaSemana(Scanner teclado, String mensaje) {
        // Muestra un mensaje, lee un día de la semana por teclado (L, M, X, J, V, S, D) y devuelve su posición
        // dentro de la semana (0-6)
        return 0; // @todo MODIFICAR PARA DEVOLVER EL DÍA DE LA SEMANA LEÍDO
    }

    public static int diaSemanaAPosicion(String dia) {
        // Devuelve la posición de un día de la semana (L, M, X, J, V, S, D) dentro de la semana (0-6)
        return 0; // @todo MODIFICAR PARA DEVOLVER LA POSICIÓN DEL DÍA DE LA SEMANA
    }

    public static String posicionADiaSemana(int pos) {
        // Devuelve el día de la semana (Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo)
        // correspondiente a una posición dentro de la semana (0-6)
        return null; // @todo MODIFICAR PARA DEVOLVER EL DÍA DE LA SEMANA
    }


}
