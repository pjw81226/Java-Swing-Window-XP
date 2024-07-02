package component;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TitleBar extends JPanel {

    private final JLabel titleLabel;
    public TitleBar(String title, Runnable closeAction) {
        setLayout(new BorderLayout());
        setBackground(new Color(1, 0, 129));

        titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.WEST);

        JButton closeButton = new JButton("X");
        closeButton.setBackground(new Color(192, 192, 192));
        closeButton.setForeground(Color.BLACK);
        closeButton.setPreferredSize(new Dimension(20, 20));
        closeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> closeAction.run());
        add(closeButton, BorderLayout.EAST);
    }

    public JLabel getTitleLabel(){
        return titleLabel;
    }
    public void setTitleLabel(String a){
        titleLabel.setText(a);
    }
}