package app;
import javax.swing.*;
import java.awt.*;

public class SignIn extends JDialog {

    public SignIn(JFrame parent) {
        JTextField field = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton bAccept = new JButton("Aceptar");
        JButton bCancel = new JButton("Cancelar");

        bAccept.addActionListener(e -> {
            if (field.getText().equals("admin") && new String(passwordField.getPassword()).equals("1234")) {
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
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inputPanel.add(field);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(bAccept);
        buttonPanel.add(bCancel);

        add(inputPanel);
        add(buttonPanel);

        setSize(250, 250);
        setLocationRelativeTo(parent);
    }
}
