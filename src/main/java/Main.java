import java.io.IOException;
//Genérico: los comentarios acabados en "-E" significan que el comentario es de Ekaitz. La mayoría de ellos están en mi código,
//pero hay alguno que está en el código de Marcos si me ha costado entenderlo a mí y creo que necesita clarificación. -E

/**
 * La clase Main es la clase más importante, ya que es la que permite ejecutar el programa. No tiene atributos privados,
 * pero sí argumentos, que deben ser fijados con tres números (y el nombre del archivo que se va a cargar si así se desea)
 * en el menú de edición de la clase. Es fundamental que los argumentos se fijen antes de ejecutar la función.
 * La función muestra todos los argumentos fijados por la pantalla e inicia el menú principal de la interfaz del usuario.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                int maxIngredientesPorReceta = Integer.parseInt(args[0]);
                int maxInstruccionesPorReceta = Integer.parseInt(args[1]);
                int maxRecetasEnLibro = Integer.parseInt(args[2]);
                System.out.println("Configuración recibida:");
                System.out.println("Máx. Ingredientes por Receta: " + maxIngredientesPorReceta);
                System.out.println("Máx. Instrucciones por Receta: " + maxInstruccionesPorReceta);
                System.out.println("Máx. Recetas en el Libro: " + maxRecetasEnLibro);
                InterfazUsuario interfaz = new InterfazUsuario(maxIngredientesPorReceta, maxInstruccionesPorReceta, maxRecetasEnLibro);
                interfaz.iniciar();
            } catch (NumberFormatException e) {
                System.out.println("Error: Los primeros tres argumentos deben ser números enteros.");
            }
        }
        else if (args.length==4) {
            try {
                int maxIngredientesPorReceta = Integer.parseInt(args[0]);
                int maxInstruccionesPorReceta = Integer.parseInt(args[1]);
                int maxRecetasEnLibro = Integer.parseInt(args[2]);
                String nombreArchivoRecetas = args[3];
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
