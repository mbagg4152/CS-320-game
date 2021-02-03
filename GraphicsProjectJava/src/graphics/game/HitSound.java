package graphics.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static graphics.game.Const.OOF_ROBLOX;

public class HitSound implements Runnable {
    Clip hitSound;
    AudioInputStream sound;
    boolean stop = false;

    HitSound() {
        this.stop = stop;
    }

    @Override
    public void run() {
        try {
            sound.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sound = AudioSystem.getAudioInputStream(new File(OOF_ROBLOX));
            hitSound = AudioSystem.getClip();
            hitSound.open(sound);
            hitSound.start();
            while (hitSound.getMicrosecondPosition() < hitSound.getMicrosecondLength()) ;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
            System.out.println("Error loading the sound file: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
