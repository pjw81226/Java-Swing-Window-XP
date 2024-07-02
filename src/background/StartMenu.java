package background;

import calculator.Calculator;
import gallery.Gallery;
import musicPlayer.MusicPlayer;
import note.Note;
import paint.Paint;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartMenu {
    public JPanel createStartMenu(JFrame mainFrame) {
        JPanel startMenu = new JPanel();
        startMenu.setLayout(null);
        startMenu.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        startMenu.setBackground(new Color(240, 240, 240));
        startMenu.setBounds(0, 25, 200, 300);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon decoImageIcon = new ImageIcon(getClass().getResource("/resources/startMenuDeco.jpg"));
        JLabel decoImage = new JLabel(decoImageIcon);
        decoImage.setBounds(0, 0, 16, 300);

        JLabel calculator = createMenuItem("Calculator", "/resources/calcuIcon.png", 0);
        JLabel paint = createMenuItem("Paint", "/resources/paintIcon.png", 40);
        JLabel note = createMenuItem("Note", "/resources/noteIcon.png", 80);
        JLabel gallery = createMenuItem("My Pictures", "/resources/galleryIcon.png", 120);
        JLabel music = createMenuItem("My Music", "/resources/musicIcon.png", 160);
        JLabel shutDown = createMenuItem("Shut Down", "/resources/shutdownIcon.png", 260);

        calculator.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                calculator.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                calculator.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                Calculator calculatorPanel = new Calculator();
                calculatorPanel.setBounds(300, 200, 300, 200);
                mainFrame.getLayeredPane().add(calculatorPanel, JLayeredPane.PALETTE_LAYER);
                mainFrame.getLayeredPane().moveToFront(calculatorPanel);
                mainFrame.repaint();
            }
        });
        paint.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                paint.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                paint.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                Paint paintPanel = new Paint();
                paintPanel.setBounds(220, 30, 500, 500);
                mainFrame.getLayeredPane().add(paintPanel, JLayeredPane.PALETTE_LAYER);
                mainFrame.getLayeredPane().moveToFront(paintPanel);
                mainFrame.repaint();
            }
        });
        note.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                note.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                note.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                Note notePanel = new Note();
                notePanel.setBounds(120, 30, 600, 400);
                mainFrame.getLayeredPane().add(notePanel, JLayeredPane.PALETTE_LAYER);
                mainFrame.getLayeredPane().moveToFront(notePanel);
                mainFrame.repaint();
            }
        });
        gallery.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gallery.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                gallery.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                Gallery galleryPanel = new Gallery();
                galleryPanel.setBounds(220, 30, 500, 500);
                mainFrame.getLayeredPane().add(galleryPanel, JLayeredPane.PALETTE_LAYER);
                mainFrame.getLayeredPane().moveToFront(galleryPanel);
                mainFrame.repaint();
            }
        });
        music.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                music.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                music.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                MusicPlayer musicPlayerPanel = new MusicPlayer();
                musicPlayerPanel.setBounds(220, 100, 500, 250);
                mainFrame.getLayeredPane().add(musicPlayerPanel, JLayeredPane.PALETTE_LAYER);
                mainFrame.getLayeredPane().moveToFront(musicPlayerPanel);
                mainFrame.repaint();
            }
        });
        shutDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                shutDown.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                shutDown.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                System.exit(0);
            }
        });
        startMenu.add(calculator);
        startMenu.add(paint);
        startMenu.add(note);
        startMenu.add(gallery);
        startMenu.add(music);
        startMenu.add(shutDown);
        startMenu.add(decoImage);

        return startMenu;
    }


    private JLabel createMenuItem(String text, String iconPath, int yPosition) {
        JLabel menuItem = new JLabel(text);
        menuItem.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        menuItem.setHorizontalTextPosition(SwingConstants.RIGHT);
        menuItem.setBounds(17, yPosition, 183, 40);
        menuItem.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        return menuItem;
    }
}
