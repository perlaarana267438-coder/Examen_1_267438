import javax.swing.JOptionPane;

public class ControladorRetiro {
    private VistaRetiro vista;
    private Cuenta cuentaActual;

    // Constructor para conectar el controlador con la vista y la cuenta
    public ControladorRetiro(VistaRetiro vista, Cuenta cuentaActual) {
        this.vista = vista;
        this.cuentaActual = cuentaActual;
    }

    public void iniciarRetiro() {
        // Arranca el caso de uso
        vista.mostrarMontos();
    }

    public double calcularComision(double monto) {
        // Supongamos que el banco cobra $15 pesos de comisión por cualquier retiro
        return 15.0;
    }

    public void solicitarEstadoCuenta(double monto) {
        // Paso 3 y 4 de tu diagrama: Obtener datos de la cuenta
        String datosCuenta = cuentaActual.obtenerDatos();
        double comision = calcularComision(monto);

        String mensaje = datosCuenta + "\nComisión por retiro: $" + comision;

        // Paso 5: Mandar los datos a la vista para que los muestre
        vista.actualizarEstadoCuenta(mensaje);
    }

    public void confirmarRetiro(double monto) {
        double comision = calcularComision(monto);

        // Paso 8 y 9: Intentar descontar el saldo
        boolean exito = cuentaActual.descontarSaldo(monto, comision);

        if (exito) {
            // Obtenemos los últimos 4 dígitos de la cuenta para el recibo
            String numeroCompleto = cuentaActual.getNumeroCuenta();
            String terminacion = numeroCompleto.substring(numeroCompleto.length() - 4);

            // Paso 10 y 11: Generar recibo
            Recibo nuevoRecibo = new Recibo(monto, comision, terminacion);
            String textoRecibo = nuevoRecibo.generarTextoRecibo();

            // Paso 12: Mandar a mostrar el recibo en pantalla
            vista.mostrarPantallaRecibo(textoRecibo);
        } else {
            // Si no hay dinero suficiente
            JOptionPane.showMessageDialog(null, "Error: Fondos insuficientes para realizar el retiro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}