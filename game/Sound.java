package game;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Initialise all sounds in the game.
 */
public class Sound {

    /** The sound fields. */
    private static SoundClip pain, nom, smash, ding, winter, spring, fall;

    /**
     * Setting the audio files to their respective fields.
     */
    static {
        try {
            // Single sounds
            pain = new SoundClip("data/oof.wav");
            nom = new SoundClip("data/nom.wav");
            smash = new SoundClip("data/smash.wav");
            ding = new SoundClip("data/ding.wav");

            // Looped sounds
            winter = new SoundClip("data/winter.wav");
            spring = new SoundClip("data/spring.wav");
            fall = new SoundClip("data/fall.wav");
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // Getters for single sounds
    /** Get the sound played when player gets hurt */
    public static SoundClip getPain() {
        return pain;
    }
    /** Get the eating sound */
    public static SoundClip getNom() {
        return nom;
    }
    /** Get the smash sound, when a player destroys an object. */
    public static SoundClip getSmash() {
        return smash;
    }
    /** Get the ding sound, when player completes a level. */
    public static SoundClip getDing() {
        return ding;
    }

    // Getters for looped sounds
    /** Get the winter ambiance sound */
    public static SoundClip getWinter() {
        return winter;
    }
    /** Get the spring ambiance sound */
    public static SoundClip getSpring() {
        return spring;
    }
    /** Get the autumn/fall ambiance sound */
    public static SoundClip getFall() {
        return fall;
    }


}
