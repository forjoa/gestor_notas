package models;

import javax.swing.*;

import utils.Constants;

import java.awt.*;

public class NoteCellRenderer extends JPanel implements ListCellRenderer<Note> {

    private final JLabel noteTitle;

    public NoteCellRenderer() {
        noteTitle = new JLabel();
        add(noteTitle);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Note> list, Note value, int index, boolean isSelected,
            boolean cellHasFocus) {
        noteTitle.setText(value.getTitle());
        noteTitle.setFont(Constants.APP_FONT_TEXT);

        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        return this;
    }

}
