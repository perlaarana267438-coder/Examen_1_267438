public class Main {
    public static void main(String[] args) {
        // 1. Crear el Modelo (Inventamos una cuenta de prueba con $5,000 pesos)
        Cuenta miCuenta = new Cuenta("1234567890123456", "Estudiante Arquitectura", 5000.0);

        // 2. Crear la Vista (La interfaz)
        VistaRetiro vista = new VistaRetiro();

        // 3. Crear el Controlador y conectarlo con la vista y la cuenta
        ControladorRetiro controlador = new ControladorRetiro(vista, miCuenta);

        // 4. Conectar la vista de regreso con el controlador para que se puedan comunicar
        vista.setControlador(controlador);

        // 5. ¡Darle play al caso de uso!
        controlador.iniciarRetiro();
    }
}