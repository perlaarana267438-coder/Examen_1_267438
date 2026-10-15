public class Cuenta {
    private String numeroCuenta;
    private String nombreCliente;
    private double saldoActual;

    //datos de prueba
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
            return true; //retiro like
        }
        return false; // retiro dislike (sin fondos)
    }

    //los datos en el recibo
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
}