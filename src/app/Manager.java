package app;

import java.awt.FlowLayout;
import java.util.Objects;
import javax.swing.*;

import models.Note;

public class Manager extends JPanel {

    private final String[] lookAndFeels = {
            "Metal (Cross-Platform)",
            "Windows",
            "Windows Classic",
            "Nimbus",
            "CDE/Motif"
    };

    public Manager() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panelUI = new JPanel();
        panelUI.setLayout(new FlowLayout(4));

        JComboBox<String> lafc = getStringJComboBox();
        add(lafc);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(new JList<Note>());
        contentPanel.add(new JTextArea());
        add(contentPanel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.add(new JButton("Agregar nota"));
        optionsPanel.add(new JButton("Eliminar nota"));
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
