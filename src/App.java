import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int opcion = -1;

        while (opcion != 0) {

            opcion = Menu.leerOpcion(scanner);

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                case 1:
                    Operaciones.agendar(scanner);
                    break;
                case 2:
                    Operaciones.listarRerservas();
                    break;
                case 3:
                    Operaciones.cancelarReserva(scanner);
                    break;
                case 4:
                    Operaciones.reporte();
                    break;
                case 5:
                    Operaciones.buscarPorCliente(scanner);
                    break;
                case 6:
                    Operaciones.editarReserva(scanner);
                    break;
                case 7:
                    Operaciones.horasDisponibles();
                    break;
                case 8:
                    Operaciones.servicioMasPedido();
                    break;
                default:
                    System.out.println("\nOpcion invalida. Intente de nuevo.\n");
                    break;
            }
        }
    }
}
