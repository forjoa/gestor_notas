package app;

import java.awt.FlowLayout;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Manager extends JPanel {

    private final String[] lookAndFeels = {
            "Metal (Cross-Platform)",
            "Windows",
            "Windows Classic",
            "Nimbus",
            "CDE/Motif"
    };

    public Manager() {
        JPanel panelUI = new JPanel();
        panelUI.setLayout(new FlowLayout());

        JComboBox<String> lafc = getStringJComboBox();
        panelUI.add(lafc);

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
                        // JOptionPane.showConfirmDialog(null, slaf);
                        break;
                    case "Windows":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                        // JOptionPane.showConfirmDialog(null, slaf);
                        break;
                    case "Windows Classic":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                        // JOptionPane.showConfirmDialog(null, slaf);
                        break;
                    case "Nimbus":
                        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                        // JOptionPane.showConfirmDialog(null, slaf);
                        break;
                    case "CDE/Motif":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                        // JOptionPane.showConfirmDialog(null, slaf);
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
