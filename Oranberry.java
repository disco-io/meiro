import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Oranberry
{
  //fields:
  public static final int FRAME = 400;
  
  private Image ornb;
  private BufferedImage myImage;

  //constructors:
  public Oranberry()
  {
    ornb = (new ImageIcon("oranberry.png")).getImage();
  }

  //instance methods:
  public void draw(Graphics myBuffer, int x, int y, int size)
  {
    myBuffer.drawImage(ornb, x, y, size, size, null, null);
  }
}