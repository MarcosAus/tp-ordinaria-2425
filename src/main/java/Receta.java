public class Receta {
private String nombre;
private int maxIngredientes;
private int maxInstrucciones;
private String[] ingredientes;
private String[] instrucciones;

    public Receta(String nombre, int maxIngredientes, int maxInstrucciones) {
        // Inicialización de la receta
        this.nombre=nombre;
        this.maxIngredientes=maxIngredientes;
        this.maxInstrucciones=maxInstrucciones;
        ingredientes=new String[maxIngredientes];
        instrucciones=new String[maxInstrucciones];
    }

    public String getNombre() {
        // Devuelve el nombre de la receta
        return nombre;
    }

    public String[] getIngredientes() {
        // Devuelve los ingredientes de la receta
        return ingredientes;
    }

    public String[] getInstrucciones() {
        // Devuelve las instrucciones de la receta
        return instrucciones;
    }

    public boolean agregarIngrediente(String ingrediente) {
        // Añade un ingrediente a la receta
        for (int i=0; i<ingredientes.length; i++) {
            if (ingredientes[i]==null) {
                ingredientes[i]=ingrediente;
                return true;
            }
        }
        return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO EL INGREDIENTE
    }

    public boolean agregarInstruccion(String instruccion) {
        // Añade una instrucción a la receta
        for (int i=0; i<instrucciones.length; i++) {
            if (instrucciones[i]==null) {
                instrucciones[i]=instruccion;
                return true;
            }
        }
        return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA INSTRUCCIÓN
    }

    public boolean ingredientesCompletos() {
        // Comprueba si la receta tiene el máximo de ingredientes
        return ingredientes.length == maxIngredientes;// @todo MODIFICAR PARA DEVOLVER SI ESTÁN COMPLETOS LOS INGREDIENTES
    }

    public boolean instruccionesCompletas() {
        // Comprueba si la receta tiene el máximo de instrucciones
        return instrucciones.length == maxInstrucciones; // @todo MODIFICAR PARA DEVOLVER SI ESTÁN COMPLETAS LAS INSTRUCCIONES
    }

    public int numIngredientes() {
        // Devuelve el número de ingredientes de la receta
        for (int i=0; i<ingredientes.length; i++) {
            if (ingredientes[i]==null) {
                return i;
            }
        }
        return maxIngredientes; // @todo MODIFICAR PARA DEVOLVER EL NÚMERO DE INGREDIENTES
    }

    public int numInstrucciones() {
        // Devuelve el número de instrucciones de la receta
        for (int i=0; i<instrucciones.length; i++) {
            if (instrucciones[i]==null) {
                return i;
            }
        }
        return maxInstrucciones; // @todo MODIFICAR PARA DEVOLVER EL NÚMERO DE INSTRUCCIONES
    }

    @Override
    public String toString() {
        StringBuilder recetaFormateada = new StringBuilder("Receta: " + getNombre() + "\n" + "Ingredientes:");
        for (int i=0; i<numIngredientes(); i++) {
            recetaFormateada.append("\n" + "- ").append(getIngredientes()[i]);
        }
        StringBuilder instrucciones= new StringBuilder("\nInstrucciones:\n");
        for (int i=0; i<numInstrucciones(); i++) {
            instrucciones.append((i+1) + ". ").append(getInstrucciones()[i]).append("\n");
        }
        return recetaFormateada + instrucciones.toString();
        // Devuelve una representación en forma de cadena de la recetaFormateada
        // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
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
        return receta + instrucciones.toString(); // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public int getMaxIngredientes() {
        // Devuelve el máximo de ingredientes permitidos en la receta
        return maxIngredientes;
    }

    public int getMaxInstrucciones() {
        // Devuelve el máximo de instrucciones permitidas en la receta
        return maxInstrucciones;
    }
}

