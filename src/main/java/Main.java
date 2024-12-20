import java.io.IOException;
//Genérico: los comentarios acabados en "-E" significan que el comentario es de Ekaitz. La mayoría de ellos están en mi código,
//pero hay alguno que está en el código de Marcos si me ha costado entenderlo a mí y creo que necesita clarificación. -E
public class Main {
    public static void main(String[] args) {
        // Comprueba los argumentos de la línea de comandos y lanza la interfaz de usuario
        if (args.length == 3) {
            try {
                // Leer los argumentos
                int maxIngredientesPorReceta = Integer.parseInt(args[0]);
                int maxInstruccionesPorReceta = Integer.parseInt(args[1]);
                int maxRecetasEnLibro = Integer.parseInt(args[2]);
                // Mostrar los valores para confirmación
                System.out.println("Configuración recibida:");
                System.out.println("Máx. Ingredientes por Receta: " + maxIngredientesPorReceta);
                System.out.println("Máx. Instrucciones por Receta: " + maxInstruccionesPorReceta);
                System.out.println("Máx. Recetas en el Libro: " + maxRecetasEnLibro);
                InterfazUsuario interfaz = new InterfazUsuario(maxIngredientesPorReceta, maxInstruccionesPorReceta, maxRecetasEnLibro);
                interfaz.iniciar();
                // Aquí puedes continuar implementando la lógica del programa usando estas variables
            } catch (NumberFormatException e) {
                System.out.println("Error: Los primeros tres argumentos deben ser números enteros.");
            }
        }
        else if (args.length==4) {
            try {
                // Leer los argumentos
                int maxIngredientesPorReceta = Integer.parseInt(args[0]);
                int maxInstruccionesPorReceta = Integer.parseInt(args[1]);
                int maxRecetasEnLibro = Integer.parseInt(args[2]);
                String nombreArchivoRecetas = args[3];
                // Mostrar los valores para confirmación
                System.out.println("Configuración recibida:");
                System.out.println("Máx. Ingredientes por Receta: " + maxIngredientesPorReceta);
                System.out.println("Máx. Instrucciones por Receta: " + maxInstruccionesPorReceta);
                System.out.println("Máx. Recetas en el Libro: " + maxRecetasEnLibro);
                System.out.println("Nombre del Archivo de Recetas: " + nombreArchivoRecetas);

                InterfazUsuario interfaz = new InterfazUsuario(maxIngredientesPorReceta, maxInstruccionesPorReceta, maxRecetasEnLibro, nombreArchivoRecetas);
                interfaz.iniciar();
            } catch (NumberFormatException e) {
                System.out.println("Error: Los primeros tres argumentos deben ser números enteros.");
            }
        }
        else {
            System.out.println("Número de argumentos no válido");
        }
    }
}
