import javax.swing.JOptionPane;

public class ControladorRetiro {
    private VistaRetiro vista;
    private Cuenta cuentaActual;

    //Esto conecta el controlador con la view y la cuentam
    public ControladorRetiro(VistaRetiro vista, Cuenta cuentaActual) {
        this.vista = vista;
        this.cuentaActual = cuentaActual;
    }

    public void iniciarRetiro() {
        vista.mostrarMontos(); //aki se arranca el CU
    }

    public double calcularComision(double monto) {
        return 17.0; //la comisión que me cobran en el oxxo
    }

    //obtener datos cuenta
    public void solicitarEstadoCuenta(double monto) {
        String datosCuenta = cuentaActual.obtenerDatos();
        double comision = calcularComision(monto);

        String mensaje = datosCuenta + "\nComisión por retiro: $" + comision;

        vista.actualizarEstadoCuenta(mensaje); //y este los manda a la view para mostrar los datos
    }

    public void confirmarRetiro(double monto) {
        double comision = calcularComision(monto);

        boolean exito = cuentaActual.descontarSaldo(monto, comision); //intenta descontar saldo

        if (exito) {
            String numeroCompleto = cuentaActual.getNumeroCuenta(); //se obtienen los digitos de la cuenta
            String terminacion = numeroCompleto.substring(numeroCompleto.length() - 4);

            Recibo nuevoRecibo = new Recibo(monto, comision, terminacion);
            String textoRecibo = nuevoRecibo.generarTextoRecibo(); //se genera el recibo

            vista.mostrarPantallaRecibo(textoRecibo); //y ya se muestra
        } else {
            //por si no hay dinero suficiente
            JOptionPane.showMessageDialog(null, "Error: fondos insuficientes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}