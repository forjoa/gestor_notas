package app;

import javax.swing.*;
import java.awt.*;

import utils.Constants;

public class SignIn extends JDialog {

    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public SignIn(JFrame parent) {
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        setUndecorated(true);
        setModal(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));

        // Agregar paneles a la ventana
        add(createInputPanel());
        add(createButtonPanel());

        setSize(250, 250);
        setLocationRelativeTo(parent);
    }

    /**
     * Crea el panel de entrada de usuario y contraseña.
     */
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 20, 5));

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setFont(Constants.APP_FONT_TEXT);
        inputPanel.add(usernameLabel);

        usernameField.setFont(Constants.APP_FONT_TEXT);
        inputPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(Constants.APP_FONT_TEXT);
        inputPanel.add(passwordLabel);

        passwordField.setFont(Constants.APP_FONT_TEXT);
        inputPanel.add(passwordField);

        return inputPanel;
    }

    /**
     * Crea el panel de botones (Aceptar y Cancelar).
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 0, 20));

        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setFont(Constants.APP_FONT_TEXT);
        acceptButton.addActionListener(e -> handleAccept());

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(Constants.APP_FONT_TEXT);
        cancelButton.addActionListener(e -> handleCancel());

        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    /**
     * Lógica para manejar el botón Aceptar.
     */
    private void handleAccept() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (Constants.USER.equals(username) && Constants.PASSWORD.equals(password)) {
            dispose(); // Cierra el diálogo si las credenciales son correctas.
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos",
                    "Mensaje de error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lógica para manejar el botón Cancelar.
     */
    private void handleCancel() {
        System.exit(0); // Termina la aplicación.
    }
}
