import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    public MainApp() {
        setTitle("Aplicación Principal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SignIn signIn = new SignIn(this);
        signIn.setVisible(true);

        JLabel welcomeLabel = new JLabel("Bienvenido a la Aplicación Principal", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel);
    }
}
