/**
 * La clase receta tiene cinco atributos:
 *-nombre (el nombre de la receta)
 *-listaIngredientes (la lista de ingredientes de la receta)
 *-istaInstrucciones (muestra la lista de instrucciones para realizar la receta)
 *-maxIngredientes (el número máximo de ingredientes)
 *-maxInstrucciones (el máximo de instrucciones de la receta)
 */
public class Receta {
    private String nombre;
    private String[] ingredientes;
    private String[] instrucciones;
    private int maxIngredientes;
    private int maxInstrucciones;

    /**
     * @param nombre inicializa el constructor del atributo nombre
     * @param maxIngredientes inicializa el constructor del atributo de ingredientes marcando su maximo
     * @param maxInstrucciones inicializa el constructor del atributo de instrucciones marcando su maximo
     */
    public Receta(String nombre, int maxIngredientes, int maxInstrucciones) {
        this.nombre= nombre;
        this.ingredientes= new String[maxIngredientes];
        this.instrucciones= new String[maxInstrucciones];
        this.maxIngredientes= maxIngredientes;
        this.maxInstrucciones= maxInstrucciones;
    }

    /**
     * @return devuelve un String con el nombre de la receta
     * La funcion es un getter del String nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return devuelve un array de String con la lista de ingredientes
     * La funcion es un getter del String listaIngredientes
     */
    public String[] getIngredientes() {
        return this.ingredientes;
    }

    /**
     * @return devuelve un array de String con la lista de instrucciones de la receta
     * La funcion es un getter del String listaInstruccciones
     */
    public String[] getInstrucciones() {
        return this.instrucciones;
    }

    /**
     * @param ingrediente es el ingrediente a introducir
     * @return devuelve true si ha sido capaz de introducir el ingrediente en el array listaIngredientes
     * La funcion agregarIngredientes añade ingredientes en el array listaIngredientes
     */
    public boolean agregarIngrediente(String ingrediente) {
        boolean libre= true;
        for (int i = 0; i<ingredientes.length&&libre; i++){
            if (ingredientes[i]==null){
                libre=false;
                ingredientes[i]=ingrediente;
            }
        }
        if (libre){
            System.out.println("No se puede añadir mas ingredientes");
        }
        return !libre;
    }

    /**
     * @param instruccion es la instruccion a añadir
     * @return duelvelve true si se ha añadido la instruccion, devuelve false si no
     * la funcion agregarInstruccion añade instrucciones en el array listaInstrucciones
     */
    public boolean agregarInstruccion(String instruccion) {
        boolean libre= true;
        for (int i = 0; i< instrucciones.length&&libre; i++){
            if (instrucciones[i]==null){
                libre=false;
                instrucciones[i]=instruccion;
            }
        }
        if (libre){
            System.out.println("No se puede añadir mas instrucciones");
        }
        return !libre;
    }

    /**
     * @return devuelve true si la receta contiene el numero maximo de ingredientes,
     * en caso contrario duevuelve falso
     */
    public boolean ingredientesCompletos() {
        boolean fin=false;
        for (int i = 0; i<ingredientes.length&&!fin; i++){
            if (ingredientes[i]==null){
                fin=true;
            }
        }
        return !fin;
    }

    /**
     * @return devuelve true si las instrucciones estan completas, en caso contrario
     * devuelve false
     */
    public boolean instruccionesCompletas() {
        boolean comprobanteInstCompletas =true;
        for (int i = 0; i<instrucciones.length; i++){
            if (instrucciones[i]==null){
                comprobanteInstCompletas = false;
                break;
            }
        }
            return comprobanteInstCompletas;
    }

    /**
     * @return devuelve el numero de ingredientes tiene la receta
     */
    public int numIngredientes() {
        int numIngredientes=0;
        for (int i=0; i<ingredientes.length; i++){
            if (ingredientes[i]!=null){
                numIngredientes+=1;
            }
        }
        return  numIngredientes;
    }

    /**
     * @return devuelve el número de instrucciones de una funcion
     */
    public int numInstrucciones() {
        int numInstrucciones=0;
        for (int i=0; i<instrucciones.length; i++){
            if (instrucciones[i]!=null){
                numInstrucciones+=1;
            }
        }
        return  numInstrucciones;
    }

    /**
     * @return devuelve un string con el nombre, ingredientes e instrucciones de la receta en cuestion
     */
    @Override
    public String toString() {
        StringBuilder recetaFormateada = new StringBuilder("Receta: " + getNombre() + "\n" + "Ingredientes:");
        for (int i=0; i<numIngredientes(); i++) {
            recetaFormateada.append("\n" + "- ").append(getIngredientes()[i]);
        }
        StringBuilder instrucciones= new StringBuilder("\nInstrucciones:\n");
        for (int i=0; i<numInstrucciones(); i++) {
            instrucciones.append((i + 1)).append(". ").append(getInstrucciones()[i]).append("\n");
        }
        return recetaFormateada + instrucciones.toString();
        // Devuelve una representación en forma de cadena de la recetaFormateada
    }

    public String toRawString() {
        // Devuelve una representación en forma de cadena de la receta sin formato
        StringBuilder receta= new StringBuilder(getNombre() + "\n");
        for (int i=0; i<numIngredientes(); i++) {
            receta.append(getIngredientes()[i]).append("\n");
        }
        StringBuilder instrucciones= new StringBuilder("INSTRUCCIONES" + "\n");
        for (int i=0; i<numInstrucciones(); i++) {
            instrucciones.append(getInstrucciones()[i]).append("\n");
        }
        return receta + instrucciones.toString() + "-----\n";
}

    /**
     * @return devuelve el maximo de ingredientes de la receta
     */
    public int getMaxIngredientes() {
        return this.maxIngredientes;
    }

    /**
     * @return devuelve el maximo de instrucciones de la receta
     */
    public int getMaxInstrucciones() {
        return this.maxInstrucciones;
}
}