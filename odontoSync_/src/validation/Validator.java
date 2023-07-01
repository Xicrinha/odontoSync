package validation;

public class Validator {
    public static boolean validarCampoVazio(String texto) {
        return !texto.trim().isEmpty();
    }

    public static boolean validarData(String data) {
        if (data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            String[] partes = data.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);

            if (mes >= 1 && mes <= 12) {
                int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
                    diasPorMes[1] = 29;
                }

                return dia >= 1 && dia <= diasPorMes[mes - 1];
            }
        }

        return false;
    }
    public static boolean validarHorario(String horario) {
        if (horario.matches("\\d{2}:\\d{2}")) {
            String[] partes = horario.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);

            return horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59;
        }

        return false;
    }
}
