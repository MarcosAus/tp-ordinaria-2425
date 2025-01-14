import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * La clase PlanificadorSemanal tiene un solo atributo privado:
 * - contenidoPlanificador (es una matriz vacía con un tamaño de 7 y 2, respectivamente)
 */
public class PlanificadorSemanal {
    private String[][] contenidoPlanificador = new String[7][2];

    /**
     * Constructor de PlanificadorSemanal, que guarda cada posición del primer array de la matriz como un día de la semana
     */
    PlanificadorSemanal() {
        // Inicialización del planificador semanal
        contenidoPlanificador[0][0] = "Lunes";
        contenidoPlanificador[1][0] ="Martes";
        contenidoPlanificador[2][0] ="Miércoles";
        contenidoPlanificador[3][0] ="Jueves";
        contenidoPlanificador[4][0] = "Viernes";
        contenidoPlanificador[5][0] ="Sábado";
        contenidoPlanificador[6][0] ="Domingo";
    }

    /**
     * Agrega una comida al planificador
     * @param dia posición del día de la semana donde se quiere agregar
     * @param receta receta que se quiere agregar al planificador
     */
    public void agregarComida(int dia, Receta receta) {
        // Añade una receta a un día de la semana en el planificador semanal
        contenidoPlanificador[dia][1] = receta.getNombre();
        System.out.println("Receta planificada para " + Utilidades.posicionADiaSemana(dia));
    }

    /**
     * toString() que imprime el planificador semanal formateado
     * @return devuelve el objeto de la clase StringBuilder
     */
    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();
        int totalDePosiciones;
        int maxLongitud = 11; // 11 es lo que mide el bloque de 1 día en el expected vacío, que es la cantidad mínima que puede llegar a medir la posición -E
        int[] longitudInput = new int[7];
        int[] longitudDia = new int[7];

        //este bloque determina la longitud de las líneas
        for (int y = 0; y < 7; y++) {
            longitudDia[y] = contenidoPlanificador[y][0].length();
            if (contenidoPlanificador[y][1] != null) {
                longitudInput[y] = contenidoPlanificador[y][1].length();
                if (longitudInput[y] > maxLongitud) {
                    maxLongitud = (longitudInput[y]+2);
                }
            }
        }

        totalDePosiciones = 7*maxLongitud; // 7 veces por las siete posiciones por los siete días de la semana -E

        for (int u = 0; u < totalDePosiciones; u++) { //este bloque es la línea 1. Añade una cantidad variable de "-"s en función de la longitud de las líneas de texto
            if (u == (totalDePosiciones-1)) {
                output.insert(u,"-\n");
            }else{
                output.insert(u, "-");
            }
        }

        for (int y = 0; y < 7; y++) { //este bloque printea los días de la semana, separados en bloques del tamaño de día o receta más largo de todos, más un espacio al princpio y otro al final -E
            output.append(" ").append(contenidoPlanificador[y][0]);
            for (int w = 0; w < ((maxLongitud - (longitudDia[y] + 1))); w++) {
                output.append(" ");
            }
            if (longitudInput[y] - longitudDia[y] == 1 && y == 6) {
                output.append("\n");
            }
        }

        for (int u = 0; u < totalDePosiciones; u++) { //prácticamente lo mismo que la línea nº1, con el caveat de que al estar en medio también necesita un \n al final
            if (u == (totalDePosiciones-1)) {
                output.append("-\n");
            }else if (u == 0) {
                output.append("\n-");
            } else {
                output.append("-");
            }
        }

        for (int i = 0; i < 7; i++) { //igual que la línea 2, pero al ser inputs de longitud variable se complica un poco más
            if (contenidoPlanificador[i][1] != null) {
                output.append(" ").append(contenidoPlanificador[i][1]);
            } else{
                output.append(" ");
            }
            for (int k = 0; k < (maxLongitud - (longitudInput[i] + 1)); k++) {
                output.append(" ");
                if (longitudInput[i] - longitudDia[i] == 1 && i == 6) {
                    output.append("\n");
                }
            }
        }

        for (int u = 0; u < totalDePosiciones; u++) { //igual que las líneas 1 y 3
            if (u == (totalDePosiciones-1)) {
                output.append("-\n");
            }else if (u == 0) {
                output.append("\n-");
            } else {
                output.append("-");
            }
        }

        output.append("\n"); //Esto aquí porque los expected acaban con dos líneas en blanco en vez de una -E

        return String.valueOf(output);
    }

    /**
     * Función que guarda el planificador semanal en un arvhivo de texto
     * @param nombreArchivo es el nombre del archivo que vas a generar
     * @throws IOException la excecpción que puede ocurrir al guardar el PLan en el archivo
     */
    public void guardarPlanEnArchivo(String nombreArchivo) throws IOException {
        try (PrintWriter salida=new PrintWriter(new FileWriter(nombreArchivo))) {
            for (String[] strings : contenidoPlanificador) {
                salida.print(strings[0] + ": " + (strings[1] == null ? "---\n" : strings[1]+"\n"));
            }
        }
    }
}
