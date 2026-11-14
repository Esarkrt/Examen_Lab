
package P_Incio_Registro;

public class ValidadorNombre {

    public static String recortar(String nombre) {
        if (nombre == null) {
            return "";
        }
        return nombre.trim();
    }

    public static String validar(String nombre) {
        String limpio = recortar(nombre);

        if (limpio.isEmpty()) {
            return "Debes escribir el nombre del entrenador.";
        }

        if (limpio.length() < 3) {
            return "El nombre debe tener al menos 3 caracteres.";
        }

        if (limpio.length() > 10) {
            return "El nombre no puede tener mas de 10 caracteres...";
        }

        for (int i = 0; i < limpio.length(); i++) {
            char c = limpio.charAt(i);
            boolean esLetra = Character.isLetter(c);
            boolean esNumero = Character.isDigit(c);
            boolean esEspacio = (c == ' ');

            if (!esLetra && !esNumero && !esEspacio) {
                return "El nombre solo puede tener letras, numeros y espacios...";
            }
        }

        return null;
    }
}
