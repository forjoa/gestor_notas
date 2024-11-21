package core;

import javax.swing.*;
import java.awt.*;
import models.Note;

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
        panel.add(new JLabel("Título:"));
        titleField = new JTextField();
        panel.add(titleField);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.addActionListener(e -> {
            note = new Note();
            note.setTitle(titleField.getText().trim());
            dialog.dispose();
        });
        JButton cancelButton = new JButton("Cancelar");
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
