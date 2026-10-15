import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class VistaRetiro extends JFrame {
    private ControladorRetiro controlador;
    private double montoTemporal;

    //paneles
    private JPanel panelDerecho;
    private JLabel lblTituloPrincipal;
    private JButton[] botonesMonto;

    //colores
    private final Color BLANCO = Color.WHITE;
    private final Color NEGRO = new Color(30, 30, 30);
    private final Color GRIS_CLARO = new Color(248, 248, 248);
    private final Color GRIS_BORDE = new Color(230, 230, 230);
    private final Font FUENTE_TITULO = new Font("SansSerif", Font.BOLD, 22);
    private final Font FUENTE_NORMAL = new Font("SansSerif", Font.PLAIN, 14);
    private final Font FUENTE_BOTON = new Font("SansSerif", Font.BOLD, 14);

    public VistaRetiro() {
        //Ventana (con formato horizontal)
        setTitle("Cajero Automático");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BLANCO);

        //header
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(BLANCO);
        panelHeader.setBorder(new EmptyBorder(20, 40, 20, 40));

        lblTituloPrincipal = new JLabel("Selección de Monto");
        lblTituloPrincipal.setFont(FUENTE_TITULO);

        JLabel lblSubtitulo = new JLabel("Cajero automático - Retiro de efectivo");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);

        panelHeader.add(lblTituloPrincipal, BorderLayout.WEST);
        panelHeader.add(lblSubtitulo, BorderLayout.EAST);
        add(panelHeader, BorderLayout.NORTH);

        //contenedor 1
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 40, 0));
        panelCentral.setBackground(BLANCO);
        panelCentral.setBorder(new EmptyBorder(0, 40, 20, 40));

        //columna izq (montos)
        JPanel panelIzquierdo = crearPanelMontos();
        panelCentral.add(panelIzquierdo);

        //columna der (esta es la dinamica)
        panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(BLANCO);
        panelCentral.add(panelDerecho);

        add(panelCentral, BorderLayout.CENTER);

        //el footer
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
        panelFooter.setBackground(BLANCO);
        panelFooter.setBorder(new EmptyBorder(10, 40, 20, 40));
        JLabel lblProgreso = new JLabel("01 Monto      02 Estado de Cuenta      03 Confirmación      04 Recibo");
        lblProgreso.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblProgreso.setForeground(Color.LIGHT_GRAY);
        panelFooter.add(lblProgreso);
        add(panelFooter, BorderLayout.SOUTH);
    }

    public void setControlador(ControladorRetiro controlador) {
        this.controlador = controlador;
    }

    private JPanel crearPanelMontos() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO);

        JLabel lblMonto = new JLabel("Monto a retirar");
        lblMonto.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblMonto.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblMonto);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblSub = new JLabel("Seleccione una cantidad");
        lblSub.setFont(FUENTE_NORMAL);
        lblSub.setForeground(Color.GRAY);
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblSub);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] montos = {"200.00", "400.00", "600.00", "800.00", "1000.00"};
        botonesMonto = new JButton[5];

        for (int i = 0; i < montos.length; i++) {
            String valorMonto = montos[i];
            JButton btn = new JButton("$" + valorMonto);
            btn.setFont(FUENTE_BOTON);
            btn.setBackground(BLANCO);
            btn.setForeground(NEGRO);
            btn.setFocusPainted(false);
            btn.setBorder(new LineBorder(GRIS_BORDE, 1));
            btn.setMaximumSize(new Dimension(300, 40));
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            int finalI = i;
            btn.addActionListener(e -> {
                resetearBotones();
                btn.setBackground(NEGRO);
                btn.setForeground(BLANCO);
                montoTemporal = Double.parseDouble(valorMonto);
                controlador.solicitarEstadoCuenta(montoTemporal);
            });

            botonesMonto[i] = btn;
            panel.add(btn);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        return panel;
    }

    private void resetearBotones() {
        for (JButton btn : botonesMonto) {
            btn.setBackground(BLANCO);
            btn.setForeground(NEGRO);
        }
    }

    //Pantallas
    //de inicio
    public void mostrarMontos() {
        lblTituloPrincipal.setText("Selección de Monto");
        panelDerecho.removeAll();

        //esto es solo relleno honestly
        JLabel lblLogoPlaceholder = new JLabel("BANCO", SwingConstants.CENTER);
        lblLogoPlaceholder.setForeground(Color.LIGHT_GRAY);
        lblLogoPlaceholder.setFont(new Font("SansSerif", Font.BOLD, 20));
        panelDerecho.add(lblLogoPlaceholder, BorderLayout.CENTER);

        actualizarPantalla();
        setVisible(true);
    }

    //estado de cuenta y confirmación
    public void actualizarEstadoCuenta(String datos) {
        lblTituloPrincipal.setText("Confirmación de Retiro");
        panelDerecho.removeAll();

        //estado de cuenta
        JPanel tarjetaGris = new JPanel(new BorderLayout());
        tarjetaGris.setBackground(GRIS_CLARO);
        tarjetaGris.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblEstadoTitulo = new JLabel("Estado de Cuenta");
        lblEstadoTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        tarjetaGris.add(lblEstadoTitulo, BorderLayout.NORTH);

        JTextArea txtDatos = new JTextArea("\n" + datos + "\n\nCantidad a Retirar: $" + montoTemporal);
        txtDatos.setEditable(false);
        txtDatos.setBackground(GRIS_CLARO);
        txtDatos.setFont(FUENTE_NORMAL);
        tarjetaGris.add(txtDatos, BorderLayout.CENTER);

        //retirar
        JButton btnRetirar = new JButton("Retirar Efectivo");
        btnRetirar.setFont(FUENTE_BOTON);
        btnRetirar.setBackground(NEGRO);
        btnRetirar.setForeground(BLANCO);
        btnRetirar.setFocusPainted(false);
        btnRetirar.setPreferredSize(new Dimension(200, 50));
        btnRetirar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRetirar.addActionListener(e -> controlador.confirmarRetiro(montoTemporal));

        JPanel panelBoton = new JPanel(new BorderLayout());
        panelBoton.setBackground(BLANCO);
        panelBoton.setBorder(new EmptyBorder(20, 0, 0, 0));
        panelBoton.add(btnRetirar, BorderLayout.CENTER);

        panelDerecho.add(tarjetaGris, BorderLayout.CENTER);
        panelDerecho.add(panelBoton, BorderLayout.SOUTH);

        actualizarPantalla();
    }

    //el RECIBOO
    public void mostrarPantallaRecibo(String datosRecibo) {
        lblTituloPrincipal.setText("Generación de Recibo");
        panelDerecho.removeAll();

        //desactiva los botones
        for(JButton btn : botonesMonto) { btn.setEnabled(false); }

        //target de recibo
        JPanel tarjetaRecibo = new JPanel(new BorderLayout());
        tarjetaRecibo.setBackground(BLANCO);
        tarjetaRecibo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(GRIS_BORDE, 1),
                new EmptyBorder(30, 30, 30, 30)
        ));

        JLabel lblReciboTitulo = new JLabel("Recibo de retiro", SwingConstants.CENTER);
        lblReciboTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        tarjetaRecibo.add(lblReciboTitulo, BorderLayout.NORTH);

        JTextArea txtRecibo = new JTextArea("\n" + datosRecibo);
        txtRecibo.setEditable(false);
        txtRecibo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtRecibo.setBackground(BLANCO);
        tarjetaRecibo.add(txtRecibo, BorderLayout.CENTER);

        JLabel lblGracias = new JLabel("Gracias por utilizar nuestros servicios", SwingConstants.CENTER);
        lblGracias.setFont(new Font("SansSerif", Font.ITALIC, 12));
        lblGracias.setForeground(Color.GRAY);
        tarjetaRecibo.add(lblGracias, BorderLayout.SOUTH);

        JPanel contenedorRecibo = new JPanel(new GridBagLayout());
        contenedorRecibo.setBackground(BLANCO);
        contenedorRecibo.add(tarjetaRecibo);

        panelDerecho.add(contenedorRecibo, BorderLayout.CENTER);

        actualizarPantalla();
    }

    private void actualizarPantalla() {
        panelDerecho.revalidate();
        panelDerecho.repaint();
    }
}