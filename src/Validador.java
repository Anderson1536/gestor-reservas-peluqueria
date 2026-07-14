public class Validador {

    public static boolean horaValida(int hora) {

        if (hora >= 8 && hora <= 17) {
            return true;
        }

        return false;
    }

    public static boolean nombreValido(String nombre) {

        if (nombre == null) {
            return false;
        } else if (nombre.isEmpty() || nombre.isBlank()) {
            return false;
        }

        return true;
    }

    public static boolean servicioValido(int servicio) {

        if (servicio == 1 || servicio == 2 || servicio == 3) {
            return true;
        }

        return false;
    }

    public static boolean opcionNumericaValida(String opcion) {
        for (int i = 0; i < opcion.length(); i++) {
            char dato = opcion.charAt(i);
            if (!Character.isDigit(dato)) {
                return false;
            }
        }
        return true;
    }

    public static boolean hayReservas(int contador){
        if (contador == 0) {
            return false;
        }

        return true;
    }
}
