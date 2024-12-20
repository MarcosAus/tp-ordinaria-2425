import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * La clase InterfazUsuario tiene cinco atributos privados:
 * - libroDeRecetas (objeto de la clase LibroDeRecetas que contiene las recetas)
 * - planificador (objeto de la clase PlanificadorSemanal)
 * - maxIngredientes (número máximo de ingredientes en la receta)
 * - maxInstrucciones (número máximo de instrucciones en la receta)
 * - recetas (un array de Recetas)
 */
public class InterfazUsuario {
    private LibroDeRecetas libroDeRecetas;
    private PlanificadorSemanal planificador;
    private int maxIngredientes;
    private int maxInstrucciones;
    private Receta[] recetas;

    /**
     * Constructor de InterfazUsuario (si no se introduce el nombre del archivo que va a cargarse)
     * @param maxIngredientes inicializa el número máximo de ingredientes
     * @param maxInstrucciones inicializa el número máximo de instrucciones
     * @param maxRecetasEnLibro inicializa el número máximo de recetas en el libro de recetas
     */
    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro) {
        this.maxIngredientes = maxIngredientes;
        this.maxInstrucciones = maxInstrucciones;
        libroDeRecetas = new LibroDeRecetas(maxRecetasEnLibro);
        planificador = new PlanificadorSemanal();
    }

    /**
     * Constructor de InterfazUsuario (si se introduce el nombre del archivo que va a cargarse)
     * @param maxIngredientes inicializa el número máximo de ingredientes
     * @param maxInstrucciones inicializa el número máximo de instrucciones
     * @param maxRecetasEnLibro inicializa el número máximo de recetas en el libro de recetas
     * @param archivoRecetas cargra el archivo de dicho nombre
     */
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

    /**
     * Función que inicia la interfaz del usuario
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
        scanner.close();
    }

    /**
     * Función que muestra el menú principal de opciones que tiene el usuario
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     */
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
                case 7 -> {}
                default -> System.out.println("Has encontrado un error");
            }
        }catch(InputMismatchException ex) {
            System.out.println("Entrada no válida");
        }
    }


    /**
     * Función que agrega una receta al lirbo de recetas
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     */
    private void agregarReceta(Scanner scanner) {
        // Solicita al usuario los datos de la receta y la añade al libro de recetas
        String nombreReceta = Utilidades.leerCadena(scanner,"Nombre de la receta: " );
        nombreReceta = scanner.nextLine(); //NOTA: en varias líneas del código, se hacen dos veces el scanner necesario, ya que sin hacerlo así el scanner no consigue leer la entrada del usuario -Marcos
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

    /**
     * Función busca una receta por su nombre y activa el menú de edición
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     */
    private void consultarReceta(Scanner scanner) {
         Receta seleccionada = buscarRecetaPorNombre(scanner);
         if (seleccionada==null) {
             return;
         }
        System.out.println(seleccionada);
        System.out.println();
        editarReceta(scanner, seleccionada);
        menuPrincipal(scanner);
    }

    /**
     * Solicita al usuario un texto para buscar y seleccionar una receta por su nombre
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     * @return devuelve la receta seleccionada tras buscarla por su nombre
     */
    private Receta buscarRecetaPorNombre(Scanner scanner) {
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

    /**
     * Función que permite agregar ingredientes e instrucciones a la receta, o eliminarla por completo
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     * @param seleccionada receta que ha sido seleccionada para ser editada
     */
    private void editarReceta(Scanner scanner, Receta seleccionada) {
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

    /**
     * Función que permite seleccionar una receta tras escribir el número de la receta deseada
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     * @param recetas array de recetas
     * @return devuelve la receta seleccionada
     */
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

    /**
     * Función que planifica las comidas en una semana
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     */
    private void planificarComidas(Scanner scanner) {
        Receta receta;
        try {
            System.out.println("Planificación de comidas para la semana:");
            int dia = Utilidades.diaSemanaAPosicion(Utilidades.leerCadena(scanner, "Introduce el día de la semana (L, M, X, J, V, S, D): "));
            dia = Utilidades.diaSemanaAPosicion(Utilidades.leerCadena(scanner, "Introduce el día de la semana (L, M, X, J, V, S, D): "));
            String recetaElegida = Utilidades.leerCadena(scanner, "Introduzca la receta que quiere degustar este día.");
            Receta[] recetas = libroDeRecetas.buscarRecetaPorNombre(recetaElegida);
            if (recetas != null) {
                receta = seleccionarReceta(scanner, recetas);
                planificador.agregarComida(dia, receta);
                System.out.println("Receta planificada para "+Utilidades.posicionADiaSemana(dia));
            }
        } catch (InputMismatchException ex) {
            System.out.println("Lo que ha introducido no coincide con los parámetros requeridos. Asegurese de introducir el nombre de una receta la próxima vez.");
        }
    }

    /**
     * Función que permite guardar las recetas en un archivo de texto
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     */
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

    /**
     * Función que permite cargar las recetas guardadas previamente en un archivo de texto
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     */
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

    /**
     * Función que permite guardar el plan semanal creado en un archivo de texto
     * @param scanner objeto de la clase Scanner que va a leer las etradas del usuario por teclado
     */
    private void guardarPlanSemanal(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y guarda el plan semanal en ese archivo
        scanner.nextLine();
        String nombreArchivo=Utilidades.leerCadena(scanner, "Introduce el nombre del archivo donde guardar el plan semanal: ");
        try (PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo))) {
            salida.print(planificador.toString());
            System.out.print("Plan semanal guardado en " + nombreArchivo);
        } catch (IOException ex) {
            System.out.println("Error al guardar el archivo.");
        }
    }
}
