package core;

import javax.swing.*;
import java.awt.*;

import utils.Constants;

public class EditNoteDialog {

    private final JDialog dialog;
    private final JTextField titleField;
    private String newTitle;

    public EditNoteDialog() {
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Editar nota");
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Campo de texto para el título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleAdd = new JLabel("Nuevo título de la nota");
        titleAdd.setFont(Constants.APP_FONT_TEXT);
        titlePanel.add(titleAdd);
        panel.add(titlePanel, BorderLayout.NORTH);
        titleField = new JTextField();
        titleField.setToolTipText("Introduce el nuevo título de la nota");
        titleField.setFont(Constants.APP_FONT_TEXT);
        panel.add(titleField);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setFont(Constants.APP_FONT_TEXT);
        acceptButton.setToolTipText("Guardar cambios");
        acceptButton.addActionListener(e -> {
            newTitle = titleField.getText();
            dialog.dispose();
        });

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(Constants.APP_FONT_TEXT);
        cancelButton.setToolTipText("Salir");
        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel);

        dialog.setContentPane(panel);
    }

    /**
     * Mostramos el diálogo
     */
    public String showDialog() {
        dialog.setVisible(true);
        return newTitle;
    }

}
