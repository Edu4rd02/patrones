import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        InsertValuesInJSON manejadorJSON = new InsertValuesInJSON();
        Persona p;
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n-------------- Menú --------------");
            System.out.println("| 1. Insertar usuario y mensaje  |");
            System.out.println("| 2. Editar mensaje de usuario   |");
            System.out.println("| 3. Mostrar usuarios y mensajes |");
            System.out.println("| 0. Salir                       |");
            System.out.println("----------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese ID del usuario: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese mensaje: ");
                    String mensaje = scanner.nextLine();
                    manejadorJSON.agregarUsuario(id, nombre, mensaje);
                    break;

                case 2:
                    System.out.print("\nIngrese ID del usuario a editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo mensaje: ");
                    String nuevoMensaje = scanner.nextLine();
                    
                    p = new Persona(idEditar, nuevoMensaje);

                    p.notificarObservador();
                    break;

                case 3:
                    manejadorJSON.imprimir();
                    break;

                case 0:
                    System.out.println("\nSaliendo del programa...");
                    break;

                default:
                    System.out.println("\nOpción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}