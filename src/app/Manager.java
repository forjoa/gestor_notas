package app;

import javax.swing.*;

import core.AddNoteDialog;

import java.awt.*;
import java.util.Objects;

import models.Note;
import models.NoteCellRenderer;

public class Manager extends JPanel {

    private final DefaultListModel<Note> noteModel;
    private final JList<Note> noteList;

    public Manager() {
        noteModel = new DefaultListModel<>();
        noteList = createNoteList();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Selector de Look and Feel
        add(createLookAndFeelSelector());

        // Panel de contenido principal
        add(createContentPanel());

        // Panel de opciones (botones)
        add(createOptionsPanel());

        setVisible(true);
    }

    /**
     * Crea la lista de notas con su modelo y renderer personalizado.
     */
    private JList<Note> createNoteList() {
        JList<Note> list = new JList<>(noteModel);
        list.setCellRenderer(new NoteCellRenderer());
        list.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return list;
    }

    /**
     * Crea el panel para cambiar el Look and Feel.
     */
    private JPanel createLookAndFeelSelector() {
        JPanel panelUI = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> lafc = getLookAndFeelComboBox();
        panelUI.add(lafc);
        return panelUI;
    }

    /**
     * Crea el panel principal con la lista de notas y un área de texto.
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.add(new JScrollPane(noteList)); // La lista dentro de un scroll.
        contentPanel.add(new JTextArea()); // Placeholder para área de texto.
        return contentPanel;
    }

    /**
     * Crea el panel con los botones de agregar y eliminar nota.
     */
    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel(new FlowLayout());

        JButton addNoteButton = new JButton("Agregar nota");
        addNoteButton.addActionListener(e -> showAddNoteDialog());
        optionsPanel.add(addNoteButton);

        JButton deleteNoteButton = new JButton("Eliminar nota");
        deleteNoteButton.addActionListener(e -> deleteSelectedNote());
        optionsPanel.add(deleteNoteButton);

        return optionsPanel;
    }

    /**
     * Abre un diálogo para agregar una nueva nota.
     */
    private void showAddNoteDialog() {
        AddNoteDialog dialog = new AddNoteDialog();
        Note newNote = dialog.showDialog();
        if (newNote != null) {
            noteModel.addElement(newNote);
        }
    }

    /**
     * Elimina la nota seleccionada de la lista.
     */
    private void deleteSelectedNote() {
        int selected = noteList.getSelectedIndex();
        if (selected != -1) {
            noteModel.remove(selected);
        }
    }

    /**
     * Crea un JComboBox para cambiar el Look and Feel.
     */
    private JComboBox<String> getLookAndFeelComboBox() {
        String[] lookAndFeels = {
                "Metal (Cross-Platform)",
                "Windows",
                "Windows Classic",
                "Nimbus",
                "CDE/Motif"
        };

        JComboBox<String> lafc = new JComboBox<>(lookAndFeels);
        lafc.addActionListener(e -> {
            try {
                String selectedLaf = (String) lafc.getSelectedItem();
                switch (Objects.requireNonNull(selectedLaf)) {
                    case "Metal (Cross-Platform)":
                        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        break;
                    case "Windows":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                        break;
                    case "Windows Classic":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                        break;
                    case "Nimbus":
                        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                        break;
                    case "CDE/Motif":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                        break;
                    default:
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        break;
                }
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return lafc;
    }
}
