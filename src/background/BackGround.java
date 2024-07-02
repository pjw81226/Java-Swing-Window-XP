package background;

import calculator.Calculator;
import gallery.Gallery;
import musicPlayer.MusicPlayer;
import note.Note;
import paint.Paint;
import starcraft.Starcraft;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BackGround extends JFrame {
    public BackGround() {
        setTitle("Windows XP Classic Theme BackGround");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); }
        catch (Exception e) { }

        JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/windowWallPaper.jpg")));
        backgroundLabel.setLayout(null);
        setContentPane(backgroundLabel);

        Taskbar taskbar = new Taskbar(this);
        taskbar.setBounds(0, 548, getWidth(), 25);
        add(taskbar);

        addProgramIcon("/resources/calcuIcon.png", "Calculator", 20, 20, new Calculator(), 300, 200, 300, 200);
        addProgramIcon("/resources/paintIcon.png", "Paint", 20, 100, new Paint(), 220, 30, 500, 500);
        addProgramIcon("/resources/noteIcon.png", "Note", 20, 180, new Note(), 120, 30, 600, 400);
        addProgramIcon("/resources/galleryIcon.png", "My Pictures", 20, 260, new Gallery(), 220, 30, 500, 500);
        addProgramIcon("/resources/starcraftIcon.png", "Starcraft", 20, 340, new Starcraft(), 220, 30, 500, 375);
        addProgramIcon("/resources/musicIcon.png", "My Music", 85, 20, new MusicPlayer(), 220, 100, 500, 250);

        BootingMusicPlayer bootingMusicPlayer = new BootingMusicPlayer();
        bootingMusicPlayer.play("src/resources/XPStartup.mp3");

        setVisible(true);
    }

    private void addProgramIcon(String iconPath, String programName, int x, int y, JPanel programPanel, int px, int py, int pw, int ph) {
        JLabel programIcon = new JLabel(programName);
        programIcon.setForeground(Color.WHITE);
        programIcon.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        programIcon.setVerticalTextPosition(SwingConstants.BOTTOM);
        programIcon.setHorizontalTextPosition(SwingConstants.CENTER);
        programIcon.setBounds(x, y, 35, 60);
        programIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                programIcon.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                programIcon.setBackground(Color.LIGHT_GRAY);
                programIcon.setOpaque(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                programIcon.setBorder(BorderFactory.createEmptyBorder());
                programIcon.setBackground(null);
                programIcon.setOpaque(false);
                if(getLayeredPane().getIndexOf(programPanel) == -1) {
                    programPanel.setBounds(px, py, pw, ph);
                    getLayeredPane().add(programPanel, JLayeredPane.PALETTE_LAYER);
                }
                getLayeredPane().moveToFront(programPanel);
                programPanel.setVisible(true);
                repaint();
            }
        });
        add(programIcon);
    }

    public static void main(String[] args) {
        new BackGround();
    }
}
