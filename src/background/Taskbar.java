package background;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Taskbar extends JPanel {

    private final JPanel startMenuPanel;
    private final JLabel timeLabel;

    public Taskbar(JFrame frame) {

        try{ UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); }
        catch(Exception e){ }

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 25));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setBackground(new Color(192, 192, 192));

        JButton startButton = new JButton("Start");
        ImageIcon btImage = new ImageIcon(getClass().getResource("/resources/smallStartIcon.png"));
        startButton.setPreferredSize(new Dimension(65, 25));
        startButton.setUI(new BasicButtonUI());
        startButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        startButton.setBackground(new Color(192, 192, 192));
        startButton.setFocusPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleStartMenu(frame, startButton);
            }
        });
        add(startButton, BorderLayout.WEST);
        startButton.setIcon(btImage);

        StartMenu startMenu = new StartMenu();
        startMenuPanel = startMenu.createStartMenu(frame);
        startMenuPanel.setVisible(false);
        frame.getLayeredPane().add(startMenuPanel, JLayeredPane.POPUP_LAYER);

        timeLabel = new JLabel();
        updateClock();
        add(timeLabel, BorderLayout.EAST);
        timeLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){ updateClock(); }
        });
        timer.start();
    }

    private void toggleStartMenu(JFrame frame, JButton startButton){
        startMenuPanel.setVisible(!startMenuPanel.isVisible());
        if (startMenuPanel.isVisible()){
            startMenuPanel.setLocation(0, 247);
            startButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
        else{
            startButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
        frame.repaint();
    }
    private void updateClock(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        timeLabel.setText(sdf.format(new Date()));
    }
}