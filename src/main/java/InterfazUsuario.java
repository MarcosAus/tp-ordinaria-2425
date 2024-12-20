import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

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
        this.maxIngredientes=maxIngredientes;
        this.maxInstrucciones=maxInstrucciones;
        libroDeRecetas=new LibroDeRecetas(maxRecetasEnLibro);
        try {
            libroDeRecetas.cargarRecetasDeArchivo(archivoRecetas, maxIngredientes, maxInstrucciones);
            System.out.println("Recetas cargadas desde "+archivoRecetas);
        }catch (IOException e) {
            System.out.println("Error al cargar el archivo.");
        }
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
                        
                        >> Elige una opción: >>""");

        try {
            int inputUser = Utilidades.leerNumero(scanner, "", 1, 7);
            switch (inputUser) {
                case 1 -> agregarReceta(scanner);
                case 2 -> consultarReceta(scanner);
                case 3 -> planificarComidas(scanner);
                case 4 -> guardarRecetas(scanner);
                case 5 -> cargarRecetas(scanner);
                case 6 -> guardarPlanSemanal(scanner);
                case 7 -> {
                }
                default -> System.out.println("Has encontrado un error");
            }
        }catch(InputMismatchException ex) {
            System.out.println("Entrada no válida");
        }
        // Muestra el menú principal y gestiona la entrada del usuario para dirigirlo a la opción seleccionada
        }


    private void agregarReceta(Scanner scanner) {
        // Solicita al usuario los datos de la receta y la añade al libro de recetas
        String nombreReceta = Utilidades.leerCadena(scanner,"Nombre de la receta: " );
        nombreReceta = scanner.nextLine();
        /*He asignado a nombreReceta dos veces el scanner porque por alguna razón, con solo el primero el nombre se guarda como ""
         mientras que ahora se guarda como debe ser -M
         */
        Receta receta=new Receta(nombreReceta, maxIngredientes, maxInstrucciones);
        System.out.println("Introduce los ingredientes (una línea por ingrediente, escribe 'fin' para terminar):");
        int ingredienteAgregado=0;
        String linea1=scanner.nextLine();
        do {
            receta.agregarIngrediente(linea1);
            ingredienteAgregado++;
        }while (!(linea1=scanner.nextLine()).equalsIgnoreCase("fin") && ingredienteAgregado<=maxIngredientes);
        if (ingredienteAgregado==maxIngredientes) {
            System.out.println("Ha llegado al número máximo de ingredientes en su receta.");
        }
        System.out.println("Introduce las instrucciones (una línea por instrucción, escribe 'fin' para terminar):");
        int instruccionAgregada=0;
        String linea2= scanner.nextLine();;
        do {
            receta.agregarInstruccion(linea2);
            instruccionAgregada++;
        } while(!(linea2=scanner.nextLine()).equalsIgnoreCase("fin") && instruccionAgregada<=maxInstrucciones);
        if (instruccionAgregada==maxInstrucciones) {
            System.out.println("Ha llegado al número máximo de instrucciones en su receta.");
        }
        if (libroDeRecetas.agregarReceta(receta)) {
            System.out.println("¡Receta agregada exitosamente!");
        } else {
            System.out.println("No se pudo añadir la receta.");
        }
        menuPrincipal(scanner);
    }

    private void consultarReceta(Scanner scanner) {
        // Busca una receta por su nombre y activa el menú de edición
         Receta seleccionada = buscarRecetaPorNombre(scanner);
         if (seleccionada==null) {
             return;
         }
        System.out.println(seleccionada);
        System.out.println();
        editarReceta(scanner, seleccionada);
        menuPrincipal(scanner);
    }
    private Receta buscarRecetaPorNombre(Scanner scanner) {
        // Solicita al usuario un texto para buscar y seleccionar una receta por su nombre

        //Esta parte simplemente llama al method de LibroDeRecetas, que te las devuelve todas. -E
        System.out.println("Introduce el texto de la receta a buscar (-FIN- para volver): ");
        if (scanner.nextLine().equals("-FIN-")) {
            menuPrincipal(scanner);
            return null;
        } else {
            String texto = scanner.nextLine();
            recetas = libroDeRecetas.buscarRecetaPorNombre(texto);
            // hzzay que modificar esta parte para que contemple qué pasa cuando no hay recetas econtradas -M
            if (recetas.length == 0 || recetas[0] == null) {
                System.out.println("No hay recetas que coincidan con el texto introducido. Por favor inténtelo de nuevo.");
                menuPrincipal(scanner);
                return null;
            }
            //^ A añadir una vez el resto del programa esté listo y se compruebe que no rompe ningún test -E
        }
        //Una vez hecho eso, devolvemos el method que te selecciona una de ellas en concreto. -E
        return seleccionarReceta(scanner, recetas);
    }

    private void editarReceta(Scanner scanner, Receta seleccionada) {
        // Pantalla de edición de receta
        int opcion=Utilidades.leerNumero(scanner,"1. Añadir ingrediente\n2. Añadir instrucción\n3. Eliminar receta\n4. Volver\nElige una opción:", 1, 7);
        scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Introduce el ingrediente a añadir: ");
                    String ingredienteAniadido = scanner.nextLine();
                    seleccionada.agregarIngrediente(ingredienteAniadido);
                    break;
                case 2:
                    System.out.print("Introduce la instrucción a añadir: ");
                    String instruccionAniadida = scanner.nextLine();
                    seleccionada.agregarInstruccion(instruccionAniadida);
                    break;
                case 3:
                    libroDeRecetas.eliminarReceta(seleccionada);
                    System.out.println("Receta eliminada.");
                    break;
                case 4:
                    menuPrincipal(scanner);
            }
        }


    private Receta seleccionarReceta(Scanner scanner, Receta[] recetas) {
        // Muestra las recetas encontradas y solicita al usuario que elija una
        int cantidadMatches = 1;
        System.out.println("Recetas encontradas:");
        for (Receta receta : recetas) {
            if (receta != null) {
                System.out.println(cantidadMatches + ". " + receta.getNombre());
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
        Receta receta;
        try {
            System.out.println("Planificación de comidas para la semana:");
            int dia = Utilidades.diaSemanaAPosicion(Utilidades.leerCadena(scanner, "Introduce el día de la semana (L, M, X, J, V, S, D): "));
            String recetaElegida = Utilidades.leerCadena(scanner, "Introduzca la receta que quiere degustar este día.");
            Receta[] recetas = libroDeRecetas.buscarRecetaPorNombre(recetaElegida);
            if (recetas != null) {
                System.out.println("Recetas encontradas:");
                receta = seleccionarReceta(scanner, recetas);
                planificador.agregarComida(dia, receta);
                System.out.println("Receta planificada para " + Utilidades.posicionADiaSemana(dia));
            }
        } catch (InputMismatchException ex) {
            System.out.println("Lo que ha introducido no coincide con los parámetros requeridos. Asegurese de introducir el nombre de una receta la próxima vez.");
        }
    }

    private void guardarRecetas(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y guarda las recetas en ese archivo
        scanner.nextLine();
        String nombreArchivo=Utilidades.leerCadena(scanner, "Introduce el nombre del archivo donde guardar las recetas: ");
        try {
            libroDeRecetas.guardarRecetasEnArchivo(nombreArchivo);
            System.out.println("Recetas guardadas en "+nombreArchivo);
        }catch(IOException e) {
            System.out.println("Error al guardar el archivo.");
        } finally {
            menuPrincipal(scanner);
        }
    }

    private void cargarRecetas(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y carga las recetas desde ese archivo
        scanner.nextLine();
        String nombreArchivo=Utilidades.leerCadena(scanner, "Introduce la ruta del archivo de donde cargar las recetas: )");
        try {
            libroDeRecetas.cargarRecetasDeArchivo(nombreArchivo, maxIngredientes, maxInstrucciones);
            System.out.println("Recetas cargadas de "+nombreArchivo);
        }catch (IOException e) {
            System.out.println("Error al cargar el archivo.");
        }finally {
            menuPrincipal(scanner);
        }
    }

    private void guardarPlanSemanal(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y guarda el plan semanal en ese archivo
    }
}
