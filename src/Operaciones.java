import java.util.Scanner;

public class Operaciones {

    public static final int cupoMaximo = 10;
    public static int contadorReservas = 0;

    public static String[] nombreClientes = new String[10];
    public static int[] horas = new int[10];
    public static int[] servicios = new int[10];

    public static void agendar(Scanner scanner) {

        if (contadorReservas > cupoMaximo) {
            System.out.println("No hay cupos disponibles. No es posible realizar el agendamiento.");
            return;
        }

        System.out.println("\nAGENDAMIENTO:");

        boolean nombreValido = true;
        do {
            System.out.print("Digite el nombre: ");
            String nombre = scanner.nextLine();
            nombreValido = Validador.nombreValido(nombre);

            if (nombreValido == false) {
                System.out.println("El nombre ingresado no es valido. Por favor intente de nuevo.");
            }

            nombreClientes[contadorReservas] = nombre.trim().toLowerCase();

        } while (!nombreValido);

        boolean horaValida = true;
        String opcion;
        do {
            System.out.print("Digite la hora en punto en forma de numero entre las 8 y 17: ");
            opcion = scanner.nextLine();
            boolean opcionValida = Validador.opcionNumericaValida(opcion);

            int hora = 0;
            if (!opcionValida) {
                horaValida = false;
            } else {
                hora = Integer.parseInt(opcion);
            }

            horaValida = Validador.horaValida(hora);

            if (!horaValida) {
                System.out.println("La hora ingresada no es valida.");
                horaValida = false;
            } else if (horaValida) {
                for (int i = 0; i <= contadorReservas; i++) {
                    if (horas[i] == hora) {
                        System.out.println(
                                "\nLa hora ingresada ya se encuentra agendada por otra persona. Por favor elija otra.\n");
                        horaValida = false;
                        break;
                    }
                }
            }

            horas[contadorReservas] = hora;

        } while (!horaValida);

        boolean servicioValido = true;
        do {
            System.out.println("\nServicios:");
            System.out.println("""
                    1. Corte de cabello - $25.000
                    2. Tinte            - $60.000
                    3. Manicure         - $30.000
                    """);

            System.out.print("Elija el servicio que desea: ");
            String opcionServicio = scanner.nextLine();

            boolean opcionValida = Validador.opcionNumericaValida(opcionServicio);

            int servicio = -1;
            if (!opcionValida) {
                servicioValido = false;
            } else {
                servicio = Integer.parseInt(opcionServicio);
            }

            servicioValido = Validador.servicioValido(servicio);

            if (servicioValido == false) {
                System.out.println("Opción invalida. Por favor intente de nuevo.");
            }

            servicios[contadorReservas] = servicio;

        } while (!servicioValido);

        System.out.println("Reserva agendada con exito.");
        contadorReservas++;
    }

    public static void listarRerservas() {

        boolean hayReservas = Validador.hayReservas(contadorReservas);
        if (!hayReservas) {
            System.out.println("Aun no hay reservas.");
            return;
        }

        System.out.println("\nLISTADO DE RESERVAS: ");
        for (int i = 0; i < contadorReservas; i++) {
            int servicio = servicios[i];
            System.out.print((i + 1) + ". " + nombreClientes[i] + " - Hora: " + horas[i] + " - Servicio: ");
            mostrarServicios(servicio);
        }
    }

    public static void mostrarServicios(int servicio) {
        switch (servicio) {
            case 1:
                System.out.print("Corte de cabello\n");
                break;
            case 2:
                System.out.print("Tinte\n");
                break;
            case 3:
                System.out.print("Manicure\n");
                break;
            default:
                break;
        }
    }

    public static void cancelarReserva(Scanner scanner) {

        boolean hayReservas = Validador.hayReservas(contadorReservas);
        if (!hayReservas) {
            System.out.println("Aun no hay reservas.");
            return;
        }

        String opcionReserva;
        boolean reservaValida = true;
        int reserva = 0;

        do {
            listarRerservas();
            System.out.print("Elija la reserva que desea cancelar: ");
            opcionReserva = scanner.nextLine();
            boolean opcionValida = Validador.opcionNumericaValida(opcionReserva);

            if (!opcionValida) {
                System.out.println("\nOpcion invalida. Intente de nuevo.");
                reservaValida = false;
            } else {
                reserva = Integer.parseInt(opcionReserva);

                if (reserva < 1 || reserva > contadorReservas) {
                    System.out.println("\nOpcion invalida. Intente de nuevo.");
                    reservaValida = false;
                } else {
                    reservaValida = true;
                }
            }

        } while (!reservaValida);

        for (int i = 0; i <= contadorReservas; i++) {
            if (i == reserva - 1) {
                nombreClientes[i] = nombreClientes[i + 1];
                horas[i] = horas[i + 1];
                servicios[i] = servicios[i + 1];

            }
        }
        System.out.println("\nLa reserva ha sido cancelada.\n");
        contadorReservas--;
    }

    public static void reporte() {

        boolean hayReservas = Validador.hayReservas(contadorReservas);
        if (!hayReservas) {
            System.out.println("Aun no hay reservas.");
            return;
        }

        double acumulador = 0d;

        for (int i = 0; i < contadorReservas; i++) {
            if (servicios[i] == 1) {
                acumulador += 25000;
            } else if (servicios[i] == 2) {
                acumulador += 60000;
            } else {
                acumulador += 30000;
            }
        }

        System.out.println("\nTotal citas: " + contadorReservas);
        System.out.println("Dinero facturado: $" + acumulador);
    }

    public static void buscarPorCliente(Scanner scanner) {

        boolean hayReservas = Validador.hayReservas(contadorReservas);
        if (!hayReservas) {
            System.out.println("Aun no hay reservas.");
            return;
        }
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine().toLowerCase();

        System.out.println("Lista de citas del cliente " + nombre + ": \n");
        for (int i = 0; i < contadorReservas; i++) {
            if (nombreClientes[i].equals(nombre)) {
                System.out.print((i + 1) + ". " + nombreClientes[i] + " - Hora: " + horas[i] + " - Servicio: ");
                mostrarServicios(servicios[i]);
            }
        }
    }

    public static void editarReserva(Scanner scanner) {

        boolean hayReservas = Validador.hayReservas(contadorReservas);
        if (!hayReservas) {
            System.out.println("Aun no hay reservas.");
            return;
        }

        boolean opcionValida = true;
        int reserva = 0;
        do {
            listarRerservas();
            System.out.print("\nElija el numero de reserva que desea cambiar de hora: ");
            String opcionReserva = scanner.nextLine();
            opcionValida = Validador.opcionNumericaValida(opcionReserva);

            if (!opcionValida) {
                System.out.println("\nOpcion invalida. Intente de nuevo.");
            } else {
                reserva = Integer.parseInt(opcionReserva);

                if (reserva < 1 || reserva > contadorReservas) {
                    System.out.println("\nOpcion invalida. Intente de nuevo.");
                    opcionValida = false;
                } else {
                    opcionValida = true;
                }
            }
        } while (!opcionValida);

        int hora = 0;
        boolean horaValida = false;
        do {
            System.out.print("Ingrese la nueva hora de la cita: ");
            String horaEntrada = scanner.nextLine();
            boolean entradaValida = Validador.opcionNumericaValida(horaEntrada);

            if (!entradaValida) {
                System.out.println("La hora ingresada no es valida. Intente de nuevo.\n");
                horaValida = false;
            } else {
                hora = Integer.parseInt(horaEntrada);

                horaValida = Validador.horaValida(hora);
                if (!horaValida) {
                    System.out.println("La hora ingresada no es valida. Intente de nuevo.\n");
                    horaValida = false;
                } else {
                    for (int i = 0; i < contadorReservas; i++) {
                        if (horas[i] == hora) {
                            System.out.println("La hora ingresada ya se encuentra agendada. Por favor elija otra.\n");
                            horaValida = false;
                            break;
                        }
                    }
                }
            }

        } while (!horaValida);

        horas[reserva - 1] = hora;
        System.out.println("La hora ha sido actualizada correctamente:");
        System.out.print(nombreClientes[reserva - 1] + " - Hora: " + horas[reserva - 1] + " - Servicio: ");
        mostrarServicios(servicios[reserva - 1]);
    }

    public static void horasDisponibles() {

        boolean hayReservas = Validador.hayReservas(contadorReservas);
        if (!hayReservas) {
            System.out.println("Aun no hay reservas.");
            return;
        }

        System.out.println("\nHoras disponibles: ");

        int[] horasDisponibles = new int[10];

        for (int i = 0; i < cupoMaximo; i++) {
            int contador = 0;
            for (int j = 8; j < 18; j++) {
                if (horas[i] == j) {
                    horasDisponibles[contador] = -1;
                    break;
                } else if (horasDisponibles[contador] != -1) {
                    horasDisponibles[contador] = j;
                    contador++;
                } else {
                    contador++;
                }
            }
        }

        for (int i = 0; i < horasDisponibles.length; i++) {
            if (horasDisponibles[i] != -1) {
                System.out.println("Hora: " + horasDisponibles[i]);
            }
        }
    }

    public static void servicioMasPedido() {

        boolean hayReservas = Validador.hayReservas(contadorReservas);
        if (!hayReservas) {
            System.out.println("Aun no hay reservas.");
            return;
        }

        int contadorCorte = 0;
        int contadorTinte = 0;
        int contadorManicure = 0;

        for (int i = 0; i < contadorReservas; i++) {
            if (servicios[i] == 1) {
                contadorCorte++;
            }

            if (servicios[i] == 2) {
                contadorTinte++;
            }

            if (servicios[i] == 3) {
                contadorManicure++;
            }
        }

        System.out.print("Servicio mas agendado: ");
        if (contadorCorte > contadorTinte && contadorCorte > contadorManicure) {
            mostrarServicios(1);
        } else if (contadorTinte > contadorCorte && contadorTinte > contadorManicure) {
            mostrarServicios(2);
        } else if (contadorManicure > contadorCorte && contadorManicure > contadorTinte) {
            mostrarServicios(3);
        } else {
            System.out.println("No hubo un servicio agendado mas veces que los otros.");
        }

    }

}
