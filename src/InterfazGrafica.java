import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazGrafica extends JFrame {
    private JTextField txtNombre;
    private JTextField txtNumeroCedula;
    private JTextArea areaResultados;
    private GestorContactos gestor;

    public InterfazGrafica() {
        gestor = new GestorContactos();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gestor de Contactos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(3, 2, 5, 5));
        txtNombre = new JTextField();
        txtNumeroCedula = new JTextField();

        panelEntrada.add(new JLabel("Nombre:"));
        panelEntrada.add(txtNombre);
        panelEntrada.add(new JLabel("Número de Cédula (9-10 dígitos):"));
        panelEntrada.add(txtNumeroCedula);
        panelEntrada.add(new JLabel("")); // Espacio en blanco para alineación
        panelEntrada.add(new JLabel("La cédula debe tener 9 o 10 dígitos"));

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(crearBoton("Agregar", e -> agregarContacto()));
        panelBotones.add(crearBoton("Buscar", e -> buscarContacto()));
        panelBotones.add(crearBoton("Actualizar", e -> actualizarContacto()));
        panelBotones.add(crearBoton("Eliminar", e -> eliminarContacto()));

        // Área de resultados
        areaResultados = new JTextArea(10, 30);
        areaResultados.setEditable(false);

        add(panelEntrada, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(new JScrollPane(areaResultados), BorderLayout.SOUTH);

        pack(); // Ajusta el tamaño de la ventana basándose en los componentes
    }

    private JButton crearBoton(String texto, ActionListener listener) {
        JButton boton = new JButton(texto);
        boton.addActionListener(listener);
        return boton;
    }

    private void agregarContacto() {
        try {
            gestor.agregarContacto(txtNombre.getText(), txtNumeroCedula.getText());
            areaResultados.setText("Contacto agregado: " + txtNombre.getText());
            txtNombre.setText("");
            txtNumeroCedula.setText("");
        } catch (Exception e) {
            areaResultados.setText("Error al agregar contacto: " + e.getMessage());
        }
    }

    private void buscarContacto() {
        Contacto contacto = gestor.buscarContacto(txtNombre.getText());
        if (contacto != null) {
            areaResultados.setText("Contacto encontrado: " + contacto);
        } else {
            areaResultados.setText("Contacto no encontrado.");
        }
    }

    private void actualizarContacto() {
        try {
            if (gestor.actualizarContacto(txtNombre.getText(), txtNumeroCedula.getText())) {
                areaResultados.setText("Contacto actualizado: " + txtNombre.getText());
            } else {
                areaResultados.setText("Contacto no encontrado para actualizar.");
            }
        } catch (Exception e) {
            areaResultados.setText("Error al actualizar contacto: " + e.getMessage());
        }
    }

    private void eliminarContacto() {
        try {
            if (gestor.eliminarContacto(txtNombre.getText())) {
                areaResultados.setText("Contacto eliminado: " + txtNombre.getText());
            } else {
                areaResultados.setText("Contacto no encontrado para eliminar.");
            }
        } catch (Exception e) {
            areaResultados.setText("Error al eliminar contacto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica().setVisible(true));
    }
}
