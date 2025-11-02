package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static SoundManager instance;
    private Map<String, Clip> soundClips;
    private Map<String, Clip> musicClips;
    private float soundVolume = 0.7f;
    private float musicVolume = 0.5f;
    private boolean soundEnabled = true;
    private boolean musicEnabled = true;

    private SoundManager() {
        soundClips = new HashMap<>();
        musicClips = new HashMap<>();
        loadSounds();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private void loadSounds() {
        // Load sound effects - tạo thư mục sounds/ và thêm file âm thanh
        try {
            loadSound("ball_bounce", "sounds/bounce.wav");
            loadSound("brick_break", "sounds/brick_break.wav");
            loadSound("item_collect", "sounds/item_collect.wav");
            loadSound("game_over", "sounds/game_over.wav");
            loadSound("level_win", "sounds/level_win.wav");
            loadSound("shoot", "sounds/shoot.wav");

            // Load music
            loadMusic("background", "sounds/background_music.wav");
            loadMusic("menu", "sounds/menu_music.wav");
        } catch (Exception e) {
            System.out.println("Không thể load âm thanh: " + e.getMessage());
        }
    }

    private void loadSound(String name, String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            soundClips.put(name, clip);
        } catch (Exception e) {
            System.out.println("Could not load sound: " + filePath);
        }
    }

    private void loadMusic(String name, String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            musicClips.put(name, clip);
        } catch (Exception e) {
            System.out.println("Could not load music: " + filePath);
        }
    }

    public void playSound(String name) {
        if (!soundEnabled) return;

        Clip clip = soundClips.get(name);
        if (clip != null) {
            // Stop if already playing and restart
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);

            // Set volume
            setClipVolume(clip, soundVolume);
            clip.start();
        }
    }

    public void playMusic(String name) {
        if (!musicEnabled) return;

        stopAllMusic();

        Clip clip = musicClips.get(name);
        if (clip != null) {
            setClipVolume(clip, musicVolume);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopAllMusic() {
        for (Clip clip : musicClips.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

    public void stopSound(String name) {
        Clip clip = soundClips.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void stopAllSounds() {
        for (Clip clip : soundClips.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

    private void setClipVolume(Clip clip, float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    // Volume control methods
    public void setSoundVolume(float volume) {
        this.soundVolume = Math.max(0.0f, Math.min(1.0f, volume));
    }

    public void setMusicVolume(float volume) {
        this.musicVolume = Math.max(0.0f, Math.min(1.0f, volume));

        // Update currently playing music volume
        for (Clip clip : musicClips.values()) {
            if (clip.isRunning()) {
                setClipVolume(clip, musicVolume);
            }
        }
    }

    public void setSoundEnabled(boolean enabled) {
        this.soundEnabled = enabled;
        if (!enabled) {
            stopAllSounds();
        }
    }

    public void setMusicEnabled(boolean enabled) {
        this.musicEnabled = enabled;
        if (!enabled) {
            stopAllMusic();
        }
    }

    public float getSoundVolume() { return soundVolume; }
    public float getMusicVolume() { return musicVolume; }
    public boolean isSoundEnabled() { return soundEnabled; }
    public boolean isMusicEnabled() { return musicEnabled; }

    public void cleanup() {
        stopAllSounds();
        stopAllMusic();

        for (Clip clip : soundClips.values()) {
            clip.close();
        }
        for (Clip clip : musicClips.values()) {
            clip.close();
        }
    }
}