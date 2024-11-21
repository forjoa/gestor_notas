package app;

import java.awt.FlowLayout;
import java.util.Objects;
import javax.swing.*;

import models.Note;
import models.NoteCellRenderer;

public class Manager extends JPanel {

    private final DefaultListModel<Note> noteModel;
    private final JList<Note> noteList;
    private final String[] lookAndFeels = {
            "Metal (Cross-Platform)",
            "Windows",
            "Windows Classic",
            "Nimbus",
            "CDE/Motif"
    };

    public Manager() {
        noteModel = new DefaultListModel<>();
        noteList = new JList<>(noteModel);
        noteList.setCellRenderer(new NoteCellRenderer());
        noteList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panelUI = new JPanel();
        panelUI.setLayout(new FlowLayout(4));

        JComboBox<String> lafc = getStringJComboBox();
        add(lafc);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.add(noteList);
        contentPanel.add(new JTextArea());
        add(contentPanel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());
        JButton addNoteButton = new JButton("Agregar nota");
        addNoteButton.addActionListener(e -> {
            JDialog dialogSetTitleNote = new JDialog();
            dialogSetTitleNote.setModal(true);
            dialogSetTitleNote.setTitle("Agregar nota");
            dialogSetTitleNote.setSize(300, 100);
            dialogSetTitleNote.setLocationRelativeTo(null);
            Note note = new Note();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Titulo"));
            panel.add(new JTextField());
            JButton bAccept = new JButton("Aceptar");
            bAccept.addActionListener(e1 -> {
                note.setTitle(((JTextField) panel.getComponent(1)).getText());
                dialogSetTitleNote.dispose();
            });
            panel.add(bAccept);
            dialogSetTitleNote.add(panel);

            dialogSetTitleNote.setVisible(true);

            noteModel.addElement(note);
        });
        optionsPanel.add(addNoteButton);

        JButton deleteNoteButton = new JButton("Eliminar nota");
        deleteNoteButton.addActionListener(e -> {
            int selected = noteList.getSelectedIndex();
            if (selected != -1) {
                noteModel.remove(selected);
            }
        });
        optionsPanel.add(deleteNoteButton);

        add(optionsPanel);

        add(panelUI);
        setVisible(true);
    }

    private JComboBox<String> getStringJComboBox() {
        JComboBox<String> lafc = new JComboBox<>(lookAndFeels);
        lafc.addActionListener(e -> {
            try {
                String slaf = (String) lafc.getSelectedItem();
                switch (Objects.requireNonNull(slaf)) {
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
                SwingUtilities.updateComponentTreeUI(Manager.this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return lafc;
    }

}
