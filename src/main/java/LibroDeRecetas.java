import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;

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
        boolean comprobanteAgregarReceta = false;
        while (!comprobanteAgregarReceta) {
            for (int i = 0; i < recetas.length; i++) {
                if (recetas[i] == null) {
                    recetas[i] = receta;
                    comprobanteAgregarReceta = true;

                }
            }
        }
        return comprobanteAgregarReceta; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA RECETA
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
        return encontradas; // @todo MODIFICAR PARA DEVOLVER LAS RECETAS ENCONTRADAS
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
    }

    public boolean recetasCompletas() {
        // Comprueba si el libro de recetas está completo
        boolean comprobanteRecetasCompletas = true;
        for (Receta receta : recetas) {
            if (receta != null) {
                continue;
            }
            comprobanteRecetasCompletas = false;
        }
        return comprobanteRecetasCompletas; // @todo MODIFICAR PARA DEVOLVER SI ESTÁ COMPLETO
    }

    public int numRecetas() {
        // Devuelve el número de recetas en el libro
        int numRecetas=0;
        for (Receta receta : recetas) {
            if (receta != null) {
                numRecetas++;
            }
        }
        return numRecetas; // @todo MODIFICAR PARA DEVOLVER EL NÚMERO DE RECETAS
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