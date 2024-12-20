import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;

/**
 * *La clase LibroDeRecetas tiene dos atributos privados:
 - maxRecetasEnLibro (el número máximo de recetas que puede haber en el libro de recetas)
 - recetas (un array de Recetas)
 */
public class LibroDeRecetas {
    private int maxRecetasEnLibro;
    private Receta[] recetas;

    /**
     * Constructor de la clase que incializa maxRecetasEnLibro y que crea un array vacío de recetas de longitud
     * "maxRecetasEnLibro"
     * @param maxRecetasEnLibro el número máximo deseado de recetas que va a tener el libro
     */
    public LibroDeRecetas(int maxRecetasEnLibro) {
        recetas = new Receta[maxRecetasEnLibro];
        this.maxRecetasEnLibro = maxRecetasEnLibro;
    }

    /**
     * Agrega una receta al libro de recetas
     * @param receta es la receta que se desea agregar al libro de recetas
     * @return "true" si se ha podido agregar, y "false" en caso contrario
     */
    public boolean agregarReceta(Receta receta) {
        for (int i = 0; i<recetas.length; i++) {
            if (recetas[i]==null) {
                recetas[i]=receta;
                return true;
            }
        }
        return false;
    }

    /**
     * Busca los nombres de las recetas parecidos al texto introducido.
     * @param texto cadena de texto que se comparará con el nombre de todas las recetas existentes en el libro
     * @return devuelve las recetas que haya encontrado que coincidan con el texto introudicido
     */
    public Receta[] buscarRecetaPorNombre(String texto) {
        Receta[] encontradas=new Receta[recetas.length];
        int posicionDeRecetasEncontradas=0;
        for (Receta receta : recetas) {
            if (receta != null && receta.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                encontradas[posicionDeRecetasEncontradas] = receta;
                posicionDeRecetasEncontradas++;
            }
        }
        return encontradas;
    }

    /**
     * Guarda las recetas creadas en un arhcivo de texto
     * @param nombreArchivo el nombre del archivo donde se van a guardar las recetas
     * @throws IOException posible excepción al ejecutarse la función
     */
    public void guardarRecetasEnArchivo(String nombreArchivo) throws IOException {
        try (PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < maxRecetasEnLibro; i++) {
                if (recetas[i] != null) {
                    salida.print(recetas[i].toRawString());
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GUARDAR LA RECETA");
        }
    }

    /**
     * Carga las recetas guardadas en un archivo de texto al libro de recetas
     * @param nombreArchivo nombre del archvio donde se guardaron las recetas
     * @param maxIngredientes el número máximo de ingredientes en la receta
     * @param maxInstrucciones el número máximo de instrucciones en la receta
     * @throws IOException posible excepción al ejecutarse la función
     */
    public void cargarRecetasDeArchivo(String nombreArchivo, int maxIngredientes, int maxInstrucciones) throws IOException {
        // Carga las recetas desde un archivo de texto
        String linea1, lineaWhile;
        try (BufferedReader entrada = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((linea1 = entrada.readLine()) != null) {
                Receta recetaImportada = new Receta(linea1, maxIngredientes, maxInstrucciones);
                while (!(lineaWhile = entrada.readLine()).equals("INSTRUCCIONES")) {
                    recetaImportada.agregarIngrediente(lineaWhile);
                }
                while (!(lineaWhile = entrada.readLine()).equals("-----")) {
                    recetaImportada.agregarInstruccion(lineaWhile);
                }
                agregarReceta(recetaImportada);
            }
        } catch (IOException e) {
            System.out.println("ERROR AL CARGAR LA RECETA");
        }
    }

    /**
     * Determina si se ha llegado al número máximo de recetas en el libro de recetas
     * @return "true" si el libro está completo, "false" en caso contrario
     */
    public boolean recetasCompletas() {
        boolean varTemp = true;
        for (Receta receta : recetas) {
            if (receta == null) {
                varTemp = false;
                break;
            }
        }
        return varTemp;
    }

    /**
     *
     * @return Devuelve el número de recetas en el libro
     */
    public int numRecetas() {
        int numRecetas=0;
        for (Receta receta : recetas) {
            if (receta != null) {
                numRecetas++;
            }
        }
        return numRecetas; //
    }

    /**
     * Elimina una receta del libro de recetas
     * @param seleccionada receta que va a ser eliminada
     */
    public void eliminarReceta(Receta seleccionada) {
        for (int i =0;i<recetas.length;i++) {
            if (recetas[i]==seleccionada) {
                recetas[i]=null;
            }
        }
    }
}