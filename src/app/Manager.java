package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import models.Note;
import models.NoteCellRenderer;
import core.AddNoteDialog;
import core.EditNoteDialog;
import utils.Constants;
import java.util.Objects;

public class Manager extends JPanel {

    private final DefaultListModel<Note> noteModel;
    private final JList<Note> noteList;
    private final JTextArea textArea = new JTextArea();

    public Manager() {
        noteModel = new DefaultListModel<>();
        noteList = createNoteList();
        configureTextArea();
        configureNoteList();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initializeComponents();
    }

    /**
     * Inicializa los componentes principales del panel.
     */
    private void initializeComponents() {
        add(createLookAndFeelSelector());
        add(createContentPanel());
        add(createOptionsPanel());
    }

    /**
     * Configura el JTextArea para actualizar el contenido de la nota seleccionada.
     */
    private void configureTextArea() {
        textArea.setFont(Constants.APP_FONT_TEXT);
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Note selectedNote = noteList.getSelectedValue();
                if (selectedNote != null) {
                    selectedNote.setContent(textArea.getText());
                }
            }
        });
    }

    /**
     * Configura el JList para mostrar y seleccionar notas.
     */
    private void configureNoteList() {
        noteList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Note selectedNote = noteList.getSelectedValue();
                if (selectedNote != null) {
                    textArea.setText(selectedNote.getContent());
                }
            }
        });
    }

    /**
     * Crea la lista de notas con su modelo y renderer personalizado.
     */
    private JList<Note> createNoteList() {
        JList<Note> list = new JList<>(noteModel);
        list.setCellRenderer(new NoteCellRenderer());
        list.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        int preferredHeight = 300; // Altura preferida para la JList
        list.setPreferredSize(new Dimension(200, preferredHeight)); // 200px de ancho y 300px de altura
        return list;
    }

    /**
     * Crea el panel principal con la lista de notas y un área de texto.
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.add(new JScrollPane(noteList)); // La lista dentro de un scroll.
        contentPanel.add(textArea); // Área de texto para la nota.
        return contentPanel;
    }

    /**
     * Crea el panel con los botones de agregar, editar y eliminar nota.
     */
    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.add(createButton("Agregar nota", "Agrega una nueva nota", e -> showAddNoteDialog()));
        optionsPanel.add(createButton("Editar nota", "Edita la nota seleccionada", e -> editSelectedNote()));
        optionsPanel.add(createButton("Eliminar nota", "Elimina la nota seleccionada", e -> deleteSelectedNote()));
        return optionsPanel;
    }

    /**
     * Crea un botón con texto, tooltip y acción asociada.
     */
    private JButton createButton(String text, String tooltip, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(Constants.APP_FONT_TEXT);
        button.setToolTipText(tooltip);
        button.addActionListener(action);
        return button;
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
     * Edita el contenido de la nota seleccionada.
     */
    private void editSelectedNote() {
        EditNoteDialog dialog = new EditNoteDialog();
        String newTitle = dialog.showDialog();
        noteList.getSelectedValue().setTitle(newTitle);
        noteList.repaint();
    }

    /**
     * Elimina la nota seleccionada de la lista.
     */
    private void deleteSelectedNote() {
        int selected = noteList.getSelectedIndex();
        if (selected != -1) {
            noteModel.remove(selected);
            textArea.setText("");
        }
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
        lafc.addActionListener(e -> changeLookAndFeel((String) lafc.getSelectedItem()));
        return lafc;
    }

    /**
     * Cambia el Look and Feel basado en la opción seleccionada.
     */
    private void changeLookAndFeel(String selectedLaf) {
        try {
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
    }
}
