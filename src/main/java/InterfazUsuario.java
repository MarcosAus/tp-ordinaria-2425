import java.util.Scanner;

public class InterfazUsuario {
    private LibroDeRecetas libroDeRecetas;
    private PlanificadorSemanal planificador;
    private int maxIngredientes;
    private int maxInstrucciones;
    private Receta[] recetas;

    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro) {
        // Inicialización de la herramienta de recetas
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
                //se podría escribir de manera más sucinta, pero verlo limpio es más cómodo, y optimizar un print tampoco es particularmente útil -E
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
    }

    private void consultarReceta(Scanner scanner) {
        // Busca una receta por su nombre y activa el menú de edición

    }
    private Receta buscarRecetaPorNombre(Scanner scanner) {
        // Solicita al usuario un texto para buscar y seleccionar una receta por su nombre

        //Esta parte simplemente llama al method de LibroDeRecetas, que te las devuelve todas. -E
        System.out.println("Introduce el texto de la receta a buscar (-FIN- para volver): ");
        Scanner texto = new Scanner(System.in);
        if (scanner.nextLine().equals("-FIN-")) {
            menuPrincipal(scanner);
        } else {
            recetas = new Receta[]{libroDeRecetas.buscarRecetaPorNombre(string.valueOf.scanner.nextLine())};
        }
        //Una vez hecho eso, llamamos al method que te selecciona una de ellas en concreto. -E
        seleccionarReceta(scanner, recetas);

        return null; // @todo MODIFICAR PARA DEVOLVER LA RECETA SELECCIONADA
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
        if (seleccion <= cantidadMatches) {
            return recetas[seleccion-1];
        }



        return null; // @todo MODIFICAR PARA DEVOLVER LA RECETA SELECCIONADA
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
