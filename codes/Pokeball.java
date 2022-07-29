import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Pokeball
{
  //fields:
  public static final int FRAME = 400;
  
  private Image pkbl;
  private BufferedImage myImage;

  //constructors:
  public Pokeball()
  {
    pkbl = (new ImageIcon("pokeball.png")).getImage();
  }

  //instance methods:
  public void draw(Graphics myBuffer, int x, int y, int size)
  {
    myBuffer.drawImage(pkbl, x, y, size, size, null, null);
  }
}