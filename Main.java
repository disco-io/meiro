import javax.swing.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main
{
  public static void main(String[] args) throws Exception
  {
    JFrame frame = new JFrame("playmeiro.io");
    frame.setSize(600, 500);
    frame.setLocation(20, 20);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new GUIPanel());
    Clip clip = AudioSystem.getClip();
    clip.open(AudioSystem.getAudioInputStream(new File("pokemonMusic.wav")));
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY); 
    frame.setVisible(true);
  }
}