import javax.swing.*;
import java.awt.*;

public class Ejemplo extends JDialog {

    public Ejemplo(JFrame parent) {
        // Creación de los componentes
        JTextField field = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton bAccept = new JButton("Aceptar");
        JButton bCancel = new JButton("Cancelar");

        // Comportamiento de los botones
        bAccept.addActionListener(e -> {
            if (field.getText().equals("admin") && new String(passwordField.getPassword()).equals("1234")) {
                JOptionPane.showMessageDialog(this, "Bienvenido a la aplicación", "Mensaje de bienvenida", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
            }
        });

        bCancel.addActionListener(e -> System.exit(0));

        // Configuración de diseño principal
        setUndecorated(true);
        setModal(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Crear paneles para organizar filas
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inputPanel.add(field);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(bAccept);
        buttonPanel.add(bCancel);

        // Añadir paneles al diálogo
        add(inputPanel);
        add(buttonPanel);

        // Configuración de la ventana
        setSize(300, 200);
        setLocationRelativeTo(parent); 
    }

    public static void main(String[] args) {
        // Crear el JFrame principal
        JFrame f = new JFrame("Aplicación Principal");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Ejemplo dialog = new Ejemplo(f);
        dialog.setVisible(true);
    }
}
