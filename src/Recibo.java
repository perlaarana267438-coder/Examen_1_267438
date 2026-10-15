public class Recibo {
    private double montoRetirado;
    private double cobroComision;
    private String terminacionCuenta;

    //solo llena el recibo con los datos
    public Recibo(double montoRetirado, double cobroComision, String terminacionCuenta) {
        this.montoRetirado = montoRetirado;
        this.cobroComision = cobroComision;
        this.terminacionCuenta = terminacionCuenta;
    }

    public String generarTextoRecibo() {
        return "=== RECIBO DE RETIRO ===\n" +
                "Cuenta terminación: " + terminacionCuenta + "\n" +
                "Monto retirado: $" + montoRetirado + "\n" +
                "Comisión cobrada: $" + cobroComision + "\n" +
                "TOTAL DESCONTADO: $" + (montoRetirado + cobroComision) + "\n" +
                "========================";
    }
}