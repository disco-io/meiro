import javax.swing.*;
/*
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip; 
*/

public class Main
{
  public static void main(String[] args) throws Exception
  {
    JFrame frame = new JFrame("playmeiro.io");
    frame.setSize(600, 500);
    frame.setLocation(20, 20);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new GUIPanel());
    /* 
    Clip clip = AudioSystem.getClip();
    clip.open(AudioSystem.getAudioInputStream(new File("pokemonMusic.wav")));
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    */
    frame.setVisible(true);
  }
}

/*

hello, trainer! want to add music to the game? c:
https://drive.google.com/file/d/1g9KlcdMy9yPJExVUqO6uj0-OS3GrHHid/view?usp=sharing

0. download the .wav file put it in the meiro folder
1. check that the file is named 'pokemonMusic.wav'
2. put it in the meiro game folder
3. uncomment lines 2-6 and 17-22.

*/
