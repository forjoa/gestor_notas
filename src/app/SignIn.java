package app;

import javax.swing.*;

import utils.Constants;

import java.awt.*;

public class SignIn extends JDialog {

    public SignIn(JFrame parent) {
        JTextField field = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton bAccept = new JButton("Aceptar");
        JButton bCancel = new JButton("Cancelar");

        bAccept.addActionListener(e -> {
            if (field.getText().equals(Constants.USER)
                    && new String(passwordField.getPassword()).equals(Constants.PASSWORD)) {
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Mensaje de error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        bCancel.addActionListener(e -> System.exit(0));

        setUndecorated(true);
        setModal(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));

        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 20, 5));

        JLabel label = new JLabel("Usuario:");
        label.setFont(Constants.APP_FONT_TEXT);
        inputPanel.add(label);
        inputPanel.add(field);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(Constants.APP_FONT_TEXT);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 0, 20));
        buttonPanel.add(bAccept);
        buttonPanel.add(bCancel);

        add(inputPanel);
        add(buttonPanel);

        setSize(250, 250);
        setLocationRelativeTo(parent);
    }
}
