import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Shiny
{
  //fields:
  public static final int FRAME = 400;
  
  private Image shny;
  private BufferedImage myImage;

  //constructors:
  public Shiny()
  {
    shny = (new ImageIcon("shiny.png")).getImage();
  }

  //instance methods:
  public void draw(Graphics myBuffer, int x, int y, int size)
  {
    myBuffer.drawImage(shny, x, y, size, size, null, null);
  }
}