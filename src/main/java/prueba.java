import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class prueba {
    private int maxRecetasEnLibro;
    private Receta[] recetas;
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
                        //agregarReceta(receta);
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

        //for (int i=0; i<this.instrucciones.length; i++) {
            //if (instrucciones[i]==null) {
                //instrucciones[i]=instruccion;
                //return true;
            //}
        //}
        //return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA INSTRUCCIÓN
    //}


    }
}
