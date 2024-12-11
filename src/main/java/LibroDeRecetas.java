import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;

public class LibroDeRecetas {
    private int maxRecetasEnLibro;
    private Receta[] recetas;

    public LibroDeRecetas(int maxRecetasEnLibro) {
        // Inicialización del libro de recetas
        recetas =new Receta[maxRecetasEnLibro];
        this.maxRecetasEnLibro=maxRecetasEnLibro;
    }
    public boolean agregarReceta(Receta receta) {
        // Añade una receta al libro de recetas
        for (int i = 0; i<this.recetas.length; i++) {
            if (this.recetas[i]==null) {
                this.recetas[i]=receta;
                return true;
            }
        }
        return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA RECETA
    }

    public Receta[] buscarRecetaPorNombre(String texto) {
        Receta[] encontradas=new Receta[recetas.length];
        // Busca recetas por su nombre y devuelve todas las encontradas
        for (int i = 0; i< recetas.length; i++) {
            if (recetas[i]!=null && recetas[i].getNombre().toLowerCase().contains(texto.toLowerCase())) {
                encontradas[i] = recetas[i];
            }
            //El siguiente cacho simplemente cambia la posición de todos los [null] en el array de "encontradas" al final,
            //para que no haya nulls en el medio del array por si necesitamos buscar la cantidad de matches que tiene o
            //si ponemos un buscador que pare al encontrar el primer [null], que siga funcionando correctamente. -E
            for (int j = 0; j< encontradas.length; j++) {
                if (encontradas[j] == null && encontradas[j+1] != null) {
                    encontradas[j] = encontradas[j+1]; encontradas[j+1] = null;
                }
            }
        }
        return encontradas; // @todo MODIFICAR PARA DEVOLVER LAS RECETAS ENCONTRADAS
    }

    public void guardarRecetasEnArchivo(String nombreArchivo) throws IOException {
        // Guarda las recetas en un archivo de texto
        try (PrintWriter salida = new PrintWriter(nombreArchivo)) {
            for (int i = 0; i < maxRecetasEnLibro; i++) {
                if (recetas[i] != null) {
                    salida.print(recetas[i].toRawString());
                }
            }
        }
    }

    public void cargarRecetasDeArchivo(String nombreArchivo, int maxIngredientes, int maxInstrucciones) throws IOException {
        // Carga las recetas desde un archivo de texto
        BufferedReader entrada = null;
        try {
            int numeroRecetas=0;
            entrada=new BufferedReader(new FileReader(nombreArchivo));
            int lineaActual=1;
            String linea;
            while (numeroRecetas<maxRecetasEnLibro) {
                while ((linea = entrada.readLine()) != null) {
                    if (lineaActual == 1) {
                        Receta receta = new Receta(linea, maxIngredientes, maxInstrucciones);
                        agregarReceta(receta);
                        lineaActual++;
                    }
                }
            }
        } catch (IOException e) {
            //Estuve a punto de añadir "⚠⚠" en el output de error pero no sé si tiene que ser esos mensajes
            //palabra por palara y simplemente no estoy viendo dónde lo pone. -E
            System.out.println("ERROR AL CARGAR LA/S RECETA/S");
        } finally {
            if (entrada!=null) {
                try {
                    entrada.close();
                }catch (IOException e) {
                    System.out.println("ERROR AL CERRAR EL FICHERO");
                }
            }
        }



    }

    public boolean recetasCompletas() {
        // Comprueba si el libro de recetas está completo
        for (int i = 0; i< recetas.length; i++) {
            if (recetas[i]==null) {
                return false;
            }
        }
        return true; // @todo MODIFICAR PARA DEVOLVER SI ESTÁ COMPLETO
    }

    public int numRecetas() {
        // Devuelve el número de recetas en el libro
        int numRecetas=0;
        for (int i=0;i<recetas.length;i++) {
            if (recetas[i]!=null) {
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

