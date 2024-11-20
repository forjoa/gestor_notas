package app;

import java.awt.*;

import javax.swing.*;

import utils.Constants;

public class SplashScreen extends JDialog {
    private JProgressBar progressBar;

    public SplashScreen() {
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUndecorated(true);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel logoLabel = new JLabel(Constants.APP_NAME, JLabel.CENTER);
        logoLabel.setFont(Constants.APP_FONT_TITLE);
        logoLabel.setForeground(Constants.APP_COLOR);

        JLabel messageLabel = new JLabel("Cargando recursos, por favor espere...", JLabel.CENTER);
        messageLabel.setFont(Constants.APP_FONT_TEXT);
        messageLabel.setForeground(Color.GRAY);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(Constants.APP_FONT_TEXT);
        progressBar.setForeground(Constants.APP_COLOR);

        mainPanel.add(logoLabel, BorderLayout.CENTER);
        mainPanel.add(messageLabel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
    }

    public void updateProgress(int progress) {
        progressBar.setValue(progress);
    }
}
