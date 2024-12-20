import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PlanificadorSemanal {
    private String[][] contenidoPlanificador = new String[7][2];

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

    public void agregarComida(int dia, Receta receta) {
        // Añade una receta a un día de la semana en el planificador semanal
        contenidoPlanificador[dia][1] = receta.getNombre();
        System.out.println("Receta planificada para " + Utilidades.posicionADiaSemana(dia));
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();
        int totalDePosiciones;
        int maxLongitud = 11;
        int[] longitudInput = new int[7];
        int[] longitudDia = new int[7];

        for (int y = 0; y < 7; y++) {
            longitudDia[y] = contenidoPlanificador[y][0].length();
            if (contenidoPlanificador[y][1] != null) {
                longitudInput[y] = contenidoPlanificador[y][1].length();
                if (longitudInput[y] > maxLongitud) {
                    maxLongitud = (longitudInput[y]+2);
                }
            }
        }

        totalDePosiciones = 7*maxLongitud;

        for (int u = 0; u < totalDePosiciones; u++) {
            if (u == (totalDePosiciones-1)) {
                output.insert(u,"-\n");
            }else{
                output.insert(u, "-");
            }
        }

        for (int y = 0; y < 7; y++) {
            output.append(" ").append(contenidoPlanificador[y][0]);
            for (int w = 0; w < ((maxLongitud - (longitudDia[y] + 1))); w++) {
                output.append(" ");
            }
            if (longitudInput[y] - longitudDia[y] == 1 && y == 6) {
                output.append("\n");
            }
        }

        for (int u = 0; u < totalDePosiciones; u++) {
            if (u == (totalDePosiciones-1)) {
                output.append("-\n");
            }else if (u == 0) {
                output.append("\n-");
            } else {
                output.append("-");
            }
        }

        for (int i = 0; i < 7; i++) {
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

        for (int u = 0; u < totalDePosiciones; u++) {
            if (u == (totalDePosiciones-1)) {
                output.append("-\n");
            }else if (u == 0) {
                output.append("\n-");
            } else {
                output.append("-");
            }
        }

        output.append("\n");

        return String.valueOf(output);
    }

    /**
     *
     * @param nombreArchivo es el nombre del archivo que vas a generar
     * @throws IOException la excecpción que puede ocurrir al guardar el PLan en el archivo
     */
    public void guardarPlanEnArchivo(String nombreArchivo) throws IOException {
        // Guarda el planificador semanal en un archivo de texto
        try (PrintWriter salida=new PrintWriter(new FileWriter(nombreArchivo))) {
            for (String[] strings : contenidoPlanificador) {
                salida.print(strings[0] + ": " + (strings[1] == null ? "---\n" : strings[1]+"\n"));
            }
        }
    }
}
