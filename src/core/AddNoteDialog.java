package core;

import javax.swing.*;
import java.awt.*;
import models.Note;
import utils.Constants;

public class AddNoteDialog {

    private final JDialog dialog;
    private final JTextField titleField;
    private Note note;

    public AddNoteDialog() {
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Agregar nota");
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Campo de texto para el título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleAdd = new JLabel("Título de la nota");
        titleAdd.setFont(Constants.APP_FONT_TEXT);
        titlePanel.add(titleAdd);
        panel.add(titlePanel, BorderLayout.NORTH);
        titleField = new JTextField();
        titleField.setFont(Constants.APP_FONT_TEXT);
        panel.add(titleField);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setFont(Constants.APP_FONT_TEXT);
        acceptButton.addActionListener(e -> {
            note = new Note();
            note.setTitle(titleField.getText().trim());
            note.setContent("Nota vacía para " + note.getTitle());
            dialog.dispose();
        });
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(Constants.APP_FONT_TEXT);
        cancelButton.addActionListener(e -> {
            note = null;
            dialog.dispose();
        });
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel);
        dialog.add(panel);
    }

    /**
     * Muestra el diálogo y devuelve la nota creada, o null si se cancela.
     */
    public Note showDialog() {
        dialog.setVisible(true);
        return note;
    }
}
