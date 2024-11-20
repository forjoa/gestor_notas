package app;

import javax.swing.*;

import utils.Constants;

public class MainApp extends JFrame {
    public MainApp() {
        setTitle(Constants.APP_NAME);
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SignIn signIn = new SignIn(this);
        signIn.setVisible(true);

        Manager manager = new Manager();
        add(manager);
        setVisible(true);
    }
}
