public class Cuenta {
    private String numeroCuenta;
    private String nombreCliente;
    private double saldoActual;

    // Constructor para ponerle datos de prueba a nuestra cuenta
    public Cuenta(String numeroCuenta, String nombreCliente, double saldoActual) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.saldoActual = saldoActual;
    }

    public String obtenerDatos() {
        return "Nombre del Cliente: " + nombreCliente + "\nMonto Actual: $" + saldoActual;
    }

    public boolean descontarSaldo(double monto, double comision) {
        double totalADescontar = monto + comision;
        if (saldoActual >= totalADescontar) {
            saldoActual = saldoActual - totalADescontar;
            return true; // Retiro exitoso
        }
        return false; // Fondos insuficientes
    }

    // Getters para usar los datos en el recibo
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
}