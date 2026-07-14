import java.util.Scanner;

public class Menu {

    public static void menu() {
        System.out.println();
        System.out.println("""
                =============================
                | MENU PRINCIPAL            |
                =============================
                1. Agendar una reserva
                2. Listar todas las reservas
                3. Cancelar una reserva
                4. Ver el reporte del día
                5. Buscar por cliente
                6. Editar reserva (hora)
                7. Horas disponibles
                8. Servicio que mas se agendó
                0. Salir
                """);
    }

    public static int leerOpcion(Scanner scanner) {
        menu();
        System.out.print("Ingrese un numero de opción de la lista: ");
        String opcion = scanner.nextLine();
        boolean opcionValida = Validador.opcionNumericaValida(opcion);

        int numero = -1;
        if (opcionValida) {
            numero = Integer.parseInt(opcion);
        }

        return numero;
    }
}
