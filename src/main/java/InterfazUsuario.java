import java.util.Scanner;

public class InterfazUsuario {
    private LibroDeRecetas libroDeRecetas;
    private PlanificadorSemanal planificador;
    private int maxIngredientes;
    private int maxInstrucciones;
    private Receta[] recetas;

    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro) {
        // Inicialización de la herramienta de recetas
        this.maxIngredientes = maxIngredientes;
        this.maxInstrucciones = maxInstrucciones;
        libroDeRecetas = new LibroDeRecetas(maxRecetasEnLibro);
        planificador = new PlanificadorSemanal();
    }

    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro, String archivoRecetas) {
        this(maxIngredientes, maxInstrucciones, maxRecetasEnLibro);

        // Cargar las recetas predefinidas al iniciar la aplicación
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
        scanner.close();
    }

    private void menuPrincipal(Scanner scanner) {
        System.out.println(
                """
                        --- Menú Principal ---
                        1. Agregar Receta
                        2. Consultar/Editar Receta
                        3. Planificar Comidas
                        4. Guardar Recetas
                        5. Cargar Recetas
                        6. Guardar Plan Semanal
                        7. Salir
                        
                        >> Elige una opción: >>"""


        );

        // Muestra el menú principal y gestiona la entrada del usuario para dirigirlo a la opción seleccionada
    }

    private void agregarReceta(Scanner scanner) {
        // Solicita al usuario los datos de la receta y la añade al libro de recetas
        String nombreReceta = Utilidades.leerCadena(scanner, "Nombre de la receta: ");
        Receta nuevaReceta = new Receta(nombreReceta, maxIngredientes, maxInstrucciones);
        System.out.println("Introduce los ingredientes (una línea por ingrediente, escribe 'fin' para terminar):\n");
        do {

        }

    }

    private void consultarReceta(Scanner scanner) {
        // Busca una receta por su nombre y activa el menú de edición

    }
    private Receta buscarRecetaPorNombre(Scanner scanner) {
        // Solicita al usuario un texto para buscar y seleccionar una receta por su nombre

        //Esta parte simplemente llama al method de LibroDeRecetas, que te las devuelve todas. -E
        System.out.println("Introduce el texto de la receta a buscar (-FIN- para volver): ");
        if (scanner.nextLine().equals("-FIN-")) {
            menuPrincipal(scanner);
        } else {
            String texto = scanner.nextLine();
            recetas = libroDeRecetas.buscarRecetaPorNombre(texto);
            //if (recetas.length == 0 || recetas[0] == null) {
            //    System.out.println("No hay recetas que coincidan con el texto introducido. Por favor inténtelo de nuevo.");
            //}
            //^ A añadir una vez el resto del programa esté listo y se compruebe que no rompe ningún test -E
        }
        //Una vez hecho eso, devolvemos el method que te selecciona una de ellas en concreto. -E
        return seleccionarReceta(scanner, recetas);
        // @todo MODIFICAR PARA DEVOLVER LA RECETA SELECCIONADA
    }

    private void editarReceta(Scanner scanner, Receta seleccionada) {
        // Pantalla de edición de receta
    }

    private Receta seleccionarReceta(Scanner scanner, Receta[] recetas) {
        // Muestra las recetas encontradas y solicita al usuario que elija una
        int cantidadMatches = 1;
        System.out.println("Recetas encontradas:");
        for (int i = 0; i<=recetas.length; i++) {
            if (recetas[i]!= null) {
                System.out.println(cantidadMatches + ". " + recetas[i].getNombre());
                cantidadMatches++;
            }
        }
        System.out.println("Elige una receta: ");
        int seleccion = scanner.nextInt();
        while (seleccion>cantidadMatches) {
            seleccion = scanner.nextInt();
        }
        return recetas[seleccion - 1];
    }

    private void planificarComidas(Scanner scanner) {
        // Inicia el proceso de planificación de comidas
    }

    private void guardarRecetas(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y guarda las recetas en ese archivo
    }

    private void cargarRecetas(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y carga las recetas desde ese archivo
    }

    private void guardarPlanSemanal(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y guarda el plan semanal en ese archivo
    }
}
