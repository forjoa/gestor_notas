package app;

import javax.swing.*;

import core.AddNoteDialog;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import models.Note;
import models.NoteCellRenderer;
import utils.Constants;

public class Manager extends JPanel {

    private final DefaultListModel<Note> noteModel;
    private final JList<Note> noteList;
    private final JTextArea textArea = new JTextArea();

    public Manager() {
        noteModel = new DefaultListModel<>();
        noteList = createNoteList();
        textArea.setFont(Constants.APP_FONT_TEXT);

        noteList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.setText(noteList.getSelectedValue().getContent());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

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
        JPanel panelUI = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
        contentPanel.add(textArea); // Placeholder para área de texto.
        return contentPanel;
    }

    /**
     * Crea el panel con los botones de agregar y eliminar nota.
     */
    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel(new FlowLayout());

        JButton addNoteButton = new JButton("Agregar nota");
        addNoteButton.setFont(Constants.APP_FONT_TEXT);
        addNoteButton.setToolTipText("Agrega una nueva nota");
        addNoteButton.addActionListener(e -> showAddNoteDialog());
        optionsPanel.add(addNoteButton);

        JButton editNoteButton = new JButton("Editar nota");
        editNoteButton.setFont(Constants.APP_FONT_TEXT);
        editNoteButton.setToolTipText("Edita la nota seleccionada");
        editNoteButton.addActionListener(e -> editSelectedNote());
        optionsPanel.add(editNoteButton);

        JButton deleteNoteButton = new JButton("Eliminar nota");
        deleteNoteButton.setFont(Constants.APP_FONT_TEXT);
        deleteNoteButton.setToolTipText("Elimina la nota seleccionada");
        deleteNoteButton.addActionListener(e -> deleteSelectedNote());
        optionsPanel.add(deleteNoteButton);

        return optionsPanel;
    }

    /*
     * Obtiene la nota seleccionada y edita su contenido en base a lo que se haya
     * escrito en el Text Area.
     */
    private void editSelectedNote() {
        noteList.getSelectedValue().setContent(textArea.getText());
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
        lafc.setFont(Constants.APP_FONT_TEXT);
        lafc.setToolTipText("Cambiar Look and Feel");
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
