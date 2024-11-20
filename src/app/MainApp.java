package app;

import javax.swing.*;

import utils.Constants;

public class MainApp extends JFrame {
    public MainApp() {
        setTitle(Constants.APP_NAME);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SignIn signIn = new SignIn(this);
        signIn.setVisible(true);

        JLabel welcomeLabel = new JLabel("Bienvenido a la Aplicación Principal", JLabel.CENTER);
        welcomeLabel.setFont(Constants.APP_FONT_TITLE);
        add(welcomeLabel);
    }
}
