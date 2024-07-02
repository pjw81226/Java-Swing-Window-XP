package musicPlayer;

import component.TitleBar;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class MusicPlayer extends JPanel {
    private AdvancedPlayer player;
    private Thread playingThread;

    public MusicPlayer() {
        setLayout(new BorderLayout());

        TitleBar titleBar = new TitleBar("Music Player", this::closeMusicPlayer);
        add(titleBar, BorderLayout.NORTH);

        JPanel musicMainPanel = new JPanel();
        musicMainPanel.setLayout(new BoxLayout(musicMainPanel, BoxLayout.Y_AXIS));

        musicMainPanel.add(createMusicController("src/resources/BachCMajorPrelude.mp3"));
        musicMainPanel.add(createMusicController("src/resources/BachPreludeNo1.mp3"));
        musicMainPanel.add(createMusicController("src/resources/SatieGymnopedieNo1.mp3"));
        musicMainPanel.add(createMusicController("src/resources/terranTheme.mp3"));

        add(new JScrollPane(musicMainPanel), BorderLayout.CENTER);
    }

    private void closeMusicPlayer() {
        setVisible(false);
        stopMusic();
    }

    private JPanel createMusicController(String filePath) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel musicLabel = new JLabel(filePath.substring(filePath.lastIndexOf("/") + 1));
        JButton playButton = new JButton("Play");
        JButton stopButton = new JButton("Stop");

        playButton.addActionListener(e -> playMusic(filePath));
        stopButton.addActionListener(e -> stopMusic());

        panel.add(musicLabel);
        panel.add(playButton);
        panel.add(stopButton);

        return panel;
    }
    private void playMusic(String filePath) {
        stopMusic();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(inputStream);
            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {}
            }).start();
        } catch (Exception e) {}
    }
    private void stopMusic() {
        if (player != null) {
            player.close();
        }
        if (playingThread != null) {
            playingThread.interrupt();
        }
    }
}