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
        // Inicialización del libro de recetas
        recetas = new Receta[maxRecetasEnLibro];
        this.maxRecetasEnLibro = maxRecetasEnLibro;
    }

    /**
     *
     * @param receta es la receta que se desea agregar al libro de recetas
     * @return "true" si se ha podido agregar, y "false" en caso contrario
     */
    public boolean agregarReceta(Receta receta) {
        // Añade una receta al libro de recetas
        for (int i = 0; i<recetas.length; i++) {
            if (recetas[i]==null) {
                recetas[i]=receta;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param texto cadena de texto que se comparará con el nombre de todas las recetas existentes en el libro
     * @return
     */
    public Receta[] buscarRecetaPorNombre(String texto) {
        Receta[] encontradas=new Receta[recetas.length];
        int posicionDeRecetasEncontradas=0;
        // Busca recetas por su nombre y devuelve todas las encontradas
        for (Receta receta : recetas) {
            if (receta != null && receta.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                encontradas[posicionDeRecetasEncontradas] = receta;
                posicionDeRecetasEncontradas++;
            }
        }
        return encontradas;
    }

    public void guardarRecetasEnArchivo(String nombreArchivo) throws IOException {
        // Guarda las recetas en un archivo de texto
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

    public boolean recetasCompletas() {
        // Comprueba si el libro de recetas está completo
        boolean varTemp = true;
        for (int i = 0; i< recetas.length; i++) {
            if (recetas[i]==null) {
                varTemp = false;
            }
        }
        return varTemp;
    }

    public int numRecetas() {
        // Devuelve el número de recetas en el libro
        int numRecetas=0;
        for (int i=0;i<recetas.length;i++) {
            if (recetas[i]!=null) {
                numRecetas++;
            }
        }
        return numRecetas; //
    }

    public void eliminarReceta(Receta seleccionada) {
        // Elimina una receta del libro de recetas
        for (int i =0;i<recetas.length;i++) {
            if (recetas[i]==seleccionada) {
                recetas[i]=null;
            }
        }
    }
}