public class Main {
    public static void main(String[] args) {
        //cuenta fake
        Cuenta miCuenta = new Cuenta("4564564564564564", "Ramon René Palacios", 900.0);

        VistaRetiro vista = new VistaRetiro();

        //el controlador que conecta con la vista y la cuenta
        ControladorRetiro controlador = new ControladorRetiro(vista, miCuenta);

        //reconecta la view con el controller y se comunican
        vista.setControlador(controlador);

        controlador.iniciarRetiro();
    }
}