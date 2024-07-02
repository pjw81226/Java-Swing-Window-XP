package background;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.InputStream;

public class BootingMusicPlayer {
    private AdvancedPlayer player;

    public void play(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(inputStream);
            new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {}
            }).start();
        } catch (Exception e) {}
    }
}
