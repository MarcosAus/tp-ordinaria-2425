import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;

/**
 La clase LibroDeRecetas tiene dos atributos privados:
 - maxRecetasEnLibro (el número máximo de recetas que puede haber en el libro de recetas)
 - recetas (un array de Recetas)
 */
public class LibroDeRecetas {
    private int maxRecetasEnLibro;
    private Receta[] recetas;

    public LibroDeRecetas(int maxRecetasEnLibro) {
        // Inicialización del libro de recetas
        recetas = new Receta[maxRecetasEnLibro];
        this.maxRecetasEnLibro = maxRecetasEnLibro;
    }
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

    public Receta[] buscarRecetaPorNombre(String texto) {
        Receta[] encontradas=new Receta[recetas.length];
        int posicionDeRecetasEncontradas=0;
        // Busca recetas por su nombre y devuelve todas las encontradas
        for (int i = 0; i < recetas.length; i++) {
            if (recetas[i] != null && recetas[i].getNombre().toLowerCase().contains(texto.toLowerCase())) {
                encontradas[posicionDeRecetasEncontradas] = recetas[i];
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