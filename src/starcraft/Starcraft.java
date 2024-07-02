package starcraft;

import component.TitleBar;

import javax.swing.*;
import java.awt.*;

public class Starcraft extends JPanel {
    public Starcraft(){
        setLayout(new BorderLayout());

        TitleBar titleBar = new TitleBar("Starcraft Broodwar 1.16.1 rip", this::closeStarcraft);
        add(titleBar, BorderLayout.NORTH);

        JLabel starcraftLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/starcraft.jpg")));
        add(starcraftLabel, BorderLayout.SOUTH);
    }
    private void closeStarcraft(){ setVisible(false); }
}
